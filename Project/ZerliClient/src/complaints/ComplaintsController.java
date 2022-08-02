package complaints;

import java.util.ArrayList;
import client.ClientController;
import client.MsgController;
import complaint.Complaint;
import common.Status;
import msg.MsgType;

/**
 * manage complaints, creating and updating
 * 
 *
 */
public class ComplaintsController {
	private Complaint complaint;
	private ClientController client = ClientController.getInstance();

	/**
	 * create the Complaint with the fields server
	 * 
	 * @param complaint
	 * @param answer
	 * @param compensation
	 * @param status
	 */
	public void createComplaint(String responsibleEmployeeUserName, String complaintText, String customerID)
			throws Exception {
		complaint = new Complaint(responsibleEmployeeUserName, complaintText, customerID);
		MsgController msgController = client.sendMsg(MsgController.createCREATE_COMPLAINTMsg(complaint));
		if (msgController.getType() == MsgType.ERROR)
			throw new Exception(msgController.getErrorMsg());

	}

	/**
	 * update the Complaint with the fields, and send the new Complaint to the
	 * server
	 * 
	 * @param complaint
	 * @param answer
	 * @param compensation
	 * @param status
	 */
	public void handleComplaint(Complaint complaint, String answer, double compensation, Status status)
			throws Exception {
		complaint.setAnswer(answer);
		complaint.setCompensation(compensation);
		complaint.setStatus(status);
		MsgController msgController = client.sendMsg(MsgController.createUPDATE_COMPLAINTMsg(complaint));
		if (msgController.getType() == MsgType.ERROR)
			throw new Exception(msgController.getErrorMsg());
	}

	/**
	 * get all the complaints related to the employee
	 * 
	 * @param employeeID
	 * @return
	 */
	public ArrayList<Complaint> getAllComplaints() {
		MsgController msgController = client.sendMsg(MsgController.createGET_ALL_COMPLAINTSMsg());
		if (msgController.getType() == MsgType.RETURN_ALL_COMPLAINTS)
			return msgController.getComplaints();
		else
			return null;
	}
}
