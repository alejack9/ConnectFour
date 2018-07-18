/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.ruleSet;

import java.util.HashMap;
import java.util.List;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.Cell;
import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.core.CellLocation;
import it.unicam.cs.pa.ConnectFour.exception.IllegalPieceLocation;

/**
 * @author giacchè
 *
 */
public interface RuleSet {
	
	/**
	 * @param field 
	 * @return true if the insert is valid, false otherwise 
	 */
	public boolean isValidInsert ( int loc, MatchField field );
	/**
	 * @return true if the column is in bound, false otherwise
	 */
	public boolean isInBound ( CellLocation loc );
	
	/**
	 * @return Final piece location inserted in the column
	 */
	public CellLocation getPieceLocation(int column , MatchField field) throws IllegalPieceLocation;
	
	/**
	 * @return The column' cells popped (without the last Piece)
	 */
	public List<Cell> pop (List<Cell> column);
		
	/**
	 * @return The number of allowed actions
	 */
	public int actionsNumber();
	
	/**
	 * @return Allowed actions
	 */
	public HashMap<Integer,ActionType> getAllowedActions();
	
	public abstract int[] getDefaultSize();
	
	/**
	 * @return true if the action is allowed, false otherwise
	 */
	public default boolean isValidAction(ActionType action) {
		return getAllowedActions().containsValue(action);
		
//		return Stream.of(getAllowedActions()).anyMatch(c -> c.equals(action));
	}
	/**
	 * @param field
	 * @param cell (in pop case, send the most bottom cell)
	 * @return P1, P2 or EMPTY if there aren't winners
	 */
	CellStatus winner(MatchField field, CellLocation cell);
	/**
	 * @param loc
	 * @param custumSize
	 * @return
	 */
	public boolean isInBound(CellLocation loc, int custumSize);
	/**
	 * @param column
	 * @param customSize
	 * @return
	 */
	boolean isInBound(int column, int customSize);
}
