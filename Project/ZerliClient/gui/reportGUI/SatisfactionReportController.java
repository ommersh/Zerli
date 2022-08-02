package reportGUI;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;
import report.QuarterlySatisfactionReport;
import report.Report;

/**
 * controller for the window: the quarterly satisfaction report
 *
 */
public class SatisfactionReportController implements IGuiController, IReportController {
	QuarterlySatisfactionReport satisfactionReport = null;
	Series<String, Integer> monthSeries = null;
	int[] complaintsPerMonth = null;

	@FXML
	private Label branchLabel;

	@FXML
	private BarChart<String, Integer> complaintsChart;

	@FXML
	private Label complaintsNum;

	@FXML
	private PieChart complaintsPie;

	@FXML
	private Label dateLabel;

	@FXML
	private AnchorPane satisfactionReportPane;

	@Override
	public Pane getBasePane() {
		return satisfactionReportPane;
	}

	@Override
	public void resetController() {
		dateLabel.setText("NoData");
		complaintsNum.setText("NoData");
		monthSeries.getData().clear();
		complaintsPerMonth = null;
	}

	@Override
	public void openWindow() {
		dateLabel.setText(satisfactionReport.getStartMonth() + "/" + satisfactionReport.getStartYear()
				+ satisfactionReport.getEndMonth() + "/" + satisfactionReport.getEndYear());
		complaintsNum.setText("" + satisfactionReport.getNumberOfComplaints());
		setPerMonthChart();
	}

	@Override
	public void setReport(Report report) {
		this.satisfactionReport = (QuarterlySatisfactionReport) report;
	}

	private void setPerMonthChart() {
		monthSeries = new XYChart.Series<>();
		complaintsPerMonth = satisfactionReport.getComplaintsPerMonth();
		for (int i = 0; i < 3; i++) {
			monthSeries.getData().add(new XYChart.Data<String, Integer>(i + "", complaintsPerMonth[i]));
		}
		complaintsChart.getData().add(monthSeries);
	}

}
