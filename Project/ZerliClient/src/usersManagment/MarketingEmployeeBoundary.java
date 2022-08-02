package usersManagment;

import java.util.ArrayList;

import catalog.Product;
import promotion.Promotion;
import promotions.PromotionController;
import shop.CatalogController;

/**
 * the marketing employee boundary, can manage promotions and manage the catalog
 * 
 *
 */
public class MarketingEmployeeBoundary {

	private PromotionController promotionController;
	private CatalogController catalogController;

	public MarketingEmployeeBoundary() {
		promotionController = new PromotionController();
		catalogController = new CatalogController();
	}

	public void addProduct(Product procuct) throws Exception {
		catalogController.saveNewProduct(procuct);
	}
	
	public void updateCatalog(Product product) throws Exception {
		catalogController.updateCatalog(product);
	}

	public ArrayList<Product> getCategory(String category) {
		return catalogController.chooseCategory(category);
	}

	/**
	 * create new promotion
	 * 
	 * @param productID     the product to promot
	 * @param discount      the discount (% as number from 0 to 1)
	 * @param promotionText the promotion description
	 * @throws Exception on failure -> throw with error message
	 */
	public void requestcreateNewPromotion(int productID, double discount, String promotionText) throws Exception {

		promotionController.createNewPromotion(productID, discount, promotionText);

		// in case activate get error the activatePromotion method throw exception that
		// come here
		// and here throw to the method who called it (GUI method catch and display the
		// ERROR and why)

	}

	/**
	 * activate existing promotion
	 * 
	 * @param promotionNumber
	 * @throws Exception on failure -> throw with error message
	 */
	public void activatePromotion(int promotionNumber) throws Exception {
		promotionController.activatePromotion(promotionNumber);
	}

	/**
	 * stoping active promotion
	 * 
	 * @param promotionNumber
	 * @throws Exception on failure -> throw with error message
	 */
	public void deActivatePromotion(int promotionNumber) throws Exception {
		promotionController.endPromotion(promotionNumber);
	}

	/**
	 * get all the promotions
	 * 
	 * @return
	 */
	public ArrayList<Promotion> getAllPromotions() {
		return promotionController.getAllPromotions();
	}
}
