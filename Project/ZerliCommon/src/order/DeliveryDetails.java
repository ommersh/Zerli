package order;

import java.io.Serializable;

/**
 * Delivery details for the order
 * 
 * @author halel
 *
 */
public class DeliveryDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int orderID;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private String comments;

	public DeliveryDetails() {
		orderID = -1;
		firstName = "";
		lastName = "";
		address = "";
		phoneNumber = "";
		comments = "";
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
