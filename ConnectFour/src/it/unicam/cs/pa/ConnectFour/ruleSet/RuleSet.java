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
import it.unicam.cs.pa.ConnectFour.core.Size;
import it.unicam.cs.pa.ConnectFour.core.CellLocation;
import it.unicam.cs.pa.ConnectFour.exception.IllegalPieceLocation;

/**
 * @author giacche`
 *
 */
public interface RuleSet {
	
	/**
	 * @return The number of allowed actions
	 */
	public default int actionsNumber() {
		return getAllowedActions().size();
	}
	/**
	 * @return Allowed actions
	 */
	public HashMap<Integer,ActionType> getAllowedActions();
	
	/**
	 * @return Final piece location inserted in the column
	 */
	public CellLocation getPieceLocation(int column , MatchField field) throws IllegalPieceLocation;
	/**
	 * @param loc
	 * @param custumSize
	 * @return
	 */
	public boolean isInBound(CellLocation loc, Size customSize);
	/**
	 * @return true if the column is in bound, false otherwise
	 */
	public boolean isInBound ( CellLocation loc );

	boolean isInBound(int column, int customColumnSize);
	
	boolean isInBound(int column);
	/**
	 * @return true if the action is allowed, false otherwise
	 */
	public default boolean isValidAction(ActionType action) {
		return getAllowedActions().containsValue(action);
	}
	
	/**
	 * @param field 
	 * @return true if the insert is valid, false otherwise 
	 */
	public boolean isValidInsert ( int loc, MatchField field );
	
	public boolean isValidPop( int column , MatchField field);
	
	/**
	 * @return The column' cells popped (without the last Piece)
	 */
	public List<Cell> pop (List<Cell> column);
		
	/**
	 * @param field
	 * @param cell (in pop case, send the most bottom cell)
	 * @return P1, P2 or EMPTY if there aren't winners
	 */
	CellStatus winner(MatchField field, CellLocation cell);
}
