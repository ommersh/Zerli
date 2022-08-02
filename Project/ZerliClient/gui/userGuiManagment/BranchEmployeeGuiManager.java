package userGuiManagment;

import branchEmployee.SearchSurverControllerGUI;
import branchEmployee.SurveyControllerGUI;
import main.GuiObjectsFactory;
import usersManagment.BranchEmployeeBoundary;

/**
 * Singleton, manage all the different branch employee windows, for each window
 * controller when trying to get the controller, load the fxml if not already
 * loaded
 * 
 * @author halel
 *
 */
public class BranchEmployeeGuiManager implements IUserGuiManager {

	private static BranchEmployeeGuiManager branchEmployeeGuiManager;

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	// the gui controllers
	private SearchSurverControllerGUI searchSurvey;
	private SurveyControllerGUI showSurvey;
	// boundaries
	private BranchEmployeeBoundary branchEmployeeBoundary;

	private BranchEmployeeGuiManager() {

	}

	public static BranchEmployeeGuiManager getInstance() {
		if (branchEmployeeGuiManager == null)
			branchEmployeeGuiManager = new BranchEmployeeGuiManager();
		return branchEmployeeGuiManager;
	}

	public SearchSurverControllerGUI getSearchSurvey() {
		if (searchSurvey == null) {
			searchSurvey = (SearchSurverControllerGUI) guiObjectsFactory
					.loadFxmlFile("/branchEmployee/BranchEmpolyeeSearchSurvey.fxml");
		}
		return searchSurvey;
	}

	public SurveyControllerGUI getShowSurvey() {
		if (showSurvey == null) {
			showSurvey = (SurveyControllerGUI) guiObjectsFactory
					.loadFxmlFile("/branchEmployee/BranchEmployeeSurveyWindow.fxml");
		}
		return showSurvey;
	}

	public BranchEmployeeBoundary getBranchEmployeeBoundary() {
		if (branchEmployeeBoundary == null) {
			branchEmployeeBoundary = new BranchEmployeeBoundary();
		}
		return branchEmployeeBoundary;
	}

	@Override
	public void logout() {
		searchSurvey = null;
		showSurvey = null;
		branchEmployeeBoundary = null;

	}

}
