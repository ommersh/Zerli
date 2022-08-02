package usersHomeWindows;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.*;
import user.UserStatus;
import user.UserType;
import userGuiManagment.MainWindowGuiManager;
import usersManagment.UserBoundary;

/**
 * the user main window(for the user home screen) including a welcome string
 * 
 * @author halel
 *
 */
public class UserHomeWindowGuiController implements IGuiController {

	private static final String userWelcomeString = "Welcome ";

	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();

	@FXML
	private AnchorPane basePane;

	@FXML
	private AnchorPane innerPane;

	@FXML
	private Label welcomeLabel;

	@FXML
	private Label accountStatusLabel;

	/**
	 * Go to the user home window
	 */
	public void openWindow() {
		onEntering();
		// move to the next window
		mainWindowManager.mainWindowController.showNewWindow(basePane);
		// change to the new menu name
		mainWindowManager.mainWindowController.changeWindowName("Home");
	}

	/**
	 * do something while entering the new window
	 */
	public void onEntering() {
		// get the users full name
		String fullName = UserBoundary.CurrentUser.getFirstName() + " " + UserBoundary.CurrentUser.getLastName();
		welcomeLabel.setText(userWelcomeString + fullName);
		if (UserBoundary.CurrentUser.getUserType() == UserType.NonAuthorizedCustomer) {
			accountStatusLabel.setText("Your account is not authorized!");
		} else if (UserBoundary.CurrentUser.getStatus().equals(UserStatus.Frozen))
			accountStatusLabel.setText("Your account is frozen!");
		else if (UserBoundary.CurrentUser.getStatus().equals(UserStatus.NotActive))
			accountStatusLabel.setText("Your account is not active!");
		else {
			accountStatusLabel.setText("");
		}

	}

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {
		welcomeLabel.setText("");
	}

}
