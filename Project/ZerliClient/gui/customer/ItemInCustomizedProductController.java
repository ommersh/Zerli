package customer;

import catalog.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.IGuiController;
import userGuiManagment.AuthorizedCustomerGuiManager;

/**
 * the controller for item in customized product gui object
 * 
 * @author halel
 *
 */
public class ItemInCustomizedProductController implements IGuiController {

	private ShopWindowController shopWindowController = AuthorizedCustomerGuiManager.getInstance()
			.getShopWindowController();

	@FXML
	private HBox baseLayer;

	@FXML
	private ImageView deleteBtn;

	@FXML
	private Label productNameLabel;

	@FXML
	private Button removeBtn;

	private Product product;

	@FXML
	void removeItem(ActionEvent event) {
		shopWindowController.removeProductFromCustomizedProduct(product, baseLayer);
	}

	public void setProduct(Product product) {
		productNameLabel.setText(product.getName());
		this.product = product;
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

	public Product getProduct() {
		return product;
	}

}
