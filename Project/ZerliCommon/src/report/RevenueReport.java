package report;

/**
 * the branch monthly revenue report
 * 
 * @author halel
 *
 */
public class RevenueReport extends Report {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * revenue per day
	 */
	private double[] revenuePerDay;
	/**
	 * total revenue for this month
	 */
	private double totalRevenue;
	/**
	 * average monthly revenue(across all branches)
	 */
	private double averageMonthlyRevenue;
	/**
	 * average revenue per order
	 */
	private double averageRevenuePerOrder;
	/**
	 * the most profitable item, we got the most money for this item!
	 */
	private String mostProfitableItem;

	public RevenueReport(int month, int year, ReportType type, String branchName) {
		super(month, year, type, branchName);
		this.revenuePerDay = new double[31];
		for (int i = 0; i < 31; i++)
			revenuePerDay[i] = 0;
		totalRevenue = 0;
		averageMonthlyRevenue = 0;
		averageRevenuePerOrder = 0;
		mostProfitableItem = null;
	}

	public void addOrderRevenuOnDay(int day, double amount) {
		if (day > 31 || day < 1 || amount <= 0)
			return;
		revenuePerDay[day - 1] += amount;
	}

	public double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public double getAverageMonthlyRevenue() {
		return averageMonthlyRevenue;
	}

	public void setAverageMonthlyRevenue(double avarageMonthlyRevenue) {
		this.averageMonthlyRevenue = avarageMonthlyRevenue;
	}

	public double getAverageRevenuePerOrder() {
		return averageRevenuePerOrder;
	}

	public void setAverageRevenuePerOrder(double avarageRevenuePerOrder) {
		this.averageRevenuePerOrder = avarageRevenuePerOrder;
	}

	public String getMostProfitableItem() {
		return mostProfitableItem;
	}

	public void setMostProfitableItem(String mostProfitableItem) {
		this.mostProfitableItem = mostProfitableItem;
	}

	public double[] getRevenuePerDay() {
		return revenuePerDay;
	}

}
