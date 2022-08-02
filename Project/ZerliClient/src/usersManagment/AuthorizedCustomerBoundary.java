package usersManagment;

import java.util.ArrayList;

import order.Order;

/**
 * Boundary for all the non shop related actions of the customer
 * 
 */
public class AuthorizedCustomerBoundary {

	private AuthorizedCustomerController AuthorizedCustomerCon;

	public AuthorizedCustomerBoundary() {

		this.AuthorizedCustomerCon = new AuthorizedCustomerController();
	}

	/**
	 * get all the xustomer's order
	 * 
	 * @return
	 */
	public ArrayList<Order> getAllOrders() {
		return AuthorizedCustomerCon.getAllOrders();

	}

	/**
	 * request to cancel a order
	 * 
	 * @param order
	 * @return
	 */
	public boolean requestOrderCancellation(Order order) {
		return AuthorizedCustomerCon.requestOrderCancellation(order);

	}

	/**
	 * get the full order with all the details
	 * 
	 * @param orderNumber
	 * @return
	 */
	public Order getfullOrder(int orderNumber) {
		return AuthorizedCustomerCon.getfullOrder(orderNumber);
	}

}
