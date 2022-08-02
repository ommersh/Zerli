package clientHandlers;

import msg.Msg;
import orderManagment.OrdersController;
import server.ServerMsgController;

/**
 * The courier actions. can update order status
 */
public class CourierTask extends ClientTasks {

	public CourierTask(HandleClientTask clientTaskHandler) {
		super(clientTaskHandler);
	}

	/**
	 * The courier actions. can update order status
	 */
	@Override
	public Msg handleTask(ServerMsgController msgController) {

		switch (msgController.getType()) {
		case UPDATE_ORDER_STATUS:
			OrdersController orderController = new OrdersController();
			try {
				orderController.approveOrderDelivery(msgController.getOrder().getOrderNumber());
				newMsgToSend = CompletedMsg;
			} catch (Exception e) {
				newMsgToSend = ServerMsgController.createERRORMsg(e.getMessage());
			}
			break;
		default:
			// handle cant do it
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;
		}
		return newMsgToSend;
	}

}
