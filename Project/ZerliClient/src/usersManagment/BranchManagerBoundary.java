package usersManagment;

import java.util.ArrayList;

import client.ClientController;
import client.MsgController;
import msg.Msg;
import msg.MsgType;
import order.*;
import report.Report;
import report.ReportType;
import user.User;
import user.UserStatus;
import user.UserType;

/**
 * use the branch manager controller and the different controllers for the
 * needed actions
 * 
 *
 */
public class BranchManagerBoundary {

	/**
	 * clientController used to communicate with clientController and let the
	 * branchManger send to the server msg contains the data or mission we want to
	 * received from the server msgController used to create mission or ask message
	 * and received the returned data
	 */
	private ClientController clientController = ClientController.getInstance();
	private Msg msg;
	/**
	 * in msgController saved the received data (in case we receive ERROR type in
	 * the GUI we check the error string)
	 */
	private MsgController msgController;

	/**
	 * request the order approval, using order number and approve\not approve
	 * 
	 * @param orderNumber
	 * @return true if the request succeed
	 */
	public boolean requestApproveOrder(int orderNumber, boolean isApproved) {
		Order order = new Order();
		order.setOrderNumber(orderNumber);
		if (isApproved) // branchManger approved the request
		{
			order.setOrderStatus(OrderStatus.APPROVED);
		} else {
			order.setOrderStatus(OrderStatus.NOT_APPROVED);
		}
		msg = MsgController.createUPDATE_ORDER_STATUSMsg(order);
		msgController = clientController.sendMsg(msg);
		if (msgController.getType().equals(MsgType.COMPLETED)) // receive completed in type mean update has been done
																// and succeed
		{
			return true;
		}
		return false; // return false mean updated for new status not succeed

	}

	/**
	 * request the order cancellation approval, using order number and approve\not
	 * approve
	 * 
	 * @param orderNumber
	 * @return true if the request succeed
	 */
	public boolean requestApproveCancelation(int orderNumber, boolean isApproved) {

		Order order = new Order();
		order.setOrderNumber(orderNumber);
		if (isApproved) // branchManger not approve Cancellation
		{
			order.setOrderStatus(OrderStatus.CANCELED);
		} else {
			order.setOrderStatus(OrderStatus.APPROVED); // branchManger didn't accept to cancel then he approved the
														// order
		}
		msg = MsgController.createUPDATE_ORDER_STATUSMsg(order);
		msgController = clientController.sendMsg(msg);
		if (msgController.getType().equals(MsgType.COMPLETED)) // receive completed in type mean update has been done
																// and succeed
		{
			return true;
		}
		return false; // return false mean update not succeed
		// (if GUI received false it should access to msgController of
		// branchMangerBoundary
		// and check the Error string and display it)

	}

	/**
	 * update a user data
	 * 
	 * @param user
	 * @throws Exception - throw exception with error msg on failure
	 */
	public void requestUpdateUserData(User user) throws Exception {

		msg = MsgController.createUPATE_USER_DATAMsg(user);
		msgController = clientController.sendMsg(msg);
		if (msgController.getType().equals(MsgType.ERROR)) {
			throw new Exception(msgController.getErrorMsg());
		}
	}

	/**
	 * return the report get the report using the client controller
	 * 
	 * @return
	 */
	public Report requestViewReport(ReportType type, int Month, int year) {
		// String branchNameOfUser = CurrentUser.getBranchName(); // get branchName from
		// branchManger user
		String branchNameOfUser = UserBoundary.CurrentUser.getBranchName();
		msg = MsgController.createGET_REPORTMsg(type, year, Month, branchNameOfUser);
		msgController = clientController.sendMsg(msg);
		if (msgController.getType().equals(MsgType.RETURN_REPORT)) {
			return msgController.getReport();
		}
		return null; // in case returned msg was ERROR for Example mean Report not found or exist
	}

	/**
	 * get user data
	 * 
	 * @param username
	 * @return
	 */
	public User requestUser(String username) {
		msg = MsgController.createGET_USERMsg(username);
		msgController = clientController.sendMsg(msg);
		if (msgController.getType().equals(MsgType.RETURN_USER)) {
			return msgController.getUser();
		}
		return null; // in case returned msg was ERROR for Example mean user not found or exist
	}

	/**
	 * get all the branch orders
	 * 
	 * @return
	 */
	public ArrayList<Order> getAllOrdersToApprove() {
		msg = MsgController.createGET_ALL_ORDERSMsg();
		msgController = clientController.sendMsg(msg);
		if (msgController.getType().equals(MsgType.RETURN_ALL_ORDERS)) {
			return msgController.getOrders();
		}
		return null; // in case returned msg was ERROR for Example mean orders not found or exist
	}

	/**
	 * get all the product in order
	 * 
	 * @param orderNumber
	 * @return
	 */
	public ArrayList<ProductInOrder> getAllProductsInOrder(int orderNumber) {
		msg = MsgController.createGET_ORDERMsg(orderNumber);
		msgController = clientController.sendMsg(msg);
		if (msgController.getType().equals(MsgType.RETURN_ORDER)) {
			return msgController.getOrder().getItems();
		}
		return null; // in case returned msg was ERROR for Example mean orders not found or exist
	}

	/**
	 * get all the customers waiting for approval
	 * 
	 * @return
	 * @throws Exception on failure -> throw with error message
	 */
	public ArrayList<User> getAllWaitingForApprovalCustomers() throws Exception {
		msg = MsgController.createGET_ALL_USERSMsg(UserType.NonAuthorizedCustomer);
		msgController = clientController.sendMsg(msg);
		if (msgController.getType().equals(MsgType.ERROR)) {
			throw new Exception(msgController.getErrorMsg());
		}
		return msgController.getUsers();
	}

	/**
	 * get all the active customers
	 * 
	 * @return
	 * @throws Exception on failure -> throw with error message
	 */
	public ArrayList<User> getAllActiveCustomers() throws Exception {
		msg = MsgController.createGET_ALL_USERSMsg(UserType.AuthorizedCustomer);
		msgController = clientController.sendMsg(msg);
		if (msgController.getType().equals(MsgType.ERROR)) {
			throw new Exception(msgController.getErrorMsg());
		}
		return msgController.getUsers();
	}

	/**
	 * get all the branch's employees
	 * 
	 * @return
	 * @throws Exception on failure -> throw with error message
	 */
	public ArrayList<User> getAllEmployees() throws Exception {
		msg = MsgController.createGET_ALL_USERSMsg(UserType.BranchEmployee);
		msgController = clientController.sendMsg(msg);
		if (msgController.getType().equals(MsgType.ERROR)) {
			throw new Exception(msgController.getErrorMsg());
		}
		return msgController.getUsers();
	}

	/**
	 * approve a customer, must have active card to be approved
	 * 
	 * @param customer
	 * @param cardIinfo
	 * @throws Exception on failure -> throw with error message
	 */
	public void approveCustomer(User customer, String cardIinfo) throws Exception {
		// add the card
		addCard(customer, cardIinfo);
		// update the info
		customer.setStatus(UserStatus.Active);
		customer.setUserType(UserType.AuthorizedCustomer);
		msg = MsgController.createUPATE_USER_DATAMsg(customer);
		msgController = clientController.sendMsg(msg);
		if (msgController.getType() == MsgType.ERROR)
			throw new Exception(msgController.getErrorMsg());
	}

	/**
	 * get the branch list
	 * 
	 * @return
	 */
	public ArrayList<String> getBranches() {
		msg = MsgController.createGET_BRANCH_LISTMsg();
		msgController = clientController.sendMsg(msg);
		if (msgController.getType() == MsgType.ERROR)
			return new ArrayList<String>();
		return msgController.getBranchNames();
	}

	/**
	 * add card for a customer
	 * 
	 * @param customer
	 * @param cardIinfo
	 * @throws Exception on failure -> throw with error message
	 */
	public void addCard(User customer, String cardIinfo) throws Exception {
		msg = MsgController.createADD_CARDMsg(cardIinfo, customer.getUsername());
		msgController = clientController.sendMsg(msg);
		if (msgController.getType() == MsgType.ERROR)
			throw new Exception(msgController.getErrorMsg());
	}

}
