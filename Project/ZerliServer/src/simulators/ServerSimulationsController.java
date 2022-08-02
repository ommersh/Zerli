package simulators;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;

import database.DBController;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * controller for the main window of the simulators, "importing" data, payment
 * and sending sms\email
 * 
 * @author halel
 *
 */
public class ServerSimulationsController {

	private ServerSimulatorsManager serverSimulatorsManager = ServerSimulatorsManager.getInstance();
	@FXML
	private Button importDataBtn;

	@FXML
	private Label connectLabel;

	@FXML
	private TextArea simulationsLog;

	@FXML
	private Button importTableBtn;

	/**
	 * open the import data window
	 * 
	 * @param event
	 */
	@FXML
	void importData(ActionEvent event) {
		if (DBController.getInstance().isConnected) {
			connectLabel.setVisible(false);

			// open the create user window
			try {
				AnchorPane root;
				FXMLLoader loader = new FXMLLoader();
				final URL resource = getClass().getResource("/simulators/ImportUserWindow.fxml");
				loader.setLocation(resource);
				root = loader.load();
				Stage stage = new Stage();
				((ImportUserController) loader.getController()).init(stage);
				stage.setTitle("Simulations");
				stage.setScene(new Scene(root));
				stage.show();
				stage.setX(0);
				stage.setY(200);
				ServerSimulatorsManager.getInstance().simStage.hide();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			connectLabel.setVisible(true);
		}
	}

	/**
	 * init the log
	 */
	public void initLog() {
		serverSimulatorsManager.SimulationsLog.addListener(new ListChangeListener<String>() {
			@SuppressWarnings("rawtypes")
			@Override
			public void onChanged(Change c) {
				updateConsole();

			}
		});
	}

	/**
	 * update the log
	 */
	public void updateConsole() {
		synchronized (serverSimulatorsManager.SimulationsLog) {
			for (int i = 0; i < serverSimulatorsManager.SimulationsLog.size(); i++) {
				String s = serverSimulatorsManager.SimulationsLog.get(i);
				simulationsLog.appendText(LocalDateTime.now().toString() + "\n");
				simulationsLog.appendText(s + "\n");
				serverSimulatorsManager.SimulationsLog.remove(i);
			}
		}
	}

	/**
	 * Import a table from the users managment system database
	 */
	@FXML
	void importTable(ActionEvent event) {
		if (DBController.getInstance().isConnected) {
			connectLabel.setVisible(false);
			// import the table
			UserDataImportFromDB dataImportFromDB = new UserDataImportFromDB();
			dataImportFromDB.importUserDataTable();
			importTableBtn.setDisable(true);
		} else {
			connectLabel.setVisible(true);
		}
	}
}
