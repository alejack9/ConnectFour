/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.core;

import java.util.HashMap;

import it.unicam.cs.pa.ConnectFour.player.Player;
import it.unicam.cs.pa.ConnectFour.player.RandomPlayer;
import it.unicam.cs.pa.ConnectFour.ruleSet.DefaultRuleSet;

/**
 * @author giacche`
 *
 */
public class RandomMatch {

	public static void main(String[] args) {
		Player p1 = new RandomPlayer("X");
		Player p2 = new RandomPlayer("O");
		
		HashMap<String, Object> prop = new HashMap<>();
		prop.put("size", DefaultRuleSet.DEFAULT_SIZE);
		prop.put("ruleset", new DefaultRuleSet());
		prop.put("firstPlayer", 0);
		Match m = Match.getInstance();
		m.initMatch(p1, p2, prop);
		m.play();
	}

}
