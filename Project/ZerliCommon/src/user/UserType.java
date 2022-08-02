package user;

/**
 * the users types
 * 
 * @author halel
 *
 */
public enum UserType {
	/**
	 * Customer before the manager approval, cant place orders yet
	 */
	NonAuthorizedCustomer,
	/**
	 * Customer
	 */
	AuthorizedCustomer,
	/**
	 * branch manager, manage users and orders,can watch reports
	 */
	BranchManager,
	/**
	 * simple branch employee, can enter survey answers
	 */
	BranchEmployee,
	/**
	 * customers service employee , manage surveys and complaints
	 */
	CustomerServiceEmloyee,
	/**
	 * marketing employee, manage promotions and the catalog
	 */
	MarketingEmployee,
	/**
	 * courier, can approve delivery
	 */
	Courier,
	/**
	 * ceo, can watch reports
	 */
	CEO
}
