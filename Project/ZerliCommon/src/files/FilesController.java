package files;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * general class to manage the creation of object from files and files from
 * objects
 * 
 * @author halel
 *
 */
public class FilesController {

	/**
	 * Open and read pdf file into simpleFile object return the new object
	 * 
	 * @param path
	 * @param newfileName
	 * @return
	 */
	public SimpleFile savePdfFileToObject(String path, String newfileName) {
		SimpleFile newSimpleFile = new SimpleFile();
		try {
			File newFile = new File(path);
			int size = (int) newFile.length();
			FileInputStream fis = new FileInputStream(newFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			newSimpleFile.initArray(size);
			bis.read(newSimpleFile.getData(), 0, size);
			bis.close();
			fis.close();
		} catch (Exception e) {
			//
		}
		return newSimpleFile;
	}

	/**
	 * Get simpleFile object, save as pdf file in the path
	 * 
	 * @param path
	 * @param simpleFile
	 * @return
	 */
	public boolean savePdfFileFromObjectToPath(String path, SimpleFile simpleFile) {
		int fileSize = simpleFile.getData().length;
		String newFilePath = path;// + "/" + simpleFile.getFullFileName();

		byte[] mybytearray = simpleFile.getData();
		File newFile = new File(newFilePath);
		try {
			newFile.createNewFile();
		} catch (IOException e1) {
			//
		}
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(newFile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(mybytearray, 0, fileSize);
			bos.close();
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	// to do, get image to file and image from file
}
