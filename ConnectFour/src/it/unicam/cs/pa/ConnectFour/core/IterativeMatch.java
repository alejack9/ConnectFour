package it.unicam.cs.pa.ConnectFour.core;

import java.util.Properties;
import java.util.Random;

import it.unicam.cs.pa.ConnectFour.player.InteractivePlayer;
import it.unicam.cs.pa.ConnectFour.player.Player;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSetType;

/**
 * @author giacche`
 *
 */
public class IterativeMatch {
 
	public static void main(String[] args) {
		Player p1 = new InteractivePlayer("X");
		Player p2 = new InteractivePlayer("O");
		
		Properties prop = new Properties();
		prop.setProperty("size", RuleSetType.DEFAULT.defaultSize().toString());
		prop.setProperty("ruleset", RuleSetType.DEFAULT.name());
		// REPORT FIRSTPLAYER MUST BE 0 OR 1
		prop.setProperty("firstPlayer", String.valueOf(new Random().nextInt(2)));
		
		Match m = Match.getInstance();
		m.initMatch(p1, p2, prop);
		m.play();
	}

}
