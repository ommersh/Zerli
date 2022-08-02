package files;

import java.io.Serializable;

/**
 * simple entity to save file data as serializable object -> the file's data is
 * saved as byte array
 * 
 * @author halel
 *
 */
public class SimpleFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The files saved as bytes
	 */
	private byte[] data;
	private String fileName;
	private String fileType;

	public void initArray(int size) {
		data = new byte[size];
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFullFileName() {
		return fileName + "." + fileType;
	}
}
