package battaglia_navale;
public class Cell {
	private int coordinateX;
	private int coordinateY;
	private boolean hasBeenHit;
	
	public Cell(int coordinateX, int coordinateY) {
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.hasBeenHit = false;
	}

	public int getCoordinateX() {
		return coordinateX;
	}

	public int getCoordinateY() {
		return coordinateY;
	}

	public boolean hasBeenHit() {
		return hasBeenHit;
	}
	
	public void hit() {
		this.hasBeenHit = true;
	}

	public boolean equals(Object c) {
		return this.coordinateX == ((Cell) c).coordinateX &&
				this.coordinateY == ((Cell) c).coordinateY;
	}
	
	public boolean isContiguous(Cell cell) {
		return (this.coordinateX == cell.getCoordinateX() && 
				Math.abs(this.coordinateY - cell.getCoordinateY()) == 1) ||
				(this.coordinateY == cell.getCoordinateY() &&
				Math.abs(this.coordinateX - cell.getCoordinateX()) == 1); 
	}
	
	public String toString() {
		return "(" + this.coordinateX + ", " + this.coordinateY + ")";
	}
	
	
}
