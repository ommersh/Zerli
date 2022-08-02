package server;

import java.util.ArrayList;

import database.DBController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import msg.Msg;
import scheduledTasks.ScheduledTasksManager;
import serverGui.ClientsData;

/**
 * Boundary for the prototype server, create the server controller, manage
 * status log and client table updates and action for the connect/disconnect
 * 
 * @author hallel
 * @version 01
 * 
 */
public class ServerBoundary {

	private ServerController server;
	private ArrayList<String> status;
	private DBController dbController;
	public ObservableList<ClientsData> clientsTable;
	public ObservableList<String> LogLines;
	private int clientsCount = 1;

	public ServerBoundary() {
		server = null;
		status = new ArrayList<String>();
		dbController = DBController.getInstance();
		clientsTable = FXCollections.observableArrayList();
		LogLines = FXCollections.observableArrayList();
	}

	/**
	 * connect to the server and the database, set status for success and failure
	 * 
	 * @param ServerPort -> the server port number
	 * @param DBname     -> the database name string
	 * @param DBuser     -> the database username
	 * @param DBpassword -> the database password
	 */
	public boolean connect(int ServerPort, String DBname, String DBuser, String DBpassword) {
		try {// try connecting to db
			dbController.connectToDB(DBname, DBuser, DBpassword);
			setStatus("Connected to database successfully");// on success
		} catch (Exception ex) {
			setStatus(ex.getMessage());// on failure
			return false;
		}
		try {// try activate the server
			server = new ServerController(ServerPort, dbController, this);// create the server
			server.listen(); // Start listening for connections
		} catch (Exception ex) {
			setStatus("ERROR - Could not listen for clients!");
			setStatus("Server not active");
			return false;
		}
		setStatus("Server active");
		// init new server scheduled tasks manager
		ScheduledTasksManager scheduledTasksManager = ScheduledTasksManager.getInstance();
		Thread scheduledTasksThread = new Thread(scheduledTasksManager);
		scheduledTasksThread.start();

		return true;
	}

	/**
	 * Disconnect from the server and the database
	 */
	public void disconnect() {
		// tell the clients we disconnect
		Msg msg = ServerMsgController.createEXITMsg();
		try {
			server.sendToAllClients(msg);
		} catch (Exception e) {
			// do nothing
		}
		// stop the server
		server.stopListening();
		try {
			server.close();
		} catch (Exception ex) {
		}
		dbController.disConnectFromDB();
		server = null;
		setStatus("Server not active");
		ScheduledTasksManager.getInstance().endRunning();
	}

	/**
	 * update the clients table using the client and the status
	 * 
	 */
	public void updateClientsTable(String ipAdress, String status, String host, String name)// return String
	{
		String tempNumber = "";
		ClientsData tempClientData = new ClientsData(tempNumber, ipAdress, status, host, name);
		if (clientsTable.contains(tempClientData)) {
			tempNumber = clientsTable.get(clientsTable.indexOf(tempClientData)).getNumber();
			ipAdress = clientsTable.get(clientsTable.indexOf(tempClientData)).getIpAddress();
			clientsTable.remove(tempClientData);
		} else {
			tempNumber += clientsCount;
			clientsCount++;
		}
		tempClientData.setNumber(tempNumber);
		tempClientData.setIpAdress(ipAdress);
		clientsTable.add(tempClientData);
	}

	/**
	 * set the next line in the status log
	 * 
	 * @param s -> the next line in the status log
	 */
	public void setStatus(String s) {
		status.add(s);
		LogLines.add(s);
	}

	/**
	 * return the string for the next line/lines in status log
	 * 
	 * @return string
	 */
	public ArrayList<String> getStatus()// return string, next line in log
	{
		ArrayList<String> s = status;
		status = new ArrayList<String>();// empty the log
		return s;// return the log
	}

	public void quit() {
		Msg msg = ServerMsgController.createEXITMsg();
		try {
			server.sendToAllClients(msg);
		} catch (Exception e) {
			// do nothing
		}
		disconnect();
	}
}
