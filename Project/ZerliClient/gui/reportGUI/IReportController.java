package reportGUI;

import javafx.scene.layout.Pane;
import report.Report;

/**
 * the interface for the report controller
 *
 */
public interface IReportController {

	void setReport(Report report);

	void openWindow();

	Pane getBasePane();
}
