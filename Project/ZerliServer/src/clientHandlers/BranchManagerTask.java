package clientHandlers;

import java.util.ArrayList;

import msg.Msg;
import order.Order;
import orderManagment.OrdersController;
import report.Report;
import server.ServerMsgController;
import user.User;
import usersManagment.UserManagmentController;

/**
 * Handle the branch manager actions can update the user data, update the order
 * status and get all the order belonging to his branch
 */
public class BranchManagerTask extends ClientTasks {

	UserManagmentController userManagmentController;

	public BranchManagerTask(HandleClientTask clientTaskHandler) {
		super(clientTaskHandler);
		userManagmentController = new UserManagmentController();
	}

	/**
	 * Handle the branch manager actions can update the user data, update the order
	 * status and get all the order belonging to his branch
	 */
	@Override
	public Msg handleTask(ServerMsgController msgController) {

		switch (msgController.getType()) {
		case UPDATE_ORDER_STATUS:
			OrdersController orderController = new OrdersController();
			try {
				if (msgController.getOrder() != null) {
					orderController.approveOrder(msgController.getOrder());
					newMsgToSend = CompletedMsg;
				} else
					newMsgToSend = ServerMsgController.createERRORMsg("Error! failed to update the order status");
			} catch (Exception e) {
				newMsgToSend = ServerMsgController.createERRORMsg(e.getMessage());
			}
			break;
		case GET_ALL_ORDERS:
			ArrayList<Order> orders = dbController.getAllOrdersInBranch(user.getBranchName());
			newMsgToSend = ServerMsgController.createRETURN_ALL_ORDERSMsg(orders);
			break;
		case GET_USER:
			User tempuser = dbController.getUser(msgController.getUserName());
			newMsgToSend = ServerMsgController.createRETURN_USERMsg(tempuser);
			break;
		case GET_ORDER:
			Order order = dbController.getOrdrFromDB(msgController.getOrderNumber());
			order.setItems(dbController.getItemInOrderFromDB(msgController.getOrderNumber()));
			newMsgToSend = ServerMsgController.createRETURN_ORDERMsg(order);
			break;
		case GET_REPORT:
			Report tempReport = new Report(msgController.getMonth(), msgController.getYear(),
					msgController.getReportType(), user.getBranchName());
			Report report = dbController.getReportFromDB(tempReport);
			newMsgToSend = ServerMsgController.creatRETURN_REPORTMsg(report);
			break;
		case GET_ALL_USERS:
			ArrayList<User> users;
			switch (msgController.getUserType()) {
			case AuthorizedCustomer:
			case NonAuthorizedCustomer:
				users = dbController.getAllUsersWithType(msgController.getUserType());
				break;
			default:
				users = dbController.getAllBranchEmployees(user.getBranchName());
				break;
			}
			newMsgToSend = ServerMsgController.createRETURN_ALL_USERSMsg(users);
			break;
		case ADD_CARD:
			try {
				userManagmentController.addCard(msgController.getUserName(), msgController.getCardinfo());
				newMsgToSend = CompletedMsg;
			} catch (Exception e) {
				newMsgToSend = ServerMsgController.createERRORMsg(e.getMessage());
			}
			break;
		case UPATE_USER_DATA:
			try {
				userManagmentController.updateUserData(msgController.getUser());
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
