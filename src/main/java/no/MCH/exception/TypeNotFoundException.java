package no.MCH.exception;

public class TypeNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TypeNotFoundException(String message) {
		super(message);
	}

	public TypeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TypeNotFoundException(Throwable cause) {
		super(cause);
	}
}
