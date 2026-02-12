package tp1.p2.control.exceptions;

public class LevelCastException extends GameException{

	private static final long serialVersionUID = 1L;
	
	public LevelCastException() {
		super();
	}
	

	public LevelCastException(String message) {
		super(message);
	}

	public LevelCastException(Throwable cause) {
		super(cause);
	}

	public LevelCastException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
