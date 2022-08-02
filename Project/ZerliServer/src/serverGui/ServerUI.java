package serverGui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import simulators.ServerSimulatorsManager;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * the main for the server application, open the server window
 *
 */
public class ServerUI extends Application {
	/**
	 * the gui controller
	 */
	private ServerGuiController controller;
	/**
	 * the simulations thread
	 */
	private Thread simThread;

	/**
	 * start the server application
	 */
	@Override
	public void start(Stage stage) {
		AnchorPane root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/serverGui/ServerGUI.fxml"));
			root = loader.load();
			controller = loader.getController();
			stage.setTitle("Server");
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setOnCloseRequest(event -> {
				System.out.println("Server is closing");
				stop();
			});
			stage.show();
		} catch (Exception e) {
			System.out.println("gui problem");
			e.printStackTrace();
		}
		// run the simulators
		simThread = new Thread(ServerSimulatorsManager.getInstance());
		simThread.run();
	}

	@Override
	public void stop() {
		try {
			controller.getBoundary().quit();
		} catch (Exception e) {
			// do nothing
		}
		System.out.println("Exit server, goodbye");
		System.exit(1);
	}

	public static void main(String[] args) {
		launch();
	}
}