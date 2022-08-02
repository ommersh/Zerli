package user;

import java.io.Serializable;

/**
 * User entity in our system
 * 
 * @author halel
 *
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * the username
	 */
	private String username;
	/**
	 * the password
	 */
	private String password;
	/**
	 * the user type
	 */
	private UserType userType;
	/**
	 * is the user already connected?
	 */
	private boolean connected;
	/**
	 * the user first name
	 */
	private String firstName;
	/**
	 * the user last name
	 */
	private String lastName;
	/**
	 * the user email
	 */
	private String email;
	/**
	 * the user phone number
	 */
	private String phoneNumber;
	/**
	 * the user personal id
	 */
	private String personID;
	/**
	 * the account status
	 */
	private UserStatus status;

	/**
	 * for branch employee and branch manager only
	 */
	private String branchName;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public UserType getUserType() {
		return userType;
	}

	public boolean isConnected() {
		return connected;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPersonID() {
		return personID;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
	}

}
