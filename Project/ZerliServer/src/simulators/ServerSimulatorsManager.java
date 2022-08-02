package simulators;

import java.io.IOException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * manage all the simulations - payment, sms\email , importing user data.
 * singleton, the server controller for the above actions can access this
 * manager to show their actions
 */
public class ServerSimulatorsManager implements Runnable {

	private static ServerSimulatorsManager instance;

	private ServerSimulatorsManager() {
		SimulationsLog = FXCollections.observableArrayList();
	}

	public static ServerSimulatorsManager getInstance() {
		if (instance == null) {
			instance = new ServerSimulatorsManager();
		}
		return instance;
	}

	public ObservableList<String> SimulationsLog;
	private ServerSimulationsController controller;
	public Stage simStage;

	/**
	 * run the simulations
	 */
	public void runSimulators() {
		AnchorPane root;
		try {
			FXMLLoader loader = new FXMLLoader();
			final URL resource = getClass().getResource("/simulators/ServerSimulations.fxml");
			loader.setLocation(resource);
			root = loader.load();
			simStage = new Stage();
			simStage.setTitle("Simulations");
			controller = loader.getController();
			controller.initLog();
			simStage.setScene(new Scene(root));
			simStage.show();
			simStage.setX(0);
			simStage.setY(200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// run the simulations
		runSimulators();

		
	}

}
