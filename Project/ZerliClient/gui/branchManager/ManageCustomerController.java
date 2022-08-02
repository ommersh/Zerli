package branchManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import user.User;
import user.UserStatus;
import userGuiManagment.BranchManagerGuiManager;
import userGuiManagment.MainWindowGuiManager;
import usersManagment.BranchManagerBoundary;

/**
 * controller for managing the customer user information
 * 
 */
public class ManageCustomerController implements IGuiController {
	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private BranchManagerGuiManager branchManagerGuiManager = BranchManagerGuiManager.getInstance();
	private BranchManagerBoundary boundary = branchManagerGuiManager.getBranchManagerBoundary();
	private User selectedCustomer = null;
	private boolean addCard = false;

	@FXML
	private Button approveBtn;

	@FXML
	private Button backBtn;

	@FXML
	private AnchorPane basePane;

	@FXML
	private TextField caedNumber;

	@FXML
	private TextField cardMonth;

	@FXML
	private TextField cardYear;

	@FXML
	private Label cardinfo1;

	@FXML
	private Label cardinfo2;

	@FXML
	private Label cardinfo3;

	@FXML
	private Label errorLabel;

	@FXML
	private ComboBox<String> status;

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
	private RadioButton newCard;

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
		// get the customer information

		temp = status.getSelectionModel().getSelectedItem();
		if (temp == null || temp.equals("")) {
			temp = selectedCustomer.getStatus().toString();
		}
		selectedCustomer.setStatus(UserStatus.valueOf(temp));

		if (addCard) {
			// try to save the card info
			if (!getAndSaveCardInfo())
				return;
		}

		// got all the info, update the customer info
		try {
			boundary.requestUpdateUserData(selectedCustomer);
			errorLabel.setText("Customer data updated!");
		} catch (Exception e) {
			errorLabel.setText(e.getMessage());
		}
	}

	@FXML
	void back(ActionEvent event) {
		branchManagerGuiManager.getManagerUsersManagmetController().openWindow();
	}

	@FXML
	void addNewCard(ActionEvent event) {
		if (newCard.isSelected()) {
			showCardInfo(true);
			addCard = true;
		} else {
			showCardInfo(false);
			addCard = false;
		}
	}

	@Override
	public void openWindow() {
		if (selectedCustomer == null)
			return;
		initComboBoxes();
		mainWindowManager.mainWindowController.changeWindowName(
				"Manage Employee - " + selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName());
		mainWindowManager.mainWindowController.showNewWindow(basePane);
		errorLabel.setText("");
		userFirstNameInfo.setText(selectedCustomer.getFirstName());
		userLastNameInfo.setText(selectedCustomer.getLastName());
		userEmailInfo.setText(selectedCustomer.getEmail());
		userID.setText(selectedCustomer.getPersonID());
		userPhoneInfo.setText(selectedCustomer.getPhoneNumber());
		userUsername.setText(selectedCustomer.getUsername());
		userUsername.setEditable(false);
		showCardInfo(false);

	}

	private void initComboBoxes() {
		String[] statusTypes = { "Active", "NotActive", "Frozen" };
		status.getItems().setAll(statusTypes);
		status.promptTextProperty().set(selectedCustomer.getStatus().toString());
	}

	private void showCardInfo(boolean val) {
		cardinfo1.setVisible(val);
		cardinfo3.setVisible(val);
		cardinfo2.setVisible(val);
		caedNumber.setVisible(val);
		cardMonth.setVisible(val);
		cardYear.setVisible(val);
	}

	private boolean getAndSaveCardInfo() {
		String temp = "";
		// get the card info
		String cardIinfo = "";
		temp = caedNumber.getText();
		if (temp == null || temp.equals("")) {
			errorLabel.setText("Please enter cardNumber");
			return false;
		}
		cardIinfo += temp + " ";
		temp = cardYear.getText();
		if (temp == null || temp.equals("")) {
			errorLabel.setText("Please enter Expiration year ");
			return false;
		}
		int num = 0;
		try {
			num = Integer.valueOf(temp);
		} catch (Exception e) {
			errorLabel.setText("Expiration year must be numbers");
			return false;
		}
		if (num < 0 || num >= 100) {
			errorLabel.setText("Wrong expiration year");
			return false;
		}
		cardIinfo += temp + "/";
		temp = cardMonth.getText();
		if (temp == null || temp.equals("")) {
			errorLabel.setText("Please enter Expiration month ");
			return false;
		}
		num = 0;
		try {
			num = Integer.valueOf(temp);
		} catch (Exception e) {
			errorLabel.setText("Expiration month must be numbers");
			return false;
		}
		if (num < 0 || num > 12) {
			errorLabel.setText("Wrong expiration month");
			return false;
		}
		cardIinfo += temp;
		try {
			boundary.addCard(selectedCustomer, cardIinfo);
			return true;
		} catch (Exception e) {
			errorLabel.setText(e.getMessage());
			return false;
		}
	}

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {

	}

	public void setCustomer(User customer) {
		selectedCustomer = customer;
	}

}
