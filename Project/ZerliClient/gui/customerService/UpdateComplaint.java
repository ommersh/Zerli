package customerService;

import common.Status;
import complaint.Complaint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import userGuiManagment.CustomerServiceGuiManager;
import userGuiManagment.MainWindowGuiManager;
import usersManagment.CustomerServiceEmployeeBoundary;

/**
 * controller for the window: update complaint window
 *
 */
public class UpdateComplaint implements IGuiController {

	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private CustomerServiceEmployeeBoundary complaintBoundary = CustomerServiceGuiManager.getInstance()
			.getEmployeeServiceBoundary();
	@FXML
	private Label answerLabel;

	@FXML
	private TextField answerText;

	@FXML
	private Button backBtn;

	@FXML
	private AnchorPane basePane;

	@FXML
	private Label compensationLabel;

	@FXML
	private TextField compensationText;

	@FXML
	private Label msgLabel;

	@FXML
	private Button updateBtn;

	Complaint selectedcomplaint;

	@FXML
	void updateComplaint(ActionEvent event) {
		if (answerText.getText() == "" || compensationText.getText() == "") {
			msgLabel.setText("Please enter the details");

		} else {
			String answer = answerText.getText();
			double compensation = Double.valueOf(compensationText.getText());
			Status status = Status.Completed;

			try {
				complaintBoundary.handlingComplaints(selectedcomplaint, answer, compensation, status);
				msgLabel.setText("Complaint successfully update");

			} catch (Exception e) {
				msgLabel.setText("Complaint unsuccessfully update");

			}
		}
	}

	public void setSelectedComplaint(Complaint selectedComplaint) {
		selectedcomplaint = selectedComplaint;
	}

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {

		answerText.clear();
		compensationText.clear();
	}

	@Override
	public void openWindow() {
		// move to the next window
		mainWindowManager.mainWindowController.showNewWindow(basePane);
		// change to the name
		mainWindowManager.mainWindowController
				.changeWindowName("update Complaint - number #" + selectedcomplaint.getComplaintsNumber());

		msgLabel.setText("");
	}

	@FXML
	void BackToShowAllComplaintsWindow(ActionEvent event) {
		resetController();
		CustomerServiceGuiManager.getInstance().getShowAllComplaints().openWindow();
	}

}
