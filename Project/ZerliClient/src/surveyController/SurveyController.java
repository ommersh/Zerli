package surveyController;

import java.util.ArrayList;

import client.ClientController;
import client.MsgController;
import files.FilesController;
import files.SimpleFile;
import survey.Survey;
import msg.MsgType;

/**
 * manage the survey interactions, can get a survey, get all the surveys, add
 * survey answers or survey result pdf file
 * 
 */
public class SurveyController {

	private Survey survey;
	private ClientController client;
	private MsgController msgController;

	public SurveyController() {
		client = ClientController.getInstance();
		msgController = new MsgController();
	}

	/**
	 * get a path and a survey number, open the file using the path and save the
	 * file as object- to send to the server
	 * 
	 * @param sNumber the survey number
	 * @param path    the file path
	 * @throws Exception on failure -> throw with error message
	 */
	public void enterSurveyResults(int sNumber, String path) throws Exception {
		FilesController fileController = new FilesController();
		SimpleFile pdfFile = fileController.savePdfFileToObject(path, "Survey_" + sNumber + "_Result");
		msgController = client.sendMsg(MsgController.createADD_SURVEY_RESULTMsg(pdfFile, sNumber));
		if (msgController.getType() == MsgType.ERROR) {
			throw new Exception(msgController.getErrorMsg());
		}
	}

	/**
	 * create new survey, need the questions for the survey
	 * 
	 * @param questions -> must be 6 questions
	 * @throws Exception on failure -> throw with error message
	 */
	public void CreateSurvey(String[] questions) throws Exception {

		if (questions.length == 6)
			survey = new Survey(questions[0], questions[1], questions[2], questions[3], questions[4], questions[5]);
		msgController = client.sendMsg(MsgController.createCREATE_SURVEYMsg(survey));
		if (msgController.getType() == MsgType.ERROR)
			throw new Exception(msgController.getErrorMsg());

	}

	/**
	 * add answers to existing survey, the answers are number from 1 to 10, must be
	 * 6 answers
	 * 
	 * @param answers      6 answers -> numbers from 1 to 10
	 * @param surveyNumber the survey number
	 * @throws Exception on failure -> throw with error message
	 */
	public void enterSurveyAnswers(int[] answers, int surveyNumber) throws Exception {
		// save the survey result using clientcontroller.saveSurveyResult
		if (answers.length == 6) {
			ArrayList<Integer> answersArrayList = new ArrayList<Integer>();
			answersArrayList.add(surveyNumber);
			for (int i = 0; i < 6; i++) {
				answersArrayList.add(answers[i]);
			}
			msgController = client.sendMsg(MsgController.createADD_SURVEY_ANSWERSMsg(answersArrayList));
			if (msgController.getType() == MsgType.ERROR)
				throw new Exception(msgController.getErrorMsg());
		} else
			throw new Exception("incorrect number of answers");
	}

	/**
	 * get survey with the given survey number
	 * 
	 * @param surveyNumber
	 * @return
	 * @throws Exception on failure -> throw with error message
	 */
	public Survey getSurvey(int surveyNumber) throws Exception {
		msgController = client.sendMsg(MsgController.createGET_SURVEYMsg(surveyNumber));
		if (msgController.getType() == MsgType.ERROR)
			throw new Exception(msgController.getErrorMsg());
		return msgController.getSurvey();
	}

	/**
	 * get all the Surveys
	 * 
	 */
	public ArrayList<Survey> getAllSurvey() throws Exception {
		MsgController msgController = client.sendMsg(MsgController.createGET_ALL_SURVEYMsg());
		if (msgController.getType() == MsgType.ERROR)
			throw new Exception(msgController.getErrorMsg());
		return msgController.getSurveys();
	}

	/**
	 * get the survey result from the object and save it as pdf file on the given
	 * path
	 * 
	 * @param sm   the file object
	 * @param path
	 */
	public void saveSurveyResultToLocalFile(SimpleFile sm, String path) {
		FilesController fileController = new FilesController();
		fileController.savePdfFileFromObjectToPath(path, sm);
	}
}
