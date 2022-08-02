package branchManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;
import user.User;
import userGuiManagment.BranchManagerGuiManager;
import userGuiManagment.MainWindowGuiManager;
import usersManagment.BranchManagerBoundary;
import usersManagment.UserBoundary;

/**
 * controller for the window: user management window
 * 
 *
 */
public class ManagerUpdateUser implements IGuiController {
	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private BranchManagerGuiManager branchManagerGuiManager = BranchManagerGuiManager.getInstance();
	private BranchManagerBoundary boundary = branchManagerGuiManager.getBranchManagerBoundary();
	private User user = null;
	private boolean isAuthorized = false, isanAuthorized = false, isEmployees = false;

	@FXML
	private Label errorLabel;

	@FXML
	private AnchorPane infoPane;

	@FXML
	private AnchorPane managerUpdateUserPane;

	@FXML
	private Button selcetBtn;

	@FXML
	private Label typeLAbel;

	@FXML
	private Button viewAuthorizedBtn;

	@FXML
	private Button viewEmployeesBtn;

	@FXML
	private Button viewanAuthorizedBtn;

	@FXML
	private TableView<User> customersTable;

	@FXML
	private TableColumn<String, String> emailCol;

	@FXML
	private TableColumn<String, String> firstNameCol;

	@FXML
	private TableColumn<String, String> idCol;

	@FXML
	private TableColumn<String, String> lastNameCol;

	@FXML
	private TableColumn<String, String> phoneCol;

	private ObservableList<User> customers = FXCollections.observableArrayList();

	@FXML
	void viewAuthorized(ActionEvent event) {
		customers.clear();
		try {
			customers.setAll(boundary.getAllActiveCustomers());
		} catch (Exception e) {
			errorLabel.setText(e.getMessage());
			return;
		}
		isAuthorized = true;
		isanAuthorized = false;
		isEmployees = false;
		user = null;
		typeLAbel.setText("View authorized Customers");
		customersTable.setItems(customers);
	}

	@FXML
	void viewEmployees(ActionEvent event) {
		customers.clear();
		try {
			customers.setAll(boundary.getAllEmployees());
		} catch (Exception e) {
			errorLabel.setText(e.getMessage());
			return;
		}
		isAuthorized = false;
		isanAuthorized = false;
		isEmployees = true;
		user = null;
		typeLAbel.setText("View " + UserBoundary.CurrentUser.getBranchName() + " Employees");
		customersTable.setItems(customers);
	}

	@FXML
	void viewanAuthorized(ActionEvent event) {
		customers.clear();
		try {
			customers.setAll(boundary.getAllWaitingForApprovalCustomers());
		} catch (Exception e) {
			errorLabel.setText(e.getMessage());
			return;
		}
		isAuthorized = false;
		isanAuthorized = true;
		isEmployees = false;
		user = null;
		typeLAbel.setText("View Customers waiting for approval");
		errorLabel.setText("");
		customersTable.setItems(customers);
	}

	@FXML
	void viewSelected(ActionEvent event) {
		user = customersTable.getSelectionModel().getSelectedItem();
		if (user != null) {
			// change window
			if (isanAuthorized) {
				ApproveCustomerController controller = (ApproveCustomerController) guiObjectsFactory
						.loadFxmlFile("/branchManager/ApproveCustomer.fxml");
				controller.setCustomer(user);
				controller.openWindow();
			} else if (isAuthorized) {
				ManageCustomerController controller = (ManageCustomerController) guiObjectsFactory
						.loadFxmlFile("/branchManager/ManageCustomerWindow.fxml");
				controller.setCustomer(user);
				controller.openWindow();
			} else if (isEmployees) {
				ManageEmployeeController controller = (ManageEmployeeController) guiObjectsFactory
						.loadFxmlFile("/branchManager/ManageEmployeeWindow.fxml");
				controller.setEmployee(user);
				controller.openWindow();

			}

		}
	}

	@Override
	public Pane getBasePane() {
		return managerUpdateUserPane;
	}

	@Override
	public void resetController() {
		user = null;
	}

	@Override
	public void openWindow() {
		mainWindowManager.mainWindowController.changeWindowName("Manager - update user");
		mainWindowManager.mainWindowController.showNewWindow(managerUpdateUserPane);
		typeLAbel.setText("");
		initializeTable();
	}

	private void initializeTable() {
		customersTable.getItems().clear();
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		idCol.setCellValueFactory(new PropertyValueFactory<>("personID"));
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
	}

}