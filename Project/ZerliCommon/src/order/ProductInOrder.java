package order;

import java.io.Serializable;

/**
 * product in order, no need to save the full product, only the relevant fields
 * 
 * @author halel
 *
 */
public class ProductInOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * the order the product is in
	 */
	private int orderNumber;
	/**
	 * the product name
	 */
	private String name;
	/**
	 * the product price
	 */
	private double price;
	/**
	 * the amount of this product in the order
	 */
	private int amount;
	/**
	 * the total price for this items
	 */
	private double total;
	/**
	 * the product category
	 */
	private String category;
	/**
	 * additional data
	 */
	private String data = "";

	public double getTotal() {
		return total;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getName() {
		if (name.length() > 100)
			return name.subSequence(0, 100).toString();
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
		this.total = amount * price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
		this.total = amount * price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[ ");
		s.append(orderNumber + ", " + name + ", " + price + ", " + amount + ", ");
		s.append(total + ", " + category + "]");
		return s.toString();
	}

	public String getData() {
		if (data.length() > 200)
			return data.subSequence(0, 200).toString();
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void addData(String s) {
		data += s;
	}
}
