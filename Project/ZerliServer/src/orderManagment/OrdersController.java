package orderManagment;

import java.sql.Timestamp;

import database.DBController;
import order.*;
import paymentManagment.CreditController;
import remindersManagment.ReminderController;
import user.User;

/**
 * controller for the different actions for order management, approving and
 * canceling orders and all the relevant reminders
 * 
 *
 */
public class OrdersController {
	DBController dbController = DBController.getInstance();
	/**
	 * 1 h = 3600000 ms
	 */
	private static final long HOUR_IN_MILLISECONS = 3600000;
	private static final long FIVE_MINUTES_IN_MILLISECONS = 300000;

	/**
	 * update the order status -> to approve, not approved and canceled, give refund
	 * if needed
	 * 
	 * @param order
	 * @throws Exception - if failed throw exception with the error msg
	 */
	public void approveOrder(Order order) throws Exception {
		switch (order.getOrderStatus()) {
		case APPROVED:
			approveOrder(order.getOrderNumber());
			break;
		case CANCELED:
			cancelOrder(order.getOrderNumber());
			break;
		case NOT_APPROVED:
			notApproveOrder(order.getOrderNumber());
			break;
		default:
			break;
		}
	}

	/**
	 * approve order, if the delivery time is too close -> change it to 3 hours from
	 * now, send the relevant reminders
	 * 
	 * @param ordernumber
	 * @throws Exception -> on error -> with the error msg
	 */
	private void approveOrder(int ordernumber) throws Exception {
		// 1. get the order from the database
		Order order = dbController.getOrdrFromDB(ordernumber);
		// 2. check the order current status
		switch (order.getOrderStatus()) {
		case APPROVED:
			throw new Exception("The order already been approved");
		case CANCELED:
		case COLLECTED:
		case COMPLETED:
		case WAITING_FOR_CANCELLATION_APPROVAL:
		case WAITING_FOR_PAYMENT:
			throw new Exception("You dont need to approve the order \n the order is :" + order.getOrderStatus());
		default:
			break;
		}
		// 3. we can approve the order!
		order.setOrderStatus(OrderStatus.APPROVED);
		if (!dbController.updateOrder(order))
			throw new Exception("Failed to approve the order! please try again later");
		// 6. if the order time is too close, we will change it t 3 hours from now, only
		// if its home delivery
		if (order.isHomeDelivery()) {
			long orderTime = order.getArrivalDate().getTime();
			long currentTime = System.currentTimeMillis();
			long timeDiff = currentTime - orderTime;
			if (timeDiff < 3 * HOUR_IN_MILLISECONS || currentTime > orderTime) {
				orderTime = currentTime + 3 * HOUR_IN_MILLISECONS;
				order.setArrivalDate(new Timestamp(orderTime));
				// save the change to the database
				if (!DBController.getInstance().updateOrderArrivalTime(order))
					throw new Exception("Failed to update the order arrival time!");
			}
		}
		// 7. we got here - the order approved successfully
		// 7. send reminder and we done!
		User user = dbController.getUser(order.getUsername());
		String text = "";
		if (order.isHomeDelivery()) {
			text = "Your order delivery time is " + order.getArrivalDate().toLocalDateTime().toString();
		} else {
			text = "Your pickup time is " + order.getArrivalDate().toLocalDateTime().toString() + "\nFrom "
					+ order.getBranchName();
		}
		sendReminder(order, " approved ", 0, user, text);
	}

	/**
	 * TBD, set order to not approved
	 * 
	 * @param ordernumber
	 * @throws Exception on error -> with the error msg
	 */
	private void notApproveOrder(int ordernumber) throws Exception {
		// 1. get the order from the database
		Order order = dbController.getOrdrFromDB(ordernumber);
		// 2. check the order current status
		switch (order.getOrderStatus()) {
		case NOT_APPROVED:
			throw new Exception("The order is already not approved");
		case CANCELED:
		case APPROVED:
		case COLLECTED:
		case COMPLETED:
		case WAITING_FOR_CANCELLATION_APPROVAL:
		case WAITING_FOR_PAYMENT:
			throw new Exception("You dont need to not approve the order \n the order is :" + order.getOrderStatus());
		default:
			break;
		}
		// 3. we can approve the order!
		order.setOrderStatus(OrderStatus.NOT_APPROVED);
		if (!dbController.updateOrder(order))
			throw new Exception("Failed to update the order! please try again later");
		// 6. we got here - the order is not approved
		// 7. send reminder and we done!
		User user = dbController.getUser(order.getUsername());
		sendReminder(order, " not approved ", 0, user, "Please contact our cudtomer support team for more information");
	}

	/**
	 * cancel order, handle the refund and the relevant reminders
	 * 
	 * @param ordernumber
	 * @throws Exception
	 */
	private void cancelOrder(int ordernumber) throws Exception {
		// 1. get the order from the database
		Order order = dbController.getOrdrFromDB(ordernumber);
		// 2. check the order current status
		switch (order.getOrderStatus()) {
		case CANCELED:
			throw new Exception("The order already been canceled");
		case APPROVED:
		case COLLECTED:
		case COMPLETED:
		case WAITING_FOR_APPROAVL:
		case WAITING_FOR_PAYMENT:
			throw new Exception("You dont need to approve the order \n the order is :" + order.getOrderStatus());
		default:
			break;
		}
		// 3. we can now check the delivery time and give refund accordingly
		double refund = 0;
		long orderTime = order.getArrivalDate().getTime();
		long currentTime = System.currentTimeMillis();
		long timeDiff = orderTime - currentTime;
		if (timeDiff < HOUR_IN_MILLISECONS) {
			// no refund in the lsat hour
			refund = 0;
		} else if (timeDiff >= HOUR_IN_MILLISECONS && timeDiff <= 3 * HOUR_IN_MILLISECONS) {
			// between hour to 3 hours - 50% refund
			refund = order.getPrice() * 0.5;
		} else {
			// more than 3 hours - full refund
			refund = order.getPrice();
		}
		// 4. we can update the order status
		order.setOrderStatus(OrderStatus.CANCELED);
		if (!dbController.updateOrder(order))
			throw new Exception("Failed to cancel the order! please try again later");
		// 5. we can give the refund
		String userCard = dbController.getCardInfo(order.getUsername());
		if (userCard == null) {
			throw new Exception("Order canceled, no card info was found for the refund");
		}
		User user = dbController.getUser(order.getUsername());
		if (refund > 0) {
			// give refund!
			CreditController creditController = new CreditController();
			creditController.refund(user.getPersonID(), refund);
		}
		// 6. we got here - the order approved successfully
		// 7. send reminder and we done!
		sendReminder(order, " canceled ", refund, user, "");
	}

	/**
	 * Send reminder, sms and email about the given order
	 * 
	 * @param order          the reminder is amout this order
	 * @param orderIs        the order update
	 * @param refund         the refunded amount, 0 if nothing was refunded
	 * @param user           the user who will receive the reminders
	 * @param additionalText optional additional text
	 */
	private void sendReminder(Order order, String orderIs, double refund, User user, String additionalText) {
		StringBuilder sb = new StringBuilder();
		sb.append("Hello " + user.getFirstName() + " " + user.getLastName() + "\n");
		sb.append("Your order, order number " + order.getOrderNumber() + " was " + orderIs + "\n");
		if (refund > 0) {
			sb.append("You will receive refund of " + refund + " as store credit\n");
			sb.append("You can use your credit in our system for your next orders");
		}
		sb.append(additionalText);
		sb.append("\nHave a great day!\n");
		sb.append("\nautomatic message from Zerli system\n");
		String msgString = sb.toString();
		ReminderController reminders = new ReminderController();
		reminders.sendEmail(user.getEmail(), msgString);
		reminders.sendSMS(user.getPhoneNumber(), msgString);
	}

	/**
	 * Approve the order delivery - update status to collected, if the delivery was
	 * late -> give the customer full refund
	 * 
	 * @param ordernumber
	 * @throws Exception - if failed throw exception with the error msg
	 */
	public void approveOrderDelivery(int ordernumber) throws Exception {
		// get the delivery time
		Timestamp deliveryTime = new Timestamp(System.currentTimeMillis());
		// 1. try to get the order from the database
		Order order = dbController.getOrdrFromDB(ordernumber);
		// 2. confirm the order exist
		if (order == null)
			throw new Exception("There is no such order!");
		// 3. check if it has a home delivery
		if (!order.isHomeDelivery())
			throw new Exception("This order wasn't for home delivery! please check again");
		// 4. check if the order status is correct
		switch (order.getOrderStatus()) {
		case CANCELED:
		case NOT_APPROVED:
		case WAITING_FOR_APPROAVL:
		case WAITING_FOR_CANCELLATION_APPROVAL:
		case WAITING_FOR_PAYMENT:
			throw new Exception(
					"This order wasn't ready for delivery! the order is: \n" + order.getOrderStatus().toString());
		case COLLECTED:
			throw new Exception("The order delivery already confirmed");
		case APPROVED:
			break;
		default:
			break;
		}
		// 5. we can confirm the order delivery!
		order.setOrderStatus(OrderStatus.COLLECTED);
		if (!dbController.updateOrder(order))
			throw new Exception("Failed to confirm the order delivery! please try again later");

		// 6. we got here - the order delivery confirmed successfully
		// 6. lets check if the delivery was late
		handleLateDelivery(order, deliveryTime);
	}

	/**
	 * Check if the order delivered on time(with 5 minutes of the wanted time) , if
	 * not give the customer full refund for the order, send the customer email and
	 * sms on both cases with the relevant notification
	 * 
	 * @param order
	 * @param deliveryTime
	 */
	private void handleLateDelivery(Order order, Timestamp deliveryTime) {
		boolean onTime = false;
		long timeDiff = order.getArrivalDate().getTime() - deliveryTime.getTime();
		// if the delivery time was before the wanted arrival time -> all good
		if (timeDiff > 0) {
			onTime = true;
		}
		// we give the delivery man 5 minutes more from the delivery time
		else if (FIVE_MINUTES_IN_MILLISECONS + timeDiff > 0) {
			onTime = true;
		}
		// the delivery was late!
		else {
			onTime = false;
		}
		// 7.lets get the customer details
		User customer = dbController.getUser(order.getUsername());
		if (customer == null) {
			return; // can't really do much
		}
		// 8. lets create the reminder
		StringBuilder sb = new StringBuilder();
		sb.append("Hello " + customer.getFirstName() + " " + customer.getLastName() + "\n");
		if (onTime) {
			// arrived on time, inform the customer that we love him <3
			sendReminder(order, "has arrived!", 0, customer, "Thanks for using our amazing app\n");
		} else {
			sendReminder(order, "arrived late", order.getPrice(), customer, "We are sorry for the inconvenience\n");
			// lets give him a refund
			CreditController creditController = new CreditController();
			creditController.refund(customer.getPersonID(), order.getPrice());
		}
	}

}
