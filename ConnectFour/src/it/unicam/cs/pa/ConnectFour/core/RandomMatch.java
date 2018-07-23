/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.core;

import java.util.Properties;

import it.unicam.cs.pa.ConnectFour.player.Player;
import it.unicam.cs.pa.ConnectFour.player.RandomPlayer;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSetType;

/**
 * @author giacche`
 *
 */
public class RandomMatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Player p1 = new RandomPlayer("P1");
		Player p2 = new RandomPlayer("P2");
		
		Properties prop = new Properties();
		int[] size = new int[] { 9 , 9 };
		prop.setProperty("size", Utils.sizeToString(size));
		prop.setProperty("ruleset", RuleSetType.DEFAULT.name());
		// REPORT FIRSTPLAYER MUST BE 0 OR 1
		prop.setProperty("firstPlayer", "0");
		
		Match m = Match.getInstance();
		m.initMatch(p1, p2, prop);
		m.play();
	}

}
