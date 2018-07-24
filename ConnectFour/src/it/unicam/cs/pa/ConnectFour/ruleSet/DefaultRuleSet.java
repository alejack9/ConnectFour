package it.unicam.cs.pa.ConnectFour.ruleSet;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.Cell;
import it.unicam.cs.pa.ConnectFour.core.CellLocation;
import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.core.Size;
import it.unicam.cs.pa.ConnectFour.exception.IllegalPieceLocation;
/**
 * @author giacche`
 *
 */
public class DefaultRuleSet implements RuleSet {

	private static final HashMap<Integer, ActionType> allowedActions = new HashMap<>();
	
	public static final Size DEFAULT_SIZE = new Size( 6 , 7 );
	
	public static final String NAME = "DefaultRuleSet";
	
	private final BiFunction<Integer, List<List<Cell>>, Optional<Cell>> destinationCell = ( column , field ) -> field.get(column).stream().filter(Cell::isEmpty).reduce((prev, last) -> last);
	
	public DefaultRuleSet () {
		allowedActions.put(ActionType.INSERT.ordinal(), ActionType.INSERT);
	}

	public HashMap<Integer , ActionType> getAllowedActions() {
		return allowedActions;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#insert(int, it.unicam.cs.pa.ConnectFour.Cell[][])
	 */
	@Override
	public CellLocation getPieceLocation (int column, MatchField field) throws IllegalPieceLocation {
		if(isInBound(column,field.getColumns())) {
			Cell cell = destinationCell.apply(column, field.getField()).orElseThrow(() -> new IllegalPieceLocation(column,field));
			return cell.getLocation();
		}
		throw new IllegalPieceLocation(column,field);
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
			if((celleConsecutive > maxConsecutive) ? celleConsecutive >= 4 : maxConsecutive >= 4) return field.getCellStatus(cell);
		}
		return CellStatus.EMPTY;
	}
}
