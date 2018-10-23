package battaglia_navale;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
	
	private static final int numBoatsPerType = 2;
	public Player player1;
	public Player player2;
	
	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void setUp() {
		List<Player> players = new ArrayList<Player>();
		players.add(this.player1);
		players.add(this.player2);
		for (Player p: players) {
			for (BoatType b: BoatType.values()) {
				for (int i = 0; i < numBoatsPerType; i++) {
					List<Cell> positions;
					boolean done = false;
					do {  // try to generate a new random boat and place it in the board until there are no collisions						
						try {
							positions = p.getBoard().generateRandomBoat(b.getSize());
							p.getBoard().setNewBoat(positions);
							done = true;
						} catch (CellOutOfBoundsException | InvalidBoatException e) {
							
						}
					} while (done == false);
				}
			}
		}
	}
	//Play the game
	public void play() {
		Random r = new Random();
		boolean beaten; // tracks if a player has been beaten
		Player attacker = this.player1;  // Player1 begins attacking
		Player defender = this.player2;
		while(true) {
			int x = r.nextInt(Board.getBoardWidth());  // Generate random coordinates to shoot
			int y = r.nextInt(Board.getBoardWidth());
			//System.out.println(attacker + " shoots " + defender + " in cell (" + x + ", " + y + ")");
			beaten = shoot(defender, x, y);
			if (beaten) {
				System.out.println(defender + " has been beaten. " + attacker + " has won!");
				break;
			}
			if (attacker == this.player1) {
				attacker = this.player2;
				defender = this.player1;
			} else {
				attacker = player1;
				defender = player2;
			}
		}
	}
	
	public boolean shoot (Player p, int coordinateX, int coordinateY) {
		try {
			Boat touched = p.getBoard().shoot(coordinateX, coordinateY);
			if (touched != null) {
				System.out.println("Boat " + touched + " from " + p + " has been touched and drawned!");
				getStatus();
			}
		} catch (CellOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		return !p.hasAliveBoats();
	}
	
	public void getStatus() {
		System.out.println(this.player1 + " has " + this.player1.getBoard().getAliveBoats() + " boats.");
		System.out.println(this.player2 + " has " + this.player2.getBoard().getAliveBoats() + " boats.");
	}

}
