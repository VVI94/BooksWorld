package exceptions;

public class UnexistingException extends Exception {

	private static final long serialVersionUID = 1716158317818837451L;

	public UnexistingException() {
		super();
	}

	public UnexistingException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public UnexistingException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public UnexistingException(String arg0) {
		super(arg0);
	}

	public UnexistingException(Throwable arg0) {
		super(arg0);
	}

	
}
