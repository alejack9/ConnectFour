/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.ruleSet;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiPredicate;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.Cell;
import it.unicam.cs.pa.ConnectFour.core.CellLocation;
import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.core.Size;
import it.unicam.cs.pa.ConnectFour.exception.IllegalColumnException;
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
	public HashMap<Integer, BiPredicate<List<Cell>,CellStatus>> getAllowedActions();
	
	/**
	 * @return Final piece location inserted in the column
	 */
	public CellLocation insertLocation(int column , MatchField field) throws IllegalColumnException , IllegalPieceLocation;
	
	/**
	 * @return The column' cells popped (without the last Piece)
	 */
	public List<Cell> popColumn (int column, MatchField field) throws IllegalColumnException;

	/**
	 * @param loc
	 * @param custumSize
	 * @return true if the column is in bound, false otherwise
	 */
	public default boolean isInBound(CellLocation loc, Size customSize) {
		return loc.getRow() >= 0 && loc.getRow() < customSize.getRows() && loc.getColumn() >= 0 && loc.getColumn() < customSize.getColumns();
	}
	
	public default boolean isInBound(int column, int customColumnSize) {
		return isInBound(new CellLocation(0, column) , new Size( 1 , customColumnSize ));
	}

	boolean isInBound ( CellLocation loc );
	
	boolean isInBound(int column);
	
	/**
	 * @return true if the action is allowed, false otherwise
	 */
	public default boolean isValidAction(ActionType action) {
		return getAllowedActions().containsKey(action.ordinal());
	}
	
	/**
	 * @param field 
	 * @return true if the insert is valid, false otherwise 
	 */
//	boolean isValidInsert ( int column , MatchField field , CellStatus player );
	
//	boolean isValidPop( int column , MatchField field , CellStatus player );
	
	/**
	 * @param field
	 * @param cell (in pop case, send the most bottom cell)
	 * @return P1, P2, BOTH or NONE if there aren't winners
	 */
	Winner winner(MatchField field, CellLocation cell);
}