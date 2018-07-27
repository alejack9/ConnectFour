/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.match;

import java.util.HashMap;
import java.util.Scanner;

import it.unicam.cs.pa.ConnectFour.player.InteractivePlayer;
import it.unicam.cs.pa.ConnectFour.player.Player;
import it.unicam.cs.pa.ConnectFour.player.RandomPlayer;
import it.unicam.cs.pa.ConnectFour.ruleSet.DefaultRuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.PopOutRuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;



/**
 * @author Alessandra Boccuto
 *
 */
public class ChooseMatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Player p1 = choosePlayerType(1);
		Player p2 = choosePlayerType(2);
		
		HashMap<String, Object> prop = new HashMap<>();
		
		prop.put("size", DefaultRuleSet.DEFAULT_SIZE);
		prop.put("ruleset", chooseRuleset());
		prop.put("firstPlayer", chooseFirstPlayer());
		Match m = Match.getInstance();
		m.initMatch(p1, p2, prop);
		m.play();
		
		}

	/**  Allows player to choose the player type
	 * @return Player
	 */
	private static Player choosePlayerType(int n) {
		Scanner in =new Scanner(System.in);
		System.out.println("Choose the player "+n+" type: (1 for InteractivePlayer, 2 for RandomPlayer)");
		int num = in.nextInt();
		while ((num<1)||(num>2)) {
			System.out.println("input error!");
			num=in.nextInt();
			}
		switch (num) {
		case 1: {return new InteractivePlayer(setPName());}
		case 2: {return new RandomPlayer(setPName());}
		default: return null;
		}
	}

	/**Allows player to choose the player name
	 * @return String the player name
	 */
	private static String setPName() {
		Scanner in =new Scanner(System.in);
		System.out.println("Choose the player name");		
		return in.nextLine();
	}

	/**Allows player to choose who is the first player
	 * @return int first player
	 */
	private static int chooseFirstPlayer() {
		Scanner in =new Scanner(System.in);
		System.out.println("Choose first Player: (1 for Player 1, 2 for Player 2)");
		int num = in.nextInt();
		while ((num<1)||(num>2)) {
			System.out.println("input error!");
			num=in.nextInt();
			}
		return num;
	}

	/**Allows player to choose a specific game ruleset
	 * @return Ruleset 
	 */
	private static RuleSet chooseRuleset() {
		Scanner in =new Scanner(System.in);
		System.out.println("Choose the Ruleset: (1 for Default, 2 for Pop Out)");
		int num = in.nextInt();
		while ((num<1)||(num>2)) {
			System.out.println("input error!");
			num=in.nextInt();
			}
		switch (num) {
		case 1: {return new DefaultRuleSet();}
		case 2: {return new PopOutRuleSet();}
		default: return null;
		}
	}
}

