package courier;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import main.IGuiController;
import userGuiManagment.CourierGuiManager;
import userGuiManagment.MainWindowGuiManager;
import usersManagment.CourierBoundary;

/**
 * controller for the approve delivery window, can place order number and
 * approve the delivery, get completed msg on success and relevant error msg on
 * error
 * 
 * @author halel
 *
 */
public class CourierControllerGUI implements IGuiController {
	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private CourierBoundary courierBoundary = CourierGuiManager.getInstance().getCourierBoundary();
	private int orderNumber;

	/**
	 * the base for this window
	 */
	@FXML
	private Pane DeliveryPane;

	@FXML
	private Button PrevPageButton;

	@FXML
	private Button confirmDeilveryButton;

	@FXML
	private Label erorrLabel;

	@FXML
	private TextField orderNum_txt;

	@FXML
	void ConfirmDelivery(ActionEvent event) {
		String putInOrderNumber = orderNum_txt.getText();
		if (!putInOrderNumber.matches("[0-9]+")) {
			setError("Order number must contain numbers only!");
			return;
		} else {
			orderNumber = Integer.valueOf(putInOrderNumber);
		}
		try {
			courierBoundary.requestConfirmDelivery(orderNumber);
		} catch (Exception e) {
			setError(e.getMessage());
			return;
		}
		setError("Delivery confirmed successfully");
		orderNumber = -1;
	}

	@FXML
	void backToMainPage(ActionEvent event) {
		mainWindowManager.userHomeWindowController.openWindow();
	}

	@FXML
	void enterOrderNumber(ActionEvent event) {
		String putInOrderNumber = orderNum_txt.getText();
		if (!putInOrderNumber.matches("[0-9]+")) {
			setError("Order number must contain numbers only!");
		} else {
			orderNumber = Integer.valueOf(putInOrderNumber);
		}

	}

	@Override
	public Pane getBasePane() {
		return DeliveryPane;
	}

	@Override
	public void resetController() {
		orderNum_txt.setText("");

	}

	@Override
	public void openWindow() {
		mainWindowManager.mainWindowController.showNewWindow(DeliveryPane);
		mainWindowManager.mainWindowController.changeWindowName("Confirm Deilvery");
		orderNum_txt.setText("");
		setError("");
	}

	private void setError(String errorString) {
		erorrLabel.setText(errorString);

	}

}
