package battaglia_navale;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattagliaNavale {
	
	

	public static void main(String[] args) {
		
		Player p1 = new Player("Alfio");
		Player p2 = new Player("FraCan");
		
		List<Player> players = new ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		// Set up Game
		Game g = new Game(p1, p2);
		g.setUp();
		System.out.println("Game set up");
		for (Player p: players) {
			System.out.println(p + "'s board");
			System.out.println(p.getBoard().toString());
		}
		//play
		g.play();
		
		
		
	}
	
	
}
