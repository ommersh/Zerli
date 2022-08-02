package remindersManagment;

import simulators.ServerSimulatorsManager;

/**
 * Manage reminders, send SMS or Email. not implemented for future use.
 * 
 * @author halel
 *
 */
public class ReminderController {

	/**
	 * Send EMail
	 * 
	 * @param emailAddress
	 * @param msgText      text to send
	 */
	public void sendEmail(String emailAddress, String msgText) {
		// send the email
		// with the given text
		String msg = "Send Email to: " + emailAddress + "\n" + msgText + "\n";
		ServerSimulatorsManager.getInstance().SimulationsLog.add(msg);
	}

	/**
	 * Send SMS
	 * 
	 * @param phoneNumber
	 * @param msgText     text to send
	 */
	public void sendSMS(String phoneNumber, String msgText) {
		// send sms
		// with the given text
		String msg = "Send SMS to: " + phoneNumber + "\n" + msgText + "\n";
		ServerSimulatorsManager.getInstance().SimulationsLog.add(msg);
	}
}
