package mainWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import userGuiManagment.MainWindowGuiManager;

/**
 * controller for the window: the opening window, show welcome string and login
 * button
 * 
 *
 */
public class StartingWindowGuiController implements IGuiController {

	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	@FXML
	private AnchorPane basePane;

	@FXML
	private Button loginButton;

	@FXML
	void login(ActionEvent event) {
		mainWindowManager.loginGuiController.openWindow();
	}

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {
		// TODO Auto-generated method stub
	}

	@Override
	public void openWindow() {
		mainWindowManager.mainWindowController.showNewWindow(basePane);
	}

}
