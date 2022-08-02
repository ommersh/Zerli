package userGuiManagment;

import customerService.NewComplaint;
import customerService.ShowAllComplaints;
import customerService.UpdateComplaint;
import main.GuiObjectsFactory;
import surveyGui.ShowChoosenSurvey;
import surveyGui.SurveyResults;
import usersManagment.CustomerServiceEmployeeBoundary;

/**
 * Singleton, manage all the different Customer Service employee windows, for
 * each window controller when trying to get the controller, load the fxml if
 * not already loaded
 * 
 * @author halel
 *
 */
public class CustomerServiceGuiManager implements IUserGuiManager {

	private static CustomerServiceGuiManager customerServiceGuiManager;

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	// the controllers
	private NewComplaint newComplaintController;
	private UpdateComplaint updateComplaint;
	private ShowAllComplaints showAllComplaints;
	private SurveyResults surveyResultsController;
	private ShowChoosenSurvey showChoosenSurvey;
	// the boundaries
	private CustomerServiceEmployeeBoundary employeeServiceBoundary;

	private CustomerServiceGuiManager() {

	}

	public static CustomerServiceGuiManager getInstance() {
		if (customerServiceGuiManager == null)
			customerServiceGuiManager = new CustomerServiceGuiManager();
		return customerServiceGuiManager;
	}

	public ShowAllComplaints getShowAllComplaints() {
		if (showAllComplaints == null) {
			showAllComplaints = (ShowAllComplaints) guiObjectsFactory
					.loadFxmlFile("/customerService/ShowAllComplaints.fxml");
		}
		return showAllComplaints;
	}

	public NewComplaint getNewComplaintController() {
		if (newComplaintController == null) {
			newComplaintController = (NewComplaint) guiObjectsFactory
					.loadFxmlFile("/customerService/NewComplaint.fxml");

		}
		return newComplaintController;
	}

	public UpdateComplaint getUpdateComplaint() {
		if (updateComplaint == null) {
			updateComplaint = (UpdateComplaint) guiObjectsFactory.loadFxmlFile("/customerService/UpdateComplaint.fxml");

		}
		return updateComplaint;
	}

	public SurveyResults getSurveyResultsController() {
		if (surveyResultsController == null) {
			surveyResultsController = (SurveyResults) guiObjectsFactory.loadFxmlFile("/surveyGui/SurveyResults.fxml");
		}
		return surveyResultsController;
	}

	public ShowChoosenSurvey getShowChoosenSurvey() {
		if (showChoosenSurvey == null) {
			showChoosenSurvey = (ShowChoosenSurvey) guiObjectsFactory.loadFxmlFile("/surveyGui/ChoosenSurvey.fxml");
		}
		return showChoosenSurvey;
	}

	public CustomerServiceEmployeeBoundary getEmployeeServiceBoundary() {
		if (employeeServiceBoundary == null) {
			employeeServiceBoundary = new CustomerServiceEmployeeBoundary();
		}
		return employeeServiceBoundary;
	}

	@Override
	public void logout() {
		newComplaintController = null;
		updateComplaint = null;
		surveyResultsController = null;
		showChoosenSurvey = null;
		employeeServiceBoundary = null;
		showAllComplaints = null;
	}

}
