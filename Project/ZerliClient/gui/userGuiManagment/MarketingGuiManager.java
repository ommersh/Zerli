package userGuiManagment;

import PromotionWindow.CreatePromotionWindowController;
import PromotionWindow.PromotionCreatedSuccessfullyWindowController;
import PromotionWindow.managePromotionWindowController;
import catalog.Product;
import catalogManegment.EditProductContorller;
import catalogManegment.NewProductController;
import catalogManegment.ShowCatagoryFromCatalogContorllerGUI;
import main.GuiObjectsFactory;
import usersManagment.MarketingEmployeeBoundary;

/**
 * Singleton, manage all the different marketing employee windows, for each
 * window controller when trying to get the controller, load the fxml if not
 * already loaded
 * 
 * @author halel
 *
 */
public class MarketingGuiManager implements IUserGuiManager {

	private static MarketingGuiManager marketingGuiManager;

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	// the controllers
	private CreatePromotionWindowController createPromotion;
	private managePromotionWindowController managePromotions;
	private PromotionCreatedSuccessfullyWindowController promotionCreatedSuccessfully;
	private ShowCatagoryFromCatalogContorllerGUI manageCatalogController;

	// Boundaries
	private MarketingEmployeeBoundary marketingEmployeeBoundary;

	private MarketingGuiManager() {

	}

	public static MarketingGuiManager getInstance() {
		if (marketingGuiManager == null)
			marketingGuiManager = new MarketingGuiManager();
		return marketingGuiManager;
	}

	public CreatePromotionWindowController getCreatePromotion() {
		if (createPromotion == null) {
			createPromotion = (CreatePromotionWindowController) guiObjectsFactory
					.loadFxmlFile("/PromotionWindow/createNewPromotion.fxml");
		}
		return createPromotion;
	}

	public managePromotionWindowController getManagePromotions() {
		if (managePromotions == null) {
			managePromotions = (managePromotionWindowController) guiObjectsFactory
					.loadFxmlFile("/PromotionWindow/managePromotionsWindow.fxml");
		}
		return managePromotions;
	}

	public MarketingEmployeeBoundary getMarketingEmployeeBoundary() {
		if (marketingEmployeeBoundary == null) {
			marketingEmployeeBoundary = new MarketingEmployeeBoundary();
		}
		return marketingEmployeeBoundary;
	}

	public PromotionCreatedSuccessfullyWindowController getPromotionCreatedSuccessfully() {
		if (promotionCreatedSuccessfully == null) {
			promotionCreatedSuccessfully = (PromotionCreatedSuccessfullyWindowController) guiObjectsFactory
					.loadFxmlFile("/PromotionWindow/AddedSuccessfully.fxml");
		}
		return promotionCreatedSuccessfully;
	}

	public ShowCatagoryFromCatalogContorllerGUI getManageCatalogController() {
		if (manageCatalogController == null) {
			manageCatalogController = (ShowCatagoryFromCatalogContorllerGUI) guiObjectsFactory
					.loadFxmlFile("/catalogManegment/UpdateTheCatalogPage.fxml");
		}
		return manageCatalogController;
	}

	public void opedNewEditProductWindow(Product product) {
		EditProductContorller controller = (EditProductContorller) GuiObjectsFactory.getInstance()
				.loadFxmlFile("/catalogManegment/EditProductPage.fxml");
		controller.setSelectedProduct(product);
		controller.openWindow();
	}

	public void opedNewCreateProductWindow() {
		NewProductController controller = (NewProductController) GuiObjectsFactory.getInstance()
				.loadFxmlFile("/catalogManegment/NewProductWindow.fxml");
		controller.openWindow();
	}

	@Override
	public void logout() {
		createPromotion = null;
		managePromotions = null;
		marketingEmployeeBoundary = null;
	}
}
