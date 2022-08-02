package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import userGuiManagment.MainWindowGuiManager;

/**
 * gui controller for the Connect window, manage connection to server attempts
 *
 */
public class ConnectGuiController implements IGuiController {

	// private String host;
	private int port = ClientUI.port;
	private String host = ClientUI.host;

	@FXML
	private AnchorPane connectionBase;

	@FXML
	private Button connectBtn;

	@FXML
	private Label errorLabel;

	@FXML
	private TextField ipAddress;

	/**
	 * try to connect to the server
	 * 
	 * @param event
	 */
	@FXML
	void tryToConnect(ActionEvent event) {
		host = ipAddress.getText();
		if (ClientUI.clientBoundary.connect(host, port)) {
			errorLabel.setText("");
			Stage stage = ClientUI.globalstage;
			MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
			Scene scene = new Scene(mainWindowManager.mainWindowController.getMainWindowRoot());
			stage.setScene(scene);
			stage.show();
			mainWindowManager.mainWindowController.init();
			mainWindowManager.mainWindowController.openWindow();
		} else {
			errorLabel.setText("Can't connect to server");
		}
	}

	@Override
	public Pane getBasePane() {
		return connectionBase;
	}

	@Override
	public void resetController() {
		port = ClientUI.port;
		host = ClientUI.host;
	}

	@Override
	public void openWindow() {

	}

}