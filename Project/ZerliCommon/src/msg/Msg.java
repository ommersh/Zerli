package msg;

import java.io.Serializable;

/**
 * the message between the client and server, have 2 fields: type -> enum for
 * the message type, we handle the expected data based on the type, and data ->
 * a Serializable object we can get back based on the message type
 * 
 * @author halel
 *
 */
public class Msg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	public MsgType type;
	public Serializable data;

	public String toString() {
		return type.toString();
	}
}
