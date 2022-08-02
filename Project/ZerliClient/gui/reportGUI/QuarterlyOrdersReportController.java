package reportGUI;

import java.util.HashMap;
import java.util.Map.Entry;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import report.QuarterlyOrdersReport;
import report.Report;

/**
 * controller for the window: the quarterly orders report
 *
 */
public class QuarterlyOrdersReportController implements IGuiController, IReportController {
	QuarterlyOrdersReport ordersReport = null;
	private int[][] ordersPerDay;
	private HashMap<String, Integer> ordersPerCategory;
	private Series<String, Integer> daySeries;

	@FXML
	private Label averageOrdersMonthLabel;

	@FXML
	private Label dateLabel;

	@FXML
	private CategoryAxis dayAxis;

	@FXML
	private Label orderNumLabel;

	@FXML
	private NumberAxis ordersNumAxis;

	@FXML
	private PieChart ordersPerCategoryChart;

	@FXML
	private LineChart<String, Integer> ordersPerDayChart;

	@FXML
	private Label popularItemLabel;

	@FXML
	private AnchorPane quarterlyOrderReportPane;

	@Override
	public Pane getBasePane() {
		return quarterlyOrderReportPane;
	}

	@Override
	public void resetController() {
		orderNumLabel.setText("NoData");
		dateLabel.setText("NoData");
		orderNumLabel.setText("NoData");
		popularItemLabel.setText("NoData");
		averageOrdersMonthLabel.setText("NoData");
		daySeries.getData().clear();
		ordersPerCategory.clear();
		ordersPerDayChart.getData().clear();
		ordersPerDay = null;
		ordersReport = null;
	}

	@Override
	public void openWindow() {
		if (ordersReport != null) {
			orderNumLabel.setText(ordersReport.getTotalOrders() + "");
			dateLabel.setText(ordersReport.getMonth() + "/" + ordersReport.getYear());
			orderNumLabel.setText(ordersReport.getTotalOrders() + "");
			popularItemLabel.setText(ordersReport.getMostPopularItems().toString());
			averageOrdersMonthLabel.setText(ordersReport.getAvarageMonthlyOrders() + "");
			setPerDayChart();
			setPerCategoryChart();
		}
	}

	private void setPerCategoryChart() {
		ordersPerCategory = ordersReport.getOrdersPerCategory();
		for (Entry<String, Integer> entry : ordersPerCategory.entrySet()) {
			ordersPerCategoryChart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
		}

	}

	private void setPerDayChart() {
		daySeries = new XYChart.Series<>();
		ordersPerDay = ordersReport.getOrdersPerDay();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 31; j++) {
				daySeries.getData().add(new XYChart.Data<String, Integer>(i * 31 + j + "", ordersPerDay[i][j]));
			}
		}
		ordersPerDayChart.getData().add(daySeries);

	}

	@Override
	public void setReport(Report report) {
		this.ordersReport = (QuarterlyOrdersReport) report;
	}

}
