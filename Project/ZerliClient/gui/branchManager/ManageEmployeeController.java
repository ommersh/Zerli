package branchManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import user.User;
import user.UserStatus;
import user.UserType;
import userGuiManagment.BranchManagerGuiManager;
import userGuiManagment.MainWindowGuiManager;
import usersManagment.BranchManagerBoundary;

/**
 * controller for the management of the employees users information window
 *
 */
public class ManageEmployeeController implements IGuiController {
	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private BranchManagerGuiManager branchManagerGuiManager = BranchManagerGuiManager.getInstance();
	private BranchManagerBoundary boundary = branchManagerGuiManager.getBranchManagerBoundary();
	private User selectedEmployee = null;

	@FXML
	private ComboBox<String> authorization;

	@FXML
	private Button backBtn;

	@FXML
	private AnchorPane basePane;

	@FXML
	private ComboBox<String> branch;

	@FXML
	private Label errorLabel;

	@FXML
	private ComboBox<String> status;

	@FXML
	private Button updateBtn;

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
	void update(ActionEvent event) {

	}

	@FXML
	void approve(ActionEvent event) {
		String temp;
		temp = null;
		temp = userFirstNameInfo.getText();
		if (temp == null || temp.equals("")) {
			errorLabel.setText("Please enter first name");
			return;
		}
		selectedEmployee.setFirstName(temp);
		temp = userLastNameInfo.getText();
		if (temp == null || temp.equals("")) {
			errorLabel.setText("Please enter last name");
			return;
		}
		selectedEmployee.setLastName(temp);
		temp = userPhoneInfo.getText();
		if (temp == null || temp.equals("")) {
			errorLabel.setText("Please enter phone number");
			return;
		}
		selectedEmployee.setPhoneNumber(temp);
		temp = userID.getText();
		if (temp == null || temp.equals("")) {
			errorLabel.setText("Please enter ID");
			return;
		}
		selectedEmployee.setPersonID(temp);
		temp = userEmailInfo.getText();
		if (temp == null || temp.equals("")) {
			errorLabel.setText("Please enter email");
			return;
		}
		selectedEmployee.setEmail(temp);
		// get the employee information
		temp = authorization.getSelectionModel().getSelectedItem();
		if (temp == null || temp.equals("")) {
			temp = selectedEmployee.getUserType().toString();
		}
		selectedEmployee.setUserType(UserType.valueOf(temp));

		temp = status.getSelectionModel().getSelectedItem();
		if (temp == null || temp.equals("")) {
			temp = selectedEmployee.getStatus().toString();
		}
		selectedEmployee.setStatus(UserStatus.valueOf(temp));

		temp = branch.getSelectionModel().getSelectedItem();
		if (temp == null || temp.equals("")) {
			temp = selectedEmployee.getBranchName();
		}
		selectedEmployee.setBranchName(temp);

		// got all the info
		try {
			boundary.requestUpdateUserData(selectedEmployee);
			errorLabel.setText("Employee data updated!");
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
		if (selectedEmployee == null)
			return;
		initComboBoxes();
		mainWindowManager.mainWindowController.changeWindowName(
				"Manage Employee - " + selectedEmployee.getFirstName() + " " + selectedEmployee.getLastName());
		mainWindowManager.mainWindowController.showNewWindow(basePane);
		errorLabel.setText("");
		userFirstNameInfo.setText(selectedEmployee.getFirstName());
		userLastNameInfo.setText(selectedEmployee.getLastName());
		userEmailInfo.setText(selectedEmployee.getEmail());
		userID.setText(selectedEmployee.getPersonID());
		userPhoneInfo.setText(selectedEmployee.getPhoneNumber());
		userUsername.setText(selectedEmployee.getUsername());
		userUsername.setEditable(false);
	}

	private void initComboBoxes() {
		String[] authorizationString = { "BranchManager", "BranchEmployee", "CustomerServiceEmloyee",
				"CustomerServiceEmloyee", "CEO" };
		authorization.getItems().setAll(authorizationString);
		String[] statusTypes = { "Active", "NotActive" };
		status.getItems().setAll(statusTypes);
		branch.getItems().setAll(boundary.getBranches());
		authorization.promptTextProperty().set(selectedEmployee.getUserType().toString());
		status.promptTextProperty().set(selectedEmployee.getStatus().toString());
		branch.promptTextProperty().set(selectedEmployee.getBranchName());
	}

	public void setEmployee(User employee) {
		selectedEmployee = employee;
	}

}
