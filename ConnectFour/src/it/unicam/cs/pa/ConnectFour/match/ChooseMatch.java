package it.unicam.cs.pa.ConnectFour.match;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;

import it.unicam.cs.pa.ConnectFour.core.Size;
import it.unicam.cs.pa.ConnectFour.core.Utils;
import it.unicam.cs.pa.ConnectFour.player.Player;
import it.unicam.cs.pa.ConnectFour.player.PlayerType;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSetType;

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
	 * Allows player to choose Matchfield size
	 * @return  MatchField size (Default size or custom size)
	 */
	
	private static Size chooseSize(RuleSet referee) {
		String line = Utils.doInput(in, out, "\nDo  you want to use default matchfield size 6x7? (y/n) ", x -> x.equals("y") || x.equals("n"), String::valueOf);
		if(line.equals("y")) return referee.getDefaultSize();
		return new Size(chooseNumber("rows"), chooseNumber("columns"));
	}

	/**
	 * Allows player to choose the number of rows or columns
	 * @return number of rows or columns
	 */
	private static Integer chooseNumber(String name) {
		int num = Utils.doInput(in, out, "\nChoose the number of " + name + " (bigger than 1) : ", x -> x > 1, Integer::parseInt);
		return num;
	}


	/**
	 * Allows player to choose the player name
	 * 
	 * @return String the player name
	 */
	private static String setPName() {
		return Utils.doInput(in, out, "\nChoose the player name: ", s -> true, String::valueOf);
	}

	/**
	 * Allows player to choose who is the first player
	 * 
	 * @return int first player
	 */
	private static int chooseFirstPlayer() {
		int num = Utils.doInput(in, System.out, "\nChoose first Player: (1 for Player 1, 2 for Player 2)", x -> x == 1 || x == 2, Integer::parseInt);
		return num - 1;
	}

	/**
	 * Allows player to choose a specific game ruleset
	 * 
	 * @return Ruleset
	 */
	private static RuleSet chooseRuleset() {
		int num = Utils.doInput(in, System.out, requestP("RuleSet"), x -> x >= 0 && x < RuleSetType.values().length, Integer::parseInt);
		return RuleSetType.values()[num].getRuleSet();
		}
	
	/**  
	 * Allows player to choose the player type
	 * @param numplayer number of the player
	 * @return Player 
	 */
	private static Player choosePlayerType(int numplayer) {
		System.out.println("\nPlayer " + numplayer + " : ");
		int num = Utils.doInput(in, System.out, requestP("Player"), x -> x >= 0 && x < PlayerType.values().length, Integer::parseInt);
		return PlayerType.values()[num].getPlayer(setPName());
		}

	/** 
	 * @param name specified the name of the object to request (Ruleset or Player)
	 * @return String that specifies available Player types or Ruleset types the user can choose 
	 */
	private static String requestP(String name) {
		String req= "\nChoose the " + name + " type:";
		switch (name) {
		case "RuleSet" :
			for (RuleSetType x : RuleSetType.values()) {
				req += "\n" + x.ordinal() + " for " + x.toString();
				
			}
			break;
		case "Player" : 
			for (PlayerType x : PlayerType.values()) {
				req += "\n" + x.ordinal() + " for " + x.toString();
			}
		}
		return req;
	}
	}

