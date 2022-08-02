package ordersPayment;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import userGuiManagment.MainWindowGuiManager;

/**
 * controller for the window: failed to pay message window
 *
 */
public class failedtopayWindowController implements IGuiController {

	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();

	@FXML
	private AnchorPane basepane;

	@FXML
	private ImageView image;

	@FXML
	private Label paymentfailed;

	@FXML
	private Label sentenceFailed;

	@Override
	public Pane getBasePane() {
		return basepane;
	}

	@Override
	public void resetController() {
		// TODO Auto-generated method stub

	}

	@Override
	public void openWindow() {
		mainWindowManager.mainWindowController.showNewWindow(basepane);
		mainWindowManager.mainWindowController.changeWindowName("payment not approved");

	}

}
