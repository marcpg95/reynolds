package estructura.exceptions;

public class ProductoYaExisteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductoYaExisteException(String msg) {
		super(msg);
	}
}
