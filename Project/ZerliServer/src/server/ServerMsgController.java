package server;

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

public class ServerMsgController {

	private MsgType type;
	private Complaint complaint;
	private Promotion promotion;
	private int pageNumber;
	private String category;
	private Cart cart;
	private Product product;
	private Survey survey;
	private SimpleFile resultFile;
	private int surveyNumber;
	private int[] answers;
	private String userName;
	private String password;
	private User user;
	private Order order;
	private ReportType reportType;
	private int year, month;
	private String branch;
	private int orderNumber;
	private int promotionNumber;
	private int productNumber;
	private String cardinfo;
	private UserType userType;

	private void resetParser() {
		this.type = MsgType.NONE;
		complaint = null;
		promotion = null;
		pageNumber = -1;
		category = null;
		cart = null;
		product = null;
		survey = null;
		resultFile = null;
		surveyNumber = -1;
		answers = null;
		userName = null;
		password = null;
		user = null;
		order = null;
		cardinfo = null;
		year = 0;
		month = 0;
		branch = "";
		orderNumber = 0;
		promotionNumber = 0;
		productNumber = 0;
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
		case CREATE_COMPLAINT:
		case UPDATE_COMPLAINT:
			complaint = (Complaint) newMsg.data;
			break;
		case GET_CATALOG_PAGE:
			ArrayList<Serializable> data = (ArrayList<Serializable>) newMsg.data;
			pageNumber = (int) data.get(0);
			category = (String) data.get(1);
			break;
		case PLACE_ORDER_REQUEST:
			cart = (Cart) newMsg.data;
			break;
		case CREATE_SURVEY:
			survey = (Survey) newMsg.data;
			break;
		case ADD_SURVEY_RESULT:
			ArrayList<Serializable> surveyData = (ArrayList<Serializable>) newMsg.data;
			surveyNumber = (int) surveyData.get(0);
			resultFile = (SimpleFile) surveyData.get(1);
			break;
		case ADD_SURVEY_ANSWERS:
			answers = new int[6];
			ArrayList<Integer> tempAnswers = (ArrayList<Integer>) newMsg.data;
			surveyNumber = tempAnswers.get(0);
			for (int i = 0; i < 6; i++) {
				answers[i] = tempAnswers.get(i + 1);
			}
			break;
		case GET_SURVEY:
			surveyNumber = (int) newMsg.data;
			break;
		case LOGIN_REQUEST:
			ArrayList<Serializable> loginData = (ArrayList<Serializable>) newMsg.data;
			userName = (String) loginData.get(0);
			password = (String) loginData.get(1);
			break;
		case UPATE_USER_DATA:
			user = (User) newMsg.data;
			break;
		case UPDATE_ORDER_STATUS:
			order = (Order) newMsg.data;
			break;
		case GET_REPORT:
			ArrayList<Serializable> reportData = (ArrayList<Serializable>) newMsg.data;
			reportType = ((ReportType) reportData.get(0));
			year = ((int) reportData.get(1));
			month = ((int) reportData.get(2));
			branch = ((String) reportData.get(3));
			break;
		case GET_USER:
			userName = (String) newMsg.data;
			break;
		case GET_ORDER:
			orderNumber = (int) newMsg.data;
			break;
		case CREATE_NEW_PROMOTION:
			promotion = (Promotion) newMsg.data;
			break;
		case END_PROMOTION:
			promotionNumber = (int) newMsg.data;
			break;
		case ACTIVATE_PROMOTION:
			promotionNumber = (int) newMsg.data;
			break;
		case UPDATE_CATALOG:
		case ADD_TO_CATALOG:
			product = (Product) newMsg.data;
			break;
		case REMOVE_FROM_CATALOG:
			productNumber = (int) newMsg.data;
			break;
		case ADD_CARD:
			ArrayList<String> arr = (ArrayList<String>) newMsg.data;
			cardinfo = arr.get(0);
			userName = arr.get(1);
			break;
		case GET_ALL_USERS:
			userType = (UserType) newMsg.data;
			break;
		case GET_ALL_COMPLAINTS:
		case GET_ALL_ORDERS:
		case LOG_OUT_REQUEST:
		case GET_ALL_SURVEY:
		case PAY_FOR_ORDER:
		case GET_BRANCH_LIST:
		case GET_ALL_PROMOTIONS:
		case EXIT:
		case ERROR:
			break;
		default:// no type was found, return false
			return false;
		}
		return true;
	}

	// getters

	public int getPromotionNumber() {
		return promotionNumber;
	}

	public UserType getUserType() {
		return userType;
	}

	public String getCardinfo() {
		return cardinfo;
	}

	public void setPromotionNumber(int promotionNumber) {
		this.promotionNumber = promotionNumber;
	}

	public ReportType getReportType() {
		return reportType;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public String getBranch() {
		return branch;
	}

	public MsgType getType() {
		return type;
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public String getCategory() {
		return category;
	}

	public Cart getCart() {
		return cart;
	}

	public Product getProduct() {
		return product;
	}

	public Survey getSurvey() {
		return survey;
	}

	public SimpleFile getResultFile() {
		return resultFile;
	}

	public int getSurveyNumber() {
		return surveyNumber;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public User getUser() {
		return user;
	}

	public Order getOrder() {
		return order;
	}

	public int[] getAnswers() {
		return answers;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public int getProductNumber() {
		return productNumber;
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
	 * create new COMPLETED msg
	 * 
	 * @return
	 */
	public static Msg createCOMPLETEDMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.COMPLETED;
		msg.data = null;
		return msg;
	}

	/**
	 * create new RETURN_ALL_COMPLAINTS msg
	 * 
	 * @return
	 */
	public static Msg createRETURN_ALL_COMPLAINTSMsg(ArrayList<Complaint> complaints) {
		Msg msg = new Msg();
		msg.type = MsgType.RETURN_ALL_COMPLAINTS;
		msg.data = complaints;
		return msg;
	}

	/**
	 * create new RETURN_CATALOG_PAGE msg
	 * 
	 * @return
	 */
	public static Msg createRETURN_CATALOG_PAGEMsg(ArrayList<Product> catalogPage) {
		Msg msg = new Msg();
		msg.type = MsgType.RETURN_CATALOG_PAGE;
		msg.data = catalogPage;
		return msg;
	}

	/**
	 * create new RETURN_ORDER msg
	 * 
	 * @return
	 */
	public static Msg createRETURN_ORDERMsg(Order order) {
		Msg msg = new Msg();
		msg.type = MsgType.RETURN_ORDER;
		msg.data = order;
		return msg;
	}

	/**
	 * create new RETURN_SURVEY msg
	 * 
	 * @return
	 */
	public static Msg createRETURN_SURVEYMsg(Survey survey) {
		Msg msg = new Msg();
		msg.type = MsgType.RETURN_SURVEY;
		msg.data = survey;
		return msg;
	}

	/**
	 * create new RETURN_ALL_SURVEY msg
	 * 
	 * @return
	 */
	public static Msg createRETURN_ALL_SURVEYMsg(ArrayList<Survey> surveys) {
		Msg msg = new Msg();
		msg.type = MsgType.RETURN_ALL_SURVEY;
		msg.data = surveys;
		return msg;
	}

	/**
	 * create new RETURN_ALL_ORDERS msg
	 * 
	 * @return
	 */
	public static Msg createRETURN_ALL_ORDERSMsg(ArrayList<Order> orders) {
		Msg msg = new Msg();
		msg.type = MsgType.RETURN_ALL_ORDERS;
		msg.data = orders;
		return msg;
	}

	/**
	 * create new COMPLETED msg
	 * 
	 * @return
	 */
	public static Msg createAPPROVE_LOGINMsg(User user) {
		Msg msg = new Msg();
		msg.type = MsgType.APPROVE_LOGIN;
		msg.data = user;
		return msg;
	}

	/**
	 * create new RETURN_PAYMENT_APPROVAL msg
	 * 
	 * @return
	 */
	public static Msg createRETURN_PAYMENT_APPROVALMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.RETURN_PAYMENT_APPROVAL;
		msg.data = null;
		return msg;
	}

	/**
	 * create new APPROVE_LOGOUT msg
	 * 
	 * @return
	 */
	public static Msg createAPPROVE_LOGOUTMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.APPROVE_LOGOUT;
		msg.data = null;
		return msg;
	}

	public static Msg creatRETURN_REPORTMsg(Report report) {
		Msg msg = new Msg();
		msg.type = MsgType.RETURN_REPORT;
		msg.data = report;
		return msg;
	}

	/**
	 * RETURN_USER
	 * 
	 * @return
	 */
	public static Msg createRETURN_USERMsg(User user) {
		Msg msg = new Msg();
		msg.type = MsgType.RETURN_USER;
		msg.data = user;
		return msg;
	}

	/**
	 * return branch names msg
	 * 
	 * @return
	 */
	public static Msg createRETURN_BRANCH_NAMESMsg(ArrayList<String> branches) {
		Msg msg = new Msg();
		msg.type = MsgType.RETURN_BRANCH_NAMES;
		msg.data = branches;
		return msg;
	}

	/**
	 * RETURN_ALL_PROMOTIONS msg
	 * 
	 * @return
	 */
	public static Msg createRETURN_ALL_PROMOTIONSMsg(ArrayList<Promotion> promotions) {
		Msg msg = new Msg();
		msg.type = MsgType.RETURN_ALL_PROMOTIONS;
		msg.data = promotions;
		return msg;
	}

	/**
	 * RETURN_ALL_USERS msg
	 * 
	 * @return
	 */
	public static Msg createRETURN_ALL_USERSMsg(ArrayList<User> users) {
		Msg msg = new Msg();
		msg.type = MsgType.RETURN_ALL_USERS;
		msg.data = users;
		return msg;
	}
}
