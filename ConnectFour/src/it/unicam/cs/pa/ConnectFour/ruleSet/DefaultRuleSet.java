package it.unicam.cs.pa.ConnectFour.ruleSet;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

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
public class DefaultRuleSet implements RuleSet {

	// FIXME REPLACE CELL WITH PIECELOCATION!
	
	private static final HashMap<Integer, ActionType> allowedActions = new HashMap<>();
	
//	private static final ActionType[] allowedActions = { ActionType.INSERT };

	private final BiFunction<Integer, List<List<Cell>>, Optional<Cell>> destinationCell = ( column , field ) -> field.get(column).stream().filter(Cell::isEmpty).reduce((prev, last) -> last);
	
	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#actionsNumber()
	 */
	@Override
	public int actionsNumber() {
		return allowedActions.size();
	}

	public DefaultRuleSet () {
		allowedActions.put(ActionType.INSERT.ordinal(), ActionType.INSERT);
	}
	
	public HashMap<Integer , ActionType> getAllowedActions() {
		return allowedActions;
//		return allowedActions;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#insert(int, it.unicam.cs.pa.ConnectFour.Cell[][])
	 */
	@Override
	public CellLocation getPieceLocation (int column, MatchField field) throws IllegalPieceLocation {
		// FIXME TO TEST
		if(isInBound(column,field.getColumns())) {
			Cell cell = destinationCell.apply(column, field.getField()).orElseThrow(() -> new IllegalPieceLocation(column,field));
			return new CellLocation(cell.getRow(), cell.getColumn());

			
//			int row = 0;
//			while(field[row++][column].isEmpty());
//			return new PieceLocation(row - 1, column);
//			int x = 0;
//			for(Cell c : field[column] ) {
//				if(c.isEmpty()) x++;
//			}
//			return new PieceLocation(x - 1, column);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#isInBound(int,int)
	 */
	@Override
	public boolean isInBound(int column , int customSize) {
		return column < customSize;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#isInBound(PieceLocation)
	 */
	@Override
	public boolean isInBound(CellLocation loc) {
		return isInBound(loc,getDefaultSize()[1]);
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#isInBound(PieceLocation,int)
	 */
	@Override
	public boolean isInBound(CellLocation loc, int customSize) {
		return isInBound(loc.getColumn(), customSize);
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#isValidInsert(int, MatchField)
	 */
	@Override
	public boolean isValidInsert(int column, MatchField field) {
		return destinationCell.apply(column, field.getField()).isPresent();
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#pop(java.util.List)
	 */
	@Override
	public List<Cell> pop(List<Cell> column) {
		return column;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#winner(it.unicam.cs.pa.ConnectFour.Cell[][])
	 */
	@Override
	public CellStatus winner(MatchField field, CellLocation cell) {
		// TODO TO TEST
		if(field.getPieces() < 8) return CellStatus.EMPTY;

		for (Function<CellLocation,List<Cell>> function : field.getListsGetters()) {
			long con = function.apply(cell).stream().takeWhile(x -> x.getPiece().getColor() == field.getCellStatus(cell)).count();
			if(con >= 4) return field.getCellStatus(cell);
		}

		return CellStatus.EMPTY;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#getDefaultSize()
	 */
	@Override
	public int[] getDefaultSize() {
		return new int[] { 6 , 7 };
	}

}
