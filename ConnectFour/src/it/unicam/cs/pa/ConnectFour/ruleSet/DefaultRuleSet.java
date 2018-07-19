package it.unicam.cs.pa.ConnectFour.ruleSet;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

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

	private static final HashMap<Integer, ActionType> allowedActions = new HashMap<>();
	
	public static final int[] DEFAULT_SIZE = { 6 , 7 };
	
//	private static final ActionType[] allowedActions = { ActionType.INSERT };

	private final BiFunction<Integer, List<List<Cell>>, Optional<Cell>> destinationCell = ( column , field ) -> field.get(column).stream().filter(Cell::isEmpty).reduce((prev, last) -> last);
	
	public DefaultRuleSet () {
		allowedActions.put(ActionType.INSERT.ordinal(), ActionType.INSERT);
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#actionsNumber()
	 */
	@Override
	public int actionsNumber() {
		return allowedActions.size();
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
		if(isInBound(column,field.getColumns())) {
			Cell cell = destinationCell.apply(column, field.getField()).orElseThrow(() -> new IllegalPieceLocation(column,field));
			return cell.getLocation();

			
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
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#isInBound(PieceLocation,int)
	 */
	@Override
	public boolean isInBound(CellLocation loc, int[] customSize) {
		return loc.getRow() >= 0 && loc.getRow() < customSize[0] && loc.getColumn() >= 0 && loc.getColumn() < customSize[1];
	}
	
	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#isInBound(PieceLocation)
	 */
	@Override
	public boolean isInBound(CellLocation loc) {
		return isInBound(loc, DEFAULT_SIZE);
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#isInBound(int,int)
	 */
	@Override
	public boolean isInBound(int column , int customColumnSize) {
		return isInBound(new CellLocation(0, column) , new int[] { 1 , customColumnSize });
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#isInBound(int)
	 */
	@Override
	public boolean isInBound(int column) {
		return isInBound( column , DEFAULT_SIZE[1] );
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
		if(field.getPieces() >= 7) {
			for (Function<CellLocation,List<Cell>> function : field.getListsGetters()) {
				List<Cell> list = function.apply(cell);
//				Stream<Integer> s = Stream.iterate(0, i -> i < list.size() , i -> i+1);
//				long con = s.filter(i -> i == 0 || (list.get(i - 1).getStatus() == list.get(i).getStatus() && list.get(i).getStatus() != CellStatus.EMPTY))
//				.count();
//				int con = 0;
				long con = Stream.iterate(0, i -> i < list.size() , i -> i + 1)
					.filter(i -> i == 0 || (list.get(i - 1).getStatus() == list.get(i).getStatus() && list.get(i).getStatus() != CellStatus.EMPTY))
					.count();
//				Stream.iterate(0, i -> i < list.size() , (i) -> i + 1).map(i -> list.get(i)).;
//				long con = function.apply(cell).stream().takeWhile(x -> x.getPiece().getColor() == field.getCellStatus(cell)).count();
				if(con >= 4) return field.getCellStatus(cell);
			}
		}
		return CellStatus.EMPTY;
	}
}
