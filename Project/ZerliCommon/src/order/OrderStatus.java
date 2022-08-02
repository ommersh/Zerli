package order;

/**
 * the order status
 * 
 * @author halel
 *
 */
public enum OrderStatus {
	/**
	 * Approved and collected
	 */
	COMPLETED,
	/**
	 * waiting for approval from branch manager
	 */
	WAITING_FOR_APPROAVL,
	/**
	 * collected/delivered
	 */
	COLLECTED,
	/**
	 * the cancellation was approved
	 */
	CANCELED,
	/**
	 * waiting for cancellation approval from branch manager
	 */
	WAITING_FOR_CANCELLATION_APPROVAL,
	/**
	 * after order has been placed, before the payment
	 */
	WAITING_FOR_PAYMENT,
	/**
	 * the order is approved -> waiting to be collected or delivered
	 */
	APPROVED,
	/**
	 * TBD
	 */
	NOT_APPROVED,
}
