package buttons;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import user.UserType;
import userGuiManagment.AuthorizedCustomerGuiManager;
import userGuiManagment.BranchEmployeeGuiManager;
import userGuiManagment.BranchManagerGuiManager;
import userGuiManagment.CEOGuiManager;
import userGuiManagment.CourierGuiManager;
import userGuiManagment.CustomerServiceGuiManager;
import userGuiManagment.MainWindowGuiManager;
import userGuiManagment.MarketingGuiManager;
import usersManagment.UserBoundary;

/**
 * controller for the buttons gui, hold the Buttons objects for the top menu,
 * can get the object using getters and place on the windows
 */
public class BtnController implements IGuiController {

	@FXML
	private AnchorPane basePane;
	@FXML
	private Button accessibilityBtn;

	@FXML
	private Button approveDeliveryBtn;

	@FXML
	private Button createComplaintBtn;

	@FXML
	private Button createPromotionBtn;

	@FXML
	private Button enterSurveyAnswersBtn;

	@FXML
	private Button enterSurveyresultBtn;

	@FXML
	private Button homeBtn;

	@FXML
	private Button infoBtn;

	@FXML
	private Button loginBtn;

	@FXML
	private Button logoutBtn;

	@FXML
	private Button manageUsersBtn;

	@FXML
	private Button orderHistoryBtn;

	@FXML
	private Button reportsBtn;

	@FXML
	private Button shopBtn;

	@FXML
	private Button updateComplaintBtn;

	@FXML
	private Button viewOrdersBtn;

	@FXML
	private Button viewSurveyBtn;

	@FXML
	private Button userHomeBtn;

	@FXML
	private ImageView logo;

	@FXML
	private Button managePromotionsBtn;

	@FXML
	private Button manageCatalogBtn;

	@FXML
	void manageCatalog(ActionEvent event) {
		MarketingGuiManager.getInstance().getManageCatalogController().openWindow();
	}

	@FXML
	void managePromotions(ActionEvent event) {
		MarketingGuiManager.getInstance().getManagePromotions().openWindow();
	}

	@FXML
	void goToUserHomepage(ActionEvent event) {
		MainWindowGuiManager.getInstance().userHomeWindowController.openWindow();
	}

	@FXML
	void accessibility(ActionEvent event) {
		MainWindowGuiManager.getInstance().accessibilityPageController.openWindow();
	}

	@FXML
	void approvedelivery(ActionEvent event) {
		CourierGuiManager.getInstance().getCourierConfirmDelivery().openWindow();
	}

	@FXML
	void createComplaint(ActionEvent event) {
		CustomerServiceGuiManager.getInstance().getNewComplaintController().openWindow();
	}

	@FXML
	void createPromotion(ActionEvent event) {
		MarketingGuiManager.getInstance().getCreatePromotion().openWindow();
	}

	@FXML
	void getInfo(ActionEvent event) {

	}

	@FXML
	void goToHomepage(ActionEvent event) {
		MainWindowGuiManager.getInstance().mainWindowController.openWindow();
	}

	@FXML
	void login(ActionEvent event) {
		MainWindowGuiManager.getInstance().loginGuiController.openWindow();
	}

	@FXML
	void logout(ActionEvent event) {
		resetAll();
		MainWindowGuiManager.getInstance().userBaundary.requestLogOut();
		MainWindowGuiManager.getInstance().loginGuiController.resetController();
		MainWindowGuiManager.getInstance().mainWindowController.openWindow();
	}

	@FXML
	void manageOrders(ActionEvent event) {
		BranchManagerGuiManager.getInstance().getManagerApproveController().openWindow();
	}

	@FXML
	void manageUsers(ActionEvent event) {
		BranchManagerGuiManager.getInstance().getManagerUsersManagmetController().openWindow();
	}

	@FXML
	void openOrdersHistory(ActionEvent event) {
		AuthorizedCustomerGuiManager.getInstance().getOrdersHistoryController().openWindow();
	}

	@FXML
	void openReports(ActionEvent event) {
		if (UserBoundary.CurrentUser != null) {
			if (UserBoundary.CurrentUser.getUserType() == UserType.BranchManager)
				BranchManagerGuiManager.getInstance().getManagerWatchReportController().openWindow();
			else if (UserBoundary.CurrentUser.getUserType() == UserType.CEO)
				CEOGuiManager.getInstance().getCeoController().openWindow();
		}
	}

	@FXML
	void openShop(ActionEvent event) {
		AuthorizedCustomerGuiManager.getInstance().getShopWindowController().openWindow();
	}

	@FXML
	void surveyAnswers(ActionEvent event) {
		BranchEmployeeGuiManager.getInstance().getSearchSurvey().openWindow();
	}

	@FXML
	void surveyResult(ActionEvent event) {
		CustomerServiceGuiManager.getInstance().getSurveyResultsController().openWindow();
	}

	@FXML
	void updateComplaint(ActionEvent event) {
		CustomerServiceGuiManager.getInstance().getShowAllComplaints().openWindow();
	}

	@FXML
	void viewSurvey(ActionEvent event) {

	}

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {
		// accessibilityBtn.setDisable(true);
	}

	public Button getAccessibilityBtn() {
		return accessibilityBtn;
	}

	public Button getApproveDeliveryBtn() {
		return approveDeliveryBtn;
	}

	public Button getCreateComplaintBtn() {
		return createComplaintBtn;
	}

	public Button getCreatePromotionBtn() {
		return createPromotionBtn;
	}

	public Button getEnterSurveyAnswersBtn() {
		return enterSurveyAnswersBtn;
	}

	public Button getEnterSurveyresultBtn() {
		return enterSurveyresultBtn;
	}

	public Button getHomeBtn() {
		return homeBtn;
	}

	public Button getInfoBtn() {
		return infoBtn;
	}

	public Button getLoginBtn() {
		return loginBtn;
	}

	public Button getLogoutBtn() {
		return logoutBtn;
	}

	public Button getManageUsersBtn() {
		return manageUsersBtn;
	}

	public Button getOrderHistoryBtn() {
		return orderHistoryBtn;
	}

	public Button getReportsBtn() {
		return reportsBtn;
	}

	public Button getShopBtn() {
		return shopBtn;
	}

	public Button getUpdateComplaintBtn() {
		return updateComplaintBtn;
	}

	public Button getViewOrdersBtn() {
		return viewOrdersBtn;
	}

	public Button getViewSurveyBtn() {
		return viewSurveyBtn;
	}

	public Button getUserHomeBtn() {
		return userHomeBtn;
	}

	public Button getManagePromotionsBtn() {
		return managePromotionsBtn;
	}

	public Button getManageCatalogBtn() {
		return manageCatalogBtn;
	}

	public ImageView getLogo() {
		return logo;
	}

	@Override
	public void openWindow() {

	}

	@FXML
	void logoBtnPress(MouseEvent event) {
		if (UserBoundary.getCurrentUser() == null) {
			MainWindowGuiManager.getInstance().mainWindowController.openWindow();
		} else {
			MainWindowGuiManager.getInstance().userHomeWindowController.openWindow();
		}
	}

	private void resetAll() {
		if (UserBoundary.CurrentUser != null) {
			switch (UserBoundary.CurrentUser.getUserType()) {
			case AuthorizedCustomer:
				AuthorizedCustomerGuiManager.getInstance().logout();
				break;
			case BranchEmployee:
				BranchEmployeeGuiManager.getInstance().logout();
				break;
			case BranchManager:
				BranchManagerGuiManager.getInstance().logout();
				break;
			case CEO:
				CEOGuiManager.getInstance().logout();
				break;
			case Courier:
				CourierGuiManager.getInstance().logout();
				break;
			case CustomerServiceEmloyee:
				CustomerServiceGuiManager.getInstance().logout();
				break;
			case MarketingEmployee:
				MarketingGuiManager.getInstance().logout();
				break;
			default:
				break;
			}
		}

	}

}
