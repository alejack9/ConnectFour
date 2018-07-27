package it.unicam.cs.pa.ConnectFour.match;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;

import it.unicam.cs.pa.ConnectFour.core.Size;
import it.unicam.cs.pa.ConnectFour.core.Utils;
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
	
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private static PrintStream out = System.out;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Player p1 = choosePlayerType(1);
		Player p2 = choosePlayerType(2);

		HashMap<String, Object> prop = new HashMap<>();

		prop.put("ruleset", chooseRuleset());
		prop.put("size", chooseSize((RuleSet)prop.get("ruleset")));
		prop.put("firstPlayer", chooseFirstPlayer());
		Match m = Match.getInstance();
		m.initMatch(p1, p2, prop);
		m.play();

	}

	/**
	 * @return Size the size of the MatchField (Default size or custom size)
	 */
	private static Size chooseSize(RuleSet referee) {
		String line = Utils.doInput(in, out, "Do  you want to use default matchfield size 6x7? (y/n) ", x -> x.equals("y") || x.equals("n"), String::valueOf);
		if(line.equals("y")) return referee.getDefaultSize();
		return new Size(chooseNumber("rows"), chooseNumber("columns"));
	}

	/**
	 * @return number of rows or columns
	 */
	private static Integer chooseNumber(String name) {
		int num = Utils.doInput(in, out, "Choose the number of " + name + " (bigger than 1) : ", x -> x > 1, Integer::parseInt);
		return num;
	}

	/**  Allows player to choose the player type
	 * @return Player
	 */
	private static Player choosePlayerType(int n) {
		int num = Utils.doInput(in, System.out, "Choose the player "+n+" type: (1 for InteractivePlayer, 2 for RandomPlayer)", x -> x == 1 || x == 2, Integer::parseInt);
		switch (num) {
		case 1: {return new InteractivePlayer(setPName());}
		case 2: {return new RandomPlayer(setPName());}
		default: return null;
		}
	}

	/**
	 * Allows player to choose the player name
	 * 
	 * @return String the player name
	 */
	private static String setPName() {
		return Utils.doInput(in, out, "Choose the player name", s -> true, String::valueOf);
	}

	/**
	 * Allows player to choose who is the first player
	 * 
	 * @return int first player
	 */
	private static int chooseFirstPlayer() {
		// Scanner in =new Scanner(System.in);
		int num = Utils.doInput(in, System.out, "Choose first Player: (1 for Player 1, 2 for Player 2)", x -> x == 1 || x == 2, Integer::parseInt);
		return num - 1;
	}

	/**
	 * Allows player to choose a specific game ruleset
	 * 
	 * @return Ruleset
	 */
	private static RuleSet chooseRuleset() {
		int num = Utils.doInput(in, System.out, "Choose the Ruleset: (1 for Default, 2 for Pop Out)", x -> x == 1 || x == 2, Integer::parseInt);
		switch (num) {
		case 1:
			return new DefaultRuleSet();
		case 2:
			return new PopOutRuleSet();
		default:
			return null;
		}
	}

}
