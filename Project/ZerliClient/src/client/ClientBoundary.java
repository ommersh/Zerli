package client;

import java.io.IOException;
import msg.Msg;

/**
 * boundary of the client , make the zerliClientController,class contains 3
 * methods and received a requests from Client UI and send to
 * zerliClientController instance to handling msg and send to server
 * 
 * @author Ronen
 *
 */
public class ClientBoundary {

	// Instance variables **********************************************

	private Msg msg;
	//private ClientController client;
	public static ClientController client;

	// Constructors ****************************************************
	/**
	 * this constructor construct a ClientBoundary
	 */
	public ClientBoundary() {
		msg = new Msg();

	}

	/**
	 * connect to the server
	 * 
	 * @param host The host to connect to
	 * @param port The port to connect on.
	 * @return true if connected to server
	 */
	public boolean connect(String host, int port) {
		try {
			//client = new ClientController(host, port,this);
			client =ClientController.getInstance_WithArguments(host, port, this);
			System.out.println("connected to server");
			return true;
		} catch (IOException e) {
			System.out.println("Error cant connect to server");
			return false;
		}
	}

	/**
	 * this method exit/stop the system when server disconnected
	 */

	public void serverDisconnect() {
		System.out.println("Server disconnected");
		System.exit(0);
	}

	/**
	 * this method our client used to stop/disconnect/terminate.send a msg to
	 * msgController and prepare ExitMsg and send it to server by
	 * handlemsgfromclientUI
	 */

	public void quit() {
	    msg = MsgController.createEXITMsg();
		client.handleMessageFromClientUI(msg);
		client.quit();
	}
	

}
