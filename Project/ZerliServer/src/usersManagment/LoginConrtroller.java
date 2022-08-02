package usersManagment;

import database.DBController;
import msg.Msg;
import server.ServerMsgController;
import user.User;

/**
 * manage the login and logout
 *
 */
public class LoginConrtroller {
	/**
	 * the database controller
	 */
	private DBController dbController = DBController.getInstance();
	/**
	 * save the active user information
	 */
	private User activeUser = null;

	/**
	 * login a non connected user with username and password
	 * 
	 * @param username
	 * @param password
	 * @return the return message, approve login with the user information on
	 *         success or error message with the error string
	 */
	public Msg login(String username, String password) {
		try {
			if (dbController.connectUser(username, password)) {
				User user = dbController.getUser(username);
				activeUser = user;
				return ServerMsgController.createAPPROVE_LOGINMsg(user);
			} else {// wrong username or password
				return ServerMsgController.createERRORMsg("Wrong username and password");
			}
		} catch (Exception e) {
			return ServerMsgController.createERRORMsg("The user already connected");// already connected msg
		}
	}

	/**
	 * logout the given user
	 * 
	 * @param user
	 * @return the return message, approve logout on success or error message with
	 *         the error string
	 */
	public Msg logout(User user) {
		activeUser = null;
		if (user != null) {
			if (dbController.disconnectUser(user.getUsername()))
				return ServerMsgController.createAPPROVE_LOGOUTMsg();
			else {
				return ServerMsgController.createERRORMsg("failed to disconnect the user");
			}
		}
		return ServerMsgController.createAPPROVE_LOGOUTMsg();
	}

	/**
	 * force logout the given user, for client who got disconnected while the user
	 * is logged in
	 * 
	 * @param user
	 * @return the return message, always null, we don't need to replay
	 */
	public Msg forceLogout(User user) {
		if (user != null)
			dbController.disconnectUser(user.getUsername());
		return null;
	}

	/**
	 * get the active user data
	 * 
	 * @return
	 */
	public User getActiveUser() {
		return activeUser;
	}

	/**
	 * set the database controller, for property injection and UT
	 * 
	 * @param dbController
	 */
	public void setDbController(DBController dbController) {
		this.dbController = dbController;
	}

}
