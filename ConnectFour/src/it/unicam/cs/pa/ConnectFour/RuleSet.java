/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author giacchè
 *
 */
public interface RuleSet {
	
	/**
	 * @return
	 */
	public boolean isValidInsert ();
	/**
	 * @param column
	 * @return
	 */
	public boolean isInBound ( int column );
	
	/**
	 * @param column
	 * @param field
	 * @return
	 */
	public PieceLocation insert(int column , Cell[][] field);
	
	/**
	 * @param column
	 * @return the column' cells
	 */
	public List<Cell> pop (List<Cell> column);
	
	/**
	 * @param field the match field
	 * @return P1, P2 or EMPTY if there aren't winners
	 */
	public CellStatus winner (Cell[][] field);
	
	/**
	 * @return
	 */
	public int actionsNumber();
	
	/**
	 * @return
	 */
	public ActionType[] getAllowedActions();
	
	/**
	 * @param action
	 * @return
	 */
	public default boolean isValidAction(ActionType action) {
		return Stream.of(getAllowedActions()).anyMatch(c -> c.equals(action));
	}
}
