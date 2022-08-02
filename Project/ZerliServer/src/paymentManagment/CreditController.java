package paymentManagment;

import database.DBController;

/**
 * handle the shop credit, adding or using credit
 * 
 */
public class CreditController {
	DBController dbController = DBController.getInstance();

	/**
	 * get the shop credit amount for the given id
	 * 
	 * @param id the customer id
	 * @return the shop credit amount
	 */
	public double getShopCredit(String id) {
		double credit = dbController.getShopCredit(id);
		if (credit == -1)
			return 0;
		else
			return credit;
	}

	/**
	 * use the shop credit of the customer for the given amount
	 * 
	 * @param id     the customer id
	 * @param amount the amount to use
	 * @return true on success
	 */
	public boolean useShopCredit(String id, double amount) {
		// 1. get the credit
		double credit = dbController.getShopCredit(id);
		if (credit == -1)
			return false;// no credit was found
		if (credit < amount)
			return false;// not enough credit
		// 2. use the credit
		return dbController.updateShopCredit(id, -amount);
	}

	/**
	 * give refund to the customer for the amount
	 * 
	 * @param id           the customer id
	 * @param refundAmount the amount to refund(add to the credit)
	 */
	public void refund(String id, double refundAmount) {
		// 1. check if credit exist for the customer
		if (dbController.getShopCredit(id) == -1) {
			// 2. no credit exist, add the refund as credit
			dbController.saveShopCreditToDB(id, refundAmount);
		} else {
			// 2. credit exist, add the refund amount
			dbController.updateShopCredit(id, refundAmount);
		}

	}
}
