package ordersPayment;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import order.DeliveryDetails;
import shop.ShopBoundary;
import userGuiManagment.AuthorizedCustomerGuiManager;
import userGuiManagment.MainWindowGuiManager;

/**
 * controller for the window: the personal card form
 *
 */
public class personalcardWindowController implements IGuiController {

	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private ShopBoundary shopBoundary = AuthorizedCustomerGuiManager.getInstance().getShopBoundary();
	private AuthorizedCustomerGuiManager authorizedCustomerGuiManager = AuthorizedCustomerGuiManager.getInstance();

	@FXML
	private AnchorPane basepane;

	@FXML
	private Button backButton;

	@FXML
	private TextArea textareagreeting;

	@FXML
	private Button nextbutton;

	@FXML
	private Label errorlabel;

	public String cardtxt;

	@FXML
	void backbuttonpreesed(ActionEvent event) {
		authorizedCustomerGuiManager.getBranch_Delivery().openWindow();
	}

	@FXML
	void nextbuttonpressed(ActionEvent event) {
		cardtxt = textareagreeting.getText();
		if (cardtxt.isEmpty()) // check if user didn't fill the text area
		{
			seterror("your personal card is empty please fill in the blank area!!!");
			return;
		}
		shopBoundary.addPersonalLetter(cardtxt); // save letter in cart of shop
		if (shopBoundary.isHomeDeliveryflag()) // if true mean customer want home delivery
		{
			authorizedCustomerGuiManager.getHomeDeliveryDetails().openWindow();
		} else // if customer didn't put V in Checkbox for home Delivery
		{
			shopBoundary.sumbmitDetailsForHomeDelivery(new DeliveryDetails());
			authorizedCustomerGuiManager.order = shopBoundary.placeOrder();
			authorizedCustomerGuiManager.getConfirmOrder().openWindow();
		}
	}

	@Override
	public Pane getBasePane() {
		return basepane;
	}

	@Override
	public void resetController() {
		textareagreeting.setText("");
		errorlabel.setText("");
	}

	/**
	 * 
	 */
	private void seterror(String ErrorMsg) {
		errorlabel.setText(ErrorMsg);
	}

	@Override
	public void openWindow() {

		mainWindowManager.mainWindowController.showNewWindow(basepane);
		mainWindowManager.mainWindowController.changeWindowName("personalCard");

	}

}
