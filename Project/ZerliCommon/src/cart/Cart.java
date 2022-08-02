package cart;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

import catalog.Product;
import order.DeliveryDetails;

/**
 * simple entity for the cart object, representing full cart with items
 * 
 *
 */
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<ProductInCart> productsInCart;
	private double price;
	private boolean withGreetingCard;
	private boolean withHomeDelivery;
	private String greetingCard;
	private DeliveryDetails deliveryDetails;
	private Timestamp arrivalDate;
	private String branchName;
	private ProductInCart tempProduct;

	public Cart() {
		productsInCart = new ArrayList<ProductInCart>();
		withGreetingCard = false;
		withHomeDelivery = false;
		greetingCard = null;
		deliveryDetails = null;
		price = 0;
		arrivalDate = null;
		tempProduct = new ProductInCart(new Product(-1), 1);
	}

	/**
	 * 
	 * add item to the cart, if the item already in the cart add the amount to the
	 * existing item amount
	 * 
	 * @param product the product we want to add
	 * @param amount  the product amount
	 */
	public void addItem(Product product, int amount) {
		ProductInCart newProduct = new ProductInCart(product, amount);
		if (productsInCart.contains(newProduct))
			productsInCart.get(productsInCart.indexOf(newProduct)).addAmount(amount);
		else
			productsInCart.add(newProduct);
	}

	/**
	 * remove item from the cart
	 * 
	 * @param productName
	 */
	public void removeItem(String productName) {
		tempProduct.getProduct().setName(productName);
		if (productsInCart.contains(tempProduct)) {
			productsInCart.remove(tempProduct);
		}
	}

	public double getPrice() {
		return price;
	}

	/**
	 * calculate and return the total price for the cart
	 * 
	 */
	public double calculatePrice() {
		double newPrice = 0;
		for (ProductInCart item : productsInCart) {
			newPrice += item.getAmount() * item.getProduct().getPrice();
		}
		this.price = newPrice;
		return newPrice;
	}

	public String getGreetingCard() {
		return greetingCard;
	}

	public void setGreetingCard(String greetingCard) {
		this.greetingCard = greetingCard;
	}

	public DeliveryDetails getDeliveryDetails() {
		return deliveryDetails;
	}

	public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
		this.deliveryDetails = deliveryDetails;
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

	public boolean isWithGreetingCard() {
		return withGreetingCard;
	}

	public void setWithGreetingCard(boolean withGreetingCard) {
		this.withGreetingCard = withGreetingCard;
	}

	public boolean isWithHomeDelivery() {
		return withHomeDelivery;
	}

	public void setWithHomeDelivery(boolean withHomeDelivery) {
		this.withHomeDelivery = withHomeDelivery;
	}

	public ArrayList<ProductInCart> getProductsInCart() {
		return productsInCart;
	}

}
