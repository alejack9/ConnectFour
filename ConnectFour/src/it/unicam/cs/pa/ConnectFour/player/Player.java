package it.unicam.cs.pa.ConnectFour.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.exception.IllegalIdValue;
import it.unicam.cs.pa.ConnectFour.exception.InternalException;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;

/**
 * @author giacchè
 *
 */
public abstract class Player {

	// REPORT sicche` referee e` statico, e` uguale per tutte le sottoclassi, percio` 2 giocati avranno lo stesso referee
	protected static RuleSet referee;
	protected MatchField field;
	
	protected String name;
	protected int ID;
	
	protected BufferedReader in;
	protected PrintStream out;
	
	protected Player( String name , RuleSet ruleset , InputStream in , PrintStream out ) {
		this.name = name;
		this.in = new BufferedReader(new InputStreamReader(in));
		this.out = out;
		referee = ruleset;
	}
	
	/**
	 * @return The column prompted by the player
	 */
	public abstract int getColumn() throws InternalException;
	
	/**
	 * @param e The error that make player win
	 */
	public abstract void winForError(Throwable e);

	/**
	 * @param e The error that make player lose
	 */
	public abstract void loseForError(Throwable e);

	/**
	 * Initialize match's parameters
	 */
	public abstract void startMatch();

	/**
	 * Notify the player that he's won
	 */
	public abstract void youWin();

	/**
	 * Notify the player that he's lost
	 */
	public abstract void youLose();

	/**
	 * @param pid The player' id
	 * @param referee The referee
	 */
	public abstract void init(int pid , MatchField field ) throws IllegalIdValue;

	/**
	 * @return The action prompted by the player
	 * @throws IOException 
	 */
	public abstract ActionType chooseAction() throws InternalException;
	
}
