package userGuiManagment;

import customer.OrderDetailsController;
import customer.OrdersHistoryController;
import customer.ProductsManager;
import customer.ShopWindowController;
import main.GuiObjectsFactory;
import order.Order;
import ordersPayment.BranchDeliveryChooseWindowController;
import ordersPayment.ConfirmOrderWindowController;
import ordersPayment.HomeDeliveryWindowController;
import ordersPayment.SuccedPayWindowController;
import ordersPayment.failedtopayWindowController;
import ordersPayment.personalcardWindowController;
import shop.ShopBoundary;
import usersManagment.AuthorizedCustomerBoundary;

/**
 * Singleton, manage all the different customer window, for each window controller
 * when trying to get the controller, load the fxml if not already loaded
 * 
 * @author halel
 *
 */
public class AuthorizedCustomerGuiManager implements IUserGuiManager {

	/**
	 * the single existing instance
	 */
	private static AuthorizedCustomerGuiManager authorizedCustomerGuiManager;

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	// the controllers
	private ShopWindowController shopWindowController;
	private OrdersHistoryController ordersHistoryController;
	private OrderDetailsController orderDetailsController;

	private personalcardWindowController personalCardcontroller;
	private HomeDeliveryWindowController HomeDeliveryDetails;
	private ConfirmOrderWindowController confirmOrder;
	private SuccedPayWindowController succedfailedpay;
	private failedtopayWindowController failedpay;
	private BranchDeliveryChooseWindowController branch_Delivery;

	// gui managers
	private ProductsManager productManager;

	// the boundaries
	private ShopBoundary shopBoundary;
	private AuthorizedCustomerBoundary authorizedCustomerBoundary;
	// object
	public Order order;

	private AuthorizedCustomerGuiManager() {

	}

	public static AuthorizedCustomerGuiManager getInstance() {
		if (authorizedCustomerGuiManager == null) {
			authorizedCustomerGuiManager = new AuthorizedCustomerGuiManager();
		}
		return authorizedCustomerGuiManager;
	}

	public static AuthorizedCustomerGuiManager getAuthorizedCustomerGuiManager() {

		return authorizedCustomerGuiManager;
	}

	public ShopWindowController getShopWindowController() {
		// load the fxml and controller if it wasn't loaded
		if (shopWindowController == null) {
			shopWindowController = (ShopWindowController) guiObjectsFactory.loadFxmlFile("/customer/ShopWindow.fxml");
		}
		return shopWindowController;
	}

	public OrdersHistoryController getOrdersHistoryController() {
		// load the fxml and controller if it wasn't loaded
		if (ordersHistoryController == null) {
			ordersHistoryController = (OrdersHistoryController) guiObjectsFactory
					.loadFxmlFile("/customer/OrdersHistory.fxml");
		}

		return ordersHistoryController;
	}

	public OrderDetailsController getOrderDetailsController() {
		// load the fxml and controller if it wasn't loaded
		if (orderDetailsController == null) {
			orderDetailsController = (OrderDetailsController) guiObjectsFactory
					.loadFxmlFile("/customer/OrderDetailsWindow.fxml");
		}
		return orderDetailsController;
	}

	public personalcardWindowController getPersonalCardcontroller() {
		// load the fxml and controller if it wasn't loaded
		if (personalCardcontroller == null) {
			personalCardcontroller = (personalcardWindowController) guiObjectsFactory
					.loadFxmlFile("/ordersPayment/PersonalCardWindow.fxml");
		}
		return personalCardcontroller;
	}

	public HomeDeliveryWindowController getHomeDeliveryDetails() {
		// load the fxml and controller if it wasn't loaded
		if (HomeDeliveryDetails == null) {
			HomeDeliveryDetails = (HomeDeliveryWindowController) guiObjectsFactory
					.loadFxmlFile("/ordersPayment/HomeDeliveryWindow.fxml");
		}
		return HomeDeliveryDetails;
	}

	public ConfirmOrderWindowController getConfirmOrder() {
		// load the fxml and controller if it wasn't loaded
		if (confirmOrder == null) {
			confirmOrder = (ConfirmOrderWindowController) guiObjectsFactory
					.loadFxmlFile("/ordersPayment/ConfirmOrderWindow.fxml");
		}
		return confirmOrder;
	}

	public SuccedPayWindowController getSuccedfailedpay() {
		// load the fxml and controller if it wasn't loaded
		if (succedfailedpay == null) {
			succedfailedpay = (SuccedPayWindowController) guiObjectsFactory
					.loadFxmlFile("/ordersPayment/SuccedPayWindow.fxml");
		}
		return succedfailedpay;
	}

	public failedtopayWindowController getFailedpay() {
		// load the fxml and controller if it wasn't loaded
		if (failedpay == null) {
			failedpay = (failedtopayWindowController) guiObjectsFactory
					.loadFxmlFile("/ordersPayment/FailedToPayWindow.fxml");
		}
		return failedpay;
	}

	public BranchDeliveryChooseWindowController getBranch_Delivery() {
		if (branch_Delivery == null) {
			branch_Delivery = (BranchDeliveryChooseWindowController) guiObjectsFactory
					.loadFxmlFile("/ordersPayment/Branch&DeliveryChoose.fxml");
		}
		return branch_Delivery;
	}

	public ProductsManager getProductManager() {
		if (productManager == null)
			productManager = new ProductsManager();
		return productManager;
	}

	public ShopBoundary getShopBoundary() {
		if (shopBoundary == null)
			shopBoundary = new ShopBoundary();
		return shopBoundary;
	}

	public AuthorizedCustomerBoundary getAuthorizedCustomerBoundary() {
		if (authorizedCustomerBoundary == null)
			authorizedCustomerBoundary = new AuthorizedCustomerBoundary();
		return authorizedCustomerBoundary;
	}

	@Override
	public void logout() {
		authorizedCustomerGuiManager = null;
		ordersHistoryController = null;
		shopWindowController = null;
		orderDetailsController = null;
		personalCardcontroller = null;
		HomeDeliveryDetails = null;
		confirmOrder = null;
		succedfailedpay = null;
		failedpay = null;
		authorizedCustomerBoundary = null;
		shopBoundary = null;
		productManager = null;
	}

}
