package main;

import client.ClientBoundary;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import userGuiManagment.MainWindowGuiManager;

/**
 * the main for the client ui
 *
 */
public class ClientUI extends Application {

	public static final String DEFAULT_HOST = "localhost";
	public static final int DEFAULT_PORT = 5555;
	public static ClientBoundary clientBoundary;
	public static String host;
	public static int port;
	public static Stage globalstage;
	public static ConnectGuiController connectGuiController;

	/**
	 * start the client application, try to connect to the server, open the
	 * connection window on fail
	 */
	@Override
	public void start(Stage stage) {
		clientBoundary = new ClientBoundary();
		globalstage = stage;
		// load the connection controller
		connectGuiController = (ConnectGuiController) GuiObjectsFactory.getInstance()
				.loadFxmlFile("/main/ConnectToServerScreen.fxml");
		if (clientBoundary.connect(host, port)) {
			MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
			Scene scene = new Scene(mainWindowManager.mainWindowController.getMainWindowRoot());
			stage.setScene(scene);
			stage.show();
			stage.setMaxHeight(820);
			stage.setMaxWidth(1220);
			stage.setMinHeight(800);
			stage.setMinWidth(1200);
			stage.setOnCloseRequest(event -> {
				System.out.println("Client is closing");
				stop();
			});
			mainWindowManager.mainWindowController.init();
			mainWindowManager.mainWindowController.openWindow();
		} else {
			// failed to connect, open the connection window
			Scene scene = new Scene(connectGuiController.getBasePane());
			stage.setScene(scene);
			stage.show();
		}

	}

	/**
	 * Stop the clients, called on exit the window, close the connection and exit
	 * the program
	 */
	@Override
	public void stop() {
		try {
			clientBoundary.quit();
		} catch (Exception e) {
			// do nothing
		}
		System.out.println("End");
		System.exit(1);
	}

	// should have arguments "host" "port"
	public static void main(String[] args) {
		// get the ip and port from the arguments
		host = DEFAULT_HOST;
		port = DEFAULT_PORT;
		try {
			host = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			port = Integer.parseInt(args[1]);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		clientBoundary = new ClientBoundary();

		launch();
	}
}
