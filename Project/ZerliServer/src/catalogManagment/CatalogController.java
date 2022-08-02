package catalogManagment;

import java.util.ArrayList;

import catalog.Product;
import database.DBController;

/**
 * Manage the simple interactions with the catalog products, can get full
 * category, save delete and update a product in the database
 *
 */
public class CatalogController {

	private DBController dbController = DBController.getInstance();

	/**
	 * Get all the product in a given category
	 * 
	 * @param category
	 * @return
	 */
	public ArrayList<Product> getCatalogCategory(String category) {
		ArrayList<Product> catalog = dbController.getCatalogCategory(category);
		return catalog;
	}

	/**
	 * Update product in the catalog
	 * 
	 * @param product
	 * @throws Exception on fail - throw exception with error msg
	 */
	public void updateProduct(Product product) throws Exception {
		if (product == null) {
			throw new Exception("No product received");
		}

		if (!dbController.updateProduct(product)) {
			throw new Exception("Failed to update the product");
		}

	}

	/**
	 * delete product from the catalog
	 * 
	 * @param productNumber
	 * @throws Exception on fail - throw exception with error msg
	 */
	public void deleteProduct(int productNumber) throws Exception {
		if (dbController.getProduct(productNumber) == null) {
			throw new Exception("No product found!");
		}
		if (!dbController.deleteProduct(productNumber)) {
			throw new Exception("Failed to delete the product");
		}
	}

	/**
	 * Add new product to the catalog
	 * 
	 * @param product
	 * @throws Exceptionon fail - throw exception with error msg
	 */
	public void addNewProduct(Product product) throws Exception {
		if (product == null) {
			throw new Exception("No product received");
		}
		if (dbController.saveProductToDB(product) == -1) {
			throw new Exception("Failed to save the product");
		}

	}
}
