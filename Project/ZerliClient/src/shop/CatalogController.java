package shop;

import java.util.ArrayList;
import catalog.*;
import client.ClientController;
import client.MsgController;
import msg.MsgType;

/**
 * Controller for the catalog -> use or management, can get a category, edit a
 * product or add a new one
 * 
 *
 */
public class CatalogController {
	private ClientController clientController = ClientController.getInstance();

	/**
	 * get the category products
	 * 
	 * @param category
	 */
	public ArrayList<Product> chooseCategory(String category) {
		MsgController msgController = clientController.sendMsg(MsgController.createGET_CATALOG_PAGEMsg(0, category));
		if (msgController.getType() == MsgType.RETURN_CATALOG_PAGE)
			return msgController.getCatalogPage();
		return null;

	}

	/**
	 * update product in catalog
	 * 
	 * @param product
	 * @throws Exception - if failed -throw exception with the error msg
	 */

	public void updateCatalog(Product product) throws Exception {

		MsgController msgController = clientController.sendMsg(MsgController.createUPDATE_CATALOGMsg(product));
		if (msgController.getType() == MsgType.ERROR)
			throw new Exception(msgController.getErrorMsg());
	}

	/**
	 * save new product to the catalog
	 * 
	 * @param product
	 * @throws Exception - if failed -throw exception with the error msg
	 */
	public void saveNewProduct(Product product) throws Exception {
		MsgController msgController = clientController.sendMsg(MsgController.createADD_TO_CATALOGMsg(product));
		if (msgController.getType() == MsgType.ERROR)
			throw new Exception(msgController.getErrorMsg());
	}

	/**
	 * delete product from the catalog
	 * 
	 * @param productNumber
	 * @throws Exception - if failed -throw exception with the error msg
	 */
	public void removeFromCatalog(int productNumber) throws Exception {
		MsgController msgController = clientController
				.sendMsg(MsgController.createREMOVE_FROM_CATALOGMsg(productNumber));
		if (msgController.getType() == MsgType.ERROR)
			throw new Exception(msgController.getErrorMsg());
	}
}
