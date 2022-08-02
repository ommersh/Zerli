package complaint;

import java.io.Serializable;
import java.sql.Timestamp;

import common.Status;

/**
 * represents a complaints. have a responsible employee (the one who created the
 * complaint), complaint string(the complaint), answer(the answer text for the
 * complaint), status, customer id and creation time fields
 * 
 * @author halel
 *
 */
public class Complaint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int complaintsNumber;
	private String responsibleEmployeeUserName;
	private String complaint;
	private String answer;
	private double compensation;
	private Status status;
	private String customerID;
	private Timestamp creationTime;

	public Complaint(String responsibleEmployeeUserName, String complaint, String customerID) {
		super();
		this.complaintsNumber = -1;
		this.responsibleEmployeeUserName = responsibleEmployeeUserName;
		this.complaint = complaint;
		this.customerID = customerID;
		status = Status.Active;
		compensation = 0;
		creationTime = new Timestamp(System.currentTimeMillis());
	}

	public int getComplaintsNumber() {
		return complaintsNumber;
	}

	public void setComplaintsNumber(int complaintsNumber) {
		this.complaintsNumber = complaintsNumber;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public double getCompensation() {
		return compensation;
	}

	public void setCompensation(double compensation) {
		this.compensation = compensation;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getComplaint() {
		return complaint;
	}

	public String getResponsibleEmployeeUserName() {
		return responsibleEmployeeUserName;
	}

	public void setResponsibleEmployeeUserName(String responsibleEmployeeUserName) {
		this.responsibleEmployeeUserName = responsibleEmployeeUserName;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}

}
