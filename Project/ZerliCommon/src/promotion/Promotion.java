package promotion;

import java.io.Serializable;
import java.sql.Timestamp;

import common.Status;

/**
 * represents a promotion -> a sale for a single product
 * 
 * @author halel
 *
 */
public class Promotion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * the product unique id
	 */
	private int productID;
	/**
	 * the promotion description
	 */
	private String promotionText;
	/**
	 * the promotion status, can be active or not active
	 */
	private Status status;
	/**
	 * the discount for the product -> % from the original price as a number between
	 * 1(100% off) to 0(original price)
	 */
	private double discount;
	/**
	 * a unique identifier for the promotion
	 */
	private int promotionNumber;
	/**
	 * the promotion creation date
	 */
	private Timestamp creationDate;

	public Promotion() {
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getProductID() {
		return productID;
	}

	public String getPromotionText() {
		return promotionText;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public void setPromotionText(String promotionText) {
		this.promotionText = promotionText;
	}

	public double getDiscount() {
		return discount;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getPromotionNumber() {
		return promotionNumber;
	}

	public void setPromotionNumber(int promotionNumber) {
		this.promotionNumber = promotionNumber;
	}

}
