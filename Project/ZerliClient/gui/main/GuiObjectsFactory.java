package main;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;

/**
 * class for creating the gui object from the fxml files, singleton
 *
 */
public class GuiObjectsFactory {
	private static GuiObjectsFactory guiObjectsFactoryInstance;

	private GuiObjectsFactory() {
		//
	}

	public static GuiObjectsFactory getInstance() {
		if (guiObjectsFactoryInstance == null) {
			guiObjectsFactoryInstance = new GuiObjectsFactory();
		}
		return guiObjectsFactoryInstance;
	}

	/**
	 * Load fxml file, return its controller
	 * 
	 * @param filePath should be "/package/filename.fxml"
	 * @return the controller
	 * @throws IOException if failed to load the fxml file
	 */
	public IGuiController loadFxmlFile(String filePath) {
		try {
			FXMLLoader loader = new FXMLLoader();
			final URL resource = getClass().getResource(filePath);
			loader.setLocation(resource);
			loader.load();
			return (IGuiController) loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
