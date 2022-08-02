package customer;

import catalog.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.IGuiController;
import shop.ShopBoundary;
import userGuiManagment.AuthorizedCustomerGuiManager;

/**
 * controller for the Item in cart gui object, for item in cart with all the
 * fields and options
 * 
 * @author halel
 *
 */
public class ItemInCartController implements IGuiController {
	private ShopBoundary shopBoundary = AuthorizedCustomerGuiManager.getInstance().getShopBoundary();
	private ShopWindowController shopWindowController = AuthorizedCustomerGuiManager.getInstance()
			.getShopWindowController();

	/**
	 * the product our item represents
	 */
	private Product product;

	/**
	 * the current amount of the product
	 */
	private int currentAmount;

	@FXML
	private Label productNameLabel;

	@FXML
	private TextField amountTextField;

	@FXML
	private HBox baseLayer;

	@FXML
	private ImageView deleteBtn;

	@FXML
	private Button removeBtn;

	@FXML
	private Label priceLabel;

	/**
	 * change the amount
	 * 
	 * @param event
	 */
	@FXML
	void changeAmount(InputMethodEvent event) {
		try {
			int amount = Integer.valueOf(amountTextField.getText());
			if (amount < 0)
				amount = 0;
			currentAmount = amount;
			shopBoundary.chooseAmount(product, currentAmount);
			amountTextField.setText("" + shopBoundary.getAmount(product.getProductID()));

		} catch (Exception e) {
			amountTextField.setText(currentAmount + "");
		}
	}

	/**
	 * remove the item from the cart
	 * 
	 * @param event
	 */
	@FXML
	void removeItem(ActionEvent event) {
		if (shopBoundary.deleteProductFromCart(product)) {
			shopWindowController.removeProductGuiObjectToCart(this.baseLayer, product.getProductID());
		}
	}

	@Override
	public Pane getBasePane() {
		return baseLayer;
	}

	@Override
	public void resetController() {

	}

	@Override
	public void openWindow() {

	}

	/**
	 * init the product we represents
	 * 
	 * @param product
	 * @param amount
	 */
	public void setProduct(Product product, int amount) {
		this.product = product;
		productNameLabel.setText(product.getName());
		amountTextField.setText(amount + "");
		priceLabel.setText(product.getPrice() + "");
	}

	public Product getProduct() {
		return product;
	}

	/**
	 * update the amount(if added more from this product)
	 * 
	 * @param amount
	 */
	public void updateAmount(int amount) {
		if (amount < 0)
			amount = 0;
		currentAmount = amount;
		amountTextField.setText(currentAmount + "");
	}

}
