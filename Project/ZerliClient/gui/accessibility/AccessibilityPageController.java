package accessibility;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import userGuiManagment.MainWindowGuiManager;

/**
 * Controller for the accessibility window, for now can only contact support for
 * help, will be added in future version
 * 
 *
 */
public class AccessibilityPageController implements IGuiController {

	@FXML
	private AnchorPane basePane;

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {
	}

	@Override
	public void openWindow() {
		MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
		mainWindowManager.mainWindowController.changeWindowName("Accessibility");
		mainWindowManager.mainWindowController.showNewWindow(basePane);
	}

}
