package clientHandlers;

import database.DBController;
import msg.Msg;
import ocsf.server.ConnectionToClient;
import server.ServerMsgController;
import user.User;
import usersManagment.LoginConrtroller;

/**
 * handle the client task, manage the connection to different user types and
 * handle the tasks for the server
 *
 */
public class HandleClientTask {
	// class variables
	private User ActiveUser;

	/**
	 * hold the handler for the active client type(or not connected client)
	 */
	private ClientTasks activeClientTask;
	/**
	 * the database controller
	 */
	private DBController dbController = DBController.getInstance();

	/**
	 * save the client connection
	 */
	@SuppressWarnings("unused")
	private ConnectionToClient client;

	public HandleClientTask(ConnectionToClient client) {
		this.client = client;
	}

	public User getActiveUser() {
		return ActiveUser;
	}

	public void setActiveUser(User activeUser) {
		ActiveUser = activeUser;
	}

	/**
	 * handle the client task, return the msg we need to return
	 * 
	 * @param msg
	 * @return
	 */
	public Msg handleTask(Object msg) {
		ServerMsgController msgController = new ServerMsgController();
		// if no correct msg was found
		if (msgController.mgsParser(msg) == false)
			return ServerMsgController.createERRORMsg("Error, wrong msg received");
		// if the correct msg was found, let the user task handle it
		if (activeClientTask == null) {
			activeClientTask = new NoUserTask(this);
		}
		// handle logout and exit tasks
		switch (msgController.getType()) {
		case LOG_OUT_REQUEST:
			LoginConrtroller loginConrtroller = new LoginConrtroller();
			Msg returnMsg = loginConrtroller.logout(ActiveUser);
			if (ActiveUser != null) {
				logout();
			}
			return returnMsg;
		case EXIT:
			LoginConrtroller loginConrtroller2 = new LoginConrtroller();
			Msg returnMsg2 = loginConrtroller2.forceLogout(ActiveUser);
			if (ActiveUser != null) {
				logout();
			}
			return returnMsg2;
		case GET_BRANCH_LIST:
			return ServerMsgController.createRETURN_BRANCH_NAMESMsg(dbController.getAllBranches());
		default:
			return activeClientTask.handleTask(msgController);
		}
	}

	/**
	 * on login, set the active task handler according to the connected client type
	 * 
	 * @param user
	 */
	public void login(User user) {
		ActiveUser = user;
		if (user == null)
			return;
		switch (user.getUserType()) {
		case AuthorizedCustomer:
			activeClientTask = new AuthorizedCustomerTask(this);
			break;
		case BranchEmployee:
			activeClientTask = new BranchEmployeeTask(this);
			break;
		case BranchManager:
			activeClientTask = new BranchManagerTask(this);
			break;
		case CEO:
			activeClientTask = new CEOTask(this);
			break;
		case Courier:
			activeClientTask = new CourierTask(this);
			break;
		case CustomerServiceEmloyee:
			activeClientTask = new CustomerServiceEmloyeeTask(this);
			break;
		case MarketingEmployee:
			activeClientTask = new MarketingEmployeeTask(this);
			break;
		case NonAuthorizedCustomer:
			activeClientTask = new NonAuthorizedCustomerTask(this);
			break;
		default:
			activeClientTask = new NoUserTask(this);
			break;
		}
	}

	public void logout() {
		ActiveUser = null;
		activeClientTask = new NoUserTask(this);
	}

	public void forceLogOut() {
		if (ActiveUser != null) {
			// to log out remove the user entity
			dbController.disconnectUser(ActiveUser.getUsername());
			logout();
		}
	}
}
