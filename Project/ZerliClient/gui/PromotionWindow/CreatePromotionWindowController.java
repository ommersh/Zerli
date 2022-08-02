package PromotionWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import userGuiManagment.MainWindowGuiManager;
import userGuiManagment.MarketingGuiManager;

/**
 * controller for the window: create new promotion
 *
 */
public class CreatePromotionWindowController implements IGuiController {

	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private MarketingGuiManager marketingGuiManager = MarketingGuiManager.getInstance();

	@FXML
	private AnchorPane basePane;

	@FXML
	private TextField ItemID;

	@FXML
	private TextField Discount;

	@FXML
	private Button createbutton;

	@FXML
	private Button cancelbutton;

	@FXML
	private Label errorLabel;

	@FXML
	private TextArea PromotionTxt;

	@FXML
	void cancelButtonPressed(ActionEvent event) {
		mainWindowManager.userHomeWindowController.openWindow();
	}

	@FXML
	void createButtonPressed(ActionEvent event) {
		double convertedDiscount;
		int productNumber;
		if (ItemID.getText().isEmpty() || Discount.getText().isEmpty() || PromotionTxt.getText().isEmpty()) {
			errorLabel.setText("**please fill all the Fields");
			return;
		}
		try {
			convertedDiscount = Double.parseDouble(Discount.getText()) / 100;
		} catch (NumberFormatException e) {
			errorLabel.setText("Discount number must be a bumber");
			return;
		}
		if (convertedDiscount < 0 || convertedDiscount > 1) {
			errorLabel.setText("**please Enter a correct Discount 0 - 100 %");
			return;
		}
		try {
			productNumber = Integer.parseInt(ItemID.getText());
		} catch (NumberFormatException e) {
			errorLabel.setText("Product number must be a bumber");
			return;
		}
		try

		{
			marketingGuiManager.getMarketingEmployeeBoundary().requestcreateNewPromotion(productNumber,
					convertedDiscount, PromotionTxt.getText());
		} catch (Exception e) {
			errorLabel.setText(e.getMessage());
			return;
		}

		// in case the create success then
		MarketingGuiManager.getInstance().getPromotionCreatedSuccessfully().openWindow();
	}

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {
		ItemID.setText("");
		errorLabel.setText("");
		PromotionTxt.setText("");
		Discount.setText("");

	}

	@Override
	public void openWindow() {
		mainWindowManager.mainWindowController.showNewWindow(basePane);
		mainWindowManager.mainWindowController.changeWindowName("RequestCreatePromotion");

	}

	// how to stop/delete a active/created/old promotion ????????????!!!!!!!!!
	// maybe we should add a start date and end date ???? !!!!!

}