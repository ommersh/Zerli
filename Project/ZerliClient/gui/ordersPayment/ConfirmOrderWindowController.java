package ordersPayment;

import java.util.ArrayList;

// in place order in function before confirm you will get order 

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.IGuiController;
import order.DeliveryDetails;
import order.ProductInOrder;
import shop.ShopBoundary;
import userGuiManagment.AuthorizedCustomerGuiManager;
import userGuiManagment.MainWindowGuiManager;

/**
 * controller for the window: confirm the order and pay(or go back)
 *
 */
public class ConfirmOrderWindowController implements IGuiController {

	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private ShopBoundary shopBoundary = AuthorizedCustomerGuiManager.getInstance().getShopBoundary();
	private AuthorizedCustomerGuiManager authorizedCustomerGuiManager = AuthorizedCustomerGuiManager.getInstance();

	@FXML
	private AnchorPane basepane;

	@FXML
	private TableView<ProductInOrder> table;

	@FXML
	private TableColumn<ProductInOrder, String> tableviewItemname;

	@FXML
	private TableColumn<ProductInOrder, Integer> tableviewamount;

	@FXML
	private TableColumn<ProductInOrder, Double> tableviewprice;

	@FXML
	private TableColumn<ProductInOrder, Double> tableviewtotal;

	@FXML
	private TextArea dipslaydeliverydetails;

	@FXML
	private TextArea displayGreetingcard;

	@FXML
	private Label TotalPrice;

	@FXML
	private CheckBox checkboxSendGift;

	@FXML
	private CheckBox checkboxsendMail;

	@FXML
	private Button confirmandpaybutton;

	@FXML
	private Button backbutton;

	@FXML
	private Text oldPriceTxt;

	@FXML
	private Label orderDataLabel;

	public Boolean isGift = false, isEmailSend = false;

	private ObservableList<ProductInOrder> data = FXCollections.observableArrayList();

	@FXML
	void backbuttpress(ActionEvent event) {
		if (shopBoundary.isHomeDeliveryflag()) {
			authorizedCustomerGuiManager.getHomeDeliveryDetails().openWindow();
		} else if (shopBoundary.isPersonalCardflag()) {
			authorizedCustomerGuiManager.getPersonalCardcontroller().openWindow();
		} else
			authorizedCustomerGuiManager.getBranch_Delivery().openWindow();

	}

	@FXML
	void confirmPayButtpress(ActionEvent event) {
		if (shopBoundary.payForOrder()) {
			authorizedCustomerGuiManager.getSuccedfailedpay().openWindow();
		} else // in case we get false in pay
			authorizedCustomerGuiManager.getFailedpay().openWindow();

	}

	@FXML
	void SendReceiptToEmail(ActionEvent event) {
		if (checkboxsendMail.isPressed())
			isEmailSend = false;
		isEmailSend = true;
	}

	@FXML
	void sendAsGift(ActionEvent event) {
		if (checkboxSendGift.isPressed())
			isGift = false;
		isGift = true;
	}

	@Override
	public Pane getBasePane() {
		return basepane;
	}

	@Override
	public void resetController() {
		checkboxSendGift.setSelected(false);
		checkboxsendMail.setSelected(false);
		displayGreetingcard.setText("");
		dipslaydeliverydetails.setText("");
		TotalPrice.setText("0");
		table.getItems().clear();

	}

	@Override
	public void openWindow() {
		initmywindow();
		mainWindowManager.mainWindowController.showNewWindow(basepane);
		mainWindowManager.mainWindowController.changeWindowName("confirmOrder");

	}

	public void initmywindow() {
		table.getItems().clear();
		if (!shopBoundary.isPersonalCardflag()) {
			shopBoundary.addPersonalLetter(""); // in case user first add a personal than change his
												// mind to not to add then we remove the string
			displayGreetingcard.clear();
		} else {
			displayGreetingcard.setText(authorizedCustomerGuiManager.order.getPersonalLetter());
		}
		if (!shopBoundary.isHomeDeliveryflag()) {
			shopBoundary.sumbmitDetailsForHomeDelivery(new DeliveryDetails());
			dipslaydeliverydetails.clear();
		} else {
			String str = buildDeliveryDetailsString(authorizedCustomerGuiManager.order.getDeliveryDetails());
			dipslaydeliverydetails.setText(str);
		}
		ArrayList<ProductInOrder> items = authorizedCustomerGuiManager.order.getItems();
		// double cntprice=0;
		for (int i = 0; i < items.size(); i++)// calculate the total price of all Items in tableview
		{
			// cntprice=cntprice+((items.get(i).getPrice())*(items.get(i).getAmount()));
			data.add(items.get(i));
		}

		tableviewItemname.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableviewamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		tableviewprice.setCellValueFactory(new PropertyValueFactory<>("price"));
		tableviewtotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		table.setItems(data);
		TotalPrice.setText(Double.toString(authorizedCustomerGuiManager.order.getPriceToPay()));

		// if the price to pay not equals to the total price
		if (authorizedCustomerGuiManager.order.getPriceToPay() != authorizedCustomerGuiManager.order.getPrice()) {
			oldPriceTxt.setText(authorizedCustomerGuiManager.order.getPrice() + "");

		} else {
			oldPriceTxt.setText("");
		}
		orderDataLabel.setText(authorizedCustomerGuiManager.order.getOrderData());
	}

	/**
	 * in this window i build the delivery details String to show in middle window
	 * 
	 * @param delivery the delivery details that user entered in HomeDeliveryWindow
	 * @return
	 */
	private String buildDeliveryDetailsString(DeliveryDetails delivery) {
		StringBuilder s = new StringBuilder();
		s.append("address:" + "\n" + delivery.getFirstName() + " " + delivery.getLastName() + "\n");
		s.append(delivery.getAddress() + "\n\n");
		s.append("arrival date and time:\n");
		// s.append(guiobjectfactory.order.getArrivalDate()+"\n");
		s.append(authorizedCustomerGuiManager.order.getArrivalDate().toLocalDateTime().toString() + "\n");
		s.append("\n\nadditional information:" + "\n");
		s.append("phone number: " + delivery.getPhoneNumber() + "\n");
		s.append("comments: " + delivery.getComments() + "\n");
		return s.toString();
	}

}
