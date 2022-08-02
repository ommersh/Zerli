package client;

import msg.Msg;
import msg.MsgType;
import ocsf.client.AbstractClient;
import java.io.IOException;

public class ClientController extends AbstractClient {

	/**
	 * how much time we wait before we timeout(in 100 millis)
	 */
	private static final int TIME_TO_WAIT_BEFORE_TIMEOUT = 1000000000;// 300;
	/**
	 * awaitResponse used to make thread sleep until thread of
	 * handleMessageFromServer finish his work
	 */
	public static boolean awaitResponse = false;
	ClientBoundary clientBoundary;
	private static ClientController client;
	public MsgController SaveParsedMSG;

	/**
	 * this is a private constructor(we used in this way to make a singlton this
	 * class singlton) private to not let make more than one instance of
	 * clientController class this constructor init a clientController in
	 * ClientBoundary
	 * 
	 * @param host           name of the host
	 * @param port           number of the port for communicate
	 * @param clientBoundary object that init this clientController object
	 * @throws IOException
	 */
	private ClientController(String host, int port, ClientBoundary clientBoundary) throws IOException {
		super(host, port);
		openConnection();
		this.clientBoundary = clientBoundary;
	}

	/**
	 * this is a singlton class because of that we use getInstance Method to return
	 * our object a not new one and constructor is private
	 * 
	 * @return return our one object
	 */
	public static ClientController getInstance() {
		if (client != null)
			return client;
		return null;
	}

	/**
	 * this method used just by ClientBoundary class to init our one object.
	 * clientBoundary send connect details to this static method and this method
	 * make our alone instance (about arguments we get them from clientBoundary to
	 * make a connection) (same comments for arguments like in the constructor)
	 */
	public static ClientController getInstance_WithArguments(String host, int port, ClientBoundary clientBoundary)
			throws IOException {
		if (client == null) // if client null mean we still not init it and this is how to init by its
							// constructor
		{
			client = new ClientController(host, port, clientBoundary);
		}
		return client;
	}

	/**
	 * send the given msg to the server, return the result in the msgCntroller
	 * 
	 * @param msg
	 * @return
	 */
	public MsgController sendMsg(Msg msg) {
		SaveParsedMSG = new MsgController(); // reset saveParsedMSG (msgType=NONE)
		if (msg != null) {
			handleMessageFromClientUI(msg);
		}
		return SaveParsedMSG; // return the data (in case msg=null saveParsedMSG will return
								// saveParsedMSG.getType=NONE)
	}

	public void handleMessageFromClientUI(Object message) {
		int timeOut = 0;
		try {
			openConnection();// in order to send more than one message
			awaitResponse = true;
			if (((Msg) message).type.equals(MsgType.EXIT)) {
				awaitResponse = false;
			}
			sendToServer(message); // this is the only way to communicate with the server
			while (awaitResponse && timeOut <= TIME_TO_WAIT_BEFORE_TIMEOUT) {
				try {
					Thread.sleep(100);
					timeOut++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			display("Could not send message to server: Terminating client." + e);
			quit();
		}
		if (timeOut > TIME_TO_WAIT_BEFORE_TIMEOUT) {
			display("Could not send message to server: Terminating client.");
			// try to send exit msg, dont wait for response
			try {
				sendToServer(MsgController.createEXITMsg());
			} catch (IOException e) {
				// do nothing , if we can't its ok too
			}
			System.exit(0);
		}
	}

	/**
	 * this method overrided from abstractClient class and this method handled a msg
	 * sent from server to our client. after we received we parse the msg by
	 * msgParser Method (implemented in MsgController class) and saved the parsed
	 * data in msgController of the client then changed awaitResponse to let client
	 * send thread wakeup
	 * 
	 * @param msg contains the data we asked from server
	 */
	@Override
	protected void handleMessageFromServer(Object msg) {
		Msg receivedMsg;
		MsgController parseMSG;
		if (msg instanceof Msg) {
			receivedMsg = (Msg) msg;
			parseMSG = new MsgController();
			if (!(parseMSG.mgsParser(receivedMsg))) // in case returned failed mean no type founded we returned msg
													// included ERROR
			{
				receivedMsg.type = MsgType.ERROR; // change receivedmsg unknown type to ERROR type
				parseMSG.mgsParser(receivedMsg);
			}
			SaveParsedMSG = parseMSG;
			awaitResponse = false; // after we parse and save data its time to wakeup the thread of client
			if (parseMSG.getType().equals(MsgType.EXIT)) { // in case server want to disconnect then terminate send exit
															// msg for all clients
				// in the future add reconnect options
				clientBoundary.serverDisconnect();
			}

		}
	}

	/**
	 * this method close the connection with server.throw IOException if
	 * closeConnection() with server didn't succeed
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * this method display a msg String to the console
	 * 
	 * @param message contains the string we want to display in console
	 */
	public void display(String message) {
		System.out.println("> " + message);
	}

}
