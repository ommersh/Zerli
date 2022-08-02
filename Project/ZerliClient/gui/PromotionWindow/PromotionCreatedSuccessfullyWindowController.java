package PromotionWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import userGuiManagment.MainWindowGuiManager;

/**
 * controller for the window: the promotion created successfully window
 *
 */
public class PromotionCreatedSuccessfullyWindowController implements IGuiController {

	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	@FXML
	private AnchorPane basePane;

	@FXML
	private ImageView Image;

	@FXML
	private Label TextUnderImage;

	@FXML
	private Button FinishButton;

	@FXML
	void FinishPressed(ActionEvent event) {
		mainWindowManager.userHomeWindowController.openWindow();

	}

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {
		// nothing to reset
	}

	@Override
	public void openWindow() {
		mainWindowManager.mainWindowController.showNewWindow(basePane);
		mainWindowManager.mainWindowController.changeWindowName("PromotionSuccess");

	}

}
