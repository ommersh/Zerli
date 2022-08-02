package reportGUI;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import report.QuarterlyRevenueReport;
import report.Report;

/**
 * controller for the window: the quarterly revenue report
 *
 */
public class QuarterlyRevenueReportController implements IGuiController, IReportController {
	private QuarterlyRevenueReport revenueReport = null;
	private Series<String, Double> daySeries;
	private double[][] revenuePerDay;

	@FXML
	private Label AveragePerOrderLabel;

	@FXML
	private Label averageGlobalRevLabel;

	@FXML
	private Label dateLabel;

	@FXML
	private LineChart<String, Double> incomeDayChart;

	@FXML
	private Label mostProfitItemLabel;

	@FXML
	private Label totalRevLabel;

	@FXML
	private AnchorPane quarterlyRevenueReportPane;

	@Override
	public Pane getBasePane() {
		return quarterlyRevenueReportPane;
	}

	@Override
	public void resetController() {
		revenueReport = null;
		daySeries.getData().clear();
		revenuePerDay = null;
		dateLabel.setText("noData");
		mostProfitItemLabel.setText("noData");
		averageGlobalRevLabel.setText("noData");
		AveragePerOrderLabel.setText("noData");
	}

	@Override
	public void openWindow() {
		AveragePerOrderLabel.setText(revenueReport.getAverageRevenuePerOrder() + "");
		averageGlobalRevLabel.setText(revenueReport.getAverageRevenuePerOrder() + "");
		dateLabel.setText(revenueReport.getMonth() + "/" + revenueReport.getYear());
		mostProfitItemLabel.setText(revenueReport.getMostProfitableItem().toString());
		totalRevLabel.setText(revenueReport.getTotalRevenue() + "");
		setPerDayChart();

	}

	private void setPerDayChart() {
		daySeries = new XYChart.Series<>();
		revenuePerDay = revenueReport.getRevenuePerDay();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 31; j++) {
				daySeries.getData().add(new XYChart.Data<String, Double>(i * 31 + j + "", revenuePerDay[i][j]));
			}
		}
		incomeDayChart.getData().add(daySeries);
	}

	@Override
	public void setReport(Report report) {
		this.revenueReport = (QuarterlyRevenueReport) report;

	}

}
