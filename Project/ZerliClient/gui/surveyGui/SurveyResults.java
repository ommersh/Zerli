package surveyGui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import survey.Survey;
import userGuiManagment.CustomerServiceGuiManager;
import userGuiManagment.MainWindowGuiManager;
import usersManagment.CustomerServiceEmployeeBoundary;

/**
 * controller for the window: add survey result window
 *
 */
public class SurveyResults implements IGuiController {

	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private CustomerServiceGuiManager customerServiceGuiManager = CustomerServiceGuiManager.getInstance();
	private CustomerServiceEmployeeBoundary surveyBoundary = customerServiceGuiManager.getEmployeeServiceBoundary();

	@FXML
	private AnchorPane showSurveyBasePane;

	@FXML
	private TableView<Survey> surveysTable;

	@FXML
	private Button viewSurvrey;

	@FXML
	private TableColumn<Survey, Integer> surveyNumberlCol;

	@FXML
	private TableColumn<Survey, Integer> numberOfParticipantsCol;

	ObservableList<Survey> surveysObs = FXCollections.observableArrayList();
	Survey selectedsurvey;

	public void initializeSurveysTable() {
		surveysObs.clear();
		surveysTable.getItems().clear();
		surveyNumberlCol.setCellValueFactory(new PropertyValueFactory<>("surveyNumber"));
		numberOfParticipantsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfParticipants"));
	}

	@Override
	public void openWindow() {
		initializeSurveysTable();
		try {
			surveysObs.setAll(surveyBoundary.getAllSurvey());
			surveysTable.setItems(surveysObs);
		} catch (Exception e) {
			//
		}
		mainWindowManager.mainWindowController.changeWindowName("Surveys");
		mainWindowManager.mainWindowController.showNewWindow(showSurveyBasePane);
	}

	@Override
	public Pane getBasePane() {
		return showSurveyBasePane;
	}

	@Override
	public void resetController() {
		surveysObs.clear();
		surveysTable.getItems().clear();
		selectedsurvey = null;
	}

	@FXML
	void selectSurvey(ActionEvent event) {
		selectedsurvey = surveysTable.getSelectionModel().getSelectedItem();
		if (selectedsurvey != null) {
			mainWindowManager.mainWindowController
					.changeWindowName("Survey details - survey #" + selectedsurvey.getSurveyNumber());
			customerServiceGuiManager.getShowChoosenSurvey().setSelectedSurvey(selectedsurvey);
			customerServiceGuiManager.getShowChoosenSurvey().openWindow();
		}
	}
}