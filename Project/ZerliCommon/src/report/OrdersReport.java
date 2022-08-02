package report;

import java.util.HashMap;

/**
 * monthly branch orders report
 * 
 * @author halel
 *
 */
public class OrdersReport extends Report {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * orders per day, the 0 place is the 1st day and so on
	 */
	private int[] ordersPerDay;
	/**
	 * products ordered per category
	 */
	private HashMap<String, Integer> ordersPerCategory;
	/**
	 * the total orders for this branch
	 */
	private int totalOrders;
	/**
	 * the most popular item this month
	 */
	private String mostPopularItem;
	/**
	 * the average monthly orders(across all branches)
	 */
	private double avarageMonthlyOrders;

	public OrdersReport(int month, int year, ReportType type, String branchName) {
		super(month, year, type, branchName);
		this.ordersPerDay = new int[31];
		this.ordersPerCategory = new HashMap<String, Integer>();
		totalOrders = 0;
		mostPopularItem = null;
		avarageMonthlyOrders = 0;
	}

	/**
	 * add 1 order on a given day
	 * 
	 * @param day
	 */
	public void addOrderOnDay(int day) {
		if (day > 31 || day < 1)
			return;
		ordersPerDay[day - 1] += 1;
	}

	/**
	 * add amount to a category
	 * 
	 * @param category
	 * @param amount
	 */
	public void addToCategory(String category, int amount) {
		Integer number = ordersPerCategory.get(category);
		if (number == null) {
			number = amount;
		} else {
			number += amount;
		}
		ordersPerCategory.put(category, number);
	}

	public int getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}

	public String getMostPopularItem() {
		return mostPopularItem;
	}

	public void setMostPopularItem(String mostPopularItem) {
		this.mostPopularItem = mostPopularItem;
	}

	public double getAvarageMonthlyOrders() {
		return avarageMonthlyOrders;
	}

	public void setAvarageMonthlyOrders(double avarageMonthlyOrders) {
		this.avarageMonthlyOrders = avarageMonthlyOrders;
	}

	public int[] getOrdersPerDay() {
		return ordersPerDay;
	}

	public HashMap<String, Integer> getOrdersPerCategory() {
		return ordersPerCategory;
	}

}
