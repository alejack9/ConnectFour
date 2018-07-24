/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.core;

import java.util.HashMap;

import it.unicam.cs.pa.ConnectFour.player.InteractivePlayer;
import it.unicam.cs.pa.ConnectFour.player.Player;
import it.unicam.cs.pa.ConnectFour.ruleSet.PopOutRuleSet;

/**
 * @author Alessandro Giacche`
 *
 */
public class PopRandomMatch {

	public static void main(String[] args) {
		// FIXME USER COULD ONLY GET NON-EMPTY COLUMN IF HE SELECTS "POP"
		// FIXME PLAYER MUST CONTROL IF THE POP IS VALID
		
		Player p1 = new InteractivePlayer("X");
		Player p2 = new InteractivePlayer("O");

		HashMap<String, Object> prop = new HashMap<>();
		prop.put("size", PopOutRuleSet.DEFAULT_SIZE);
		prop.put("ruleset", new PopOutRuleSet());
		prop.put("firstPlayer", 0);
		Match m = Match.getInstance();
		m.initMatch(p1, p2, prop);
		m.play();
	}
}