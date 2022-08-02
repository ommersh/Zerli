package scheduledTasks;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import reportManagment.ReportsController;

/**
 * Task for creating all the needed reports, will happen at night if possible,
 * if the reports wasn't created and its the new month already, create them now
 * 
 * @author halel
 *
 */

public class ScheduledReportCreationTask extends ScheduledTask {

	public ScheduledReportCreationTask(Timestamp tasktime) {
		super(tasktime);
		//super(new Timestamp(System.currentTimeMillis()));
	}

	@Override
	public void run() {
		// get the month and year of the last month
		int month = LocalDateTime.now().getMonthValue();
		int year = LocalDateTime.now().getYear();
		// if we stated a new year- get the 12 of last year
		if (month == 1) {
			year = year - 1;
			month = 12;
		}
		// if not - get the last month
		else {
			month = month - 1;
		}
		ReportsController reportController = new ReportsController(month, year);
		reportController.createAllReports();
		reportController.saveAllReports();
		// update log
	}

}
