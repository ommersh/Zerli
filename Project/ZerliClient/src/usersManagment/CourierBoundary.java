package usersManagment;

import client.ClientController;
import client.MsgController;
import msg.MsgType;
import order.Order;
import order.OrderStatus;

/**
 * boundary for the courier, can only approve delivery
 *
 */
public class CourierBoundary {
	private ClientController client = ClientController.getInstance();

	public void requestConfirmDelivery(int orderNumber) throws Exception {
		Order order = new Order();
		order.setOrderNumber(orderNumber);
		order.setOrderStatus(OrderStatus.COLLECTED);
		MsgController msgController = client.sendMsg(MsgController.createUPDATE_ORDER_STATUSMsg(order));
		if (msgController.getType() == MsgType.ERROR) {
			throw new Exception(msgController.getErrorMsg());
		}
	}
}
