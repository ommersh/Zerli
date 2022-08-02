package promotions;

import java.sql.Timestamp;
import java.util.ArrayList;

import client.ClientController;
import client.MsgController;
import common.Status;
import msg.Msg;
import msg.MsgType;
import promotion.Promotion;

/**
 * Manage promotions, creation, update and getting the promotions data
 * 
 *
 */
public class PromotionController {

	/**
	 * activate existing promotion
	 * 
	 * @param productID
	 * @param discount
	 * @param promotionText
	 */
	public void activatePromotion(int promotionNumber) throws Exception {
		Msg msg = MsgController.createACTIVATE_PROMOTIONMsg(promotionNumber);

		MsgController msgController = ClientController.getInstance().sendMsg(msg);
		if (msgController.getType() == MsgType.COMPLETED)
			return;
		if (msgController.getType() == MsgType.ERROR)
			throw new Exception(msgController.getErrorMsg());
	}

	/**
	 * end active promotion
	 * 
	 * @param productID
	 * @param discount
	 * @param promotionText
	 */
	public void endPromotion(int promotionNumber) throws Exception {
		Msg msg = MsgController.createEND_PROMOTIONMsg(promotionNumber);

		MsgController msgController = ClientController.getInstance().sendMsg(msg);
		if (msgController.getType() == MsgType.COMPLETED)
			return;
		if (msgController.getType() == MsgType.ERROR)
			throw new Exception(msgController.getErrorMsg());
	}

	/**
	 * create and activate new promotion
	 * 
	 * @param productID     the product for the promotion
	 * @param discount      the discount - 0(free) to 1 (full price)
	 * @param promotionText the promotion text
	 * @return
	 * @throws Exception if failed throws exception with the error msg
	 */
	public void createNewPromotion(int productID, double discount, String promotionText) throws Exception {
		if (discount < 0 || discount > 1)
			throw new Exception("Wrong discount values");
		Promotion promotion = new Promotion();
		promotion.setDiscount(discount);
		promotion.setProductID(productID);
		promotion.setPromotionText(promotionText);
		promotion.setStatus(Status.Active);
		promotion.setCreationDate(new Timestamp(System.currentTimeMillis()));
		Msg msg = MsgController.createCREATE_NEW_PROMOTIONMsg(promotion);

		MsgController msgController = ClientController.getInstance().sendMsg(msg);
		if (msgController.getType() == MsgType.COMPLETED)
			return;
		if (msgController.getType() == MsgType.ERROR)
			throw new Exception(msgController.getErrorMsg());
	}

	/**
	 * get all the promtions
	 * 
	 * @return
	 */
	public ArrayList<Promotion> getAllPromotions() {
		MsgController msgController = ClientController.getInstance()
				.sendMsg(MsgController.createGET_ALL_PROMOTIONSMsg());
		if (msgController.getType() == MsgType.RETURN_ALL_PROMOTIONS) {
			return msgController.getAllpromotions();
		}
		return null;

	}
}
