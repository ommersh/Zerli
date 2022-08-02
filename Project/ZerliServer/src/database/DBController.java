package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import catalog.Product;
import common.Status;
import complaint.Complaint;
import files.SimpleFile;
import order.DeliveryDetails;
import order.Order;
import order.ProductInOrder;
import promotion.Promotion;
import report.Report;
import simulators.ImportedUserData;
import survey.Survey;
import user.User;
import user.UserStatus;
import user.UserType;

/**
 * The database controller, manage connection to database using DBBoundary,
 * create queries and manage the result
 *
 */
public class DBController {

	private static DBController singelton = null;

	public static DBController getInstance() {
		if (singelton == null)
			singelton = new DBController();

		return singelton;
	}

	/**
	 * The database name
	 */
	private String DBname;
	/**
	 * The database boundary
	 */
	private DBBoundry dbBoundry;

	/**
	 * class to handle ResultSet
	 */
	private DBObjectsManager objectManager;

	public boolean isConnected = false;

	private DBController() {
		dbBoundry = new DBBoundry();
		objectManager = new DBObjectsManager();
		DBname = "";
	}

	public DBBoundry getDbBoundry() {
		return dbBoundry;
	}

	/**
	 * Connect to the Driver and on success to the database
	 * 
	 * @param DBName : the database name('root' for example)
	 * @param DBUser : the database username
	 * @param DBPass : the database password
	 * @throws Exception with relevant message
	 */
	public void connectToDB(String DBName, String DBUser, String DBPass) throws Exception {
		this.DBname = DBName;
		if (!dbBoundry.ConnectDriver()) {
			throw new Exception("Error - can't connect to JDBC driver");
		}
		try {
			dbBoundry.ConnectDB(DBName, DBUser, DBPass);
		} catch (Exception e) {
			dbBoundry.disconnectDB();// disconnect the driver
			throw new Exception("Error - can't connect to the database");

		}
		isConnected = true;
	}

	/**
	 * Disconnect from the database
	 */
	public void disConnectFromDB() {
		isConnected = false;
		dbBoundry.disconnectDB();
	}

	/**
	 * Get order from the database
	 * 
	 * @param orderNum the order number
	 * @return the order, null if no such order exist
	 */
	public Order getOrdrFromDB(int orderNum) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".order WHERE (orderNumber = " + orderNum + " );";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Order> orders = objectManager.orderDB(res);
		return orders.size() > 0 ? orders.get(0) : null;
	}

	/**
	 * Update existing order status
	 * 
	 * @param order with updated status
	 * @return true if the order was updated
	 */

	public boolean updateOrder(Order order) {
		// create the query
		String s = "UPDATE  " + DBname + ".order  SET orderStatus = '" + order.getOrderStatus().toString()
				+ "'  WHERE (orderNumber = " + order.getOrderNumber() + ") ;";
		// send query + get result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	/**
	 * update the order arrival time
	 * 
	 * @param order
	 * @return
	 */
	public boolean updateOrderArrivalTime(Order order) {
		// create the query
		String s = "UPDATE  " + DBname + ".order  SET arrivalDate = TIMESTAMP '" + order.getArrivalDate()
				+ "'  WHERE (orderNumber = " + order.getOrderNumber() + ") ;";
		// send query + get result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	/**
	 * Save new order in orders table in database
	 * 
	 * @param new order
	 * @return order's ID generated in the database
	 */
	public int saveOrderToDB(Order order) {
		int lastID = -1;
		String s = "INSERT INTO " + DBname + ".order VALUES (default, TIMESTAMP '" + order.getOrderDate()
				+ "',TIMESTAMP '" + order.getArrivalDate() + "'," + order.isHomeDelivery() + ",'"
				+ order.getBranchName() + "','" + order.getPrice() + "','" + order.getUsername() + "','"
				+ order.getPersonalLetter() + "','" + order.getOrderStatus().toString() + "','" + order.getOrderData()
				+ "');";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		if (res) {
			s = "SELECT last_insert_id() as last_id from " + DBname + ".order";
			ResultSet idRes = (ResultSet) dbBoundry.sendQueary(s);
			lastID = objectManager.lastID(idRes);
		}
		return lastID;
	}

	/**
	 * delete order from orders table in database
	 * 
	 * @param order's ID
	 * @return true if order was deleted successfully
	 */

	public boolean deleteOrder(int orderNum) {
		String s = "DELETE FROM " + DBname + ".orders WHERE (orderNumber = " + orderNum + " );";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	/**
	 * save product in productsInOrder table in database
	 * 
	 * @param product object
	 * @return true if the product is saved successfully
	 */

	public boolean saveItemInOrderToDB(ProductInOrder product) {
		// create the query
		String s = "INSERT INTO " + DBname + ".productinorder VALUES ('" + product.getOrderNumber() + "','"
				+ product.getName() + "'," + product.getPrice() + "," + product.getAmount() + ",'"
				+ product.getCategory() + "','" + product.getData() + "');";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	/**
	 * get all product from productsInOrder table in database
	 * 
	 * @param Order's id
	 * @return ArrayList<ProductInOrder> of products in the order specified
	 */

	public ArrayList<ProductInOrder> getItemInOrderFromDB(int orderNumber) {
		// create the query
		ArrayList<ProductInOrder> itemsList = new ArrayList<>();
		ProductInOrder item;
		String s = "SELECT * FROM " + DBname + ".productinorder WHERE (orderNumber = " + orderNumber + " );";
		// get the result
		try {
			ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
			// get the returned values
			while (res.next()) {
				item = new ProductInOrder();
				item.setAmount(res.getInt("amount"));
				item.setCategory(res.getString("category"));
				item.setName(res.getString("name"));
				item.setOrderNumber(orderNumber);
				item.setPrice(res.getDouble("price"));
				item.setData(res.getString("data"));
				itemsList.add(item);
			}
		} catch (Exception e) {
			return null;
		}
		return itemsList;
	}

	/**
	 * return all orders in the specified branch
	 * 
	 * @param branchName name of the branch
	 * @return ArrayList<Order> with all the orders with the same branch name and
	 *         customer's id
	 */

	public ArrayList<Order> getAllOrdersInBranch(String branchName) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".order WHERE (branchName = '" + branchName + "' );";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Order> orders = objectManager.orderDB(res);
		return orders;
	}

	/**
	 * return all orders of the specified customer according to the customer's id
	 * 
	 * @param customerID
	 * @return ArrayList<Order> with all orders of the customer
	 */

	public ArrayList<Order> getAllOrdersOfCustomer(String customerID) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".order WHERE (customerID = '" + customerID + "' );";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Order> orders = objectManager.orderDB(res);
		return orders;
	}

	/**
	 * 
	 * @param orderNumber order's id
	 * @return DeliveryDetails of the specified order
	 */

	public DeliveryDetails getDeliveryDetails(int orderNumber) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".deliverydetails WHERE orderNumber = " + orderNumber + ";";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		DeliveryDetails deliveryDetails = objectManager.deliveryDetailsDB(res);
		return deliveryDetails;
	}

	/**
	 * 
	 * @param deliveryDetails object
	 * @return true if delivery's details saved in 'deliverydetails' table in the
	 *         database
	 */

	public boolean saveDeliveryDetails(DeliveryDetails deliveryDetails) {
		// create the query
		String s = "INSERT INTO " + DBname + ".deliverydetails VALUES (" + deliveryDetails.getOrderID() + ",'"
				+ deliveryDetails.getFirstName() + "','" + deliveryDetails.getLastName() + "','"
				+ deliveryDetails.getAddress() + "','" + deliveryDetails.getPhoneNumber() + "','"
				+ deliveryDetails.getComments() + "');";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	/**
	 * 
	 * @param report - general report with date and type and brnach's name
	 * @return specific report
	 */

	public Report getReportFromDB(Report report) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".report WHERE branchName = '" + report.getBranchName()
				+ "' AND type = '" + report.getType().toString() + "' AND year = " + report.getYear() + " AND month = "
				+ report.getMonth() + ";";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Report> reports = objectManager.reportDB(res);
		return reports.size() > 0 ? reports.get(0) : null;
	}

	/**
	 * 
	 * @param report - specific report to save on database
	 * @return true if report is saved successfully
	 */

	public boolean saveReportToDB(Report report) {
		// create the query
		String sdata = objectManager.objectToBlobString(report);
		String s = "INSERT INTO " + DBname + ".report VALUES ('" + report.getBranchName() + "','"
				+ report.getType().toString() + "','" + report.getYear() + "','" + report.getMonth() + "','" + sdata
				+ "');";
		// get the result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		// get the returned values
		return res;
	}

	/**
	 * 
	 * @param username - User's username
	 * @param password - User's password
	 * @return - true if the user logged in successfully
	 * @throws Exception if the user is already connected
	 */

	public boolean connectUser(String username, String password) throws Exception {
		// create the query
		boolean res = false;
		String s = "SELECT * FROM " + DBname + ".users WHERE (username = '" + username + "' );";
		// get the user
		ResultSet user = (ResultSet) dbBoundry.sendQueary(s);
		// check if password is correct
		try {
			if (user.next() && user.getString("password").equals(password)) {
				s = "UPDATE  " + DBname + ".users  SET connected = true WHERE (username = '" + username
						+ "' AND connected = false) ;";
				res = (boolean) dbBoundry.sendQueary(s);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		if (res == false) {
			throw new Exception("Already connecteds");
		}
		return res;
	}

	/**
	 * update's db that the user disconnected
	 * 
	 * @param username - the user that disconnect
	 * @return true if the user disconnected
	 */

	public boolean disconnectUser(String username) {
		boolean res = false;
		try {
			String s = "UPDATE  " + DBname + ".users  SET connected = false WHERE (username = '" + username + "');";
			res = (boolean) dbBoundry.sendQueary(s);
		} catch (Exception e) {
			// user is already disconnected
		}
		return res;
	}

	/**
	 * update user's account's functionality details
	 * 
	 * @param userName of the user that is edited
	 * @param type     - the type of user we want the user to be
	 * @param status   - the status of the user we want the user to be
	 * @return true if the user's details updated successfully
	 */

	public boolean updateUser(String userName, UserType type, UserStatus status) {
		// create the query
		String s = "UPDATE  " + DBname + ".users  SET userName = '" + userName + "', userType = '" + type.toString()
				+ "', status = '" + status.toString() + "' WHERE username = '" + userName + "';";
		// send query + get result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	/**
	 * get survey from the database
	 * 
	 * @param surveyNumber - the id number of the survey we want
	 * @return the requested survey
	 */

	public Survey getSurvey(int surveyNumber) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".survey WHERE (surveyNumber = " + surveyNumber + ");";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Survey> surveys = objectManager.surveyDB(res);
		return surveys.size() > 0 ? surveys.get(0) : null;
	}

	/**
	 * get survey's result
	 * 
	 * @param surveyNumber of the requested survey
	 * @return the requested survey
	 */

	public java.sql.Blob getSurveyResult(int surveyNumber) {
		// create the query
		java.sql.Blob pdf;
		String s = "SELECT * FROM " + DBname + ".survey WHERE (surveyNumber = " + surveyNumber + ");";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		pdf = objectManager.surveyResultDB(res);
		return pdf;
	}

	/**
	 * save the result of a survey as blob is the database
	 * 
	 * @param surveyNumber - the number of the survey we save the result for
	 * @param resultFile   - the file we save as blob in the database
	 * @return true if the result file is saved in the database successfully
	 */

	public boolean saveSurveyResult(int surveyNumber, SimpleFile resultFile) {
		// create the query
		String pdf = objectManager.objectToBlobString(resultFile);
		String s = "UPDATE  " + DBname + ".survey  SET surveyResult = '" + pdf + "'  WHERE (surveyNumber = "
				+ surveyNumber + ");";
		// get the result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		// get the returned values
		return res;
	}

	/**
	 * get all survey's in the database
	 * 
	 * @return all the surveys
	 */

	public ArrayList<Survey> getAllSurvey() {
		// create the query
		String s = "SELECT * FROM " + DBname + ".survey;";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Survey> surveys = objectManager.surveyDB(res);
		return surveys;
	}

	/**
	 * 
	 * @param answers      - list with 6 score values, 1 for each question
	 * @param surveyNumber - the survey's number save the anwer's score to
	 * @return true if the answers saved successfully
	 */

	public boolean addSurveyAnswers(int[] answers, int surveyNumber) {
		// create the query
		String s = "UPDATE  " + DBname + ".survey  SET a1 = a1 + " + answers[0] + " , a2 = a2 + " + answers[1]
				+ ", a3 = a3 + " + answers[2] + " , a4 = a4 + " + answers[3] + " , a5 = a5 + " + answers[4]
				+ " , a6 = a6 + " + answers[5] + " , participants = participants + 1 WHERE (surveyNumber = "
				+ surveyNumber + ");";
		// send query + get result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	/**
	 * save new survey in the database
	 * 
	 * @param survey - the survey we add to the database
	 * @return the id of new survey in the database
	 */

	public int createSurvey(Survey survey) {
		int lastID = -1;
		String[] questions = survey.getQuestions();
		String s = "INSERT INTO " + DBname + ".survey VALUES ('" + questions[0] + "','" + questions[1] + "','"
				+ questions[2] + "','" + questions[3] + "','" + questions[4] + "','" + questions[5]
				+ "', 0, 0, 0, 0, 0, 0, 0);";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		if (res) {
			// s = "select last_insert_id() as last_id from survey";
			// ResultSet idRes = (ResultSet) dbBoundry.sendQueary(s);
			// lastID = objectManager.lastID(idRes);
		}
		return lastID;
	}

	/**
	 * save new complaint in the database
	 * 
	 * @param complaint - the complain's object we save in the database
	 * @return id of the complaint we saved in the database
	 */

	public int createComplaint(Complaint complaint) {
		int lastID = -1;
		String s = "INSERT INTO " + DBname + ".complaint  VALUES (default ,'"
				+ complaint.getResponsibleEmployeeUserName() + "','" + complaint.getComplaint() + "','"
				+ complaint.getAnswer() + "'," + complaint.getCompensation() + ",'" + complaint.getStatus().toString()
				+ "','" + complaint.getCustomerID() + "', TIMESTAMP '" + complaint.getCreationTime() + "');";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		if (res) {
			s = "SELECT last_insert_id() as last_id from complaint";
			ResultSet idRes = (ResultSet) dbBoundry.sendQueary(s);
			lastID = objectManager.lastID(idRes);
		}
		return lastID;
	}

	/**
	 * update existing complaint
	 * 
	 * @param answer
	 * @param complaintNumber
	 * @param status
	 * @return
	 */
	public boolean updateComplaint(String answer, int complaintNumber, Status status) {
		String s = "UPDATE  " + DBname + ".complaint  SET answer = '" + answer + "', status = '" + status.toString()
				+ "' WHERE (complaintNumber = " + complaintNumber + ");";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	/**
	 * get complain from the database
	 * 
	 * @param complaintNumber - the complaint we want's number
	 * @return the complaint
	 */

	public Complaint getComplaint(int complaintNumber) {
		String s = "SELECT * FROM " + DBname + ".complaint WHERE (complaintNumber = " + complaintNumber + ");";
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Complaint> complaints = objectManager.complaintDB(res);
		if (complaints.size() > 0)
			return complaints.get(0);
		else
			return null;
	}

	/**
	 * get all complaints of a specific employee
	 * 
	 * @param employeeUsername - the employee in charge of the complaints we get
	 * @return ArrayList<Complaints> with the employee's complaints
	 */

	public ArrayList<Complaint> getAllComplaints(String employeeUsername) {
		String s = "SELECT * FROM " + DBname + ".complaint WHERE (responsibleEmployeeUsername = '" + employeeUsername
				+ "');";
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Complaint> complaints = objectManager.complaintDB(res);
		return complaints;
	}

	/**
	 * get all complaints in the database with a specific status
	 * 
	 * @param status - the status of the complaints we want
	 * @return ArrayList<Complaint> with the specified status
	 */

	public ArrayList<Complaint> getAllComplaints(Status status) {
		String s = "SELECT * FROM " + DBname + ".complaint WHERE (status = '" + status.toString() + "');";
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Complaint> complaints = objectManager.complaintDB(res);
		return complaints;
	}

	/**
	 * count the complaints in a month
	 * 
	 * @param month
	 * @param year
	 * @return
	 */
	public int countComplaints(int month, int year) {
		String s = "SELECT COUNT(1) FROM " + DBname + ".complaint WHERE MONTH(DATE(creationTime)) = " + month
				+ " AND YEAR(DATE(creationTime)) = " + year + ";";
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		int result = 0;
		try {
			if (res.next())
				result = res.getInt("COUNT(1)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * get all products in the category
	 * 
	 * @param category - the category we wants products from.
	 * @return ArrayList<Product> on the specified category.
	 */

	public ArrayList<Product> getCatalogCategory(String category) {
		String s = "SELECT * FROM " + DBname + ".product WHERE (category = '" + category + "');";
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Product> products = objectManager.productDB(res);
		return products;
	}

	/**
	 * get all orders of the specified month and year
	 * 
	 * @param month - the month the orders requested were made
	 * @param year  - the month the orders requested were made
	 * @return ArrayList<Order> of the specified month and year
	 */

	public ArrayList<Order> getAllOrdersForReport(int month, int year) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".order WHERE (MONTH(DATE(orderDate)) = " + month
				+ " AND YEAR(DATE(orderDate)) = " + year + " );";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Order> orders = objectManager.orderDB(res);
		return orders;
	}

	/**
	 * get all products in the specified order.
	 * 
	 * @param orderNumber - the order's products we want's number.
	 * @return ArrayList<ProductInOrder> with products of the order we want.
	 */

	public ArrayList<ProductInOrder> getAllProductsInOrder(int orderNumber) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".productInOrder WHERE (orderNumber = " + orderNumber + ");";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<ProductInOrder> products = objectManager.productsInOrderDB(res);
		return products;
	}

	/**
	 * get all reports between two specified month on a specific year
	 * 
	 * @param startMonths - the earliest month requested to get reports from.
	 * @param endMonth    - the latest month requested to get reports from.
	 * @param year        - the year requested to get the reports from.
	 * @return ArrayList<Report> with reports of the appropriate month and year
	 */

	public ArrayList<Report> getAllReportsInTimePeriod(int startMonths, int endMonth, int year) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".report WHERE (month >= " + startMonths + " AND month <= " + endMonth
				+ " AND year = " + year + ");";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Report> reports = objectManager.reportDB(res);
		return reports;
	}

	/**
	 * get user object with the specified username.
	 * 
	 * @param username - the user's we want's username.
	 * @return User object with the specified username.
	 */

	public User getUser(String username) {
		String s = "SELECT * FROM " + DBname + ".users WHERE (username = '" + username + "');";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<User> users = objectManager.userDB(res);
		if (users.size() > 0)
			return users.get(0);
		return null;
	}

	/**
	 * get credit-card information
	 * 
	 * @param username - the user's username we want his card information.
	 * @return usename's card's info.
	 */

	public String getCardInfo(String username) {
		String s = "SELECT cardInfo FROM " + DBname + ".creditdetails WHERE (username = '" + username + "');";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		String info = objectManager.cardDB(res);
		return info;
	}

	/**
	 * get names of all branches in the database
	 * 
	 * @return ArrayList<String> with branche's names
	 */

	public ArrayList<String> getAllBranches() {
		// create the query
		String s = "SELECT * FROM " + DBname + ".Branch;";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<String> branches = objectManager.branchNameDB(res);
		return branches;
	}

	/**
	 * save details of promotion in the database
	 * 
	 * @param promotion - promotion's object his details we want to save in the
	 *                  database
	 * @return ID the of the promotion we saved in the database.
	 */

	// promotions management
	public int savePromotion(Promotion promotion) {
		int lastID = -1;
		String s = "INSERT INTO " + DBname + ".promotion  VALUES (default , " + promotion.getProductID() + ","
				+ promotion.getDiscount() + ",'" + promotion.getPromotionText() + "','" + promotion.getCreationDate()
				+ "','" + promotion.getStatus().toString() + "');";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		if (res) {
			s = "SELECT last_insert_id() as last_id from promotion";
			ResultSet idRes = (ResultSet) dbBoundry.sendQueary(s);
			lastID = objectManager.lastID(idRes);
		}
		return lastID;
	}

	/**
	 * get the product of the specified product's id
	 * 
	 * @param productID - id of the product's requested
	 * @return Product object with the productsID
	 */

	public Product getProduct(int productID) {
		String s = "SELECT * FROM " + DBname + ".product WHERE (productID = " + productID + ");";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Product> prod = objectManager.productDB(res);
		if (prod.size() == 0)
			return null;
		else
			return prod.get(0);
	}

	/**
	 * delete product from the database
	 * 
	 * @param productNumber
	 * @return
	 */
	public boolean deleteProduct(int productNumber) {
		String s = "DELETE FROM " + DBname + ".product WHERE (productID = " + productNumber + " );";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	/**
	 * update's product's price.
	 * 
	 * @param product - the product's object we update in the database.
	 * @return true if the product's price updated successfully.
	 */
	public boolean updateProduct(Product product) {
		// create the query
		String s = "UPDATE  " + DBname + ".product  SET oldPrice  = " + product.getOldPrice() + ",  price = "
				+ product.getPrice() + ", name = '" + product.getName() + "', description = '"
				+ product.getDescription() + "', colors = '" + product.getColors() + "' , category = '"
				+ product.getCategory() + "'  WHERE productID = " + product.getProductID() + ";";
		// send query + get result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	/**
	 * update product price
	 * 
	 * @param product
	 * @return
	 */
	public boolean updateProductPrice(Product product) {
		// create the query
		String s = "UPDATE  " + DBname + ".product  SET oldPrice  = " + product.getOldPrice() + ",  price = "
				+ product.getPrice() + " WHERE productID = " + product.getProductID() + ";";
		// send query + get result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	/**
	 * save new product to the database
	 * 
	 * @param product
	 * @return
	 */
	public int saveProductToDB(Product product) {
		int lastID = -1;
		String s = "INSERT INTO " + DBname + ".product VALUES(default,'" + product.getName() + "'," + product.getPrice()
				+ ",'" + product.getDescription() + "','" + product.getColors() + "', NULL ,'" + product.getCategory()
				+ "'," + product.getOldPrice() + ");";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		if (res) {
			s = "SELECT last_insert_id() as last_id from " + DBname + ".product";
			ResultSet idRes = (ResultSet) dbBoundry.sendQueary(s);
			lastID = objectManager.lastID(idRes);
		}
		return lastID;
	}

	/**
	 * update's promotion's status
	 * 
	 * @param promotionID - the id of the promotion that is updated
	 * @param status      - the new status of the promotion
	 * @return - true if the status of the promotion updated successfully.
	 */
	public boolean updatePromotion(int promotionID, Status status) {
		// create the query
		String s = "UPDATE  " + DBname + ".promotion  SET status  = '" + status.toString() + "' WHERE promotionID = "
				+ promotionID + ";";
		// send query + get result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	/***
	 * get promotion with specified promotion's number.
	 * 
	 * @param promotionNumber - the promotion number of the promotion requested.
	 * @return Promotion object with the specified promotion number.
	 */

	public Promotion getPromotion(int promotionNumber) {
		String s = "SELECT * FROM " + DBname + ".promotion WHERE promotionID = " + promotionNumber + ";";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Promotion> promotions = objectManager.promotionsDB(res);
		if (promotions.size() == 0)
			return null;
		else
			return promotions.get(0);
	}

	/**
	 * get all promotions in the database.
	 * 
	 * @return ArrayList<Promotion> with all promotions in the database
	 */

	public ArrayList<Promotion> getAllPromotions() {
		String s = "SELECT * FROM " + DBname + ".promotion ;";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Promotion> promotions = objectManager.promotionsDB(res);
		return promotions;
	}

	/**
	 * get user's credit amount.
	 * 
	 * @param id - the user's id
	 * @return the amount of credit the user have
	 */

	public double getShopCredit(String id) {
		String s = "SELECT credit FROM " + DBname + ".shopcredit WHERE customerID = '" + id + "' ;";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		double credit = objectManager.shopCreditDB(res);
		return credit;
	}

	/**
	 * update user's credit amount.
	 * 
	 * @param id - the user's id
	 * @return true if the credit amount updated successfully.
	 */

	public boolean updateShopCredit(String id, double amount) {
		// create the query
		String s = "UPDATE  " + DBname + ".shopcredit  SET credit = credit + " + amount + " WHERE customerID = '" + id
				+ "' ;";
		// send query + get result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	/**
	 * insert user's row on the database with credit's amount
	 * 
	 * @param id     - id of the user
	 * @param credit - the amount of credit the user have
	 * @return true if the user's credit inserted successfully.
	 */
	public boolean saveShopCreditToDB(String id, double credit) {
		String s = "INSERT INTO " + DBname + ".shopcredit VALUES( '" + id + "' , " + credit + " );";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	/**
	 * For import users data into our database
	 * 
	 * @param user
	 */
	public boolean createNewUser(ImportedUserData user) {
		if (user == null)
			return false;
		String status = "Active";
		if (user.getType().equals("NonAuthorizedCustomer"))
			status = "NotActive";
		String s = "INSERT INTO " + DBname + ".users VALUES( '" + user.getUserName() + "' ,'" + user.getPassword()
				+ "' ,'" + user.getType() + "' , 0 ,'" + user.getFirstname() + "' ,'" + user.getLastname() + "' ,'"
				+ user.getEmail() + "' ,'" + user.getPhone() + "' ,'" + user.getId() + "' ,'" + status + "' ,'"
				+ user.getBranch() + "' );";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	/**
	 * save new card to the database
	 * 
	 * @param userName
	 * @param cardInfo
	 * @return
	 */
	public boolean saveCardInfo(String userName, String cardInfo) {
		String s = "INSERT INTO " + DBname + ".creditdetails VALUES('" + userName + "' , '" + cardInfo + "');";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	/**
	 * update existing card info
	 * 
	 * @param userName
	 * @param cardInfo
	 * @return
	 */
	public boolean updateCardInfo(String userName, String cardInfo) {
		// create the query
		String s = "UPDATE  " + DBname + ".creditdetails  SET cardInfo = '" + cardInfo + "' WHERE username = '"
				+ userName + "' ;";
		// send query + get result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	/**
	 * update all the users fields
	 * 
	 * @param user
	 * @return
	 */
	public boolean updateAllUserData(User user) {
		// create the query
		String s = "UPDATE  " + DBname + ".users  SET userType = '" + user.getUserType().toString()
				+ "',  firstName = '" + user.getFirstName() + "' , lastName = '" + user.getLastName() + "' , email ='"
				+ user.getEmail() + "', phoneNumber = '" + user.getPhoneNumber() + "' , personID = '"
				+ user.getPersonID() + "', status = '" + user.getStatus().toString() + "' , branch = '"
				+ user.getBranchName() + "' WHERE username = '" + user.getUsername() + "' ;";
		// send query + get result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	/**
	 * get all the users with user type
	 * 
	 * @param userType
	 * @return
	 */
	public ArrayList<User> getAllUsersWithType(UserType userType) {
		String s = "SELECT * FROM " + DBname + ".users WHERE (userType = '" + userType.toString() + "');";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<User> users = objectManager.userDB(res);
		return users;
	}

	/**
	 * get all the users from a branch
	 * 
	 * @param branch
	 * @return
	 */
	public ArrayList<User> getAllBranchEmployees(String branch) {
		String s = "SELECT * FROM " + DBname + ".users WHERE (branch = '" + branch + "');";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<User> users = objectManager.userDB(res);
		return users;
	}

}
