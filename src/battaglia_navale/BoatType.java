package battaglia_navale;

public enum BoatType {
	CORVETTE(2),
	FRIGATE(3),
	VESSEL(4);
	
	private int size;
	private BoatType(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public static BoatType getBoatType(int size)  throws InvalidBoatException {
		if (size == CORVETTE.size) {
			return CORVETTE;
		} else if (size == FRIGATE.size){
			return FRIGATE;
		} else if (size == VESSEL.size) {
			return VESSEL;
		} else {
			String validBoats = "";
			for (BoatType b: BoatType.values()) {
				validBoats.concat(b.getSize() + " ");
			}
			throw new InvalidBoatException("Size of boats allows are: " + validBoats);
		}
	}
}
