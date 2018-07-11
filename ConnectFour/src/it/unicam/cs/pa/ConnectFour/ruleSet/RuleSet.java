/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.ruleSet;

import java.util.List;
import java.util.stream.Stream;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.Cell;
import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.core.PieceLocation;

/**
 * @author giacchè
 *
 */
public interface RuleSet {
	
	/**
	 * @return true if the insert is valid, false otherwise 
	 */
	public boolean isValidInsert ( int column );
	/**
	 * @return true if the column is in bound, false otherwise
	 */
	public boolean isInBound ( int column );
	
	/**
	 * @return Final piece location inserted in the column
	 */
	public PieceLocation insert(int column , Cell[][] field);
	
	/**
	 * @return The column' cells popped (without the last Piece)
	 */
	public List<Cell> pop (List<Cell> column);
	
	/**
	 * @param field The match field
	 * @return P1, P2 or EMPTY if there aren't winners
	 */
	public CellStatus winner (Cell[][] field);
	
	/**
	 * @return The number of allowed actions
	 */
	public int actionsNumber();
	
	/**
	 * @return Allowed actions
	 */
	public ActionType[] getAllowedActions();
	
	/**
	 * @return true if the action is allowed, false otherwise
	 */
	public default boolean isValidAction(ActionType action) {
		return Stream.of(getAllowedActions()).anyMatch(c -> c.equals(action));
	}
}
