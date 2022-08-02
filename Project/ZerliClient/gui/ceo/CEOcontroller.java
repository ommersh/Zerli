package ceo;

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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;
import report.Report;
import report.ReportType;
import reportGUI.IReportController;
import reportGUI.OrderReportController;
import reportGUI.QuarterlyOrdersReportController;
import reportGUI.QuarterlyRevenueReportController;
import reportGUI.RevenueReportController;
import reportGUI.SatisfactionReportController;
import userGuiManagment.CEOGuiManager;
import userGuiManagment.MainWindowGuiManager;
import usersManagment.CEOBoundary;

/**
 * controller for the window: the ceo watch reports window
 *
 */
public class CEOcontroller implements IGuiController {
	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private CEOBoundary ceoBoundry = CEOGuiManager.getInstance().getCeoBoundry();
	private IReportController leftReportController;
	private IReportController rightReportController;
	private IReportController middleReportController;
	private Report report = null;
	@FXML
	private AnchorPane MiddleReport;

	@FXML
	private ScrollPane middleScroll;

	@FXML
	private Button getReportBot;

	@FXML
	private Button openReportBot;

	@FXML
	private Button previewReportLeftBot;

	@FXML
	private Button previewReportRightBot;

	@FXML
	private ComboBox<Integer> ceoReportMonth;

	@FXML
	private AnchorPane ceoReportScreen;

	@FXML
	private ComboBox<ReportType> ceoReportType;

	@FXML
	private ComboBox<Integer> ceoReportYear;

	@FXML
	private AnchorPane ceoWatchReportPane;

	@FXML
	private AnchorPane leftReport;

	@FXML
	private Label reportMessage;

	@FXML
	private AnchorPane rightReport;

	@FXML
	private ComboBox<String> ceoReportBranch;

	private ArrayList<Integer> monthsList;
	private ArrayList<Integer> periodsList;
	private ArrayList<String> branches;

	@FXML
	void openReport(ActionEvent event) {
		previewReport(MiddleReport, middleReportController);
		middleScroll.setVisible(true);

	}

	@FXML
	void previewReportLeft(ActionEvent event) {
		previewReport(leftReport, leftReportController);
		middleScroll.setVisible(false);

	}

	@FXML
	void previewReportRight(ActionEvent event) {
		previewReport(rightReport, rightReportController);
		middleScroll.setVisible(false);
	}

	private void previewReport(AnchorPane anchor, IReportController reportController) {
		openReportBot.setDisable(true);
		previewReportLeftBot.setDisable(true);
		previewReportRightBot.setDisable(true);
		getReportBot.setDisable(false);
		reportController = getController();
		reportController.setReport(report);
		reportController.openWindow();
		anchor.getChildren().clear();
		anchor.getChildren().add(reportController.getBasePane());
	}

	@FXML
	void getReport(ActionEvent event) {
		try {
			report = ceoBoundry.requestViewReport(ceoReportType.getSelectionModel().getSelectedItem(),
					ceoReportMonth.getSelectionModel().getSelectedItem(),
					ceoReportYear.getSelectionModel().getSelectedItem(),
					ceoReportBranch.getSelectionModel().getSelectedItem());
		} catch (Exception e) {
			return;
		}
		if (report != null) {
			openReportBot.setDisable(false);
			previewReportLeftBot.setDisable(false);
			previewReportRightBot.setDisable(false);
			getReportBot.setDisable(true);
			reportMessage.setVisible(false);
		} else {
			reportMessage.setVisible(true);
		}
	}

	@Override
	public Pane getBasePane() {
		return ceoWatchReportPane;
	}

	@Override
	public void resetController() {
		report = null;
		middleScroll.setVisible(false);
		ceoReportMonth.setDisable(true);
		ceoReportMonth.setPromptText("month");
		ceoReportType.getSelectionModel().clearSelection();
		ceoReportType.setPromptText("report type");
		ceoReportMonth.getSelectionModel().clearSelection();
		ceoReportMonth.setPromptText("month");
		ceoReportYear.getSelectionModel().clearSelection();
		ceoReportYear.setPromptText("year");
		ceoReportBranch.getSelectionModel().clearSelection();
		ceoReportBranch.setPromptText("branch");
	}

	@Override
	public void openWindow() {
		mainWindowManager.mainWindowController.changeWindowName("CEO - view report");
		mainWindowManager.mainWindowController.showNewWindow(ceoWatchReportPane);
		ArrayList<Integer> yearsList = (ArrayList<Integer>) IntStream.range(2000, LocalDate.now().getYear() + 1).boxed()
				.collect(Collectors.toList());
		Collections.reverse(yearsList);
		monthsList = (ArrayList<Integer>) IntStream.range(1, 13).boxed().collect(Collectors.toList());
		periodsList = (ArrayList<Integer>) IntStream.range(1, 5).boxed().collect(Collectors.toList());
		branches = ceoBoundry.getBranches();
		ceoReportBranch.getItems().setAll(branches);
		ceoReportMonth.getItems().setAll(monthsList);
		ceoReportYear.getItems().setAll(yearsList);
		ceoReportType.getItems().setAll(ReportType.values());
	}

	@FXML
	void changeMonthChoice(ActionEvent event) {
		ceoReportMonth.setDisable(false);
		switch (ceoReportType.getSelectionModel().getSelectedItem().ordinal()) {
		case 0:
		case 1:
			if (ceoReportMonth.getItems().contains(5))
				break;
			ceoReportMonth.getItems().setAll(monthsList);
			ceoReportMonth.setPromptText("month");
			break;
		case 2:
		case 3:
		case 4:
			if (ceoReportMonth.getItems().contains(12))
				ceoReportMonth.getItems().setAll(periodsList);
			ceoReportMonth.setPromptText("period");
			break;
		}
	}

	private IReportController getController() {
		switch (report.getType().ordinal()) {
		case 0:
			return (OrderReportController) guiObjectsFactory.loadFxmlFile("/reportGUI/ordersReport.fxml");
		case 1:
			return (RevenueReportController) guiObjectsFactory.loadFxmlFile("/reportGUI/revenueReport.fxml");
		case 2:
			return (QuarterlyOrdersReportController) guiObjectsFactory
					.loadFxmlFile("/reportGUI/quarterlyOrdersReport.fxml");
		case 3:
			return (QuarterlyRevenueReportController) guiObjectsFactory
					.loadFxmlFile("/reportGUI/quarterlyRevenueReport.fxml");
		case 4:
			return (SatisfactionReportController) guiObjectsFactory.loadFxmlFile("/reportGUI/satisfactionReport.fxml");
		default:
			return null;
		}
	}

}
