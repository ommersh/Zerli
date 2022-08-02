package clientHandlers;

import java.util.ArrayList;

import catalog.Product;
import catalogManagment.CatalogController;
import msg.Msg;
import promotionManagment.PromotionManager;
import server.ServerMsgController;

/**
 * handle the marketing employee, can use and edit the catalog and manage
 * promotions
 */
public class MarketingEmployeeTask extends ClientTasks {

	/**
	 * to handle the catalog
	 */
	private CatalogController catalogController;
	/**
	 * to handle promotions
	 */
	private PromotionManager promotionManager;

	public MarketingEmployeeTask(HandleClientTask clientTaskHandler) {
		super(clientTaskHandler);
		promotionManager = new PromotionManager();
		catalogController = new CatalogController();
	}

	/**
	 * handle the marketing employee, can use and edit the catalog and manage
	 * promotions
	 */
	@Override
	public Msg handleTask(ServerMsgController msgController) {

		switch (msgController.getType()) {
		case ACTIVATE_PROMOTION:
			newMsgToSend = promotionManager.activatePromotion(msgController.getPromotionNumber());
			break;
		case CREATE_NEW_PROMOTION:
			newMsgToSend = promotionManager.createNewPromotion(msgController.getPromotion());
			break;
		case END_PROMOTION:
			newMsgToSend = promotionManager.deactivatePromotion(msgController.getPromotionNumber());
			break;
		case GET_ALL_PROMOTIONS:
			newMsgToSend = promotionManager.getAllPromotions();
			break;
		case GET_CATALOG_PAGE:
			// get catalog page
			ArrayList<Product> catalog = catalogController.getCatalogCategory(msgController.getCategory());
			newMsgToSend = ServerMsgController.createRETURN_CATALOG_PAGEMsg(catalog);
			break;
		case UPDATE_CATALOG:
			try {
				catalogController.updateProduct(msgController.getProduct());
				newMsgToSend = CompletedMsg;
			} catch (Exception e) {
				newMsgToSend = ServerMsgController.createERRORMsg(e.getMessage());
			}
			break;
		case ADD_TO_CATALOG:
			try {
				catalogController.addNewProduct(msgController.getProduct());
				newMsgToSend = CompletedMsg;
			} catch (Exception e) {
				newMsgToSend = ServerMsgController.createERRORMsg(e.getMessage());
			}
			break;
		case REMOVE_FROM_CATALOG:
			try {
				catalogController.deleteProduct(msgController.getProductNumber());
				newMsgToSend = CompletedMsg;
			} catch (Exception e) {
				newMsgToSend = ServerMsgController.createERRORMsg(e.getMessage());
			}
			break;
		default:
			// handle cant do it
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;
		}
		return newMsgToSend;
	}

}
