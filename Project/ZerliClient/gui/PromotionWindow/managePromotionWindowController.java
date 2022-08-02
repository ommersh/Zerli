package PromotionWindow;

import java.sql.Timestamp;
import common.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import promotion.Promotion;
import userGuiManagment.MainWindowGuiManager;
import userGuiManagment.MarketingGuiManager;

/**
 * controller for the window: manage promotion window
 *
 */
public class managePromotionWindowController implements IGuiController {

	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private MarketingGuiManager marketingGuiManager = MarketingGuiManager.getInstance();

	@FXML
	private AnchorPane basePane;

	@FXML
	private TableView<Promotion> ActivatePromotionTable;

	@FXML
	private TableColumn<Promotion, Integer> PromotionIDcol;

	@FXML
	private TableColumn<Promotion, Double> DiscountCol;

	@FXML
	private TableColumn<Promotion, String> PromotionTextCol;

	@FXML
	private TableColumn<Promotion, Status> statusCol;

	@FXML
	private TableColumn<Promotion, Timestamp> CreationTimeCol;

	@FXML
	private TableColumn<Promotion, Integer> ProductIDCol;

	@FXML
	private Button DeActivateButton;

	@FXML
	private Button ActivateButton;

	@FXML
	private Label errorLabel;

	private ObservableList<Promotion> data = FXCollections.observableArrayList();

	private Promotion selectedCol;

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {
		ActivatePromotionTable.getItems().clear();
		selectedCol = null;
		errorLabel.setText("");

	}

	@Override
	public void openWindow() {
		initmywindow();
		mainWindowManager.mainWindowController.showNewWindow(basePane);
		mainWindowManager.mainWindowController.changeWindowName("ActivatePromotion");

	}

	public void initmywindow() {
		data.clear();
		ActivatePromotionTable.getItems().clear();
		data.setAll(marketingGuiManager.getMarketingEmployeeBoundary().getAllPromotions());
		PromotionIDcol.setCellValueFactory(new PropertyValueFactory<>("promotionNumber"));
		DiscountCol.setCellValueFactory(new PropertyValueFactory<>("discount"));
		PromotionTextCol.setCellValueFactory(new PropertyValueFactory<>("promotionText"));
		statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		CreationTimeCol.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
		ProductIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
		ActivatePromotionTable.setItems(data);

	}

	@FXML
	void ActivateButton(ActionEvent event) {
		selectedCol = ActivatePromotionTable.getSelectionModel().getSelectedItem();
		if (selectedCol.getStatus() == Status.Canceled) {
			try {
				marketingGuiManager.getMarketingEmployeeBoundary().activatePromotion(selectedCol.getPromotionNumber());
				initmywindow();
				errorLabel.setText("Promotion activated!");
			} catch (Exception e) {
				errorLabel.setText(e.getMessage());
				return;
			}
		} else if (selectedCol.getStatus() == Status.Active) {
			errorLabel.setText("the promotion is already active");
			return;
		}
	}

	@FXML
	void DeActivateButton(ActionEvent event) {
		selectedCol = ActivatePromotionTable.getSelectionModel().getSelectedItem();
		if (selectedCol.getStatus() == Status.Active) {
			try {
				marketingGuiManager.getMarketingEmployeeBoundary()
						.deActivatePromotion(selectedCol.getPromotionNumber());
				initmywindow();
				errorLabel.setText("Promotion deactivated!");
			} catch (Exception e) {
				errorLabel.setText(e.getMessage());
				return;
			}
		} else if (selectedCol.getStatus() == Status.Canceled) {
			errorLabel.setText("the promotion is already not active");
			return;
		}
	}

}
