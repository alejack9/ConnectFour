/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.match;

import java.util.HashMap;

import it.unicam.cs.pa.ConnectFour.player.InteractivePlayer;
import it.unicam.cs.pa.ConnectFour.player.Player;
import it.unicam.cs.pa.ConnectFour.ruleSet.PopOutRuleSet;

/**
 * @author giacche`
 *
 */
public class PopInteractiveMatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Player p1 = new InteractivePlayer("X");
		Player p2 = new InteractivePlayer("O");

		HashMap<String, Object> proop = new HashMap<>();

		proop.put("size", PopOutRuleSet.DEFAULT_SIZE);
		proop.put("ruleset", new PopOutRuleSet());
		proop.put("firstPlayer", 0);
		Match m = Match.getInstance();
		m.initMatch(p1, p2, proop);
		m.play();

	}

}
