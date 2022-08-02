package usersManagment;

import client.ClientController;
import client.MsgController;
import msg.Msg;

/**
 * use the clientcontroller to get the requests to the server and handle the
 * result
 * 
 * @author halel
 *
 */
public class UserController {

	/**
	 * loginResults saved the returned data from sendMsg method in clientController
	 */
	private ClientController clientController = ClientController.getInstance();
	private MsgController loginResults;
	// public boolean login(String username, String password)

	/**
	 * received the username and password details from a users who extend a
	 * userboundary class then make a login request msg and send it the the server
	 * by clientController
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public MsgController login(String username, String password) {
		// clientController=clientController.getInstance();
		Msg msg = MsgController.createLOGIN_REQUESTMsg(username, password);
		loginResults = clientController.sendMsg(msg);
		// if (loginResults.getType().equals(MsgType.APPROVE_LOGIN)) {
		return loginResults; // returned what loginResults contains (ERROR with String of error type/User
								// data)
		// }
		// return null;
	}

	/**
	 * request a logout from users who extends userboundary. create logout request
	 * msg and send the request to server by clientController
	 */
	public void logout() {
		Msg msg = MsgController.createLOG_OUT_REQUESTMsg();
		@SuppressWarnings("unused")
		MsgController loginResults = clientController.sendMsg(msg);
		loginResults = new MsgController(); // to reset the User msgController
		// we don't really care if we got approved on our log out request
	}
}
