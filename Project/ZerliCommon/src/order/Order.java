package order;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Order entity, implements Serializable and can be used in messages
 * 
 * @author hallel
 *
 */
public class Order implements Serializable {

	/**
	 * the order version, must be the same in both client and server
	 */
	private static final long serialVersionUID = 6L;
	/**
	 * max size for the greeting card field
	 */
	public final int GREETING_CARD_MAX_SIZE = 200;
	/**
	 * The unique order id number
	 */
	private int orderNumber;
	/**
	 * the order creation date
	 */
	private Timestamp orderDate;
	/**
	 * the order expected arrival date
	 */
	private Timestamp arrivalDate;
	/**
	 * the branch we ordered from
	 */
	private String branchName;
	/**
	 * is the order with home delivery?
	 */
	private boolean homeDelivery;
	/**
	 * the order total price\cost
	 */
	private double price;
	/**
	 * the order personal letter(not a must, but its free :) )
	 */
	private String personalLetter;
	/**
	 * the order status
	 */
	private OrderStatus orderStatus;
	/**
	 * additional data for the order, you should check it out!
	 */
	private String data;
	/**
	 * the items in the order
	 */
	private ArrayList<ProductInOrder> items;
	/**
	 * delivery details -> only if needed
	 */
	private DeliveryDetails deliveryDetails;
	/**
	 * the customer user name
	 */
	private String username;
	/**
	 * the actoal price for the order (after discount + use of shop credit)
	 */
	private double priceToPay;

	public double getPriceToPay() {
		return priceToPay;
	}

	public void setPriceToPay(double priceToPay) {
		this.priceToPay = priceToPay;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Set the order data string, up to 45 characters
	 * 
	 * @param OrderData
	 */
	public void setOrderData(String OrderData) {
		data = OrderData;
	}

	/**
	 * Get the order data string
	 * 
	 * @return the order data string
	 */
	public String getOrderData() {
		return data;
	}

	/**
	 * Get the order id number
	 * 
	 * @return the order id number
	 */
	public int getOrderNumber() {
		return orderNumber;
	}

	/**
	 * Set the order id, must be positive number
	 * 
	 * @param orderID
	 */
	public void setOrderNumber(int orderID) {
		if (orderID >= 0)
			this.orderNumber = orderID;
	}

	/**
	 * Get the order price
	 * 
	 * @return the order price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Set the order price, must be positive number
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		if (price > 0)
			this.price = price;
	}

	/**
	 * Get the Greeting Card
	 * 
	 * @return the Greeting Card
	 */
	public String getPersonalLetter() {
		return personalLetter;
	}

	/**
	 * Set the greeting card string ,must be <= from GREETING_CARD_MAX_SIZE
	 * 
	 * @param greetingCard
	 */
	public void setPersonalLetter(String personalLetter) {
		if (personalLetter != null)
			if (personalLetter.length() <= GREETING_CARD_MAX_SIZE)
				this.personalLetter = personalLetter;
	}

	/**
	 * Get the order creation date
	 * 
	 * @return the order creation date
	 */
	public Timestamp getOrderDate() {
		return orderDate;
	}

	/**
	 * Set the order creation date
	 * 
	 * @param orderDate
	 */
	public void setOrderDate(Timestamp orderDate) {
		if (orderDate != null)
			this.orderDate = orderDate;
	}

	public Timestamp getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Timestamp arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public boolean isHomeDelivery() {
		return homeDelivery;
	}

	public void setHomeDelivery(boolean homeDelivery) {
		this.homeDelivery = homeDelivery;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public ArrayList<ProductInOrder> getItems() {
		return items;
	}

	public void setItems(ArrayList<ProductInOrder> items) {
		this.items = items;
	}

	public DeliveryDetails getDeliveryDetails() {
		return deliveryDetails;
	}

	public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
		this.deliveryDetails = deliveryDetails;
	}

	/**
	 * Get the order as string ain the format:
	 * [id,price,color,shop,arrivalDate,orderDate,orderData,greetingCard]
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[ ");
		s.append(orderNumber + ", " + price + ", " + branchName + ", ");
		s.append(data + ", " + orderDate + ", " + personalLetter + "]");
		return s.toString();
	}
}
