package it.unicam.cs.pa.ConnectFour.ruleSet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.Cell;
import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.core.PieceLocation;
import it.unicam.cs.pa.ConnectFour.exception.IllegalPieceLocation;
/**
 * @author giacchè
 *
 */
public class DefaultRuleSet implements RuleSet {

	private static final ActionType[] allowedActions = { ActionType.INSERT };

	private final BiFunction<Integer, List<List<Cell>>, Optional<Cell>> involvedCell = ( column , field ) -> field.get(column).stream().filter(Cell::isEmpty).reduce((prev, last) -> last);
	
	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#actionsNumber()
	 */
	@Override
	public int actionsNumber() {
		return allowedActions.length;
	}

	public ActionType[] getAllowedActions() {
		return allowedActions;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#insert(int, it.unicam.cs.pa.ConnectFour.Cell[][])
	 */
	@Override
	public PieceLocation insert(int column, List<List<Cell>> field) throws IllegalPieceLocation {
		// FIXME TO TEST
		if(isInBound(column,field.size())) {
			Cell cell = involvedCell.apply(column, field).orElseThrow(() -> new IllegalPieceLocation(column,field));
			return new PieceLocation(cell.getRow(), cell.getColumn());

			
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
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#isInBound(int)
	 */
	@Override
	public boolean isInBound(int column) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private boolean isInBound(int column, int custumSize) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#isValidInsert(int)
	 */
	@Override
	public boolean isValidInsert(int column , List<List<Cell>> field) {
		return involvedCell.apply(column, field).isPresent();
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
	public CellStatus winner(List<List<Cell>> field) {
		Function<List<List<Cell>>,Stream<List<Cell>>> buildN = (f) -> {
			return f.stream();
		};
		Function<List<List<Cell>>,Stream<List<Cell>>> buildNW = (f) -> {
			List<List<Cell>> toStream = new LinkedList<>();
			
			
			return f.stream();
		};

		
		Stream<List<Cell>> s = Stream.iterate(field., arg1)
		
				
				
				
				
				
				
				
//		for(List<Cell> column : field) {
//
//			for(Cell cell : column)
//			
//			winN(cell,field);
//			winE(cell,field);
//			winNE(cell,field);
//			winNW(cell,field);
//			
//			
//		}
		
		
		
		
		
		
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#getDefaultSize()
	 */
	@Override
	public int[] getDefaultSize() {
		return new int[] { 6 , 7 };
	}

}
