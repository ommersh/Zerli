package customer;

import java.util.ArrayList;
import java.util.HashMap;

import catalog.CustomizedProduct;
import catalog.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.IGuiController;
import shop.ShopBoundary;
import user.UserStatus;
import user.UserType;
import userGuiManagment.AuthorizedCustomerGuiManager;
import userGuiManagment.MainWindowGuiManager;
import usersManagment.UserBoundary;

/**
 * manage the shop-> the catalog view, the cart and the customized products
 * 
 * @author halel
 *
 */
public class ShopWindowController implements IGuiController {
	private AuthorizedCustomerGuiManager authorizedCustomerGuiManager = AuthorizedCustomerGuiManager.getInstance();
	private ShopBoundary shopBoundary = AuthorizedCustomerGuiManager.getInstance().getShopBoundary();
	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	/**
	 * save the items in cart controllers base on the product unique number
	 */
	private HashMap<Integer, ItemInCartController> itemInCartMap = new HashMap<Integer, ItemInCartController>();
	/**
	 * we give the customized products unique temp ids with negative numbers
	 */
	private int customizedItemID = 0;// give each customize item unique temp id
	/**
	 * save the customized products we created
	 */
	private HashMap<Integer, CustomizedProduct> CustomizedProducts = new HashMap<Integer, CustomizedProduct>();
	/**
	 * save the customozed products names
	 */
	private HashMap<String, Integer> customizedProductsNames = new HashMap<String, Integer>();
	/**
	 * list for the combo box items
	 */
	private ObservableList<String> comboBoxList = FXCollections.observableArrayList();
	/**
	 * save the currently edited customized product
	 */
	private CustomizedProduct currentCustomizedProduct;
	/**
	 * for the initial opening of the window
	 */
	private boolean isOpen = false;

	@FXML
	private VBox anniversaryFlowersBase;

	@FXML
	private HBox basePane;

	@FXML
	private VBox birthdayFlowerBase;

	@FXML
	private TabPane catalogTabPane;

	@FXML
	private VBox congratulationFlowersBase;

	@FXML
	private VBox newBabyFlowersBase;

	@FXML
	private Button placeOrderBtn;

	@FXML
	private VBox singleItemsBase;

	@FXML
	private VBox weddingFlowersbase;

	// shop tabs
	@FXML
	private Tab congratulationFlowersTab;

	@FXML
	private Tab singleItemsTab;

	@FXML
	private Tab weddingFlowersTab;

	@FXML
	private Tab birthdayFlowersTab;

	@FXML
	private Tab babyFlowersTab;

	@FXML
	private Tab AnniversaryFlowersTab;
	// cart tabs
	@FXML
	private Tab cartTab;

	@FXML
	private Tab CustomizedProductTab;
	// cart
	@FXML
	private TabPane cartTabPane;

	@FXML
	private VBox cartItemsList;

	@FXML
	private Label cartLabel;

	@FXML
	private VBox cartPane;

	@FXML
	private Label cartErrorLabel;

	// customized product
	@FXML
	private Button newCostumizedProduct;

	@FXML
	private Button addCPtoCart;

	@FXML
	private Label customizedProductErrorLabel;

	@FXML
	private Button saveChangeBtn;

	@FXML
	private TextField costumizedItemName;

	@FXML
	private VBox costumizedProductItemList;

	@FXML
	private ComboBox<String> costumizedProductsChooseList;

	@FXML
	private ComboBox<String> itemPriceRange;

	@FXML
	private ComboBox<String> itemColor;

	@FXML
	private ComboBox<String> itemType;

	/**
	 * place the order, only if the cart is not empty
	 * 
	 * @param event
	 */
	@FXML
	void PalceOrder(ActionEvent event) {
		if (itemInCartMap.isEmpty()) {
			cartErrorLabel.setText("The cart is empty!");
			return;
		}
		authorizedCustomerGuiManager.getBranch_Delivery().openWindow();
	}

	@FXML
	void selectCartTab(Event event) {

	}

	/**
	 * select category, get all the category products
	 * 
	 * @param event
	 */
	@FXML
	void selectCategoryTab(Event event) {
		if (!isOpen)
			return;
		ArrayList<Product> products;
		switch (((Tab) event.getSource()).getId()) {
		case "congratulationFlowersTab":
			products = shopBoundary.chooseCategory("congratulationFlowers");
			authorizedCustomerGuiManager.getProductManager().fillProductList(congratulationFlowersBase, products);
			break;
		case "singleItemsTab":
			products = shopBoundary.chooseCategory("singleItems");
			authorizedCustomerGuiManager.getProductManager().fillProductList(singleItemsBase, products);
			break;
		case "weddingFlowersTab":
			products = shopBoundary.chooseCategory("weddingFlowers");
			authorizedCustomerGuiManager.getProductManager().fillProductList(weddingFlowersbase, products);
			break;
		case "birthdayFlowersTab":
			products = shopBoundary.chooseCategory("birthdayFlowers");
			authorizedCustomerGuiManager.getProductManager().fillProductList(birthdayFlowerBase, products);
			break;
		case "babyFlowersTab":
			products = shopBoundary.chooseCategory("babyFlowers");
			authorizedCustomerGuiManager.getProductManager().fillProductList(newBabyFlowersBase, products);
			break;
		case "AnniversaryFlowersTab":
			products = shopBoundary.chooseCategory("AnniversaryFlowers");
			authorizedCustomerGuiManager.getProductManager().fillProductList(anniversaryFlowersBase, products);
			break;

		default:

			break;

		}
	}

	@Override
	public Pane getBasePane() {
		return null;
	}

	@Override
	public void resetController() {
		//
	}

	/**
	 * open the shop window, init all the fields, open the default category
	 */
	@Override
	public void openWindow() {
		// move to the next window
		mainWindowManager.mainWindowController.showNewWindow(basePane);
		// change to the name
		mainWindowManager.mainWindowController.changeWindowName("Shop");
		// get the first category
		ArrayList<Product> products = shopBoundary.chooseCategory("birthdayFlowers");
		authorizedCustomerGuiManager.getProductManager().fillProductList(birthdayFlowerBase, products);
		// set the customized product handling window
		if (!isOpen) {
			costumizedProductsChooseList.setItems(comboBoxList);
			String[] pricing = { "10-20", "20-30", "30-40", "40-50" };
			itemPriceRange.setItems(FXCollections.observableArrayList(pricing));
			String[] colors = { "colorful", "red", "blue", "green", "yellow", "purple", "pink" };
			itemColor.setItems(FXCollections.observableArrayList(colors));
			String[] types = { "Bouquet", "Arrangement", "Vase" };
			itemType.setItems(FXCollections.observableArrayList(types));
			itemPriceRange.setDisable(true);
			itemColor.setDisable(true);
			itemType.setDisable(true);
			saveChangeBtn.setDisable(true);
			addCPtoCart.setDisable(true);
		}
		isOpen = true;
		// if the customer is frozen/ not yet authorized
		if (UserBoundary.CurrentUser.getStatus().equals(UserStatus.Frozen)
				|| UserBoundary.CurrentUser.getUserType().equals(UserType.NonAuthorizedCustomer)) {
			placeOrderBtn.setDisable(true);
		} else {
			placeOrderBtn.setDisable(false);
		}
		cartErrorLabel.setText("");
	}

	public VBox getCartPane() {
		return cartPane;

	}

	/**
	 * add product to cart
	 * 
	 * @param p
	 * @param controller
	 */
	public void addProductGuiObjectToCart(Pane p, ItemInCartController controller) {
		cartItemsList.getChildren().add(p);
		itemInCartMap.put(controller.getProduct().getProductID(), controller);
		cartErrorLabel.setText("");
	}

	/**
	 * remove product from cart
	 * 
	 * @param p
	 */
	public void removeProductGuiObjectToCart(Pane p,int productID) {
		if (cartItemsList.getChildren().contains(p))
			cartItemsList.getChildren().remove(p);
		itemInCartMap.remove(productID);
		cartErrorLabel.setText("");
	}

	/**
	 * update the product amount in cart
	 * 
	 * @param productId
	 */
	public void updateAmount(int productId) {
		synchronized (itemInCartMap) {
			if (itemInCartMap.containsKey(productId)) {
				itemInCartMap.get(productId).updateAmount(shopBoundary.getAmount(productId));
			}
		}
		cartErrorLabel.setText("");
	}

	// handle customized product
	/**
	 * add product to the active customized product
	 * 
	 * @param product
	 */
	public void addProductToCustomizedProduct(Product product) {
		cartTabPane.getSelectionModel().select(CustomizedProductTab);
		if (currentCustomizedProduct == null) {
			updateCSError("Please select product first");
			return;
		}
		if (currentCustomizedProduct.getItems().contains(product)) {
			updateCSError("You alredy added " + product.getName());
			return;
		}
		currentCustomizedProduct.addItemToProduct(product);
		ItemInCustomizedProductController newController = authorizedCustomerGuiManager.getProductManager()
				.createNewCSItem(product);
		costumizedProductItemList.getChildren().add(newController.getBasePane());

	}

	/**
	 * remove product from the active customized product
	 * 
	 * @param product
	 * @param pane
	 */
	public void removeProductFromCustomizedProduct(Product product, Pane pane) {
		currentCustomizedProduct.getItems().remove(product);
		costumizedProductItemList.getChildren().remove(pane);
	}

	/**
	 * select active customized product
	 * 
	 * @param customizedProduct
	 */
	private void chooseCustomizedProduct(CustomizedProduct customizedProduct) {
		costumizedItemName.setDisable(true);
		// empty the list
		costumizedProductItemList.getChildren().removeAll(costumizedProductItemList.getChildren());
		currentCustomizedProduct = customizedProduct;
		updateCSError("");
		costumizedItemName.setText(customizedProduct.getName());
		for (Product p : customizedProduct.getItems()) {
			ItemInCustomizedProductController newController = authorizedCustomerGuiManager.getProductManager()
					.createNewCSItem(p);
			costumizedProductItemList.getChildren().add(newController.getBasePane());
		}
	}

	/**
	 * add customized product to cart
	 * 
	 * @param event
	 */
	@FXML
	void addCPToCart(ActionEvent event) {
		if (shopBoundary.addToCart(currentCustomizedProduct, 1))// add to cart
		{
			ItemInCartController controller = authorizedCustomerGuiManager.getProductManager()
					.createNewCartItem(currentCustomizedProduct, 1);
			addProductGuiObjectToCart(controller.getBasePane(), controller);
		} else {
			updateAmount(currentCustomizedProduct.getProductID());
		}
		cartTabPane.getSelectionModel().select(cartTab);
	}

	/**
	 * create new customized product
	 * 
	 * @param event
	 */
	@FXML
	void createNewCustomizedProduct(ActionEvent event) {
		currentCustomizedProduct = new CustomizedProduct(--customizedItemID, "");
		costumizedItemName.setText("newProduct" + (-customizedItemID));
		costumizedItemName.setDisable(false);
		costumizedProductItemList.getChildren().removeAll(costumizedProductItemList.getChildren());
		itemPriceRange.setDisable(false);
		itemColor.setDisable(false);
		itemType.setDisable(false);
		saveChangeBtn.setDisable(false);
		addCPtoCart.setDisable(false);
	}

	/**
	 * save changes to the customize product
	 * 
	 * @param event
	 */
	@FXML
	void saveChnages(ActionEvent event) {

		String name = costumizedItemName.getText();
		// for each existing customized product
		for (int i = -1; i >= customizedItemID; i--) {
			if (i != currentCustomizedProduct.getProductID()) {
				if (customizedProductsNames.containsKey(name)) {
					updateCSError("the name : " + name + "alredy exist");
					return;
				}
			}
		}
		if (currentCustomizedProduct.getItems().isEmpty()) {
			updateCSError("Your product contains nothing!");
			return;
		}
		if (customizedItemID != currentCustomizedProduct.getProductID()) {
			customizedProductsNames.remove(currentCustomizedProduct.getName());
			customizedProductsNames.put(name, currentCustomizedProduct.getProductID());
		}
		String range = itemPriceRange.getValue();
		if (range == null && customizedItemID == currentCustomizedProduct.getProductID()) {
			range = "10-20";
		}

		currentCustomizedProduct.setName(name);
		currentCustomizedProduct.setDescription("Customized Product");
		changePrice(range);
		// if its a new product
		if (customizedItemID == currentCustomizedProduct.getProductID()) {
			CustomizedProducts.put(customizedItemID, currentCustomizedProduct);
			customizedProductsNames.put(name, customizedItemID);
			comboBoxList.add(name);
			costumizedItemName.setDisable(true);
			updateCSError("Product saved!");
		}

	}

	/**
	 * update the customized product error label
	 * 
	 * @param s
	 */
	private void updateCSError(String s) {
		customizedProductErrorLabel.setText(s);
	}

	/**
	 * init the customized product tab
	 * 
	 * @param event
	 */
	@FXML
	void customizedProductSelected(ActionEvent event) {
		int id = customizedProductsNames.get(costumizedProductsChooseList.getValue());
		CustomizedProduct p = CustomizedProducts.get(id);
		chooseCustomizedProduct(p);
		itemPriceRange.setDisable(false);
		itemColor.setDisable(false);
		itemType.setDisable(false);
		saveChangeBtn.setDisable(false);
		addCPtoCart.setDisable(false);

	}

	/**
	 * choose price range
	 * 
	 * @param event
	 */
	@FXML
	void changeItemPrice(ActionEvent event) {
		String range = itemPriceRange.getValue();
		changePrice(range);

	}

	/**
	 * set the price for the price range
	 * 
	 * @param range
	 */
	private void changePrice(String range) {
		double topVal = 10, botVal = 20;
		if (range.equals("10-20")) {
			topVal = 20;
			botVal = 10;
		}
		if (range.equals("20-30")) {
			topVal = 30;
			botVal = 20;
		}
		if (range.equals("30-40")) {
			topVal = 40;
			botVal = 30;
		}
		if (range.equals("40-50")) {
			topVal = 50;
			botVal = 40;
		}
		currentCustomizedProduct.choosePrice(topVal, botVal);
	}

	/**
	 * change the active customized product color
	 * 
	 * @param event
	 */
	@FXML
	void changeItemColor(ActionEvent event) {
		String color = itemColor.getValue();
		currentCustomizedProduct.setColors(color);
	}

	/**
	 * change the active customized product type
	 * 
	 * @param event
	 */
	@FXML
	void changeItemType(ActionEvent event) {
		String type = itemType.getValue();
		currentCustomizedProduct.setType(type);
	}

	/**
	 * remove all from the cart gui
	 */
	public void emptyCart() {
		itemInCartMap = new HashMap<Integer, ItemInCartController>();
		
		cartItemsList.getChildren().clear();
	}

}
