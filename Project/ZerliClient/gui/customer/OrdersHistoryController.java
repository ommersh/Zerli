package customer;

import java.sql.Timestamp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import order.Order;
import order.OrderStatus;
import userGuiManagment.AuthorizedCustomerGuiManager;
import userGuiManagment.MainWindowGuiManager;
import usersManagment.AuthorizedCustomerBoundary;

/**
 * controller for the order history window
 * 
 * @author halel
 *
 */
public class OrdersHistoryController implements IGuiController {

	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private AuthorizedCustomerBoundary authorizedCustomerBoundary = AuthorizedCustomerGuiManager.getInstance()
			.getAuthorizedCustomerBoundary();
	@FXML
	private AnchorPane orderHistoryBasePane;

	@FXML
	private TableView<Order> ordersTable;

	@FXML
	private Button selectOrderBtn;

	@FXML
	private TableColumn<Order, Timestamp> orderArrivalCol;

	@FXML
	private TableColumn<Order, Timestamp> orderDateCol;

	@FXML
	private TableColumn<Order, Boolean> orderDeliveryCol;

	@FXML
	private TableColumn<Order, String> orderLetterCol;

	@FXML
	private TableColumn<Order, Integer> orderNumCol;

	@FXML
	private TableColumn<Order, Double> orderPriceCol;

	@FXML
	private TableColumn<Order, String> orderUserCol;

	@FXML
	private TableColumn<Order, OrderStatus> statusCol;

	private ObservableList<Order> ordersObs = FXCollections.observableArrayList();
	private Order selectedOrder;;

	public void initializeOrdersTable() {
		ordersObs.clear();
		ordersTable.getItems().clear();
		orderArrivalCol.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
		orderDateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		orderDeliveryCol.setCellValueFactory(new PropertyValueFactory<>("homeDelivery"));
		orderLetterCol.setCellValueFactory(new PropertyValueFactory<>("personalLetter"));
		orderNumCol.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
		orderPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		orderUserCol.setCellValueFactory(new PropertyValueFactory<>("username"));
		statusCol.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
	}

	@Override
	public void openWindow() {
		initializeOrdersTable();
		ordersObs.setAll(authorizedCustomerBoundary.getAllOrders());
		ordersTable.setItems(ordersObs);
		mainWindowManager.mainWindowController.changeWindowName("Customer - order history");
		mainWindowManager.mainWindowController.showNewWindow(orderHistoryBasePane);
	}

	@Override
	public Pane getBasePane() {
		return orderHistoryBasePane;
	}

	@Override
	public void resetController() {
		ordersObs.clear();
		ordersTable.getItems().clear();
		selectedOrder = null;
	}

	/**
	 * select order, move to the order details window
	 * 
	 * @param event
	 */
	@FXML
	void selectOrder(ActionEvent event) {
		selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
		if (selectedOrder != null) {
			mainWindowManager.mainWindowController.changeWindowName("Order details");
			AuthorizedCustomerGuiManager.getInstance().getOrderDetailsController().setSelectedOrder(selectedOrder);
			AuthorizedCustomerGuiManager.getInstance().getOrderDetailsController().openWindow();
		}
	}
}
