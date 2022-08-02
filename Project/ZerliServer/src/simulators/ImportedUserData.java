package simulators;

/**
 * simple class to save the imported user's data
 * 
 * @author halel
 *
 */
public class ImportedUserData {

	private String userName;
	private String password;
	private String email;
	private String type;
	private String phone;
	private String firstname;
	private String lastname;
	private String branch;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getType() {
		return type;
	}

	public String getPhone() {
		return phone;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getBranch() {
		return branch;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

}
