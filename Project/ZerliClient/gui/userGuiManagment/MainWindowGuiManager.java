package userGuiManagment;

import accessibility.AccessibilityPageController;
import buttons.BtnController;
import buttons.BtnMenuManager;
import main.GuiObjectsFactory;
import mainWindow.LoginGuiController;
import mainWindow.MainWindowController;
import mainWindow.StartingWindowGuiController;
import usersHomeWindows.UserHomeWindowGuiController;
import usersManagment.UserBoundary;

/**
 * Manage the main window, and the global windows(login, accessibility...), init
 * all the windows on system start
 * 
 * @author halel
 *
 */
public class MainWindowGuiManager implements IUserGuiManager {

	private static MainWindowGuiManager mainWindowGuiManager;
	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();

	// the gui controllers
	public MainWindowController mainWindowController;
	public LoginGuiController loginGuiController;
	public BtnController btnController;
	public UserHomeWindowGuiController userHomeWindowController;
	public StartingWindowGuiController startingWindowController;
	public AccessibilityPageController accessibilityPageController;

	// gui managers
	public BtnMenuManager btnMenuManager;

	// Boundaries
	public UserBoundary userBaundary;

	public static MainWindowGuiManager getInstance() {
		if (mainWindowGuiManager == null) {
			mainWindowGuiManager = new MainWindowGuiManager();
			mainWindowGuiManager.initMainWindow();
		}
		return mainWindowGuiManager;
	}

	private void initMainWindow() {
		btnController = (BtnController) guiObjectsFactory.loadFxmlFile("/buttons/Buttons.fxml");
		mainWindowController = (MainWindowController) guiObjectsFactory.loadFxmlFile("/mainWindow/MainWindow.fxml");
		loginGuiController = (LoginGuiController) guiObjectsFactory.loadFxmlFile("/mainWindow/LoginWindow.fxml");
		userHomeWindowController = (UserHomeWindowGuiController) guiObjectsFactory
				.loadFxmlFile("/usersHomeWindows/UserHomeWindow.fxml");
		accessibilityPageController = (AccessibilityPageController) guiObjectsFactory
				.loadFxmlFile("/accessibility/AccessibilityWindow.fxml");
		startingWindowController = (StartingWindowGuiController) guiObjectsFactory
				.loadFxmlFile("/mainWindow/StartingWindow.fxml");

		// init managers
		btnMenuManager = new BtnMenuManager();
		userBaundary = new UserBoundary();

	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub

	}

}
