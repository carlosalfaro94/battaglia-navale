package battaglia_navale;

import java.util.List;


public class Boat {
	private List<Cell> positions;
	private BoatType boatType;
	
	public Boat(List<Cell> positions) throws InvalidBoatException {
		
		this.boatType = BoatType.getBoatType(positions.size());
		for (int i = 0; i < positions.size() - 1; i++) {
			if (!positions.get(i).isContiguous(positions.get(i+1))) {
				System.out.println(positions.get(i));
				System.out.println(positions.get(i+1));
				throw new InvalidBoatException("Positions of boat are not contiguous"); //Create Exception
			}
		}
		
		this.positions = positions;
		
	}

	public List<Cell> getPositions() {
		return positions;
	}

	public BoatType getBoatType() {
		return boatType;
	}
	
	public boolean hasBeenHit() {
		for (Cell c: this.positions) {
			if (c.hasBeenHit()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean collides(Boat b) {
		for (Cell c : b.getPositions()) {
			if (this.getPositions().contains(c)) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String boatString = "";
		for (Cell c: this.getPositions()) {
			boatString += c.toString() + " ";
		}
		return boatString;
	}
}

