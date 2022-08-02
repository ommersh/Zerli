package clientHandlers;

import java.util.ArrayList;

import catalog.Product;
import msg.Msg;
import order.Order;
import server.ServerMsgController;

/**
 * handle the non authorized customer actions, can view the catalog, orders
 * history and cancel orders
 */
public class NonAuthorizedCustomerTask extends ClientTasks {

	public NonAuthorizedCustomerTask(HandleClientTask clientTaskHandler) {
		super(clientTaskHandler);
		// TODO Auto-generated constructor stub
	}

	/**
	 * handle the non authorized customer actions, can view the catalog, orders
	 * history and cancel orders
	 */
	@Override
	public Msg handleTask(ServerMsgController msgController) {

		switch (msgController.getType()) {
		case GET_CATALOG_PAGE:
			// get catalog page
			ArrayList<Product> catalog = dbController.getCatalogCategory(msgController.getCategory());// toto
			newMsgToSend = ServerMsgController.createRETURN_CATALOG_PAGEMsg(catalog);
			break;
		case GET_ALL_ORDERS:
			ArrayList<Order> orders = dbController.getAllOrdersOfCustomer(user.getUsername());
			newMsgToSend = ServerMsgController.createRETURN_ALL_ORDERSMsg(orders);
			break;
		case UPDATE_ORDER_STATUS:
			// update order status in the db
			dbController.updateOrder(msgController.getOrder());
			break;

		default:
			// handle cant do it
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;
		}
		return newMsgToSend;
	}

}
