package branchManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import user.User;
import userGuiManagment.BranchManagerGuiManager;
import userGuiManagment.MainWindowGuiManager;
import usersManagment.BranchManagerBoundary;

/**
 * controller for the approve customer window
 *
 * 
 */
public class ApproveCustomerController implements IGuiController {
	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private BranchManagerGuiManager branchManagerGuiManager = BranchManagerGuiManager.getInstance();
	private BranchManagerBoundary boundary = branchManagerGuiManager.getBranchManagerBoundary();
	private User selectedCustomer = null;

	@FXML
	private AnchorPane basePane;

	@FXML
	private Button approveBtn;

	@FXML
	private Button backBtn;

	@FXML
	private TextField caedNumber;

	@FXML
	private TextField cardMonth;

	@FXML
	private TextField cardYear;

	@FXML
	private Label errorLabel;

	@FXML
	private TextField userEmailInfo;

	@FXML
	private TextField userFirstNameInfo;

	@FXML
	private TextField userID;

	@FXML
	private TextField userLastNameInfo;

	@FXML
	private TextField userPhoneInfo;

	@FXML
	private TextField userUsername;

	@FXML
	void approve(ActionEvent event) {
		String temp;
		temp = null;
		temp = userFirstNameInfo.getText();
		if (temp == null || temp.equals("")) {
			errorLabel.setText("Please enter first name");
			return;
		}
		selectedCustomer.setFirstName(temp);
		temp = userLastNameInfo.getText();
		if (temp == null || temp.equals("")) {
			errorLabel.setText("Please enter last name");
			return;
		}
		selectedCustomer.setLastName(temp);
		temp = userPhoneInfo.getText();
		if (temp == null || temp.equals("")) {
			errorLabel.setText("Please enter phone number");
			return;
		}
		selectedCustomer.setPhoneNumber(temp);
		temp = userID.getText();
		if (temp == null || temp.equals("")) {
			errorLabel.setText("Please enter ID");
			return;
		}
		selectedCustomer.setPersonID(temp);
		temp = userEmailInfo.getText();
		if (temp == null || temp.equals("")) {
			errorLabel.setText("Please enter email");
			return;
		}
		selectedCustomer.setEmail(temp);
		// get the card info
		String cardIinfo = "";
		temp = caedNumber.getText();
		if (temp == null || temp.equals("")) {
			errorLabel.setText("Please enter cardNumber");
			return;
		}
		cardIinfo += temp + " ";
		temp = cardYear.getText();
		if (temp == null || temp.equals("")) {
			errorLabel.setText("Please enter Expiration year ");
			return;
		}
		int num = 0;
		try {
			num = Integer.valueOf(temp);
		} catch (Exception e) {
			errorLabel.setText("Expiration year must be numbers");
			return;
		}
		if (num < 0 || num >= 100) {
			errorLabel.setText("Wrong expiration year");
			return;
		}
		cardIinfo += temp + "/";
		temp = cardMonth.getText();
		if (temp == null || temp.equals("")) {
			errorLabel.setText("Please enter Expiration month ");
			return;
		}
		num = 0;
		try {
			num = Integer.valueOf(temp);
		} catch (Exception e) {
			errorLabel.setText("Expiration month must be numbers");
			return;
		}
		if (num < 0 || num > 12) {
			errorLabel.setText("Wrong expiration month");
			return;
		}
		cardIinfo += temp;

		// got all the info
		try {
			boundary.approveCustomer(selectedCustomer, cardIinfo);
			errorLabel.setText("Customer approved!");
		} catch (Exception e) {
			errorLabel.setText(e.getMessage());
		}
	}

	@FXML
	void back(ActionEvent event) {
		branchManagerGuiManager.getManagerUsersManagmetController().openWindow();
	}

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {
	}

	@Override
	public void openWindow() {
		if (selectedCustomer == null)
			return;
		mainWindowManager.mainWindowController.changeWindowName(
				"Manage Customer - " + selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName());
		mainWindowManager.mainWindowController.showNewWindow(basePane);
		errorLabel.setText("");
		userFirstNameInfo.setText(selectedCustomer.getFirstName());
		userLastNameInfo.setText(selectedCustomer.getLastName());
		userEmailInfo.setText(selectedCustomer.getEmail());
		userID.setText(selectedCustomer.getPersonID());
		userPhoneInfo.setText(selectedCustomer.getPhoneNumber());
		userUsername.setText(selectedCustomer.getUsername());
		userUsername.setEditable(false);
	}

	public void setCustomer(User customer) {
		selectedCustomer = customer;
	}
}
