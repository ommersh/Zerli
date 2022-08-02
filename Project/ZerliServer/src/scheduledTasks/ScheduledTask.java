package scheduledTasks;

import java.sql.Timestamp;

/**
 * Represent scheduled task, needed to be at a specific time. a runnable class
 * with run method to handle the task and task time to know when to handle the
 * task
 * 
 */
abstract class ScheduledTask implements Runnable {
	private Timestamp tasktime;

	public ScheduledTask(Timestamp tasktime) {
		this.tasktime = tasktime;
	}

	/**
	 * get the task time in milliseconds
	 * 
	 * @return
	 */
	public long getTimeInMilli() {
		return tasktime.getTime();
	}

	/**
	 * get the time left until the task in milliseconds
	 */
	public long getTimeLeftInMilli(long currentTime) {
		long time = tasktime.getTime() - currentTime;//
		if (time <= 0)
			return 0;
		return time;
	}
}
