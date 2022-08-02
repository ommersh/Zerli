package paymentManagment;

import simulators.ServerSimulatorsManager;

/**
 * not implemented, for future update. manage payment and refund
 * 
 * @author halel
 *
 */
public class PaymentController {

	/**
	 * pay the given amount using the credit cart info
	 * 
	 * @param cardInfo
	 * @param amount   the amount to charge
	 * @return true on success
	 */
	public boolean pay(String cardInfo, double amount) {
		if (amount < 0)
			return false;
		if (cardInfo == null)
			return false;
		// if the credit card info is bad return false
		// pay the amount
		String msg = "Charge card: " + cardInfo + "\n" + "For: " + amount + "\n";
		ServerSimulatorsManager.getInstance().SimulationsLog.add(msg);
		return true;
	}

	/**
	 * refund the given amount
	 * 
	 * @param cardInfo
	 * @param amount   the amount to refund
	 * @return true on success
	 */
	public boolean refund(String cardInfo, double amount) {
		if (amount <= 0)
			return false;
		// if the credit card info is bad return false
		// refund the amount
		String msg = "Refund card: " + cardInfo + "\n" + "For: " + amount + "\n";
		ServerSimulatorsManager.getInstance().SimulationsLog.add(msg);
		return true;
	}
}
