package promotionManagment;

import catalog.Product;
import common.Status;
import database.DBController;
import msg.Msg;
import msg.MsgType;
import promotion.Promotion;
import server.ServerMsgController;

/**
 * Manage promotion, creation\activation\deactivation
 *
 */
public class PromotionManager {
	private Msg errorMsg = new Msg();
	private Msg completedMsg = new Msg();

	public PromotionManager() {
		errorMsg.type = MsgType.ERROR;
		errorMsg.data = "";
		completedMsg.type = MsgType.COMPLETED;
	}

	/**
	 * create new promotion, if possible
	 * 
	 * @param promotion
	 * @return the result msg
	 */
	public Msg createNewPromotion(Promotion promotion) {
		DBController dbController = DBController.getInstance();
		if (!activePromotionInDB(promotion)) {
			return errorMsg;
		}
		if (dbController.savePromotion(promotion) != -1) {
			return completedMsg;
		}
		return errorMsg;
	}

	/**
	 * activate existing promotion
	 * 
	 * @param promotionNumber
	 * @return the result msg
	 */
	public Msg activatePromotion(int promotionNumber) {
		DBController dbController = DBController.getInstance();
		Promotion promotion = dbController.getPromotion(promotionNumber);
		// 1.check if the promotion exist
		if (promotion == null) {
			errorMsg.data = "Promotion not found";
			return errorMsg;
		}
		// 2. check if the promotion is already active
		if (promotion.getStatus() == Status.Active) {
			errorMsg.data = "The promotion is already active";
			return errorMsg;
		}
		// 3. try activating the promotion
		if (!activePromotionInDB(promotion)) {
			return errorMsg;
		}
		// 4. updating the promotion status
		if (dbController.updatePromotion(promotionNumber, Status.Active)) {
			return completedMsg;
		}
		return errorMsg;

	}

	/**
	 * deactivate promotion
	 * 
	 * @param promotionNumber
	 * @return the result msg
	 */
	public Msg deactivatePromotion(int promotionNumber) {
		DBController dbController = DBController.getInstance();
		Promotion promotion = dbController.getPromotion(promotionNumber);
		// 1.check if the promotion exist
		if (promotion == null) {
			errorMsg.data = "Promotion not found";
			return errorMsg;
		}
		// 2. check if the promotion is already not active
		if (promotion.getStatus() == Status.Canceled) {
			errorMsg.data = "The promotion is already not active";
			return errorMsg;
		}
		// 3. try deactivating the promotion
		Product product = DBController.getInstance().getProduct(promotion.getProductID());
		if (product == null) {// product not found
			errorMsg.data = "Product not found";
			return errorMsg;
		}
		product.setPrice(product.getOldPrice());
		product.setOldPrice(0);
		if (!dbController.updateProductPrice(product)) {
			errorMsg.data = "Failed to update product price";
			return errorMsg;
		}
		// 4. updating the promotion status
		if (dbController.updatePromotion(promotionNumber, Status.Canceled)) {
			return completedMsg;
		}
		return errorMsg;
	}

	/**
	 * get all the existing promotions
	 * 
	 * @return RETURN_ALL_PROMOTIONS msg
	 */
	public Msg getAllPromotions() {
		DBController dbController = DBController.getInstance();
		return ServerMsgController.createRETURN_ALL_PROMOTIONSMsg(dbController.getAllPromotions());
	}

	/**
	 * calculate the product new price for the promotion
	 * 
	 * @param price    the original price
	 * @param discount the discount
	 * @return the new price
	 */
	private double calculateNewPrice(double price, double discount) {
		Double newPrice = price - price * discount;
		// round the price
		newPrice = Math.round(newPrice * 100.0) / 100.0;
		return newPrice;
	}

	/**
	 * activating a promotion
	 * 
	 * @param promotion
	 * @return true on success
	 */
	private boolean activePromotionInDB(Promotion promotion) {
		Product product = DBController.getInstance().getProduct(promotion.getProductID());
		if (product == null) {// product not found
			errorMsg.data = "Product not found";
			return false;
		}

		if (product.getOldPrice() != 0) {// there is already active promotion for this product
			errorMsg.data = "There is already active promotion for this product";
			return false;
		}
		product.setOldPrice(product.getPrice());
		product.setPrice(calculateNewPrice(product.getPrice(), promotion.getDiscount()));
		if (!DBController.getInstance().updateProductPrice(product)) {
			errorMsg.data = "Failed to update product price";
			return false;
		}
		return true;
	}

}
