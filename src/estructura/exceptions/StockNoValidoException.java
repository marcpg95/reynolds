package estructura.exceptions;

public class StockNoValidoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockNoValidoException(String msg) {
		super(msg);
	}
}
