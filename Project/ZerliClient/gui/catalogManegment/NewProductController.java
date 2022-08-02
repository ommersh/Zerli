package catalogManegment;

import catalog.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import main.IGuiController;
import userGuiManagment.MainWindowGuiManager;
import userGuiManagment.MarketingGuiManager;
import usersManagment.MarketingEmployeeBoundary;

/**
 * controller for the window: create new product window
 *
 */
public class NewProductController implements IGuiController {

	private MarketingGuiManager marketingEmployeeGuiManager = MarketingGuiManager.getInstance();
	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private MarketingEmployeeBoundary marketingEmployeeBoundary = marketingEmployeeGuiManager
			.getMarketingEmployeeBoundary();

	@FXML
	private Button backBtn;

	@FXML
	private ComboBox<String> catagoryComboBox;

	@FXML
	private TextField colorTxt;

	@FXML
	private TextField descriptionTxt;

	@FXML
	private Pane editProductPane;

	@FXML
	private Label erorrLabel;

	@FXML
	private Label errorlabel;

	@FXML
	private TextField nameOfProductTxt;

	@FXML
	private TextField priceTxt;

	@FXML
	private Label promotionWarning;

	@FXML
	private Button saveBnt;

	@FXML
	private Label titleLAbel;

	@FXML
	void goBack(ActionEvent event) {
		marketingEmployeeGuiManager.getManageCatalogController().openWindow();
	}

	@FXML
	void saveChangesOfProduct(ActionEvent event) {
		Product selectedProduct = new Product(-1);
		String temp = null;
		// get the name
		temp = nameOfProductTxt.getText();
		if (temp.equals("") || temp == null) {
			errorlabel.setText("Please add a name");
			return;
		}
		selectedProduct.setName(temp);
		// get the price
		temp = priceTxt.getText();
		double price = 0;
		try {
			price = Double.valueOf(temp);
		} catch (Exception e) {
			errorlabel.setText("Price must be a number");
			return;
		}
		if (price < 0) {
			errorlabel.setText("Price can't be negative number");
			return;
		}
		selectedProduct.setPrice(price);
		selectedProduct.setOldPrice(0);
		// get the category (if not selected stay the same
		temp = catagoryComboBox.getSelectionModel().getSelectedItem();
		if (temp != null)
			selectedProduct.setCategory(temp);
		else {
			errorlabel.setText("Must choose category!");
			return;
		}
		// get the colors
		temp = colorTxt.getText();
		if (temp.equals("") || temp == null) {
			errorlabel.setText("Please add colors");
			return;
		}
		selectedProduct.setColors(temp);
		// get the description
		temp = descriptionTxt.getText();
		if (temp.equals("") || temp == null) {
			errorlabel.setText("Please add description");
			return;
		}
		selectedProduct.setDescription(temp);
		// we got them all! lets save the product
		try {
			marketingEmployeeBoundary.addProduct(selectedProduct);
			errorlabel.setText("Product saved!");
		} catch (Exception e) {
			errorlabel.setText(e.getMessage());
		}
	}

	@Override
	public Pane getBasePane() {
		return editProductPane;
	}

	@Override
	public void resetController() {

	}

	@Override
	public void openWindow() {
		initComboBox();
		mainWindowManager.mainWindowController.changeWindowName("Crate new product");
		mainWindowManager.mainWindowController.showNewWindow(editProductPane);

	}

	private void initComboBox() {
		String[] s = { "congratulationFlowers", "AnniversaryFlowers", "babyFlowers", "birthdayFlowers",
				"weddingFlowers", "singleItems", "RemovedFromCatalog" };
		catagoryComboBox.getItems().setAll(s);

	}

}
