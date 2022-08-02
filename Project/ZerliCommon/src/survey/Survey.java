package survey;

import java.io.Serializable;

import files.SimpleFile;

/**
 * each survey is 6 questions, with the answers 1-10 to create a new survey:
 * place 6 questions in the constructor to add result: add 6 numbers from 1-10,
 * each for each question the survey result are the average of the answers
 * 
 * @author halel
 *
 */
public class Survey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * the survey id
	 */
	private int surveyNumber;
	/**
	 * the survey questions(6 questions)
	 */
	private String[] questions;
	/**
	 * for each question, the answers result(sum of the answers)
	 */
	private int[] result;
	/**
	 * the number of the survey's participants
	 */
	private int numberOfParticipants;

	private SimpleFile resultFile;

	public Survey(String question1, String question2, String question3, String question4, String question5,
			String question6) {
		questions = new String[6];
		result = new int[6];
		for (int i = 0; i < 6; i++)
			result[i] = 0;
		numberOfParticipants = 0;
		questions[0] = question1;
		questions[1] = question2;
		questions[2] = question3;
		questions[3] = question4;
		questions[4] = question5;
		questions[5] = question6;
		resultFile = null;
	}

	public void setSurveyNumber(int surveyNumber) {
		this.surveyNumber = surveyNumber;
	}

	/**
	 * add the answers to the survey result
	 * 
	 * @param answers -> array with length 6, for the 6 answers must be number
	 *                between 1 to 10
	 * @throws Exception when the answers array is not ok
	 */
	public void setAnswers(int[] answers) throws Exception {
		result = answers;
	}

	/**
	 * create string array, 7 string
	 * 
	 * @return string array with length 7
	 */
	public String getResult() {
		StringBuilder sb = new StringBuilder();
		sb.append("\t\t\tSurvey #" + surveyNumber + " :\n");
		for (int i = 0; i < 6; i++) {
			double tempRes = 0;
			if (numberOfParticipants != 0) {
				tempRes = (double) result[i] / numberOfParticipants;
			}
			sb.append("Question " + (i + 1) + " : " + questions[i] + "\n");
			sb.append("Average result : " + tempRes + "\n");
		}
		return sb.toString();
	}

	public String[] getQuestions() {
		return questions;
	}

	public int getNumberOfParticipants() {
		return numberOfParticipants;
	}

	public void setNumberOfParticipants(int numberOfParticipants) {
		this.numberOfParticipants = numberOfParticipants;
	}

	public int getSurveyNumber() {
		return surveyNumber;
	}

	public void setQuestions(String[] questions) {
		this.questions = questions;
	}

	public void setResult(int[] result) {
		this.result = result;
	}

	public SimpleFile getResultFile() {
		return resultFile;
	}

	public void setResultFile(SimpleFile resultFile) {
		this.resultFile = resultFile;
	}

}
