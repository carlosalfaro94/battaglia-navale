package battaglia_navale;

public class InvalidBoatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cause;
	
	public InvalidBoatException(String cause) {
		this.cause = cause;
	}
	
	public String toString() {
		return cause;
	}
}
