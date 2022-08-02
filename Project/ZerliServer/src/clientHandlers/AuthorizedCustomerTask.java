package clientHandlers;

import java.util.ArrayList;

import catalog.Product;
import catalogManagment.CatalogController;
import msg.Msg;
import order.Order;
import orderManagment.OrderProcessManager;
import server.ServerMsgController;

/**
 * handle the authorized customer actions, can view the catalog, get his orders
 * history, pay for order ,place order and update order status
 */
public class AuthorizedCustomerTask extends ClientTasks {
	/**
	 * order process controller to manage the order process
	 */
	private OrderProcessManager orderProcessManager;

	/**
	 * to handle the catalog use
	 */
	private CatalogController catalogController;

	public AuthorizedCustomerTask(HandleClientTask clientTaskHandler) {
		super(clientTaskHandler);
		orderProcessManager = new OrderProcessManager();
		catalogController = new CatalogController();
	}

	/**
	 * handle the authorized customer actions, can view the catalog, get his orders
	 * history, pay for order ,place order and update order status
	 */
	@Override
	public Msg handleTask(ServerMsgController msgController) {
		switch (msgController.getType()) {
		case GET_CATALOG_PAGE:
			// get catalog page
			ArrayList<Product> catalog = catalogController.getCatalogCategory(msgController.getCategory());
			newMsgToSend = ServerMsgController.createRETURN_CATALOG_PAGEMsg(catalog);
			break;
		case GET_ALL_ORDERS:
			ArrayList<Order> orders = dbController.getAllOrdersOfCustomer(user.getUsername());
			newMsgToSend = ServerMsgController.createRETURN_ALL_ORDERSMsg(orders);
			break;
		case PAY_FOR_ORDER:
			// get the card info
			String cardInfo = dbController.getCardInfo(user.getUsername());
			// use the order controller to pay
			if (orderProcessManager.payForOrder(cardInfo)) {
				// payment succeed, save the order!
				if (orderProcessManager.saveOrderToDB()) {
					// the order saved successfully
					newMsgToSend = ServerMsgController.createRETURN_PAYMENT_APPROVALMsg();
				} else {
					newMsgToSend = ServerMsgController.createERRORMsg("Error! failed to save the order!");
				}
			} else {
				newMsgToSend = ServerMsgController.createERRORMsg("Payment declined!");
			}
			// reset the order controller
			orderProcessManager.reset();
			break;
		case PLACE_ORDER_REQUEST:
			// use the order controller
			orderProcessManager = new OrderProcessManager();
			Order order = orderProcessManager.placeOrder(msgController.getCart(), 0, user.getUsername(),
					user.getPersonID());
			newMsgToSend = ServerMsgController.createRETURN_ORDERMsg(order);
			break;
		case UPDATE_ORDER_STATUS:
			// update order status in the db
			if (dbController.updateOrder(msgController.getOrder())) {
				newMsgToSend = CompletedMsg;
			} else {
				newMsgToSend = ServerMsgController.createERRORMsg("Failed to cancel the order");
			}
			break;
		case GET_ORDER:
			Order order2 = dbController.getOrdrFromDB(msgController.getOrderNumber());
			order2.setItems(dbController.getItemInOrderFromDB(msgController.getOrderNumber()));
			newMsgToSend = ServerMsgController.createRETURN_ORDERMsg(order2);
			break;
		default:
			// handle cant do it
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;
		}
		return newMsgToSend;
	}
}
