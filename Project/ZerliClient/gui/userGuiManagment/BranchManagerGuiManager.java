package userGuiManagment;

import branchManager.ManagerApproveController;
import branchManager.ManagerUpdateUser;
import branchManager.ManagerWatchReportController;
import main.GuiObjectsFactory;
import usersManagment.BranchManagerBoundary;

/**
 * Singleton, manage all the different branch manager windows, for each window
 * controller when trying to get the controller, load the fxml if not already
 * loaded
 * 
 * @author halel
 *
 */
public class BranchManagerGuiManager implements IUserGuiManager {

	private static BranchManagerGuiManager branchManagerGuiManager;

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	// the controllers
	private ManagerApproveController managerApproveController;
	private ManagerWatchReportController managerWatchReportController;
	private ManagerUpdateUser managerUsersManagmetController;
	// Boundaries
	private BranchManagerBoundary branchManagerBoundary;

	private BranchManagerGuiManager() {

	}

	public static BranchManagerGuiManager getInstance() {
		if (branchManagerGuiManager == null)
			branchManagerGuiManager = new BranchManagerGuiManager();
		return branchManagerGuiManager;
	}

	public ManagerApproveController getManagerApproveController() {
		if (managerApproveController == null) {
			managerApproveController = (ManagerApproveController) guiObjectsFactory
					.loadFxmlFile("/branchManager/approveOrder.fxml");

		}
		return managerApproveController;
	}

	public ManagerWatchReportController getManagerWatchReportController() {
		if (managerWatchReportController == null) {
			managerWatchReportController = (ManagerWatchReportController) guiObjectsFactory
					.loadFxmlFile("/branchManager/managerWatchReport.fxml");
		}
		return managerWatchReportController;
	}

	public ManagerUpdateUser getManagerUsersManagmetController() {
		if (managerUsersManagmetController == null) {
			managerUsersManagmetController = (ManagerUpdateUser) guiObjectsFactory
					.loadFxmlFile("/branchManager/userInfoUpdate.fxml");

		}
		return managerUsersManagmetController;
	}

	public BranchManagerBoundary getBranchManagerBoundary() {
		if (branchManagerBoundary == null) {
			branchManagerBoundary = new BranchManagerBoundary();
		}
		return branchManagerBoundary;
	}

	@Override
	public void logout() {
		managerApproveController = null;
		managerWatchReportController = null;
		managerUsersManagmetController = null;
		branchManagerBoundary = null;
	}

}
