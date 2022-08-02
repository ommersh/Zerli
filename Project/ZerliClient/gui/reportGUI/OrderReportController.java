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
import report.OrdersReport;
import report.Report;

/**
 * controller for the window: the orders report
 *
 */
public class OrderReportController implements IGuiController, IReportController {
	private OrdersReport ordersReport = null;
	private int[] ordersPerDay;
	private HashMap<String, Integer> ordersPerCategory;
	private Series<String, Integer> daySeries;

	@FXML
	private Label averageOrdersMonthLabel;

	@FXML
	private Label branchLabel;

	@FXML
	private Label dateLabel;

	@FXML
	private CategoryAxis dayAxis;

	@FXML
	private Label orderNumLabel;

	@FXML
	private AnchorPane orderReportPane;

	@FXML
	private NumberAxis ordersNumAxis;

	@FXML
	private PieChart ordersPerCategoryChart;

	@FXML
	private LineChart<String, Integer> ordersPerDayChart;

	@FXML
	private Label popularItemLabel;

	@Override
	public Pane getBasePane() {
		return orderReportPane;
	}

	@Override
	public void resetController() {
		orderNumLabel.setText("NoData");
		dateLabel.setText("NoData");
		branchLabel.setText("NoData");
		orderNumLabel.setText("NoData");
		popularItemLabel.setText("NoData");
		averageOrdersMonthLabel.setText("NoData");
		daySeries.getData().clear();
		ordersPerDayChart.getData().clear();
		ordersPerCategory.clear();
		ordersPerDay = null;
		ordersReport = null;
	}

	@Override
	public void openWindow() {
		if (ordersReport != null) {
			orderNumLabel.setText(ordersReport.getTotalOrders() + "");
			dateLabel.setText(ordersReport.getMonth() + "/" + ordersReport.getYear());
			branchLabel.setText(ordersReport.getBranchName() + " branch");
			orderNumLabel.setText(ordersReport.getTotalOrders() + "");
			popularItemLabel.setText(ordersReport.getMostPopularItem());
			averageOrdersMonthLabel.setText(Double.toString(ordersReport.getAvarageMonthlyOrders()));
			setPerDayChart();
			setPerCategoryChart();
		}
	}

	public void setReport(Report ordersReport) {
		this.ordersReport = (OrdersReport) ordersReport;

	}

	private void setPerDayChart() {
		daySeries = new XYChart.Series<>();
		ordersPerDay = ordersReport.getOrdersPerDay();
		for (int i = 0; i < 31; i++) {
			daySeries.getData().add(new XYChart.Data<String, Integer>(i + "", ordersPerDay[i]));
		}
		ordersPerDayChart.getData().add(daySeries);
	}

	private void setPerCategoryChart() {
		ordersPerCategory = ordersReport.getOrdersPerCategory();
		for (Entry<String, Integer> entry : ordersPerCategory.entrySet()) {
			ordersPerCategoryChart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
		}
	}

}
