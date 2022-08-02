package usersManagment;

import database.DBController;
import paymentManagment.PaymentController;
import remindersManagment.ReminderController;
import user.*;

/**
 * manage the users management actions
 *
 */
public class UserManagmentController {
	private DBController dbController = DBController.getInstance();

	/**
	 * add card for a user
	 * 
	 * @param userName
	 * @param cardInfo
	 * @throws Exception -> throw exception with error message on failure
	 */
	public void addCard(String userName, String cardInfo) throws Exception {
		// 1. check if the card info is ok
		PaymentController paymentController = new PaymentController();
		if (!paymentController.pay(cardInfo, 0.001)) {
			throw new Exception("The card info is bad!");
		}
		// 2. check if the customer already have a card
		String temp = dbController.getCardInfo(userName);
		if (temp == null) {
			// add new card
			if (!dbController.saveCardInfo(userName, cardInfo)) {
				throw new Exception("Failed to save the card info");
			}
		} else {
			if (!dbController.updateCardInfo(userName, cardInfo)) {
				throw new Exception("Failed to update the card info");
			}
		}
	}

	/**
	 * update the user date(all the fields except username and password)
	 * 
	 * @param user
	 * @throws Exception -> throw exception with error message on failure
	 */
	public void updateUserData(User user) throws Exception {
		boolean newUser = false;
		// 1. get the user from the database
		User oldUser = dbController.getUser(user.getUsername());
		if (oldUser.getUserType() == UserType.NonAuthorizedCustomer
				|| user.getUserType() == UserType.AuthorizedCustomer) {
			// its a newly approved customer! send him a message
			newUser = true;
		}
		// 1. update the user data
		if (!dbController.updateAllUserData(user)) {
			throw new Exception("Failed to update the user info");
		}
		if (newUser) {
			ReminderController reminderController = new ReminderController();
			StringBuilder sb = new StringBuilder();
			sb.append("Hello " + user.getFirstName() + " " + user.getLastName() + "\n");
			sb.append("Welcome to our system!\n");
			sb.append("You will get 20% off automaticly on your first order!\n");
			sb.append("Your card was added successfully\n");
			sb.append("We hope you will enjoy our system\n");
			String s = sb.toString();
			reminderController.sendEmail(user.getEmail(), s);
			reminderController.sendSMS(user.getPhoneNumber(), s);
		}
	}

}
