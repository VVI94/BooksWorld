package exceptions;

public class AlreadyExistException extends Exception {

	private static final long serialVersionUID = 2975742212608168797L;

	public AlreadyExistException() {
		super();
	}

	public AlreadyExistException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public AlreadyExistException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AlreadyExistException(String arg0) {
		super(arg0);
	}

	public AlreadyExistException(Throwable arg0) {
		super(arg0);
	}
	
	

}
