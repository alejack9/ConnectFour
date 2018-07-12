package it.unicam.cs.pa.ConnectFour.player;

import java.io.IOException;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.exception.IllegalIdValue;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;

/**
 * @author giacchè
 *
 */
public interface Player {

	/**
	 * @return The column prompted by the player
	 */
	int getColumn();
	
	/**
	 * @param e The error that make player win
	 */
	void winForError(Throwable e);

	/**
	 * @param e The error that make player lose
	 */
	void loseForError(Throwable e);

	/**
	 * Initialize match's parameters
	 */
	void startMatch();

	/**
	 * Notify the player that he's won
	 */
	void youWin();

	/**
	 * Notify the player that he's lost
	 */
	void youLose();

	/**
	 * @return The action prompted by the player
	 * @throws IOException 
	 */
	ActionType chooseAction();

	/**
	 * @param pid The player' id
	 * @param referee The referee
	 */
	void init(int pid , RuleSet referee ) throws IllegalIdValue;

}
