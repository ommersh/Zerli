package userGuiManagment;

import courier.CourierControllerGUI;
import main.GuiObjectsFactory;
import usersManagment.CourierBoundary;

/**
 * Singleton, manage all the different courier windows, for each window
 * controller when trying to get the controller, load the fxml if not already
 * loaded
 * 
 * @author halel
 *
 */
public class CourierGuiManager implements IUserGuiManager {

	private static CourierGuiManager courierGuiManager;

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();

	// gui controllers
	public CourierControllerGUI courierConfirmDelivery;
	// Boundaries
	public CourierBoundary courierBoundary;

	private CourierGuiManager() {

	}

	public static CourierGuiManager getInstance() {
		if (courierGuiManager == null) {
			courierGuiManager = new CourierGuiManager();
		}
		return courierGuiManager;
	}

	public CourierControllerGUI getCourierConfirmDelivery() {
		if (courierConfirmDelivery == null) {
			courierConfirmDelivery = (CourierControllerGUI) guiObjectsFactory.loadFxmlFile("/courier/CourierGUI.fxml");
		}
		return courierConfirmDelivery;
	}

	public CourierBoundary getCourierBoundary() {
		if (courierBoundary == null) {
			courierBoundary = new CourierBoundary();
		}
		return courierBoundary;
	}

	@Override
	public void logout() {
		courierConfirmDelivery = null;
		courierBoundary = null;
	}

}
