package branchEmployee;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import main.IGuiController;
import survey.Survey;
import userGuiManagment.BranchEmployeeGuiManager;
import userGuiManagment.MainWindowGuiManager;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;

/**
 * controller for the entering the survey answers window
 *
 */
public class SurveyControllerGUI implements IGuiController {
	private BranchEmployeeGuiManager branchEmployeeGuiManager = BranchEmployeeGuiManager.getInstance();

	private int[] tempSurveyResult = new int[6];

	@FXML
	private Button SaveButton;

	@FXML
	private ComboBox<Integer> comboBoxQ1 = new ComboBox<Integer>();

	@FXML
	private ComboBox<Integer> comboBoxQ2 = new ComboBox<Integer>();

	@FXML
	private ComboBox<Integer> comboBoxQ3 = new ComboBox<Integer>();

	@FXML
	private ComboBox<Integer> comboBoxQ4 = new ComboBox<Integer>();

	@FXML
	private ComboBox<Integer> comboBoxQ5 = new ComboBox<Integer>();

	@FXML
	private ComboBox<Integer> comboBoxQ6 = new ComboBox<Integer>();

	@FXML
	private Label errorLable;

	@FXML
	private TextArea q1_txt;

	@FXML
	private TextArea q2_txt;

	@FXML
	private TextArea q3_txt;

	@FXML
	private TextArea q4_txt;

	@FXML
	private TextArea q5_txt;

	@FXML
	private TextArea q6_txt;

	@FXML
	private Label surveyLable;

	@FXML
	private Pane surveyPane;

	@FXML
	private Button prevPageButton;

	private String[] questions;
	private int surveyNumber;

	public void setComboBoxValues() {
		comboBoxQ1.getItems().addAll(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
		comboBoxQ2.getItems().addAll(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
		comboBoxQ3.getItems().addAll(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
		comboBoxQ4.getItems().addAll(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
		comboBoxQ5.getItems().addAll(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
		comboBoxQ6.getItems().addAll(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
	}

	@Override
	public Pane getBasePane() {
		return surveyPane;
	}

	@Override
	public void resetController() {

	}

	@Override
	public void openWindow() {
		setComboBoxValues();
		MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
		mainWindowManager.mainWindowController.showNewWindow(surveyPane);
		mainWindowManager.mainWindowController.changeWindowName("Filling out a survey");

	}

	public void setSelectedSurvey(Survey selectedSurvey) {

		surveyNumber = selectedSurvey.getSurveyNumber();
		surveyLable.setText("Survey " + surveyNumber);
		questions = selectedSurvey.getQuestions();
		q1_txt.setText(questions[0]);
		q2_txt.setText(questions[1]);
		q3_txt.setText(questions[2]);
		q4_txt.setText(questions[3]);
		q5_txt.setText(questions[4]);
		q6_txt.setText(questions[5]);
	}

	@FXML
	void saveSurvey(ActionEvent event) {
		try {
			tempSurveyResult[0] = comboBoxQ1.getValue();
			tempSurveyResult[1] = comboBoxQ2.getValue();
			tempSurveyResult[2] = comboBoxQ3.getValue();
			tempSurveyResult[3] = comboBoxQ4.getValue();
			tempSurveyResult[4] = comboBoxQ5.getValue();
			tempSurveyResult[5] = comboBoxQ6.getValue();
		} catch (Exception e) {
			setError("Please answer all the questions!");
			return;
		}
		try {
			branchEmployeeGuiManager.getBranchEmployeeBoundary().enterSurveyAnswers(tempSurveyResult, surveyNumber);
			setError("Servey answers saved");
		} catch (Exception e) {
			setError(e.getMessage());
		}
	}

	private void setError(String errorString) {
		errorLable.setText(errorString);

	}

	@FXML
	void goBack(ActionEvent event) {
		branchEmployeeGuiManager.getSearchSurvey().resetController();
		branchEmployeeGuiManager.getSearchSurvey().openWindow();
	}

}