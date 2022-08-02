package complaintsManagment;

import common.Status;
import complaint.Complaint;
import database.DBController;
import paymentManagment.CreditController;
import remindersManagment.ReminderController;
import user.User;

/**
 * manage complaints, creating handling and relevant reminders
 *
 */
public class ComplaintsController {
	DBController dbController = DBController.getInstance();

	/**
	 * handle complaint answer, refund if needed and relevant reminders
	 * 
	 * @param inputComplaint
	 * @throws Exception - on error throw exception with error msg
	 */
	public void handleComplaintAnswer(Complaint inputComplaint) throws Exception {
		// 1. get the complaint from the database
		Complaint complaint = dbController.getComplaint(inputComplaint.getComplaintsNumber());
		if (complaint == null) {
			throw new Exception("The complaint was not found!");
		}
		// 2. check the complaint status
		if (complaint.getStatus() != Status.Active) {
			throw new Exception("The complaint is already " + complaint.getStatus());
		}
		// 3. update the complaint in the database
		if (!dbController.updateComplaint(inputComplaint.getAnswer(), inputComplaint.getComplaintsNumber(),
				inputComplaint.getStatus())) {
			throw new Exception("Failed to update the complaints, try again later");
		}
		// 4. now handle the refund
		if (inputComplaint.getCompensation() > 0) {
			CreditController creditController = new CreditController();
			creditController.refund(complaint.getCustomerID(), inputComplaint.getCompensation());
		}
	}

	/**
	 * create new complaint
	 * 
	 * @param complaint
	 * @return true on success
	 */
	public boolean createComplaint(Complaint complaint) {
		if (dbController.createComplaint(complaint) != -1)
			return true;
		return false;
	}

	/**
	 * Check if a complaint still not been dealt with, if not send the responsible
	 * employee a reminder to his email address and to his phone
	 * 
	 * @param complaintNumber
	 */
	public void sendComplaintReminder(int complaintNumber) {
		// 1. first we check if the complaint still not completed
		Complaint complaint = dbController.getComplaint(complaintNumber);
		if (complaint == null) {
			return;// no complaint was found, nothing to do here
		}
		if (complaint.getStatus() != Status.Active) {
			return;// the complaint was already handled
		}
		// 2. lets get the responsible employee info
		User responsibleEmployee = dbController.getUser(complaint.getResponsibleEmployeeUserName());
		if (responsibleEmployee == null) {
			return;// not exist, nothing to do here
		}
		// 3. creating the complaint reminder
		StringBuilder sb = new StringBuilder();
		sb.append("Hello " + responsibleEmployee.getFirstName() + " " + responsibleEmployee.getLastName() + "\n");
		sb.append("It's alredy been 24 hours!\n");
		sb.append("please handle the complaint, complaint number " + complaintNumber + "\n");
		sb.append("automatic message from Zerli system\n");
		// 4. sending the reminder
		ReminderController reminderController = new ReminderController();
		String reminder = sb.toString();
		reminderController.sendEmail(responsibleEmployee.getEmail(), reminder);
		reminderController.sendSMS(responsibleEmployee.getPhoneNumber(), reminder);
	}

}
