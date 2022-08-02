package clientHandlers;

import database.DBController;
import msg.Msg;
import server.ServerMsgController;
import user.User;

/**
 * the abstarct class for the client task, for each type of connected client we
 * have a extended class to handle the tasks, the tasks handles dont care which
 * client in connected, we save error and completed msgs, database controller ,
 * the client task handler(for login\out) and the active user object
 * 
 *
 */
public abstract class ClientTasks {
	// class variables

	/**
	 * the database controller
	 */
	protected DBController dbController;
	/**
	 * static msg
	 */
	protected Msg CompletedMsg;
	protected Msg ErrorMsg;
	/**
	 * the new msg we want to sand,
	 */
	protected Msg newMsgToSend;
	/**
	 * the client task handler
	 */
	protected HandleClientTask clientTaskHandler;
	/**
	 * the active user
	 */
	protected User user;

	public ClientTasks(HandleClientTask clientTaskHandler) {
		this.clientTaskHandler = clientTaskHandler;
		this.dbController = DBController.getInstance();
		CompletedMsg = ServerMsgController.createCOMPLETEDMsg();
		ErrorMsg = ServerMsgController.createERRORMsg("");
		user = clientTaskHandler.getActiveUser();
	}

	/**
	 * get a msg, handle the task and return the msg to send back
	 * 
	 * @param msg
	 * @return
	 */
	public abstract Msg handleTask(ServerMsgController msgController);
}
