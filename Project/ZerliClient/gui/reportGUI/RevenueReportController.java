package reportGUI;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import report.Report;
import report.RevenueReport;

/**
 * controller for the window: the revenue report
 *
 */
public class RevenueReportController implements IGuiController, IReportController {
	private RevenueReport revenueReport = null;
	private Series<String, Double> daySeries;
	private double[] revenuePerDay;

	@FXML
	private Label AveragePerOrderLabel;

	@FXML
	private Label averageGlobalRevLabel;

	@FXML
	private Label branchLabel;

	@FXML
	private Label dateLabel;

	@FXML
	private LineChart<String, Double> incomeDayChart;

	@FXML
	private Label mostProfitItemLabel;

	@FXML
	private AnchorPane revenueReportPane;

	@FXML
	private Label totalRevLabel;

	@Override
	public Pane getBasePane() {
		return revenueReportPane;
	}

	@Override
	public void resetController() {
		daySeries.getData().clear();
		incomeDayChart.getData().clear();
		revenueReport = null;
		revenuePerDay = null;
		dateLabel.setText("noData");
		mostProfitItemLabel.setText("noData");
		branchLabel.setText("noData");
		averageGlobalRevLabel.setText("noData");
		AveragePerOrderLabel.setText("noData");
	}

	@Override
	public void openWindow() {
		AveragePerOrderLabel.setText(revenueReport.getAverageRevenuePerOrder() + "");
		averageGlobalRevLabel.setText(revenueReport.getAverageMonthlyRevenue() + "");
		dateLabel.setText(revenueReport.getMonth() + "/" + revenueReport.getYear());
		branchLabel.setText(revenueReport.getBranchName() + "branch");
		mostProfitItemLabel.setText(revenueReport.getMostProfitableItem());
		totalRevLabel.setText(revenueReport.getTotalRevenue() + "");
		setPerDayChart();
	}

	private void setPerDayChart() {
		daySeries = new XYChart.Series<>();
		revenuePerDay = revenueReport.getRevenuePerDay();
		for (int i = 0; i < 31; i++) {
			daySeries.getData().add(new XYChart.Data<String, Double>(i + "", revenuePerDay[i]));
		}
		incomeDayChart.getData().add(daySeries);
	}

	@Override
	public void setReport(Report report) {
		this.revenueReport = (RevenueReport) report;
	}

}
