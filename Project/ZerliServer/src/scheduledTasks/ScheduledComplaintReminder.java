package scheduledTasks;

import java.sql.Timestamp;

import complaintsManagment.ComplaintsController;

/**
 * Scheduled reminder, in the given time a reminder will be sent. can be sms or
 * email or both
 *
 */
public class ScheduledComplaintReminder extends ScheduledTask {

	private int complaintNumber = 0;

	/**
	 * reminder scheduled to the given time
	 * 
	 * @param tasktime
	 */
	public ScheduledComplaintReminder(Timestamp tasktime, int complaintNumber) {
		super(tasktime);
		this.complaintNumber = complaintNumber;
	}

	@Override
	public void run() {
		ComplaintsController complaintsController = new ComplaintsController();
		complaintsController.sendComplaintReminder(complaintNumber);
	}

}
