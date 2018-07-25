/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.ruleSet;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.Cell;
import it.unicam.cs.pa.ConnectFour.core.CellLocation;
import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.core.Match;
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.core.Size;
import it.unicam.cs.pa.ConnectFour.core.Utils;
import it.unicam.cs.pa.ConnectFour.exception.IllegalColumnException;
import it.unicam.cs.pa.ConnectFour.exception.IllegalPieceLocation;

/**
 * //REPORT Molto simile al Default, e` tuttavia astrattamente impossibile sapere come sono tutte le varianti, si e` optato percio` in un "copia-incolla", anche se una soluzione plausibile sarebbe potuta essre stata insere i metodi di defaultruleset in RuleSet come default.
 * @author Alessandro Giacche`
 *
 */
public class PopOutRuleSet implements RuleSet {

	private static final HashMap<Integer, BiPredicate<List<Cell>,CellStatus>> allowedActions = new HashMap<>();

	private final Function<List<Cell>, Optional<Cell>> destinationCell = ( column ) -> column.stream().filter(Cell::isEmpty).reduce((prev, last) -> last);	
	
	private final BiPredicate<List<Cell>,CellStatus> checkIns = (column, cell) -> destinationCell.apply(column).isPresent();
	private final BiPredicate<List<Cell>,CellStatus> checkPop = (column, cell) -> {
		if(cell != column.get(column.size() - 1).getStatus()) return false;
		return column.parallelStream().filter(x -> !x.isEmpty()).count() > 1;
	};
	
	
	public static final Size DEFAULT_SIZE = new Size( 6 , 7 );
	
	public static final String NAME = "PopOutRuleSet";
	
	public PopOutRuleSet () {
		allowedActions.put(ActionType.INSERT.ordinal(), checkIns);
		allowedActions.put(ActionType.POP.ordinal(), checkPop);
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#getAllowedActions()
	 */
	@Override
	public HashMap<Integer, BiPredicate<List<Cell>,CellStatus>> getAllowedActions() {
		return allowedActions;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#getPieceLocation(int, it.unicam.cs.pa.ConnectFour.core.MatchField)
	 */
	@Override
	public CellLocation insertLocation (int column, MatchField field) throws IllegalColumnException , IllegalPieceLocation {
		if(!isInBound(column,field.getColumns())) throw new IllegalColumnException(column,field);

		return destinationCell.apply(field.getColumn(column))
				.orElseThrow(() -> new IllegalPieceLocation(column, field)).getLocation();
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#pop(int, it.unicam.cs.pa.ConnectFour.core.MatchField)
	 */
	@Override
	public List<Cell> popColumn(int column, MatchField field) throws IllegalColumnException {
		if(!isInBound(column)) throw new IllegalColumnException(column, field);
		List<Cell> toReturn = field.getColumn(column);
		Collections.rotate(toReturn , 1);
		toReturn.get(0).pop();
		return toReturn;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#isInBound(it.unicam.cs.pa.ConnectFour.core.CellLocation)
	 */
	@Override
	public boolean isInBound(CellLocation loc) {
		return isInBound(loc, DEFAULT_SIZE);
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#isInBound(int)
	 */
	@Override
	public boolean isInBound(int column) {
		return isInBound( column , DEFAULT_SIZE.getColumns() );
	}

//	/* (non-Javadoc)
//	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#isValidInsert(int, it.unicam.cs.pa.ConnectFour.core.MatchField)
//	 */
//	@Override
//	public boolean isValidInsert(int column, MatchField field, CellStatus player) {
//		return getAllowedActions().get(ActionType.INSERT.ordinal()).test(field.getColumn(column), player);
//	}
//
//	/* (non-Javadoc)
//	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#isValidPop(int, it.unicam.cs.pa.ConnectFour.core.MatchField)
//	 */
//	@Override
//	public boolean isValidPop(int column, MatchField field, CellStatus player) {
//		return getAllowedActions().get(ActionType.POP.ordinal()).test(field.getColumn(column), player);
//	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#winner(it.unicam.cs.pa.ConnectFour.core.MatchField, it.unicam.cs.pa.ConnectFour.core.CellLocation)
	 */
	@Override
	public Winner winner(MatchField field, CellLocation cellLocation) {
		Winner toReturn = Winner.NONE;
		if(winner(field, cellLocation, field.getCellStatus(cellLocation))) toReturn = Winner.convert(field.getCellStatus(cellLocation));
		if(winner(field, cellLocation, Utils.parsePlayer(Match.otherPlayer(field.getCellStatus(cellLocation).ordinal()))))
			if(toReturn == Winner.NONE) toReturn = Winner.P2;
			else toReturn = Winner.BOTH;
		return toReturn;
	}
	
//	private boolean isValidPop(List<Cell> column, CellStatus player) {
//		if(player != column.get(column.size() - 1).getStatus()) return false;
//		return column.parallelStream().filter(x -> !x.isEmpty()).count() > 1;
//	}

	/**
	 * @param field
	 * @param cell
	 * @param parse
	 */
	private boolean winner(MatchField field, CellLocation cell, CellStatus player) {
		for (Function<CellLocation,List<Cell>> function : field.getListsGetters()) {
			List<Cell> list = function.apply(cell);
			int maxConsecutive = 1 , consecutiveCell = 1;
			for(int i = 0; i < list.size() - 1; i++) {
				if(list.get(i).getStatus() == list.get(i+1).getStatus() && !list.get(i).isEmpty() && list.get(i).getStatus() == field.getCellStatus(cell)) consecutiveCell++;
				else {
					if(consecutiveCell > maxConsecutive) maxConsecutive = consecutiveCell;
					consecutiveCell = 1;
				}
			}
			if((consecutiveCell > maxConsecutive) ? consecutiveCell >= 4 : maxConsecutive >= 4) return true;
		}
		return false;
	}

}
