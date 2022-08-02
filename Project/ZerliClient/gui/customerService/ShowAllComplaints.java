package customerService;

import java.sql.Timestamp;

import common.Status;
import complaint.Complaint;
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
import main.IGuiController;
import userGuiManagment.CustomerServiceGuiManager;
import userGuiManagment.MainWindowGuiManager;
import usersManagment.CustomerServiceEmployeeBoundary;

/**
 * controller for the window: show all the complaints window
 *
 */
public class ShowAllComplaints implements IGuiController {
	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private CustomerServiceEmployeeBoundary complaintBoundary = CustomerServiceGuiManager.getInstance()
			.getEmployeeServiceBoundary();
	private UpdateComplaint updateComplaint = CustomerServiceGuiManager.getInstance().getUpdateComplaint();

	@FXML
	private AnchorPane showAllComplaintPane;

	@FXML
	private TableView<Complaint> ComplaintTable;

	@FXML
	private Button updateBot;

	@FXML
	private Label complaintLabel;

	@FXML
	private Label msgLabel;

	@FXML
	private TableColumn<Complaint, Integer> complaintsNumberCol;

	@FXML
	private TableColumn<Complaint, Integer> responsibleEmployeeUserNameCol;

	@FXML
	private TableColumn<Complaint, String> complaintCol;

	@FXML
	private TableColumn<Complaint, Status> statusCol;

	@FXML
	private TableColumn<Complaint, String> customerUserNameCol;

	@FXML
	private TableColumn<Complaint, Timestamp> creationTimeCol;

	ObservableList<Complaint> complaintObs = FXCollections.observableArrayList();
	Complaint selectedComplaint;;

	public void initializeComplaintsTable() {
		complaintObs.clear();
		ComplaintTable.getItems().clear();
		complaintsNumberCol.setCellValueFactory(new PropertyValueFactory<>("complaintsNumber"));
		responsibleEmployeeUserNameCol.setCellValueFactory(new PropertyValueFactory<>("responsibleEmployeeUserName"));
		complaintCol.setCellValueFactory(new PropertyValueFactory<>("complaint"));
		statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		customerUserNameCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		creationTimeCol.setCellValueFactory(new PropertyValueFactory<>("creationTime"));
	}

	@Override
	public void openWindow() {
		initializeComplaintsTable();
		complaintObs.addAll(complaintBoundary.getMyComplaints());
		ComplaintTable.setItems(complaintObs);
		mainWindowManager.mainWindowController.showNewWindow(showAllComplaintPane);
	}

	@Override
	public Pane getBasePane() {
		return showAllComplaintPane;
	}

	@Override
	public void resetController() {
		complaintObs.clear();
		ComplaintTable.getItems().clear();
		msgLabel.setText("");
	}

	@FXML
	void GoToUpdateComplaintWindow(ActionEvent event) {
		selectedComplaint = ComplaintTable.getSelectionModel().getSelectedItem();
		if (selectedComplaint != null) {
			if (selectedComplaint.getStatus().equals(Status.Completed)) {
				msgLabel.setText("Complaint completed");
			} else {
				mainWindowManager.mainWindowController.changeWindowName("update complaint");
				updateComplaint.setSelectedComplaint(selectedComplaint);
				updateComplaint.openWindow();
			}
		}

	}
}