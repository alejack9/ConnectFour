/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.ruleSet;

import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.Cell;
import it.unicam.cs.pa.ConnectFour.core.CellLocation;
import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.core.Size;
import it.unicam.cs.pa.ConnectFour.exception.IllegalColumnException;

/**
 * Generic referee interface
 * 
 * @author giacche`
 *
 */
public interface RuleSet {

	/**
	 * Provides the number of possible actions
	 * 
	 * @return The number of allowed actions
	 */
	public default int actionsNumber() {
		return getAllowedActions().size();
	}

	/**
	 * Provides a map of actions
	 * 
	 * @return A map of allowed actions:
	 *         <ul>
	 *         <li>{@link ActionType} the action</li>
	 *         <li>{@link BiPredicate} the associated predicate to test if the
	 *         action is valid in a given {@link List Column} and
	 *         {@link CellStatus}</li>
	 *         </ul>
	 */
	public Map<ActionType, BiPredicate<List<Cell>, CellStatus>> getAllowedActions();

	/**
	 * Provides the default size of a RuleSet
	 * 
	 * @return The default {@link Size}
	 */
	public Size getDefaultSize();

	/**
	 * Provides the {@link CellLocation} of a Cell if inserted into the passed
	 * column in the given field
	 * 
	 * @param column The column number
	 * @param field  The {@link MatchField}
	 * @return The final piece location to insert in the column
	 * @throws IllegalColumnException if the column number is not valid (e.g. column
	 *                                out of MatchField bounds)
	 */
	public CellLocation insertLocation(int column, MatchField field) throws IllegalColumnException;

	/**
	 * Provides a list of cell computed from a column of field after the pop operation
	 * 
	 * @param column The column number
	 * @param field The {@link MatchField}
	 * @return The result column where the cells has been popped
	 * @throws IllegalColumnException if the column number is not valid (e.g.
	 *                                column out of MatchField bounds)
	 */
	public List<Cell> popColumn(int column, MatchField field) throws IllegalColumnException;

	/**
	 * Checks if a {@link CellLocation location} is in bound
	 * 
	 * @param loc The cell location
	 * @param customSize The field {@link Size sizes}
	 * @return {@code True} if the cell is in bound, {@code false} otherwise
	 */
	public default boolean isInBound(CellLocation loc, Size customSize) {
		return loc.getRow() >= 0 && loc.getRow() < customSize.getRows() && loc.getColumn() >= 0
				&& loc.getColumn() < customSize.getColumns();
	}

	/**
	 * Checks if a column is in bound
	 * 
	 * @param column The column number
	 * @param customColumnSize The field's column size
	 * @return {@code True} if the column is in bound, {@code false} otherwise
	 */
	public default boolean isInBound(int column, int customColumnSize) {
		return isInBound(new CellLocation(0, column), new Size(1, customColumnSize));
	}

	/**
	 * Checks if the passed action is valid
	 * 
	 * @param action The action
	 * @return {@code True} if the action is allowed, {@code false} otherwise
	 */
	public default boolean isValidAction(ActionType action) {
		return getAllowedActions().containsKey(action);
	}

	/**
	 * Gives the Winner
	 * 
	 * @param field The {@link MatchField}
	 * @param cell (in pop case, pass the most bottom cell)
	 * @return the {@link Winner}
	 */
	Winner winner(MatchField field, CellLocation cell);
}