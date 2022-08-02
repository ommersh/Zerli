package user;

/**
 * the account status
 * 
 * @author halel
 *
 */
public enum UserStatus {
	/**
	 * can do anything!
	 */
	Active,
	/**
	 * only for customers, can place new orders
	 */
	Frozen,
	/**
	 * not active in our system, cant do anything
	 */
	NotActive
}
