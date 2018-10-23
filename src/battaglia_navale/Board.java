package battaglia_navale;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
	private static final int width = 8;
	private static final int length = 8;
	private List<Cell> cells;
	private List<Boat> boats; 
	
	public Board () {
		this.cells = new ArrayList<Cell>();
		this.boats = new ArrayList<Boat>();
		for (int i = 0;i < Board.width; i++) {
			for  (int j = 0; j < Board.length; j++ ) {
				this.cells.add(new Cell(i,j));
			}
		}
	}
	public void setNewBoat(List<Cell> positions) 
			throws CellOutOfBoundsException, InvalidBoatException {
		for (Cell c: positions) {
			if (!isValidPositionInBoard(c)) {
				throw new CellOutOfBoundsException("New boat is placed out of board");
			}
		}
		Boat newBoat = new Boat(positions);
		for (Boat b: getBoats()) {
			if (newBoat.collides(b)) {
				throw new InvalidBoatException("New boat collides with existing boat");
			}
		}
		this.boats.add(newBoat);
	}
	
	public List<Cell> getCells() {
		return this.cells;
	}
	
	public List<Boat> getBoats() {
		return this.boats;
	}
	
	public int getAliveBoats() {
		return this.boats.size();
	}
	
	public static int getBoardWidth() {
		return width;
	}
	public static int getBoardLength() {
		return length;
	}
	
	public Cell getCell(int coordinateX, int coordinateY) 
			throws CellOutOfBoundsException {
		if (!isValidPositionInBoard(new Cell(coordinateX, coordinateY))) {
			throw new CellOutOfBoundsException("Cell searched is out of board bounds");
		}
		return this.cells.get(coordinateX*Board.width + coordinateY);
	}
	
	public boolean isValidPositionInBoard(Cell c) {
		return (c.getCoordinateX() >= 0 && c.getCoordinateX() < length) &&
				(c.getCoordinateY() >= 0 && c.getCoordinateY() < width);
	}
	
	public Boat shoot(int coordinateX, int coordinateY) throws CellOutOfBoundsException {
		getCell(coordinateX, coordinateY).hit();
		
		for (Boat b : getBoats()) {
			if (b.hasBeenHit()) {
				boats.remove(b);
				// System.out.println(b + " has been hit.");
				return b;
			}
		}
		return null;
	}
	
	public boolean isOccupiedCell(Cell c) {
		for (Boat b : this.getBoats()) {
			if (b.getPositions().contains(c)) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String board = "";
		for (int j = 0; j < width; j++) {
			board += " -";
		}
		board += "\n";
		for (int i = 0; i < length; i++) {
			board += "|"; 
			for (int j = 0; j < width; j++) {
				Cell c;
				try {
					c = this.getCell(i, j);
					if (isOccupiedCell(c)) {
						board += "x";
					} else {
						board += " ";
					}
				} catch (CellOutOfBoundsException e) {
					System.out.println(e);
				}
				board += "|";
			}
			board += "\n";
			for (int j = 0; j < width; j++) {
				board += " -";
			}
			board += "\n";
		}
		return board;
	}
	
	public List<Cell> generateRandomBoat(int size) throws CellOutOfBoundsException {
		List<Cell> positions = new ArrayList<Cell>();
		Random r = new Random();
		int randNum = r.nextInt(2);
		boolean horizontally = randNum == 0 ? true : false;
		if (horizontally) { // row is fixed, boat is placed horizontally
			int row = r.nextInt(Board.getBoardLength());
			int firstCol = r.nextInt(Board.getBoardWidth());
			if (firstCol <= Board.getBoardWidth() - size) {
				for(int col=firstCol;col < firstCol + size; col++) {
					positions.add(this.getCell(row, col));
				}
			} else {
				for(int col = firstCol;col > firstCol - size; col--) {
					positions.add(this.getCell(row, col));
				}
			}
		} else { // col is fixed, boat is placed vertically
			int col = r.nextInt(Board.getBoardWidth());
			int firstRow = r.nextInt(Board.getBoardLength());
			if (firstRow <= Board.getBoardLength() - size) {
				for(int row = firstRow;row < firstRow + size; row++) {
					positions.add(this.getCell(row, col));
				}
			} else {
				for(int row = firstRow;row > firstRow - size; row--) {
					positions.add(this.getCell(row, col));
				}
			}
		}
		return positions;
	}
}
