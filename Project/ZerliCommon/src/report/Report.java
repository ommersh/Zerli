package report;

import java.io.Serializable;

/**
 * the general report object -> have month year type and branch
 * 
 * @author halel
 *
 */
public class Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	/**
	 * the report's month(1-12) for monthly reports, the quarter(1-4) for quarterly
	 */
	private int month;
	/**
	 * the report year
	 */
	private int year;
	/**
	 * the report type
	 */
	private ReportType type;
	/**
	 * the report branch, can be "ALL" for general reports
	 */
	private String BranchName;

	/**
	 * 
	 * @param month
	 * @param year
	 * @param type
	 * @param branchName
	 */
	public Report(int month, int year, ReportType type, String branchName) {
		super();
		this.month = month;
		this.year = year;
		this.type = type;
		BranchName = branchName;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public ReportType getType() {
		return type;
	}

	public String getBranchName() {
		return BranchName;
	}

	public String getStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

}
