package branchManager;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import order.Order;
import order.OrderStatus;
import order.ProductInOrder;
import userGuiManagment.MainWindowGuiManager;

/**
 * controller for the window: approving order
 *
 */
public class ManagerApprovalConfirmed implements IGuiController {
	private Order order;
	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private ArrayList<ProductInOrder> products;

	@FXML
	private AnchorPane approvalConfirmedPane;

	@FXML
	private Label approveLabel;

	@FXML
	private Label errorLabel;

	@FXML
	private Label guidanceLabel;

	@FXML
	private AnchorPane orderApprovePane;

	@FXML
	private TextArea orderTextArea;

	@FXML
	private TextArea productsTextArea;

	@Override
	public Pane getBasePane() {
		return approvalConfirmedPane;
	}

	@Override
	public void resetController() {
		approveLabel.setText("Order Approved");
		orderTextArea.setText("");
		productsTextArea.setText("");
	}

	@Override
	public void openWindow() {
		mainWindowManager.mainWindowController.showNewWindow(approvalConfirmedPane);
		if (order.getOrderStatus() == OrderStatus.CANCELED)
			approveLabel.setText("Cancellation Approved");
		orderTextArea.setText(order.toString());
		productsTextArea.setText(productsToString(products));
	}

	public void setOrder(Order selectedOrder) {
		this.order = selectedOrder;
	}

	public void setProducts(ArrayList<ProductInOrder> products) {
		this.products = products;
	}

	private String productsToString(ArrayList<ProductInOrder> products) {
		StringBuilder s = new StringBuilder();
		for (ProductInOrder prod : products) {
			s.append(prod.toString() + "\n");
		}
		return s.toString();

	}

}
