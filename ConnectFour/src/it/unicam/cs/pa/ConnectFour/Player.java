/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

/**
 * @author giacchè
 *
 */
public interface Player {

	/**
	 * @param piece
	 * @return
	 */
	//PieceLocation insert(Piece piece);

	int getColumn();
	
	/**
	 * @param e
	 */
	void winForError(Throwable e);

	/**
	 * @param e
	 */
	void loseForError(Throwable e);

	/**
	 * 
	 */
	void startMatch();

	/**
	 * 
	 */
	void youWin();

	/**
	 * 
	 */
	void youLose();

	/**
	 * @param pid
	 * @param ruleSet
	 */
	void init(int pid, RuleSet ruleSet);

	/**
	 * @return
	 */
	ActionType chooseAction();

}
