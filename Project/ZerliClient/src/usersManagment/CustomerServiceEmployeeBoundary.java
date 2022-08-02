package usersManagment;

import java.util.ArrayList;

import client.ClientController;
import client.MsgController;
import complaint.Complaint;
import complaints.ComplaintsController;
import files.SimpleFile;
import msg.MsgType;
import common.Status;
import survey.Survey;
import surveyController.SurveyController;

/**
 * Boundary for the customer service employee, can manage complains and surveys
 *
 */
public class CustomerServiceEmployeeBoundary {
	/**
	 * to manage the complaints
	 */
	private ComplaintsController complaintsController = new ComplaintsController();
	/**
	 * to manage surveys
	 */
	private SurveyController surveyController = new SurveyController();
	private ClientController client = ClientController.getInstance();

	/**
	 * create new complaint
	 * 
	 * @param responsibleEmployeeUserName - the employee name
	 * @param complaintText               - the complaint text
	 * @param customerID                  - the customer user name
	 * @throws Exception on failure -> throw with error message
	 */
	public void createComplaints(String responsibleEmployeeUserName, String complaintText, String customerID)
			throws Exception {
		complaintsController.createComplaint(responsibleEmployeeUserName, complaintText, customerID);
	}

	public void handlingComplaints(Complaint complaint, String answer, double compensation, Status status)
			throws Exception {
		complaintsController.handleComplaint(complaint, answer, compensation, status);
	}

	/**
	 * get all the employee's complaints
	 * 
	 * @return
	 */
	public ArrayList<Complaint> getMyComplaints() {
		return complaintsController.getAllComplaints();
	}

	/**
	 * save pdf file as the survey result
	 * 
	 * @param sNumber the survey number
	 * @param path    the file path
	 * @throws Exception on failure -> throw with error message
	 */
	public void enterSurveyResult(int sNumber, String path) throws Exception // pdf file
	{
		surveyController.enterSurveyResults(sNumber, path);
	}

	/**
	 * add survey answers
	 * 
	 * @param answers      - 6 answers between 1 to 10
	 * @param surveyNumber - the survey number
	 * @throws Exception - on failure -> throw with error message
	 */
	public void enterSurveyAnswers(int[] answers, int surveyNumber) throws Exception {
		surveyController.enterSurveyAnswers(answers, surveyNumber);
	}

	/**
	 * get survey
	 * 
	 * @param surveyNumber the survey number
	 * @return
	 * @throws Exception on failure -> throw with error message
	 */
	public Survey getSurvey(int surveyNumber) throws Exception {
		return surveyController.getSurvey(surveyNumber);
	}

	/**
	 * save the survey result localy
	 * 
	 * @param sm   the file object
	 * @param path the path
	 */
	public void saveSurveyResultToLocalFile(SimpleFile sm, String path) {
		surveyController.saveSurveyResultToLocalFile(sm, path);
	}

	/**
	 * get all the surveys
	 * 
	 * @return
	 * @throws Exception on failure -> throw with error message
	 */
	public ArrayList<Survey> getAllSurvey() throws Exception {
		MsgController msgController = client.sendMsg(MsgController.createGET_ALL_SURVEYMsg());
		if (msgController.getType() == MsgType.RETURN_ALL_SURVEY)
			return msgController.getSurveys();
		else
			throw new Exception("cannot get all survey");
	}

}
