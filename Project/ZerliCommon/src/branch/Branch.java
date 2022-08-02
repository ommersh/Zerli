package branch;

import java.io.Serializable;

/**
 * simple entity for the branch - branch name and branch phone number
 *
 * 
 */
public class Branch implements Serializable {

	private static final long serialVersionUID = 1L;
	private String branchName;
	private String phoneNumber;

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
