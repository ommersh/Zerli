package report;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * quarterly all branches orders report
 * 
 * @author halel
 *
 */
public class QuarterlyOrdersReport extends Report {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * orders per day,per month, the 0 place is the 1st day and so on each row of
	 * the matrix is a month, each column of the matrix is the day
	 */
	private int[][] ordersPerDay;
	/**
	 * orders of items per category
	 */
	private HashMap<String, Integer> ordersPerCategory;
	/**
	 * total quarter orders
	 */
	private int totalOrders;
	/**
	 * list of the most popular items
	 */
	private ArrayList<String> mostPopularItems;
	/**
	 * Average monthly branch orders(1 for each month;
	 */
	private double[] avarageMonthlyOrders;

	public QuarterlyOrdersReport(int month, int year) {
		super(month, year, ReportType.QUARTERLY_ORDERS_REPORT, "ALL");
		totalOrders = 0;
		ordersPerDay = new int[3][31];
		ordersPerCategory = new HashMap<String, Integer>();
		mostPopularItems = null;
		avarageMonthlyOrders = new double[3];
		mostPopularItems = new ArrayList<String>();
	}

	public void addOrdersOnDay(int day, int month, int amount) {
		if (day > 31 || day < 1)
			return;
		ordersPerDay[(month - 1) % 3][day - 1] += amount;
	}

	public void addToCategory(String category, int amount) {
		Integer number = ordersPerCategory.get(category);
		if (number == null) {
			number = amount;
		} else {
			number += amount;
		}
		ordersPerCategory.put(category, number);
	}

	public void addPopularItem(String itemName) {
		if (!mostPopularItems.contains(itemName))
			mostPopularItems.add(itemName);
	}

	public ArrayList<String> getMostPopularItems() {
		return mostPopularItems;
	}

	public void addToTotalOrders(int amount) {
		if (amount > 0)
			totalOrders += amount;
	}

	public int[][] getOrdersPerDay() {
		return ordersPerDay;
	}

	public HashMap<String, Integer> getOrdersPerCategory() {
		return ordersPerCategory;
	}

	public int getTotalOrders() {
		return totalOrders;
	}

	public String getAvarageMonthlyOrders() {
		return avarageMonthlyOrders[0] + "," + avarageMonthlyOrders[1] + "," + avarageMonthlyOrders[2];
	}

	public void setAvarageMonthlyOrders(double amount, int month) {
		this.avarageMonthlyOrders[month % 3] = amount;
	}

}
