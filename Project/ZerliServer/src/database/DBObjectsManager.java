package database;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import catalog.Product;
import common.Status;
import complaint.Complaint;
import files.SimpleFile;
import order.DeliveryDetails;
import order.Order;
import order.OrderStatus;
import order.ProductInOrder;
import promotion.Promotion;
import report.Report;
import survey.Survey;
import user.User;
import user.UserStatus;
import user.UserType;

/**
 * Manage the creation of the different object from the result set
 */
public class DBObjectsManager {

	/**
	 * change object into a string to put in database query as blob
	 * 
	 * @param object - the object we want to save as blob
	 * @return String representing the object.
	 */
	String objectToBlobString(Object object) {
		byte[] data;
		String sdata = "";
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(object);
			oos.flush();
			data = bos.toByteArray();
			sdata = Base64.getEncoder().encodeToString(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sdata;
	}

	/**
	 * change blob object into the original object
	 * 
	 * @param blob
	 * @return the original object from blob.
	 */

	Object blobToObject(java.sql.Blob blob) {
		Object object = null;
		byte[] data;
		try {
			blob.getBytes(1, (int) blob.length());
			data = blob.getBytes(1, (int) blob.length());
			ByteArrayInputStream bis = new ByteArrayInputStream(Base64.getDecoder().decode(data));
			ObjectInputStream ois = new ObjectInputStream(bis);
			object = ois.readObject();
		} catch (Exception e) {
			return null;
		}
		return object;
	}

	/**
	 * 
	 * @param res - resultSet of query to get last id
	 * @return last id generated automatically in the database
	 */

	int lastID(ResultSet res) {
		int lastID = -1;
		try {
			if (res.next()) {
				lastID = res.getInt("last_id");
			}
		} catch (SQLException e) {
		}
		return lastID;

	}

	/**
	 * create the ArrayList<Order> object from the resultset
	 * 
	 * @param res - resultSet of query to get orders
	 * @return ArrayList<Order> object
	 */

	ArrayList<Order> orderDB(ResultSet res) {
		ArrayList<Order> orders = new ArrayList<>();
		try {
			while (res.next()) {
				Order order = new Order();
				order.setOrderNumber(res.getInt("orderNumber"));
				order.setOrderDate(res.getTimestamp("orderDate", Calendar.getInstance()));
				order.setArrivalDate(res.getTimestamp("arrivalDate", Calendar.getInstance()));
				order.setHomeDelivery(res.getBoolean("homeDelivery"));
				order.setBranchName(res.getString("branchName"));
				order.setPrice(res.getDouble("price"));
				order.setUsername(res.getString("customerID"));
				order.setPersonalLetter(res.getString("personalLetter"));
				order.setOrderStatus(OrderStatus.valueOf(res.getString("orderStatus")));
				order.setOrderData(res.getString("data"));
				orders.add(order);
			}
		} catch (SQLException e) {
		}
		return orders;
	}

	/**
	 * 
	 * @param res - resultSet of query to get surveys
	 * @return ArrayList<Survey> object
	 */

	ArrayList<Survey> surveyDB(ResultSet res) {
		ArrayList<Survey> surveys = new ArrayList<>();
		try {
			while (res.next()) {
				Survey survey = new Survey(res.getString("q1"), res.getString("q2"), res.getString("q3"),
						res.getString("q4"), res.getString("q5"), res.getString("q6"));
				int[] answers = new int[6];
				for (int i = 1; i <= 6; i++) {
					answers[i - 1] = res.getInt("a" + i);
				}
				SimpleFile resultFile = (SimpleFile) blobToObject(res.getBlob("surveyResult"));
				survey.setResultFile(resultFile);
				survey.setAnswers(answers);
				survey.setNumberOfParticipants(res.getInt("participants"));
				surveys.add(survey);
				survey.setSurveyNumber(res.getInt("surveyNumber"));
			}
		} catch (Exception e) {
		}
		return surveys;
	}

	/**
	 * 
	 * @param res resultSet of query to get complaints
	 * @return ArrayList<Complaint> object
	 */

	public ArrayList<Complaint> complaintDB(ResultSet res) {
		ArrayList<Complaint> complaints = new ArrayList<>();
		try {
			while (res.next()) {
				Complaint complaint = new Complaint(res.getString("responsibleEmployeeUsername"),
						res.getString("complaint"), res.getString("customerID"));
				complaint.setAnswer(res.getString("answer"));
				complaint.setCompensation(res.getDouble("compensation"));
				complaint.setComplaintsNumber(res.getInt("complaintNumber"));
				complaint.setStatus(Status.valueOf(res.getString("status")));
				complaint.setCreationTime(res.getTimestamp("creationTime", Calendar.getInstance()));
				complaints.add(complaint);
				// need set for participants and answers
			}
		} catch (Exception e) {
		}
		return complaints;
	}

	/**
	 * 
	 * @param res - resultSet of query to get products
	 * @return ArrayList<Product> object
	 */

	public ArrayList<Product> productDB(ResultSet res) {
		ArrayList<Product> products = new ArrayList<>();
		try {
			while (res.next()) {
				Product product = new Product(res.getInt("productID"));
				product.setName(res.getString("name"));
				product.setPrice(res.getDouble("price"));
				product.setDescription(res.getString("description"));
				product.setColors(res.getString("colors"));
				product.setCategory(res.getString("category"));
				product.setProductID(res.getInt("productID"));
				product.setOldPrice(res.getDouble("oldPrice"));
				product.setImage(null);
				products.add(product);
			}
		} catch (Exception e) {
		}
		return products;
	}

	/**
	 * 
	 * @param res - resultSet of query to get 'Product In Order'.
	 * @return ArrayList<ProductInOrder>
	 */

	public ArrayList<ProductInOrder> productsInOrderDB(ResultSet res) {
		ArrayList<ProductInOrder> products = new ArrayList<>();
		try {
			while (res.next()) {
				ProductInOrder product = new ProductInOrder();
				product.setOrderNumber(res.getInt("orderNumber"));
				product.setName(res.getString("name"));
				product.setPrice(res.getDouble("price"));
				product.setAmount(res.getInt("amount"));
				product.setCategory(res.getString("category"));
				products.add(product);
			}
		} catch (Exception e) {
		}
		return products;
	}

	/**
	 * 
	 * @param res - resultSet of query to get reports.
	 * @return ArrayList<Report> object
	 */

	public ArrayList<Report> reportDB(ResultSet res) {
		ArrayList<Report> reports = new ArrayList<>();
		try {
			while (res.next()) {
				reports.add((Report) blobToObject(res.getBlob("data")));
			}
		} catch (Exception e) {
		}
		return reports;
	}

	/**
	 * 
	 * @param res - resultSet of query to get user.
	 * @return User object
	 */

	public ArrayList<User> userDB(ResultSet res) {
		ArrayList<User> arr = new ArrayList<User>();
		User user = null;
		try {
			while (res.next()) {
				user = new User();
				user.setUsername(res.getString("username"));
				user.setUserType(UserType.valueOf(res.getString("userType")));
				user.setConnected(res.getBoolean("connected"));
				user.setFirstName(res.getString("firstName"));
				user.setLastName(res.getString("lastName"));
				user.setEmail(res.getString("email"));
				user.setPhoneNumber(res.getString("phoneNumber"));
				user.setPersonID(res.getString("personID"));
				user.setStatus(UserStatus.valueOf(res.getString("status")));
				user.setPersonID(res.getString("personID"));
				user.setBranchName(res.getString("branch"));
				arr.add(user);
			}
		} catch (Exception e) {
		}
		return arr;
	}

	/**
	 * 
	 * @param res - resultSet of query to get card's info.
	 * @return String with the card's information.
	 */

	public String cardDB(ResultSet res) {
		String info = null;
		try {
			if (res.next()) {
				info = res.getString("cardInfo");
				// need set for participants and answers
			}
		} catch (Exception e) {
		}
		return info;
	}

	/**
	 * 
	 * @param res - resultSet of query to get survey's result's blob.
	 * @return Blob object
	 */

	public Blob surveyResultDB(ResultSet res) {
		java.sql.Blob pdf = null;
		try {
			if (res.next()) {
				pdf = res.getBlob("surveyResult");
				// need set for participants and answers
			}
		} catch (Exception e) {
		}
		return pdf;
	}

	/**
	 * 
	 * @param res - resultSet of query to get branch's names.
	 * @return ArrayList<String> object
	 */

	public ArrayList<String> branchNameDB(ResultSet res) {
		ArrayList<String> branches = new ArrayList<>();
		try {
			while (res.next()) {
				branches.add(res.getString("branchName"));
			}
		} catch (Exception e) {
		}
		return branches;
	}

	/**
	 * 
	 * @param res - resultSet of query to get promotion's.
	 * @return ArrayList<Promotion> object.
	 */

	public ArrayList<Promotion> promotionsDB(ResultSet res) {
		ArrayList<Promotion> promotions = new ArrayList<>();
		try {
			while (res.next()) {
				Promotion promotion = new Promotion();
				promotion.setCreationDate(res.getTimestamp("creationDate", Calendar.getInstance()));
				promotion.setDiscount(res.getDouble("discount"));
				promotion.setProductID(res.getInt("productID"));
				promotion.setPromotionNumber(res.getInt("promotionID"));
				promotion.setPromotionText(res.getString("promotionText"));
				promotion.setStatus(Status.valueOf(res.getString("status")));
				promotions.add(promotion);
			}
		} catch (Exception e) {
		}
		return promotions;
	}

	/**
	 * 
	 * @param res - resultSet of query to get delivery details.
	 * @return DeliveryDetails object
	 */

	public DeliveryDetails deliveryDetailsDB(ResultSet res) {
		DeliveryDetails deliveryDetails = null;
		try {
			if (res.next()) {
				deliveryDetails = new DeliveryDetails();
				deliveryDetails.setOrderID(res.getInt("orderNumber"));
				deliveryDetails.setAddress(res.getString("address"));
				deliveryDetails.setComments(res.getString("comments"));
				deliveryDetails.setFirstName(res.getString("firstName"));
				deliveryDetails.setLastName(res.getString("lastName"));
				deliveryDetails.setPhoneNumber(res.getString("phoneNumber"));
			}
		} catch (Exception e) {
		}
		return deliveryDetails;
	}

	/**
	 * 
	 * @param res - resultSet of query to get user's credit amount.
	 * @return double object
	 */

	public double shopCreditDB(ResultSet res) {
		double credit = -1;
		try {
			if (res.next()) {
				credit = res.getDouble("credit");
			}
		} catch (Exception e) {
		}
		return credit;
	}

}