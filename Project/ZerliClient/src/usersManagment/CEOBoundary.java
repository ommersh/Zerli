package usersManagment;

import java.util.ArrayList;

import client.ClientController;
import client.MsgController;
import msg.Msg;
import msg.MsgType;
import report.*;

/**
 * Boundary for the ceo, can watch reports
 * 
 *
 */
public class CEOBoundary extends UserBoundary {
	private ClientController clientController = ClientController.getInstance();
	private Msg msg;
	private MsgController msgController;

	/**
	 * get a report
	 * 
	 * @param type   report type
	 * @param Month  the report month -for monthly report, the quarter(1-4) for
	 *               quarterly report
	 * @param year   the report year
	 * @param branch
	 * @return
	 */
	public Report requestViewReport(ReportType type, int Month, int year, String branch) {
		msg = MsgController.createGET_REPORTMsg(type, year, Month, branch);
		msgController = clientController.sendMsg(msg);
		if (msgController.getType().equals(MsgType.RETURN_REPORT)) {
			return msgController.getReport();
		}
		return null; // in case returned msg was ERROR for Example mean Report not found or exist
	}

	/**
	 * get the branch names
	 * 
	 * @return
	 */
	public ArrayList<String> getBranches() {
		MsgController msgController = clientController.sendMsg(MsgController.createGET_BRANCH_LISTMsg());
		if (msgController.getType() == MsgType.RETURN_BRANCH_NAMES) {
			return msgController.getBranchNames();
		}
		return null;
	}
}
