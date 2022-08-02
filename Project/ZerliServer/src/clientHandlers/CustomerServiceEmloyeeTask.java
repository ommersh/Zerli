package clientHandlers;

import java.util.ArrayList;

import complaint.Complaint;
import complaintsManagment.ComplaintsController;
import msg.Msg;
import server.ServerMsgController;
import survey.Survey;

/**
 * handle the customer service employee actions, can create complaint, update
 * complaint, create new survey, add survey result and get all the complaints he
 * responsible for
 */
public class CustomerServiceEmloyeeTask extends ClientTasks {

	private ComplaintsController complaintController;

	public CustomerServiceEmloyeeTask(HandleClientTask clientTaskHandler) {
		super(clientTaskHandler);
		complaintController = new ComplaintsController();
	}

	/**
	 * handle the customer service employee actions, can create complaint, update
	 * complaint, create new survey, add survey result and get all the complaints he
	 * responsible for
	 */
	@Override
	public Msg handleTask(ServerMsgController msgController) {

		switch (msgController.getType()) {
		case CREATE_COMPLAINT:
			if (complaintController.createComplaint(msgController.getComplaint()))
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ServerMsgController.createERRORMsg("Error! failed to create the complaint");
			break;
		case UPDATE_COMPLAINT:
			Complaint tempComplaint = msgController.getComplaint();
			try {
				complaintController.handleComplaintAnswer(tempComplaint);
				newMsgToSend = CompletedMsg;
			} catch (Exception e) {
				newMsgToSend = ServerMsgController.createERRORMsg(e.getMessage());
			}
			break;
		case CREATE_SURVEY:
			if (dbController.createSurvey(msgController.getSurvey()) != -1)
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ServerMsgController.createERRORMsg("Error! failed to create the survey");
			break;
		case ADD_SURVEY_RESULT:
			if (dbController.saveSurveyResult(msgController.getSurveyNumber(), msgController.getResultFile()))
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ServerMsgController.createERRORMsg("Error! failed to add the survey result");
			break;
		case GET_ALL_COMPLAINTS:
			// get all the relevant complaints from db
			ArrayList<Complaint> complaints = dbController.getAllComplaints(user.getUsername());
			newMsgToSend = ServerMsgController.createRETURN_ALL_COMPLAINTSMsg(complaints);
			break;
		case GET_ALL_SURVEY:
			ArrayList<Survey> surveys = dbController.getAllSurvey();
			newMsgToSend = ServerMsgController.createRETURN_ALL_SURVEYMsg(surveys);
			break;
		default:
			// handle cant do it
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;
		}
		return newMsgToSend;
	}

}
