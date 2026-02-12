package tp1.p2.control.exceptions;

public class InvalidPositionException extends GameException{
	
	private static final long serialVersionUID = 1L;
	
	public InvalidPositionException() {
		super();
	}
	
	public InvalidPositionException(String message, String parameters, String parameters2) {
		super(String.format(message, parameters, parameters2));
	}

	public InvalidPositionException(String message) {
		super(message);
	}

	public InvalidPositionException(Throwable cause) {
		super(cause);
	}

	public InvalidPositionException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
