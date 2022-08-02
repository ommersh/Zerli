package catalog;

import java.util.ArrayList;

/**
 * representing a customized product, a product with list of components(other
 * products), have "colors" "type" and price range field
 * 
 * @author halel
 *
 */
public class CustomizedProduct extends Product {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * list of the items in our product
	 */
	private ArrayList<Product> items;
	/**
	 * the price range low limit, the to limit is the Product's price
	 */
	private double priceRangeLowLimit;

	private String type;

	public CustomizedProduct(int productID, String name) {
		super(productID);
		super.setName(name);
		super.setColors("colorful");
		super.setCategory("CustomizedProduct");
		items = new ArrayList<Product>();
		type = "Bouquet";
	}

	public void addItemToProduct(Product product) {
		if (!items.contains(product)) {
			items.add(product);
		}
	}

	public void choosePrice(double highPrice, double lowPrice) {
		this.setPrice(highPrice);
		this.priceRangeLowLimit = lowPrice;
	}

	public ArrayList<Product> getItems() {
		return items;
	}

	public double getPriceRangeLowLimit() {
		return priceRangeLowLimit;
	}

	public double getPriceRangeHighLimit() {
		return this.getPrice();
	}

	@Override
	public String getName() {
		return super.getName() + "(Customized)";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
