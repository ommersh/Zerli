package clientHandlers;

import java.util.ArrayList;

import msg.Msg;
import server.ServerMsgController;
import survey.Survey;

/**
 * handle the branch employee actions, can get survey, get all the survey and
 * add survey answers
 */
public class BranchEmployeeTask extends ClientTasks {

	public BranchEmployeeTask(HandleClientTask clientTaskHandler) {
		super(clientTaskHandler);
	}

	/**
	 * handle the branch employee actions, can get survey, get all the survey and
	 * add survey answers
	 */
	@Override
	public Msg handleTask(ServerMsgController msgController) {

		switch (msgController.getType()) {
		case GET_SURVEY:
			// get survey from db
			Survey survey = dbController.getSurvey(msgController.getSurveyNumber());
			newMsgToSend = ServerMsgController.createRETURN_SURVEYMsg(survey);
			break;
		case GET_ALL_SURVEY:
			// get all the surveys
			ArrayList<Survey> surveys = dbController.getAllSurvey();
			newMsgToSend = ServerMsgController.createRETURN_ALL_SURVEYMsg(surveys);
			break;
		case ADD_SURVEY_ANSWERS:
			if (dbController.addSurveyAnswers(msgController.getAnswers(), msgController.getSurveyNumber()))
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ServerMsgController.createERRORMsg("Error! failed to update the survey");
			break;
		default:
			// handle cant do it
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;
		}
		return newMsgToSend;
	}

}
