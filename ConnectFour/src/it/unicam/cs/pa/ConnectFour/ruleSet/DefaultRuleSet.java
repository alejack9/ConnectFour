package it.unicam.cs.pa.ConnectFour.ruleSet;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;

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
public class DefaultRuleSet implements RuleSet {

	private static final HashMap<Integer, BiPredicate<List<Cell>,CellStatus>> allowedActions = new HashMap<>();
	
	public static final Size DEFAULT_SIZE = new Size( 6 , 7 );
	
	public static final String NAME = "DefaultRuleSet";
	
	private final Function<List<Cell>, Optional<Cell>> destinationCell = ( column ) -> column.stream().filter(Cell::isEmpty).reduce((prev, last) -> last);
	
	private final BiPredicate<List<Cell>,CellStatus> checkIns = (column, cell) -> destinationCell.apply(column).isPresent();
	
	public DefaultRuleSet () {
		allowedActions.put(ActionType.INSERT.ordinal(), checkIns);
	}

	public HashMap<Integer, BiPredicate<List<Cell>,CellStatus>> getAllowedActions() {
		return allowedActions;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#insert(int, it.unicam.cs.pa.ConnectFour.Cell[][])
	 */
	@Override
	public CellLocation insertLocation (int column, MatchField field) throws IllegalColumnException , IllegalPieceLocation {
		if(!isInBound(column,field.getColumns())) throw new IllegalColumnException(column,field);

		return destinationCell.apply(field.getColumn(column))
				.orElseThrow(() -> new IllegalPieceLocation(column, field)).getLocation();
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#isInBound(PieceLocation,int)
	 */
	@Override
	public boolean isInBound(CellLocation loc, Size customSize) {
		return loc.getRow() >= 0 && loc.getRow() < customSize.getRows() && loc.getColumn() >= 0 && loc.getColumn() < customSize.getColumns();
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
		return isInBound(new CellLocation(0, column) , new Size( 1 , customColumnSize ));
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#isInBound(int)
	 */
	@Override
	public boolean isInBound(int column) {
		return isInBound( column , DEFAULT_SIZE.getColumns() );
	}

//	/* (non-Javadoc)
//	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#isValidInsert(int, MatchField)
//	 */
//	@Override
//	public boolean isValidInsert(int column, MatchField field, CellStatus player ) {
//		return getAllowedActions().get(ActionType.INSERT.ordinal()).test(field.getColumn(column), player);
//	}
//	
//	/* (non-Javadoc)
//	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#isValidPop(int, it.unicam.cs.pa.ConnectFour.core.MatchField)
//	 */
//	@Override
//	public boolean isValidPop(int column, MatchField field, CellStatus player) {
//		return false;
//	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#pop(int, it.unicam.cs.pa.ConnectFour.core.MatchField)
	 */
	@Override
	public List<Cell> popColumn( int column , MatchField field) {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#winner(it.unicam.cs.pa.ConnectFour.Cell[][])
	 */
	@Override
	public Winner winner(MatchField field, CellLocation cell) {
		for (Function<CellLocation,List<Cell>> function : field.getListsGetters()) {
			List<Cell> list = function.apply(cell);
			int maxConsecutive = 1;
			int celleConsecutive = 1;
			for(int i = 0; i < list.size() - 1; i++) {
				if(list.get(i).getStatus() == list.get(i+1).getStatus() && !list.get(i).isEmpty()) celleConsecutive++;
				else {
					if(celleConsecutive > maxConsecutive) maxConsecutive = celleConsecutive;
					celleConsecutive = 1;
				}
			}
			if((celleConsecutive > maxConsecutive) ? celleConsecutive >= 4 : maxConsecutive >= 4) return Winner.convert(field.getCellStatus(cell));
		}
		return Winner.NONE;
	}
}
