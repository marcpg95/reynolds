package estructura.exceptions;

public class FicheroNoExisteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FicheroNoExisteException(String msg) {
		super(msg);
	}
}
