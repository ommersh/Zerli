package catalogManegment;

import catalog.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import userGuiManagment.MainWindowGuiManager;
import userGuiManagment.MarketingGuiManager;
import usersManagment.MarketingEmployeeBoundary;

/**
 * controller for the window: view the and choose from the catalog window
 *
 */
public class ShowCatagoryFromCatalogContorllerGUI implements IGuiController {
	private MarketingGuiManager marketingEmployeeGuiManager = MarketingGuiManager.getInstance();
	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private MarketingEmployeeBoundary marketingEmployeeBoundary = marketingEmployeeGuiManager
			.getMarketingEmployeeBoundary();

	private ObservableList<Product> productObs = FXCollections.observableArrayList();

	@FXML
	private AnchorPane showCatagoryPane;

	@FXML
	private Label CatagoryNameLabel;

	@FXML
	private Button backBnt;

	@FXML
	private Button editProductBnt;

	@FXML
	private ComboBox<String> chooseCatagoryComboBox;

	@FXML
	private TableColumn<Product, String> colorsCol;

	@FXML
	private TableColumn<Product, String> descriptionCol;

	@FXML
	private TableColumn<Product, Double> priceCol;

	@FXML
	private TableColumn<Product, Integer> productIdCol;

	@FXML
	private TableColumn<Product, String> productNameCol;

	@FXML
	private TableView<Product> productsTable;

	@FXML
	private Label errorLabel;

	@FXML
	private Button createNewBtn;

	@FXML
	void createNewProduct(ActionEvent event) {
		errorLabel.setText("");
		marketingEmployeeGuiManager.opedNewCreateProductWindow();
	}

	@FXML
	void goBack(ActionEvent event) {
		mainWindowManager.userHomeWindowController.openWindow();
	}

	@FXML
	void chooseCatagory(ActionEvent event) {
		String category = chooseCatagoryComboBox.getSelectionModel().getSelectedItem();
		if (category == null)
			return;
		productObs.setAll(marketingEmployeeBoundary.getCategory(category));
		productsTable.setItems(productObs);
		mainWindowManager.mainWindowController.changeWindowName("Manage catalog - " + category);
	}

	@FXML
	void showProductToEdit(ActionEvent event) {
		Product product = productsTable.getSelectionModel().getSelectedItem();
		if (product == null) {
			errorLabel.setText("Please choose product first!");
			return;
		}
		errorLabel.setText("");
		marketingEmployeeGuiManager.opedNewEditProductWindow(product);
	}

	public void initializeTable() {
		productObs.clear();
		productsTable.getItems().clear();
		productIdCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
		productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		colorsCol.setCellValueFactory(new PropertyValueFactory<>("colors"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
	}

	@Override
	public void openWindow() {
		initializeTable();
		mainWindowManager.mainWindowController.changeWindowName("Manage catalog");
		mainWindowManager.mainWindowController.showNewWindow(showCatagoryPane);
		initComboBox();
		errorLabel.setText("");
	}

	@Override
	public Pane getBasePane() {
		return showCatagoryPane;
	}

	@Override
	public void resetController() {

	}

	private void initComboBox() {
		String[] s = { "congratulationFlowers", "AnniversaryFlowers", "babyFlowers", "birthdayFlowers",
				"weddingFlowers", "singleItems", "RemovedFromCatalog" };
		chooseCatagoryComboBox.getItems().setAll(s);
	}

}
