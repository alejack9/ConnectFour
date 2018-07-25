/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import it.unicam.cs.pa.ConnectFour.player.Player;
import it.unicam.cs.pa.ConnectFour.player.RandomPlayer;
import it.unicam.cs.pa.ConnectFour.ruleSet.DefaultRuleSet;

/**
 * @author giacche`
 *
 */
public class RandomMatch {

	public static void main(String[] args) throws IOException {
		
		// FIXME DOESN'T WORK
		Player p1 = new RandomPlayer("X",true);
		Player p2 = new RandomPlayer("O",true);
		
		HashMap<String, Object> prop = new HashMap<>();
		prop.put("size", DefaultRuleSet.DEFAULT_SIZE);
		prop.put("ruleset", new DefaultRuleSet());
		prop.put("firstPlayer", 0);
		Match m = Match.getInstance();
		m.initMatch(p1, p2, prop);
		m.play();
		System.out.println("Continue?(y/N) ");
		while(new BufferedReader(new InputStreamReader(System.in)).readLine().equals("y")) {
			m.restart();
			System.out.println("Continue?(y/N) ");
		}
		System.out.println("Bye!");
	}

}
