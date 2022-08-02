package ordersPayment;

import java.sql.Timestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import main.IGuiController;
import order.DeliveryDetails;
import shop.ShopBoundary;
import userGuiManagment.AuthorizedCustomerGuiManager;
import userGuiManagment.MainWindowGuiManager;

/**
 * controller for the window: the delivery details form
 *
 */
public class HomeDeliveryWindowController implements IGuiController {

	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private ShopBoundary shopBoundary = AuthorizedCustomerGuiManager.getInstance().getShopBoundary();
	private AuthorizedCustomerGuiManager authorizedCustomerGuiManager = AuthorizedCustomerGuiManager.getInstance();

	@FXML
	private AnchorPane basePane;

	@FXML
	private DatePicker deliveryDate;

	@FXML
	private ComboBox<String> hour;

	@FXML
	private ComboBox<String> min;

	@FXML
	private TextArea comments;

	@FXML
	private Button backbutt;

	@FXML
	private Button nextbutt;

	@FXML
	private TextField firstname;

	@FXML
	private TextField lastname;

	@FXML
	private TextField city;

	@FXML
	private TextField street;

	@FXML
	private TextField streetnum;

	@FXML
	private TextField zipcode;

	@FXML
	private TextField phonenum;

	@FXML
	private Label errorLabel;

	public DeliveryDetails deliveryDetails = new DeliveryDetails();
	private Timestamp arrivalDate = new Timestamp(0);

	private List<TextField> textFields;

	/**
	 * this method happen when the user press in back button it back to personalcard
	 * window if the user was choose to add a personal card else back to order
	 * window
	 * 
	 * @param event
	 */

	@FXML
	void backbuttpress(ActionEvent event) {
		// in case personal card was chosen by user
		if (shopBoundary.isPersonalCardflag())
			authorizedCustomerGuiManager.getPersonalCardcontroller().openWindow();
		else
			authorizedCustomerGuiManager.getBranch_Delivery().openWindow();
	}

	/**
	 * this method happen when the user press in next button it move to ConfirmOrder
	 * window. we first check if all important Fields are filled if true then we
	 * send data to shop boundary to save, false we not accept to move to next
	 * window
	 *
	 * @param event
	 */

	@FXML
	void nextbuttpress(ActionEvent event) {
		textFields = Arrays.asList(firstname, lastname, street, streetnum, zipcode, city, zipcode);
		boolean stat = saveDetails();
		if (!stat) // in case there are field is empty
			return;
		shopBoundary.sumbmitDetailsForHomeDelivery(deliveryDetails);
		shopBoundary.submitDetailsForArivalDate(arrivalDate);
		authorizedCustomerGuiManager.order = shopBoundary.placeOrder();
		authorizedCustomerGuiManager.getConfirmOrder().openWindow();

	}

	/**
	 * this method save the delivery details of the text field in deliveryDetails
	 * 
	 * @return false:if save not success, true if success
	 */
	private boolean saveDetails() {

		if (!checkifEmpty())
			return false;
		deliveryDetails.setFirstName(firstname.getText());
		deliveryDetails.setLastName(lastname.getText());
		deliveryDetails.setComments(comments.getText());
		deliveryDetails.setPhoneNumber(phonenum.getText());
		try {
			deliveryDetails.setAddress(street.getText() + " " + Integer.parseInt(streetnum.getText()) + " "
					+ city.getText() + " " + Integer.parseInt(zipcode.getText()));

			LocalDate date = deliveryDate.getValue();// delivery details should have LocalDate Variable and hour and min

			LocalTime time = LocalTime.of(Integer.parseInt(hour.getValue()), Integer.parseInt(min.getValue()));

			if (!checkIfCorrectDateAndTime(date, time))
				return false;

			arrivalDate = Timestamp.valueOf(LocalDateTime.of(date, time));
		} catch (Exception e) {
			errorLabel.setText(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * this private method check if any text field are empty and in case there is
	 * empty color the border of empty text field red
	 * 
	 * @return false:if anytxt empty , true: if every field is full
	 */
	private boolean checkifEmpty() {
		Boolean stat = true;
		for (TextField txt : textFields) {
			if (txt.getText().isEmpty()) {
				txt.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
				stat = false;
			} else {
				txt.setStyle("-fx-background-color: -fx-control-inner-background;");
			}
		}
		String comboEmpty = hour.getValue();
		if (comboEmpty == null) {
			stat = false;

			hour.setStyle("-fx-border-color: #B22222");
		} else {
			hour.setStyle("-fx-background-color: -fx-control-inner-background");
		}
		comboEmpty = min.getValue();
		if (comboEmpty == null) {
			stat = false;
			min.setStyle("-fx-border-color: #B22222");
		} else {
			min.setStyle("-fx-background-color: -fx-control-inner-background");
		}
		return stat;
	}

	private boolean checkIfCorrectDateAndTime(LocalDate date, LocalTime time) {
		if (date.isEqual(LocalDate.now()) && LocalTime.now().compareTo(time) > 0) {
			errorLabel.setText("please choose Correct time!!!");
			return false;
		}
		return true;
	}

	/**
	 * in this method we init the combobox of Hour and min in Delivery time in
	 * addition in datePicker can choose just from today date
	 */
	private void initcomboboxesAndDate() {
		deliveryDate.setValue(LocalDate.now()); // the today date is the default of date picker

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

		deliveryDate.setDayCellFactory(day);

		int i;
		for (i = 1; i < 24; i++) // this init the combobox of hours
		{
			if (i < 10) {
				hour.getItems().add("0" + i);
			} else
				hour.getItems().add("" + i);

		}

		for (i = 0; i < 59; i = i + 5) // init the combobox of min
		{
			if (i < 10) {
				min.getItems().add("0" + i);
			} else
				min.getItems().add("" + i);

		}

	}

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {
		firstname.setText("");
		lastname.setText("");
		city.setText("");
		street.setText("");
		streetnum.setText("");
		zipcode.setText("");
		phonenum.setText("");
		comments.setText("");
		errorLabel.setText("");

	}

	@Override
	public void openWindow() {
		mainWindowManager.mainWindowController.showNewWindow(basePane);
		mainWindowManager.mainWindowController.changeWindowName("homeDelivery");
		initcomboboxesAndDate();

	}

}
