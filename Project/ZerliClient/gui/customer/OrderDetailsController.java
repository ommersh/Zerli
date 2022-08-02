package customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import order.DeliveryDetails;
import order.Order;
import order.OrderStatus;
import order.ProductInOrder;
import userGuiManagment.AuthorizedCustomerGuiManager;
import userGuiManagment.MainWindowGuiManager;
import usersManagment.AuthorizedCustomerBoundary;

/**
 * controller for order details window, show the full details of the order
 * 
 * @author halel
 *
 */
public class OrderDetailsController implements IGuiController {
	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private AuthorizedCustomerBoundary authorizedCustomerBoundary = AuthorizedCustomerGuiManager.getInstance()
			.getAuthorizedCustomerBoundary();

	@FXML
	private Label msgLabel;

	@FXML
	private Button backToOrderSelectBot;

	@FXML
	private Button cancelBtn;

	@FXML
	private TextArea dipslaydeliverydetails;

	@FXML
	private AnchorPane orderDetailsBasePane;

	@FXML
	private TableView<ProductInOrder> productsInOrderTable;

	@FXML
	private TextField refundField;

	@FXML
	private Label refundText;

	@FXML
	private TableColumn<ProductInOrder, Integer> prodOrderNumCol;

	@FXML
	private TableColumn<ProductInOrder, Integer> productAmountCol;

	@FXML
	private TableColumn<ProductInOrder, String> productCategoryCol;

	@FXML
	private TableColumn<ProductInOrder, String> productNameCol;

	@FXML
	private TableColumn<ProductInOrder, Double> productPriceCol;

	@FXML
	private TableColumn<ProductInOrder, Double> productTotalCol;

	ObservableList<ProductInOrder> productsObs = FXCollections.observableArrayList();
	Order selectedOrder;

	/**
	 * init the products in order table
	 */
	private void initializeProductsTable() {
		productsObs.clear();
		productsInOrderTable.getItems().clear();
		productCategoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
		productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		prodOrderNumCol.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
		productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		productAmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
		productTotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
	}

	@Override
	public void openWindow() {
		msgLabel.setText("");
		initializeProductsTable();
		displayDeliveryDetails();
		displayDeliveryDetails();
		productsObs.addAll(selectedOrder.getItems());
		productsInOrderTable.setItems(productsObs);
		mainWindowManager.mainWindowController.changeWindowName("Order details - " + selectedOrder.getOrderNumber());
		mainWindowManager.mainWindowController.showNewWindow(orderDetailsBasePane);
	}

	/**
	 * display the delivery\pickup details
	 */
	private void displayDeliveryDetails() {
		DeliveryDetails delivery = selectedOrder.getDeliveryDetails();
		StringBuilder s = new StringBuilder();
		if (selectedOrder.isHomeDelivery() && selectedOrder.getDeliveryDetails() != null) {
			s.append("address:" + "\n" + delivery.getFirstName() + " " + delivery.getLastName() + "\n");
			s.append(delivery.getAddress() + "\n\n");
			s.append("\n\nadditional information:" + "\n");
			s.append("phone number: " + delivery.getPhoneNumber() + "\n");
			s.append("comments: " + delivery.getComments() + "\n");
		} else {
			s.append("arrival date and time:\n");
			s.append(selectedOrder.getArrivalDate().toLocalDateTime().toString() + "\n");
			s.append("from branch :\n");
			s.append(selectedOrder.getBranchName());
		}
		dipslaydeliverydetails.setText(s.toString());
	}

	@Override
	public Pane getBasePane() {
		return orderDetailsBasePane;
	}

	@Override
	public void resetController() {
		dipslaydeliverydetails.setText("");
		productsInOrderTable.getItems().clear();
		productsObs.clear();
		selectedOrder = null;
		msgLabel.setText("");
		cancelBtn.setDisable(true);
	}

	@FXML
	void BackToOrderSelectWindow(ActionEvent event) {
		resetController();
		AuthorizedCustomerGuiManager.getInstance().getOrdersHistoryController().openWindow();
	}

	/**
	 * cancel the order
	 * 
	 * @param event
	 */
	@FXML
	void cancelOrder(ActionEvent event) {
		if (authorizedCustomerBoundary.requestOrderCancellation(selectedOrder))
			msgLabel.setText("Request sent, you will recieved notifiaction on approval");
		else
			msgLabel.setText("can't cancel the order");
	}

	/**
	 * set the order object for this window
	 * 
	 * @param order
	 */
	public void setSelectedOrder(Order order) {
		selectedOrder = order;
		Order tempOrder = authorizedCustomerBoundary.getfullOrder(selectedOrder.getOrderNumber());
		if (tempOrder != null)
			selectedOrder = tempOrder;
		OrderStatus status = selectedOrder.getOrderStatus();
		if (status == OrderStatus.APPROVED || status == OrderStatus.WAITING_FOR_APPROAVL) {
			cancelBtn.setDisable(false);
		}
	}

}
