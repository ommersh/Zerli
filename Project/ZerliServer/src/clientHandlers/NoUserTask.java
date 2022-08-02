package clientHandlers;

import java.util.ArrayList;

import msg.Msg;
import server.ServerMsgController;
import usersManagment.LoginConrtroller;

/**
 * the action for non connected user, can login
 */
public class NoUserTask extends ClientTasks {

	public NoUserTask(HandleClientTask clientTaskHandler) {
		super(clientTaskHandler);
	}

	/**
	 * the action for non connected user, can login
	 */
	@Override
	public Msg handleTask(ServerMsgController msgController) {

		// action for none connected clients
		switch (msgController.getType()) {
		case GET_BRANCH_LIST:
			ArrayList<String> branches = dbController.getAllBranches();
			newMsgToSend = ServerMsgController.createRETURN_BRANCH_NAMESMsg(branches);
			break;
		case LOGIN_REQUEST:
			LoginConrtroller loginController = new LoginConrtroller();
			newMsgToSend = loginController.login(msgController.getUserName(), msgController.getPassword());
			user = loginController.getActiveUser();
			if (user != null) {
				this.clientTaskHandler.login(user);
			}
			break;
		case ERROR:
			// none
			break;
		default:
			// handle cant do it
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;
		}
		return newMsgToSend;
	}

}
