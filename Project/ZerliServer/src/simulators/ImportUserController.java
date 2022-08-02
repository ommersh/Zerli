package simulators;

import java.util.ArrayList;

import database.DBController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * simple gui controller to simulate importing user's data
 * 
 * @author halel
 *
 */
public class ImportUserController {

	@FXML
	private Button addUserBtn;

	@FXML
	private TextField email;

	@FXML
	private TextField firstName;

	@FXML
	private TextField id;

	@FXML
	private TextField lastName;

	@FXML
	private Label msgLabel;

	@FXML
	private TextField password;

	@FXML
	private TextField phoneNumber;

	@FXML
	private ComboBox<String> type;

	@FXML
	private TextField userName;
	@FXML
	private ComboBox<String> branch;

	private Stage stage;

	/**
	 * get all the fields and "import" the user
	 * 
	 * @param event
	 */
	@FXML
	void addUser(ActionEvent event) {
		ImportedUserData data = new ImportedUserData();
		String temp = null;
		temp = userName.getText();
		if (temp == null || temp == "") {
			msgLabel.setText("please enter user name");
			return;
		}
		if (temp.length() < 4) {
			msgLabel.setText("username must be atleast 4 characters");
			return;
		}
		data.setUserName(temp);
		temp = password.getText();
		if (temp == null || temp == "") {
			msgLabel.setText("please enter password");
			return;
		}
		if (temp.length() < 6) {
			msgLabel.setText("password must be atleast 6 characters");
			return;
		}
		data.setPassword(temp);
		temp = id.getText();
		if (temp == null || temp == "") {
			msgLabel.setText("please enter id");
			return;
		}
		data.setId(temp);
		temp = phoneNumber.getText();
		if (temp == null || temp == "") {
			msgLabel.setText("please enter phoneNumber");
			return;
		}
		data.setPhone(temp);
		temp = email.getText();
		if (temp == null || temp == "") {
			msgLabel.setText("please enter email");
			return;
		}
		data.setEmail(temp);
		temp = firstName.getText();
		if (temp == null || temp == "") {
			msgLabel.setText("please enter first name");
			return;
		}
		data.setFirstname(temp);
		temp = lastName.getText();
		if (temp == null || temp == "") {
			msgLabel.setText("please enter last name");
			return;
		}
		data.setLastname(temp);
		temp = type.getSelectionModel().getSelectedItem();
		if (temp == null || temp == "") {
			msgLabel.setText("please select user type");
			return;
		}
		data.setType(temp);
		if (temp.equals("BranchManager") || temp.equals("BranchEmployee")) {
			temp = branch.getSelectionModel().getSelectedItem();
			if (temp == null) {
				msgLabel.setText("please select branch");
				return;
			}
			data.setBranch(temp);
		} else {
			data.setBranch("");
		}

		if (DBController.getInstance().createNewUser(data)) {
			data = null;
			msgLabel.setText("User imported successfully");
		} else {
			msgLabel.setText("failed to imported user");
		}
	}

	@FXML
	void checkType(ActionEvent event) {
		String temp = type.getSelectionModel().getSelectedItem();
		if (temp == null) {
			return;
		}
		if (temp.equals("BranchManager") || temp.equals("BranchEmployee")) {
			// get branch
			ArrayList<String> branchList = DBController.getInstance().getAllBranches();
			branch.getItems().setAll(branchList);
			branch.setDisable(false);
		} else {
			branch.setDisable(true);
		}
	}

	public ImportUserController() {

	}

	/**
	 * init the controller
	 * 
	 * @param stage
	 */
	public void init(Stage stage) {
		this.stage = stage;
		stage.setOnCloseRequest(event -> {
			closeWindow();
		});
		String[] s = { "NonAuthorizedCustomer", "BranchManager", "BranchEmployee", "CustomerServiceEmloyee",
				"MarketingEmployee", "Courier", "CEO" };
		type.getItems().setAll(s);
		branch.setDisable(true);
	}

	private void closeWindow() {
		stage.close();
		ServerSimulatorsManager.getInstance().simStage.show();
	}
}
