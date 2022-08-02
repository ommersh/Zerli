package surveyGui;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import main.ClientUI;
import main.IGuiController;
import survey.Survey;
import userGuiManagment.CustomerServiceGuiManager;
import userGuiManagment.MainWindowGuiManager;
import usersManagment.CustomerServiceEmployeeBoundary;

/**
 * controller for the window: show the survey window
 *
 */
public class ShowChoosenSurvey implements IGuiController {

	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private CustomerServiceGuiManager customerServiceGuiManager = CustomerServiceGuiManager.getInstance();
	private CustomerServiceEmployeeBoundary surveyBoundary = customerServiceGuiManager.getEmployeeServiceBoundary();

	@FXML
	private AnchorPane SurveyBasePane;

	@FXML
	private Button saveFileBtn;

	@FXML
	private Button showConclusionsBtn;

	@FXML
	private Button backBtn;

	@FXML
	private TextField pathText;

	@FXML
	private Label resultLabel;

	@FXML
	private Label msgLabel;

	private Survey selectedSurvey;

	public void setSelectedSurvey(Survey selectedsurvey) {
		selectedSurvey = selectedsurvey;
	}

	@Override
	public void openWindow() {
		msgLabel.setText("");
		String resultString;
		if (selectedSurvey.getResultFile() == null)
			showConclusionsBtn.setDisable(true);
		else
			showConclusionsBtn.setDisable(false);
		resultString = selectedSurvey.getResult();
		resultLabel.setText(resultString);
		// guiObjectsFactory.mainWindowController.changeWindowName("Survey details");
		mainWindowManager.mainWindowController.showNewWindow(SurveyBasePane);
	}

	@FXML
	void saveFile(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.setSelectedExtensionFilter(new ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
		File f = fc.showOpenDialog(ClientUI.globalstage);
		if (f == null)
			return;
		String path = f.getAbsolutePath();
		if(path.endsWith(".pdf")) {
			try {
				surveyBoundary.enterSurveyResult(selectedSurvey.getSurveyNumber(), path);
				msgLabel.setText("Result file saved !");
			} catch (Exception e) {
				msgLabel.setText("Failed " + e.getMessage());
			}
		}else {
			msgLabel.setText("File has wrong format !");
		}
	}

	@FXML
	public void conclusions() {
		FileChooser fc = new FileChooser();
		String[] exts = new String[1];
		exts[0] = ".pdf";
		fc.getExtensionFilters().setAll(new ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
		File f = fc.showSaveDialog(ClientUI.globalstage);
		if (f == null)
			return;
		String path = f.getAbsolutePath();
		try {
			surveyBoundary.saveSurveyResultToLocalFile(selectedSurvey.getResultFile(), path);
			msgLabel.setText("Result file saved !");
		} catch (Exception e) {
			msgLabel.setText("Failed to save the file");
		}
	}

	@FXML
	void goBack(ActionEvent event) {
		customerServiceGuiManager.getSurveyResultsController().openWindow();
	}

	@Override
	public Pane getBasePane() {
		return SurveyBasePane;
	}

	@Override
	public void resetController() {
		resultLabel.setText("");
		msgLabel.setText("");

	}
}