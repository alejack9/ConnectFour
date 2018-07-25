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
	 * @return A map of allowed actions:
	 *         <ul>
	 *         <li>{@link ActionType} the action</li>
	 *         <li>{@link BiPredicate} the associated predicate to test if the
	 *         action is valid in a given {@link List Column} and
	 *         {@link CellStatus}</li>
	 */
	public Map<ActionType, BiPredicate<List<Cell>, CellStatus>> getAllowedActions();

	/**
	 * @param column - The column number
	 * @param field  - The {@link MatchField}
	 * @return The final piece location to insert in the column
	 * @throws IllegalColumnException - if the column number is not valid (e.g.
	 *                                column out of MatchField bounds)
	 * @throws IllegalPieceLocation   - if the computed {@link CellLocation} is not
	 *                                valid (e.g. full column)
	 */
	public CellLocation insertLocation(int column, MatchField field)
			throws IllegalColumnException, IllegalPieceLocation;

	/**
	 * @param column - The column number
	 * @param field  - The {@link MatchField}
	 * @return The result column where the cells has been popped
	 * @throws IllegalColumnException - if the column number is not valid (e.g.
	 *                                column out of MatchField bounds)
	 */
	public List<Cell> popColumn(int column, MatchField field) throws IllegalColumnException;

	/**
	 * @param loc         - The cell location
	 * @param custuomSize - The field {@link Size sizes}
	 * @return true if the cell is in bound, false otherwise
	 */
	public default boolean isInBound(CellLocation loc, Size customSize) {
		return loc.getRow() >= 0 && loc.getRow() < customSize.getRows() && loc.getColumn() >= 0
				&& loc.getColumn() < customSize.getColumns();
	}

	/**
	 * @param column           - The column number
	 * @param customColumnSize - The field's column size
	 * @return true if the cell is in bound, false otherwise
	 */
	public default boolean isInBound(int column, int customColumnSize) {
		return isInBound(new CellLocation(0, column), new Size(1, customColumnSize));
	}

	/**
	 * @param action - The action
	 * @return true if the action is allowed, false otherwise
	 */
	public default boolean isValidAction(ActionType action) {
		return getAllowedActions().containsKey(action);
	}

	/**
	 * @param field - The {@link MatchField}
	 * @param cell  - (in pop case, pass the most bottom cell)
	 * @return P1, P2, BOTH or NONE if there aren't winners
	 */
	Winner winner(MatchField field, CellLocation cell);
}