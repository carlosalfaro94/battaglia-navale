package battaglia_navale;

public class Player {
	private String name;
	
	private Board board;
	
	public Player(String name) {
		this.name = name;
		this.board = new Board();
	}
	
	public String toString() {
		return name;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public boolean hasAliveBoats() {
		return !this.getBoard().getBoats().isEmpty();
	}
	
	public static void swapPlayers(Player p1, Player p2) {
		Player temp;
		temp = p1;
		p1 = p2;
		p2 = temp;
	}

}
