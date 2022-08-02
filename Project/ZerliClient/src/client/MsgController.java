package client;

import java.io.Serializable;
import java.util.ArrayList;
import cart.Cart;
import catalog.Product;
import complaint.Complaint;
import files.SimpleFile;
import msg.Msg;
import msg.MsgType;
import order.Order;
import promotion.Promotion;
import report.Report;
import report.ReportType;
import survey.Survey;
import user.User;
import user.UserType;

/**
 * message controller, can parse give message and save the type and fields in
 * the corresponding class members, hold static methods to create each type of
 * out client message
 * 
 * @author halel
 *
 */
public class MsgController {

	private MsgType type;
	private ArrayList<Complaint> complaints;
	private ArrayList<Product> catalogPage;
	private ArrayList<Survey> surveys;
	private ArrayList<Order> orders;
	private Order order;
	private Survey survey;
	private User user;
	private Report report;
	private String errorMsg;
	private ArrayList<String> branchNames;
	private ArrayList<Promotion> Allpromotions;
	private ArrayList<User> users;

	public MsgController() {
		resetParser();
	}

	private void resetParser() {
		this.type = MsgType.NONE;
		this.complaints = null;
		this.catalogPage = null;
		this.surveys = null;
		this.orders = null;
		this.order = null;
		this.survey = null;
		branchNames = null;
		this.user = null;
		this.Allpromotions = null;
	}

	/**
	 * parse given msg, return true if the msg was ok, can get the result using
	 * getters
	 * 
	 * @param msg
	 * @return true if the msg was ok
	 */
	@SuppressWarnings("unchecked")
	public boolean mgsParser(Object msg) {
		resetParser();
		if (msg == null)
			return false;
		if (!(msg instanceof Msg))
			return false;
		Msg newMsg = (Msg) msg;
		type = newMsg.type;
		switch (type) {// save the relevant data for each type
		case RETURN_ALL_COMPLAINTS:
			complaints = (ArrayList<Complaint>) newMsg.data;
			break;
		case RETURN_CATALOG_PAGE:
			catalogPage = (ArrayList<Product>) newMsg.data;
			break;
		case RETURN_ORDER:
			order = (Order) newMsg.data;
			break;
		case RETURN_SURVEY:
			survey = (Survey) newMsg.data;
			break;
		case RETURN_ALL_SURVEY:
			surveys = (ArrayList<Survey>) newMsg.data;
			break;
		case RETURN_ALL_ORDERS:
			orders = (ArrayList<Order>) newMsg.data;
			break;
		case APPROVE_LOGIN:
			user = (User) newMsg.data;
			break;
		case RETURN_REPORT:
			report = (Report) newMsg.data;
			break;
		case RETURN_BRANCH_NAMES:
			branchNames = (ArrayList<String>) newMsg.data;
			break;
		case RETURN_USER:
			user = (User) newMsg.data;
			break;
		case RETURN_ALL_PROMOTIONS:
			Allpromotions = (ArrayList<Promotion>) newMsg.data;
			break;
		case RETURN_ALL_USERS:
			users = (ArrayList<User>) newMsg.data;
			break;
		case ERROR:
			errorMsg = (String) newMsg.data;
			break;
		case RETURN_PAYMENT_APPROVAL:
		case APPROVE_LOGOUT:
		case EXIT:
		case COMPLETED:
			break;
		default:// no type was found, return false
			return false;
		}
		return true;
	}
	// getters

	public ArrayList<Promotion> getAllpromotions() {
		return Allpromotions;
	}

	public MsgType getType() {
		return type;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public ArrayList<Complaint> getComplaints() {
		return complaints;
	}

	public ArrayList<Product> getCatalogPage() {
		return catalogPage;
	}

	public ArrayList<Survey> getSurveys() {
		return surveys;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public Order getOrder() {
		return order;
	}

	public Survey getSurvey() {
		return survey;
	}

	public Report getReport() {
		return report;
	}

	public User getUser() {
		return user;
	}

	public ArrayList<String> getBranchNames() {
		return branchNames;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	// create msg static methods
	/**
	 * create new exit msg
	 * 
	 * @return
	 */
	public static Msg createEXITMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.EXIT;
		msg.data = null;
		return msg;
	}

	/**
	 * create new ERROR msg
	 * 
	 * @return
	 */
	public static Msg createERRORMsg(String errorMsg) {
		Msg msg = new Msg();
		msg.type = MsgType.ERROR;
		msg.data = errorMsg;
		return msg;
	}

	/**
	 * create new CREATE_COMPLAINT msg
	 * 
	 * @return
	 */
	public static Msg createCREATE_COMPLAINTMsg(Complaint complaint) {
		Msg msg = new Msg();
		msg.type = MsgType.CREATE_COMPLAINT;
		msg.data = complaint;
		return msg;
	}

	/**
	 * create new GET_ALL_COMPLAINT msg
	 * 
	 * @return
	 */
	public static Msg createGET_ALL_COMPLAINTSMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.GET_ALL_COMPLAINTS;
		msg.data = null;
		return msg;
	}

	/**
	 * create new CREATE_COMPLAINT msg
	 * 
	 * @return
	 */
	public static Msg createUPDATE_COMPLAINTMsg(Complaint complaint) {
		Msg msg = new Msg();
		msg.type = MsgType.UPDATE_COMPLAINT;
		msg.data = complaint;
		return msg;
	}

	/**
	 * create new GET_CATALOG_PAGE msg
	 * 
	 * @return
	 */
	public static Msg createGET_CATALOG_PAGEMsg(int pageNumber, String category) {
		Msg msg = new Msg();
		msg.type = MsgType.GET_CATALOG_PAGE;
		ArrayList<Serializable> data = new ArrayList<Serializable>();
		data.add(pageNumber);
		data.add(category);
		msg.data = data;
		return msg;
	}

	/**
	 * create new PLACE_ORDER_REQUEST msg
	 * 
	 * @return
	 */
	public static Msg createPLACE_ORDER_REQUESTMsg(Cart cart) {
		Msg msg = new Msg();
		msg.type = MsgType.PLACE_ORDER_REQUEST;
		msg.data = cart;
		return msg;
	}

	/**
	 * create new PAY_FOR_ORDER msg
	 * 
	 * @return
	 */
	public static Msg createPAY_FOR_ORDERMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.PAY_FOR_ORDER;
		msg.data = null;
		return msg;
	}

	/**
	 * create new UPDATE_CATALOG msg
	 * 
	 * @return
	 */
	public static Msg createUPDATE_CATALOGMsg(Product product) {
		Msg msg = new Msg();
		msg.type = MsgType.UPDATE_CATALOG;
		msg.data = product;
		return msg;
	}

	/**
	 * create new CREATE_SURVEY msg
	 * 
	 * @return
	 */
	public static Msg createCREATE_SURVEYMsg(Survey survey) {
		Msg msg = new Msg();
		msg.type = MsgType.CREATE_SURVEY;
		msg.data = survey;
		return msg;
	}

	/**
	 * create new ADD_SURVEY_RESULT msg
	 * 
	 * @return
	 */
	public static Msg createADD_SURVEY_RESULTMsg(SimpleFile resultFile, int surveyNumber) {
		Msg msg = new Msg();
		msg.type = MsgType.ADD_SURVEY_RESULT;
		ArrayList<Serializable> data = new ArrayList<Serializable>();
		data.add(surveyNumber);
		data.add(resultFile);
		msg.data = data;
		return msg;
	}

	/**
	 * create new ADD_SURVEY_ANSWERS msg in the 0 place we have the survey number in
	 * the 1-6 we have the answers
	 * 
	 * @return
	 */
	public static Msg createADD_SURVEY_ANSWERSMsg(ArrayList<Integer> answers) {
		Msg msg = new Msg();
		msg.type = MsgType.ADD_SURVEY_ANSWERS;
		msg.data = answers;
		return msg;
	}

	/**
	 * create new GET_SURVEY msg
	 * 
	 * @return
	 */
	public static Msg createGET_SURVEYMsg(int surveyNumber) {
		Msg msg = new Msg();
		msg.type = MsgType.GET_SURVEY;
		msg.data = surveyNumber;
		return msg;
	}

	/**
	 * create new GET_ALL_SURVEY msg
	 * 
	 * @return
	 */
	public static Msg createGET_ALL_SURVEYMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.GET_ALL_SURVEY;
		msg.data = null;
		return msg;
	}

	/**
	 * create new LOGIN_REQUEST msg
	 * 
	 * @return
	 */
	public static Msg createLOGIN_REQUESTMsg(String userName, String password) {
		Msg msg = new Msg();
		msg.type = MsgType.LOGIN_REQUEST;
		ArrayList<Serializable> data = new ArrayList<Serializable>();
		data.add(userName);
		data.add(password);
		msg.data = data;
		return msg;
	}

	/**
	 * create new LOG_OUT_REQUEST msg
	 * 
	 * @return
	 */
	public static Msg createLOG_OUT_REQUESTMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.LOG_OUT_REQUEST;
		msg.data = null;
		return msg;
	}

	/**
	 * create new UPATE_USER_DATA msg
	 * 
	 * @return
	 */
	public static Msg createUPATE_USER_DATAMsg(User user) {
		Msg msg = new Msg();
		msg.type = MsgType.UPATE_USER_DATA;
		msg.data = user;
		return msg;
	}

	/**
	 * create new GET_ALL_ORDERS msg
	 * 
	 * @return
	 */
	public static Msg createGET_ALL_ORDERSMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.GET_ALL_ORDERS;
		msg.data = null;
		return msg;
	}

	/**
	 * create new UPDATE_ORDER_STATUS msg
	 * 
	 * @return
	 */
	public static Msg createUPDATE_ORDER_STATUSMsg(Order order) {
		Msg msg = new Msg();
		msg.type = MsgType.UPDATE_ORDER_STATUS;
		msg.data = order;
		return msg;
	}

	/**
	 * create GET_REPORT msg
	 * 
	 * @param type
	 * @param year
	 * @param month
	 * @return
	 */
	public static Msg createGET_REPORTMsg(ReportType type, int year, int month, String branch) {
		Msg msg = new Msg();
		msg.type = MsgType.GET_REPORT;
		ArrayList<Serializable> data = new ArrayList<Serializable>();
		data.add(type);
		data.add(year);
		data.add(month);
		data.add(branch);
		msg.data = data;
		return msg;
	}

	/**
	 * create GET_BRANCH_LIST
	 * 
	 * @return
	 */
	public static Msg createGET_BRANCH_LISTMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.GET_BRANCH_LIST;
		msg.data = null;
		return msg;
	}

	public static Msg createGET_ALL_PROMOTIONSMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.GET_ALL_PROMOTIONS;
		msg.data = null;
		return msg;
	}

	/**
	 * create REQUEST_USER
	 * 
	 * @return
	 */
	public static Msg createGET_USERMsg(String username) {
		Msg msg = new Msg();
		msg.type = MsgType.GET_USER;
		msg.data = username;
		return msg;
	}

	/**
	 * GET_ORDER
	 * 
	 * @return
	 */
	public static Msg createGET_ORDERMsg(int orderNumber) {
		Msg msg = new Msg();
		msg.type = MsgType.GET_ORDER;
		msg.data = orderNumber;
		return msg;
	}

	/**
	 * CREATE_NEW_PROMOTION
	 * 
	 * @return
	 */
	public static Msg createCREATE_NEW_PROMOTIONMsg(Promotion promotion) {
		Msg msg = new Msg();
		msg.type = MsgType.CREATE_NEW_PROMOTION;
		msg.data = promotion;
		return msg;
	}

	/**
	 * END_PROMOTION
	 * 
	 * @return
	 */
	public static Msg createEND_PROMOTIONMsg(int promotionNumber) {
		Msg msg = new Msg();
		msg.type = MsgType.END_PROMOTION;
		msg.data = promotionNumber;
		return msg;
	}

	/**
	 * activate existing promotion
	 * 
	 * @return
	 */
	public static Msg createACTIVATE_PROMOTIONMsg(int promotionNumber) {
		Msg msg = new Msg();
		msg.type = MsgType.ACTIVATE_PROMOTION;
		msg.data = promotionNumber;
		return msg;
	}

	/**
	 * remove product from the catalog
	 * 
	 * @return
	 */
	public static Msg createREMOVE_FROM_CATALOGMsg(int productNumber) {
		Msg msg = new Msg();
		msg.type = MsgType.REMOVE_FROM_CATALOG;
		msg.data = productNumber;
		return msg;
	}

	/**
	 * add product to the catalog
	 * 
	 * @return
	 */
	public static Msg createADD_TO_CATALOGMsg(Product product) {
		Msg msg = new Msg();
		msg.type = MsgType.ADD_TO_CATALOG;
		msg.data = product;
		return msg;
	}

	/**
	 * get all users with the type
	 * 
	 * @return
	 */
	public static Msg createGET_ALL_USERSMsg(UserType type) {
		Msg msg = new Msg();
		msg.type = MsgType.GET_ALL_USERS;
		msg.data = type;
		return msg;
	}

	/**
	 * save card for user
	 * 
	 * @return
	 */
	public static Msg createADD_CARDMsg(String cardInfo, String username) {
		Msg msg = new Msg();
		msg.type = MsgType.ADD_CARD;
		ArrayList<String> arr = new ArrayList<>();
		arr.add(cardInfo);
		arr.add(username);
		msg.data = arr;
		return msg;
	}

}