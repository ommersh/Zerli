package clientHandlers;

import msg.Msg;
import report.Report;
import report.ReportType;
import server.ServerMsgController;

/**
 * Handle the ceo actions, can watch reports
 */
public class CEOTask extends ClientTasks {

	public CEOTask(HandleClientTask clientTaskHandler) {
		super(clientTaskHandler);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Handle the ceo actions, can watch reports
	 */
	@Override
	public Msg handleTask(ServerMsgController msgController) {

		switch (msgController.getType()) {
		case GET_REPORT:
			String branch = msgController.getBranch();
			ReportType type = msgController.getReportType();
			if (type == ReportType.QUARTERLY_ORDERS_REPORT || type == ReportType.QUARTERLY_REVENUE_REPORT
					|| type == ReportType.QUARTERLY_SATISFACTION_REPORT) {
				branch = "ALL";
			}
			Report tempReport = new Report(msgController.getMonth(), msgController.getYear(),
					msgController.getReportType(), branch);
			Report report = dbController.getReportFromDB(tempReport);
			newMsgToSend = ServerMsgController.creatRETURN_REPORTMsg(report);
			break;
		default:
			// handle cant do it
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;
		}
		return newMsgToSend;
	}

}
