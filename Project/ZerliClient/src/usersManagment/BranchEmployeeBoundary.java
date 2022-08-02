package usersManagment;

import survey.Survey;
import surveyController.SurveyController;

/**
 * boundary for the branch employee actions, entering survey answers
 */
public class BranchEmployeeBoundary {
	/**
	 * the survey controller
	 */
	private SurveyController surveyController;

	public BranchEmployeeBoundary() {
		surveyController = new SurveyController();
	}

	/**
	 * enter survey answers, must be 6 answers
	 * 
	 * @param answers      must be 6 answers
	 * @param surveyNumber the survey number
	 * @throws Exception on failure -> throw with error message
	 */
	public void enterSurveyAnswers(int[] answers, int surveyNumber) throws Exception {
		surveyController.enterSurveyAnswers(answers, surveyNumber);
	}

	/**
	 * get a survey
	 * 
	 * @param surveyNumber the survey number
	 * @return the survey
	 * @throws Exception on failure -> throw with error message
	 */
	public Survey getSurvey(int surveyNumber) throws Exception {
		return surveyController.getSurvey(surveyNumber);

	}
}
