package it.unicam.cs.pa.ConnectFour.core;

import java.util.Properties;

import it.unicam.cs.pa.ConnectFour.player.InteractivePlayer;
import it.unicam.cs.pa.ConnectFour.player.Player;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSetType;

/**
 * @author giacchè
 *
 */
public class IterativeMatch {

	public static void main(String[] args) {
		Player p1 = new InteractivePlayer("Pippo");
		Player p2 = new InteractivePlayer("Pluto");
		
		Properties prop = new Properties();
		prop.setProperty("size", RuleSetType.DEFAULT.defaultSize());
		prop.setProperty("ruleset", RuleSetType.DEFAULT.name());
		// REPORT FIRSTPLAYER MUST BE 0 OR 1
		prop.setProperty("firstPlayer", "0");
		
		Match m = Match.getInstance();
		m.initMatch(p1, p2, prop);
		m.play();
	}

}
