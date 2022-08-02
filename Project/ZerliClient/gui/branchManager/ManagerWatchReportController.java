package branchManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;
import report.Report;
import report.ReportType;
import reportGUI.IReportController;
import reportGUI.OrderReportController;
import reportGUI.RevenueReportController;
import userGuiManagment.MainWindowGuiManager;
import usersManagment.BranchManagerBoundary;

/**
 * controller for the window: watch reports window
 *
 */
public class ManagerWatchReportController implements IGuiController {
	IReportController reportController = null;
	BranchManagerBoundary managerBoundry = new BranchManagerBoundary();
	private Report report = null;
	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	OrderReportController orderReportController;
	RevenueReportController revenueReportController;

	@FXML
	private AnchorPane reportPane;

	@FXML
	private Button managerGetReport;

	@FXML
	private Button managerOpenReport;

	@FXML
	private ComboBox<Integer> managerReportMonth;

	@FXML
	private ComboBox<ReportType> managerReportType;

	@FXML
	private ComboBox<Integer> managerReportYear;

	@FXML
	private AnchorPane managerWatchReportPane;

	@FXML
	private Label reportMessage;

	// private ObservableList<Integer> yearObs;
	// private ObservableList<Integer> monthObs;
	// private ObservableList<ReportType> reportObs;

	@Override
	public Pane getBasePane() {
		return managerWatchReportPane;
	}

	@Override
	public void resetController() {
		managerOpenReport.setDisable(true);
		managerGetReport.setDisable(false);
		managerReportMonth.getSelectionModel().clearAndSelect(0);
		managerReportYear.getSelectionModel().clearSelection();
		managerReportType.getSelectionModel().clearSelection();
		managerReportType.setPromptText("report type");
		managerReportMonth.setPromptText("month");
		managerReportYear.setPromptText("year");
	}

	@Override
	public void openWindow() {
		MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
		mainWindowManager.mainWindowController.showNewWindow(managerWatchReportPane);
		mainWindowManager.mainWindowController.changeWindowName("Manager - watch report");
		ArrayList<Integer> yearsList = (ArrayList<Integer>) IntStream.range(2000, LocalDate.now().getYear() + 1).boxed()
				.collect(Collectors.toList());
		Collections.reverse(yearsList);
		ArrayList<Integer> monthsList = (ArrayList<Integer>) IntStream.range(1, 13).boxed()
				.collect(Collectors.toList());
		managerReportMonth.getItems().setAll(monthsList);
		managerReportYear.getItems().setAll(yearsList);
		managerReportType.getItems().setAll(ReportType.MONTHLY_ORDERS_REPORT, ReportType.MONTHLY_REVENU_EREPORT);
		try {
			loadFXMLs();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void getReport(ActionEvent event) {
		try {
			report = managerBoundry.requestViewReport(managerReportType.getSelectionModel().getSelectedItem(),
					managerReportMonth.getSelectionModel().getSelectedItem(),
					managerReportYear.getSelectionModel().getSelectedItem());
		} catch (Exception e) {
			// return
			return;
		}
		if (report != null) {
			managerOpenReport.setDisable(false);
			managerGetReport.setDisable(true);
			reportMessage.setVisible(false);
		} else {
			reportMessage.setVisible(true);
		}
	}

	@FXML
	void openReport(ActionEvent event) throws IOException {
		managerOpenReport.setDisable(true);
		managerGetReport.setDisable(false);

		reportController = getController();
		reportController.setReport(report);
		reportController.openWindow();
		reportPane.getChildren().setAll(reportController.getBasePane());
		reportMessage.setVisible(false);

	}

	private void loadFXMLs() throws IOException {
		orderReportController = (OrderReportController) guiObjectsFactory.loadFxmlFile("/reportGUI/ordersReport.fxml");
		revenueReportController = (RevenueReportController) guiObjectsFactory
				.loadFxmlFile("/reportGUI/revenueReport.fxml");
	}

	private IReportController getController() throws IOException {
		loadFXMLs();
		switch (report.getType()) {
		case MONTHLY_ORDERS_REPORT:
			return orderReportController;
		case MONTHLY_REVENU_EREPORT:
			return revenueReportController;
		default:
			return null;
		}

	}

}
