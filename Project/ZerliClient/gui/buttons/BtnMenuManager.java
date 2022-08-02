package buttons;

import user.UserStatus;
import userGuiManagment.MainWindowGuiManager;
import usersManagment.UserBoundary;

/**
 * manage the top menu. place the correct buttons for each type of user
 *
 */
public class BtnMenuManager {

	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();

	private BtnController btns;

	public BtnMenuManager() {
		btns = mainWindowManager.btnController;
	}

	public void setUserBtns() {
		mainWindowManager.mainWindowController.resetBtns();
		mainWindowManager.mainWindowController.setBtn(btns.getAccessibilityBtn(), 6);
		mainWindowManager.mainWindowController.setBtn(btns.getLogoutBtn(), 7);
		mainWindowManager.mainWindowController.setBtn(btns.getUserHomeBtn(), 1);
		// for each user type init different btns
		switch (UserBoundary.getCurrentUser().getUserType()) {
		case NonAuthorizedCustomer:
		case AuthorizedCustomer:
			setCustomerBtns();
			break;
		case BranchManager:
			setBranchManagerBtns();
			break;
		case BranchEmployee:
			setBranchEmployeeBtns();
			break;
		case CustomerServiceEmloyee:
			setCSEmployeeBtns();
			break;
		case MarketingEmployee:
			setMarketingEmployeeBtns();
			break;
		case Courier:
			setCurierBtns();
			break;
		case CEO:
			setCEObtns();
			break;
		default:
			break;
		}
	}

	public void setCEObtns() {
		mainWindowManager.mainWindowController.setBtn(btns.getReportsBtn(), 2);
		if (UserBoundary.getCurrentUser().getStatus().equals(UserStatus.NotActive)) {
			btns.getReportsBtn().setDisable(true);

		} else {
			btns.getReportsBtn().setDisable(false);
		}
	}

	public void setCustomerBtns() {
		mainWindowManager.mainWindowController.setBtn(btns.getOrderHistoryBtn(), 2);
		mainWindowManager.mainWindowController.setBtn(btns.getShopBtn(), 3);
		if (UserBoundary.getCurrentUser().getStatus().equals(UserStatus.NotActive)) {
			btns.getOrderHistoryBtn().setDisable(true);
			btns.getShopBtn().setDisable(true);

		} else {
			btns.getOrderHistoryBtn().setDisable(false);
			btns.getShopBtn().setDisable(false);
		}
	}

	public void setCurierBtns() {
		mainWindowManager.mainWindowController.setBtn(btns.getApproveDeliveryBtn(), 2);
		if (UserBoundary.getCurrentUser().getStatus().equals(UserStatus.NotActive)) {
			btns.getApproveDeliveryBtn().setDisable(true);

		} else {
			btns.getApproveDeliveryBtn().setDisable(false);
		}

	}

	public void setBranchEmployeeBtns() {
		mainWindowManager.mainWindowController.setBtn(btns.getEnterSurveyAnswersBtn(), 2);
		if (UserBoundary.getCurrentUser().getStatus().equals(UserStatus.NotActive)) {
			btns.getEnterSurveyAnswersBtn().setDisable(true);

		} else {
			btns.getEnterSurveyAnswersBtn().setDisable(false);
		}
	}

	public void setBranchManagerBtns() {
		mainWindowManager.mainWindowController.setBtn(btns.getReportsBtn(), 2);
		mainWindowManager.mainWindowController.setBtn(btns.getViewOrdersBtn(), 3);
		mainWindowManager.mainWindowController.setBtn(btns.getManageUsersBtn(), 4);
		if (UserBoundary.getCurrentUser().getStatus().equals(UserStatus.NotActive)) {
			btns.getReportsBtn().setDisable(true);
			btns.getViewOrdersBtn().setDisable(true);
			btns.getManageUsersBtn().setDisable(true);

		} else {
			btns.getReportsBtn().setDisable(false);
			btns.getViewOrdersBtn().setDisable(false);
			btns.getManageUsersBtn().setDisable(false);
		}
	}

	public void setMarketingEmployeeBtns() {
		mainWindowManager.mainWindowController.setBtn(btns.getCreatePromotionBtn(), 2);
		mainWindowManager.mainWindowController.setBtn(btns.getManagePromotionsBtn(), 3);
		mainWindowManager.mainWindowController.setBtn(btns.getManageCatalogBtn(), 4);
		if (UserBoundary.getCurrentUser().getStatus().equals(UserStatus.NotActive)) {
			btns.getCreatePromotionBtn().setDisable(true);
			btns.getManagePromotionsBtn().setDisable(true);
			btns.getManageCatalogBtn().setDisable(true);

		} else {
			btns.getCreatePromotionBtn().setDisable(false);
			btns.getManagePromotionsBtn().setDisable(false);
			btns.getManageCatalogBtn().setDisable(false);
		}
	}

	public void setCSEmployeeBtns() {
		mainWindowManager.mainWindowController.setBtn(btns.getCreateComplaintBtn(), 2);
		mainWindowManager.mainWindowController.setBtn(btns.getUpdateComplaintBtn(), 3);
		mainWindowManager.mainWindowController.setBtn(btns.getEnterSurveyresultBtn(), 4);
		if (UserBoundary.getCurrentUser().getStatus().equals(UserStatus.NotActive)) {
			btns.getCreateComplaintBtn().setDisable(true);
			btns.getUpdateComplaintBtn().setDisable(true);
			btns.getEnterSurveyresultBtn().setDisable(true);

		} else {
			btns.getCreateComplaintBtn().setDisable(false);
			btns.getUpdateComplaintBtn().setDisable(false);
			btns.getEnterSurveyresultBtn().setDisable(false);
		}
	}

	public void setLoginWindowBtns() {
		mainWindowManager.mainWindowController.resetBtns();
		mainWindowManager.mainWindowController.setBtn(btns.getHomeBtn(), 1);
		mainWindowManager.mainWindowController.setBtn(btns.getAccessibilityBtn(), 6);
		mainWindowManager.mainWindowController.setBtn(btns.getInfoBtn(), 7);
	}

	public void setMainWindowBtns() {
		mainWindowManager.mainWindowController.resetBtns();
		mainWindowManager.mainWindowController.setBtn(btns.getHomeBtn(), 1);
		mainWindowManager.mainWindowController.setBtn(btns.getAccessibilityBtn(), 6);
		mainWindowManager.mainWindowController.setBtn(btns.getLoginBtn(), 7);
	}

}
