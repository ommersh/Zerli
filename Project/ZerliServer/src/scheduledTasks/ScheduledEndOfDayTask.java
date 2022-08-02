package scheduledTasks;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Should be called at the end of each day, get all the next day tasks and
 * create end of day task for the next day
 *
 */
public class ScheduledEndOfDayTask extends ScheduledTask {

	
	/**
	 * set the task time to the current day at 23.59.999999
	 */
	public ScheduledEndOfDayTask() {
		super(Timestamp.valueOf(LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MAX)));
	}

	/**
	 * handle the task
	 */
	@Override
	public void run() {
		// sleep for 20 sec, wait for the new day to start
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// do nothing
		}
		ScheduledTasksManager scheduledTasksManager = ScheduledTasksManager.getInstance();
		ArrayList<ScheduledTask> tasksToAdd = scheduledTasksManager.getTasks();
		scheduledTasksManager.addTasks(tasksToAdd);

	}

}
