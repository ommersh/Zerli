package branchManager;

import java.util.ArrayList;

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
import main.GuiObjectsFactory;
import main.IGuiController;
import order.Order;
import order.OrderStatus;
import order.ProductInOrder;
import userGuiManagment.BranchManagerGuiManager;
import userGuiManagment.MainWindowGuiManager;
import usersManagment.BranchManagerBoundary;

/**
 * controller for the window: view product window
 *
 */
public class ManagerViewProducts implements IGuiController {
	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private BranchManagerGuiManager branchManagerGuiManager = BranchManagerGuiManager.getInstance();
	private BranchManagerBoundary managerBoundry = branchManagerGuiManager.getBranchManagerBoundary();
	private Order selectedOrder = null;
	private ObservableList<ProductInOrder> productsObs = FXCollections.observableArrayList();
	private IGuiController lastController = null;
	private ArrayList<ProductInOrder> products;
	ManagerApprovalConfirmed managerApprovalConfirmed;

	@FXML
	private Button approveBot;

	@FXML
	private Button backToOrderSelectBot;

	@FXML
	private TableColumn<ProductInOrder, Integer> prodOrderNumCol;

	@FXML
	private TableColumn<ProductInOrder, Integer> productAmountCol;

	@FXML
	private TableColumn<ProductInOrder, String> productCategoryCol;

	@FXML
	private AnchorPane productInOrderPane;

	@FXML
	private TableColumn<ProductInOrder, String> productNameCol;

	@FXML
	private TableColumn<ProductInOrder, Double> productPriceCol;

	@FXML
	private TableColumn<ProductInOrder, Double> productTotalCol;

	@FXML
	private TableView<ProductInOrder> productsInOrderTable;

	@FXML
	void ApproveOrder(ActionEvent event) {
		if (selectedOrder.getOrderStatus() == OrderStatus.WAITING_FOR_APPROAVL) {
			managerBoundry.requestApproveOrder(selectedOrder.getOrderNumber(), true);
			selectedOrder.setOrderStatus(OrderStatus.APPROVED);
		}
		if (selectedOrder.getOrderStatus() == OrderStatus.WAITING_FOR_CANCELLATION_APPROVAL) {
			managerBoundry.requestApproveCancelation(selectedOrder.getOrderNumber(), true);
			selectedOrder.setOrderStatus(OrderStatus.CANCELED);
		}

		managerApprovalConfirmed = (ManagerApprovalConfirmed) guiObjectsFactory
				.loadFxmlFile("/branchManager/approvalConfirmed.fxml");
		managerApprovalConfirmed.setOrder(selectedOrder);
		managerApprovalConfirmed.setProducts(products);
		managerApprovalConfirmed.openWindow();
	}

	@FXML
	void BackToOrderSelectWindow(ActionEvent event) {
		MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
		mainWindowManager.mainWindowController.showNewWindow(lastController.getBasePane());
		resetController();
		lastController.openWindow();
	}

	@Override
	public Pane getBasePane() {
		return productInOrderPane;
	}

	@Override
	public void resetController() {
		selectedOrder = null;
		productsObs.clear();
	}

	@Override
	public void openWindow() {
		mainWindowManager.mainWindowController.showNewWindow(productInOrderPane);
		mainWindowManager.mainWindowController.changeWindowName("Manager - Products In Selected Order");
		initializeProductsTable();
		products = managerBoundry.getAllProductsInOrder(selectedOrder.getOrderNumber());
		productsObs.setAll(products);
		productsInOrderTable.setItems(productsObs);
		if (selectedOrder.getOrderStatus() == OrderStatus.WAITING_FOR_APPROAVL)
			approveBot.setText("Approve Order");
		if (selectedOrder.getOrderStatus() == OrderStatus.WAITING_FOR_CANCELLATION_APPROVAL) {
			approveBot.setText("Approve Cancellation");
		}
	}

	public void setSelectedOrder(Order order) {
		this.selectedOrder = order;
	}

	public void setLastController(IGuiController controller) {
		this.lastController = controller;
	}

	public void initializeProductsTable() {
		productCategoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
		productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		prodOrderNumCol.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
		productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		productAmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
		productTotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
	}

}
