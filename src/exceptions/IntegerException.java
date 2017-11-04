package exceptions;

public class IntegerException extends Exception {

	private static final long serialVersionUID = 1L;

	public IntegerException() {
		super();
	}

	public IntegerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IntegerException(String message, Throwable cause) {
		super(message, cause);
	}

	public IntegerException(String message) {
		super(message);
	}

	public IntegerException(Throwable cause) {
		super(cause);
	}

}
