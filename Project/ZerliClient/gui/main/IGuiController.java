package main;

import javafx.scene.layout.Pane;

/**
 * The controller interface, all the gui controller should implement
 * IGuiController
 *
 */
public interface IGuiController {

	public Pane getBasePane();
	
	public void resetController();
	
	public void openWindow();
}
