package it.unicam.cs.pa.ConnectFour.match;

import java.util.HashMap;

import it.unicam.cs.pa.ConnectFour.player.InteractivePlayer;
import it.unicam.cs.pa.ConnectFour.player.Player;
import it.unicam.cs.pa.ConnectFour.ruleSet.DefaultRuleSet;

/**
 * @author giacche`
 *
 */
public class InteractiveMatch {
 
	public static void main(String[] args) throws NumberFormatException, IllegalArgumentException {

		Player p1 = new InteractivePlayer("Jack");
		Player p2 = new InteractivePlayer("Vale");
		
		HashMap<String, Object> proop = new HashMap<>();

		proop.put("size", DefaultRuleSet.DEFAULT_SIZE);
		proop.put("ruleset", new DefaultRuleSet());
		proop.put("firstPlayer", 0);
		Match m = Match.getInstance();
		m.initMatch(p1, p2, proop);
		m.play();

		// REPORT We used to use Properties class, but it's more convenient use an HashMap containing the objects and not the objects' name 
	}

}
