package battaglia_navale;

public class CellOutOfBoundsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cause;
	
	public CellOutOfBoundsException(String cause) {
		this.cause = cause;
	}
	
	public String toString() {
		return cause;
	}
}
