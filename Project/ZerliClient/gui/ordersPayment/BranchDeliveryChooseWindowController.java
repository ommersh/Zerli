package ordersPayment;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import main.IGuiController;
import shop.ShopBoundary;
import userGuiManagment.AuthorizedCustomerGuiManager;
import userGuiManagment.MainWindowGuiManager;

/**
 * controller for the window: choosing the branch + home delivery or pickup
 *
 */
public class BranchDeliveryChooseWindowController implements IGuiController {

	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private ShopBoundary shopBoundary = AuthorizedCustomerGuiManager.getInstance().getShopBoundary();
	private AuthorizedCustomerGuiManager authorizedCustomerGuiManager = AuthorizedCustomerGuiManager.getInstance();

	@FXML
	private DatePicker StorePickupDate;

	@FXML
	private AnchorPane basePane;

	@FXML
	private CheckBox GreetingCardChoosen;

	@FXML
	private Button chooseButton;

	@FXML
	private RadioButton storePickupToggle;

	@FXML
	private ToggleGroup DeliveryOption;

	@FXML
	private RadioButton homeDeliverytoggle;

	@FXML
	private Button backButton;

	@FXML
	private ComboBox<String> BranchChosen;

	@FXML
	private ComboBox<String> pickupHour;

	@FXML
	private ComboBox<String> minPickup;

	@FXML
	private Label ErrorText;

	private Timestamp arrivalDate = new Timestamp(0);

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {
		ErrorText.setText("");
		GreetingCardChoosen.setSelected(false);

	}

	@Override
	public void openWindow() {
		initMywindow();
		mainWindowManager.mainWindowController.showNewWindow(basePane);
		mainWindowManager.mainWindowController.changeWindowName("Branch&DeliveryChoose");

	}

	public void initMywindow() {
		StorePickupDate.setDisable(false);
		if (BranchChosen.getItems().isEmpty()) {
			ArrayList<String> branchList = shopBoundary.getAllBranches();
			BranchChosen.getItems().addAll(branchList);
		}
		initComboBoxofMin_HourAndDatePicker();
	}

	private void initComboBoxofMin_HourAndDatePicker() {

		StorePickupDate.setValue(LocalDate.now()); // the today date is the default of date picker

		// this code make the dates before today disable witch mean user cant choose
		// dates before today
		final Callback<DatePicker, DateCell> day = (DatePicker) -> new DateCell() {

			@Override
			public void updateItem(LocalDate item, boolean empty) {
				super.updateItem(item, empty);
				if (item.isBefore(LocalDate.now())) {
					setDisable(true); // disable dates before today

				}
			}
		};

		StorePickupDate.setDayCellFactory(day);

		int i;
		for (i = 9; i < 24; i++) // this init the combobox of hours (branch open in 9 o'clock :) )
		{
			if (i < 10) {
				pickupHour.getItems().add("0" + i);
			} else
				pickupHour.getItems().add("" + i);

		}

		for (i = 0; i < 59; i = i + 5) // init the combobox of min
		{
			if (i < 10) {
				minPickup.getItems().add("0" + i);
			} else
				minPickup.getItems().add("" + i);

		}
	}

	@FXML
	void HomeDeliveryChoose(ActionEvent event) {
		StorePickupDate.setDisable(true);
		pickupHour.setDisable(true);
		minPickup.setDisable(true);
	}

	@FXML
	void storPickupChoose(ActionEvent event) {
		StorePickupDate.setDisable(false);
		pickupHour.setDisable(false);
		minPickup.setDisable(false);
	}

	@FXML
	void backButtonPressed(ActionEvent event) {
		authorizedCustomerGuiManager.getShopWindowController().openWindow();
	}

	@FXML
	void chooseButtonPressed(ActionEvent event) {

		String chosenBranch = BranchChosen.getValue();
		try {
			if (!chosenBranch.isEmpty())
				shopBoundary.chooseBranch(chosenBranch);
		} catch (Exception e) {
			ErrorText.setText("Please Choose Branch!!!");
			return;
		}
		if (GreetingCardChoosen.isSelected()) {
			shopBoundary.selectPersonalCard(true);
		} else // default is false but in case user choose to add then make back and choose not
				// to add
		{
			shopBoundary.selectPersonalCard(false);
		}

		if (homeDeliverytoggle.isSelected()) {
			shopBoundary.selectDeliveryOption(true);
		}
		if (storePickupToggle.isSelected()) { // if customer choose storePickup then check if time is correct
			try {
				LocalDate date = StorePickupDate.getValue();
				LocalTime time = LocalTime.of(Integer.parseInt(pickupHour.getValue()),
						Integer.parseInt(minPickup.getValue()));
				if (!checkIfCorrectDateAndTime(date, time)) {
					return;
				}
				arrivalDate = Timestamp.valueOf(LocalDateTime.of(date, time));
				shopBoundary.submitDetailsForArivalDate(arrivalDate);
				shopBoundary.selectDeliveryOption(false);
			} catch (Exception e) {
				ErrorText.setText("please choose time!!!");
				return;
			}
		}
		// -----------------------------------------------------------------------

		if (shopBoundary.isPersonalCardflag()) {
			authorizedCustomerGuiManager.getPersonalCardcontroller().openWindow();
		} else if (shopBoundary.isHomeDeliveryflag()) {
			shopBoundary.addPersonalLetter("");
			authorizedCustomerGuiManager.getHomeDeliveryDetails().openWindow();
		} else {
			authorizedCustomerGuiManager.order = shopBoundary.placeOrder(); // in case the user didn't put v in
																			// homeDelivery and Personal card
																			// then we do there placeOrder
			authorizedCustomerGuiManager.getConfirmOrder().openWindow();
		}

	}

	private boolean checkIfCorrectDateAndTime(LocalDate date, LocalTime time) {
		if (date.isEqual(LocalDate.now()) && LocalTime.now().compareTo(time) > 0) {
			ErrorText.setText("please choose Correct time!!!");
			return false;
		}
		return true;
	}
}
