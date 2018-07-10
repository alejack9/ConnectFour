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
	
	public boolean isValidInsert ();
	public boolean isInBound ();
	
	public PieceLocation insert(int column , Cell[][] field);
	/**
	 * i'm fucking out of mind
	 * @param column
	 * @return the column' cells
	 */
	public List<Cell> pop (List<Cell> column);
	
	public CellStatus winner (Cell[][] field);
	
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
