package report;

import java.util.ArrayList;

/**
 * quarterly all branches revenue report
 * 
 * @author halel
 *
 */
public class QuarterlyRevenueReport extends Report {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * revenue for each day of the quarter(3 month in 3 rows,31 days in each row)
	 */
	private double[][] revenuePerDay;
	/**
	 * Total sum of the quarter revenue
	 */
	private double totalRevenue;
	/**
	 * The average branch monthly revenue
	 */
	private double[] avarageMonthlyRevenue;
	/**
	 * List of the most profitable items
	 */
	private ArrayList<String> mostProfitableItem;
	/**
	 * the average revenue per order for the quarter
	 */
	private double averageRevenuePerOrder;

	public QuarterlyRevenueReport(int month, int year) {
		super(month, year, ReportType.QUARTERLY_REVENUE_REPORT, "ALL");
		revenuePerDay = new double[3][31];
		totalRevenue = 0;
		avarageMonthlyRevenue = new double[3];
		mostProfitableItem = new ArrayList<String>();
	}

	public void addProfitableItem(String name) {
		if (!mostProfitableItem.contains(name)) {
			mostProfitableItem.add(name);
		}
	}

	public void addRevenuOnDay(int month, int day, double amount) {
		if (day > 31 || day < 1 || amount <= 0)
			return;
		revenuePerDay[month % 3][day - 1] += amount;
	}

	public void addToTotalRevenue(double amount) {
		if (amount > 0)
			totalRevenue += amount;
	}

	public void setMonthlyAvarageRevenu(int month, double amount) {
		avarageMonthlyRevenue[month % 3] = amount;
	}

	public double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public double[][] getRevenuePerDay() {
		return revenuePerDay;
	}

	public double[] getAvarageMonthlyRevenue() {
		return avarageMonthlyRevenue;
	}

	public ArrayList<String> getMostProfitableItem() {
		return mostProfitableItem;
	}

	public double getAverageRevenuePerOrder() {
		return averageRevenuePerOrder;
	}

	public void setAverageRevenuePerOrder(double averageRevenuePerOrder) {
		this.averageRevenuePerOrder = averageRevenuePerOrder;
	}

}
