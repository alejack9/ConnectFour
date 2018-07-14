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
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.core.PieceLocation;
import it.unicam.cs.pa.ConnectFour.exception.IllegalPieceLocation;
/**
 * @author giacchè
 *
 */
public class DefaultRuleSet implements RuleSet {

	private static final ActionType[] allowedActions = { ActionType.INSERT };

	private final BiFunction<Integer, List<List<Cell>>, Optional<Cell>> destinationCell = ( column , field ) -> field.get(column).stream().filter(Cell::isEmpty).reduce((prev, last) -> last);
	
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
	public PieceLocation insert(int column, MatchField field) throws IllegalPieceLocation {
		// FIXME TO TEST
		if(isInBound(column,field.getColumns())) {
			Cell cell = destinationCell.apply(column, field.getField()).orElseThrow(() -> new IllegalPieceLocation(column,field));
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
	public boolean isValidInsert(int column , MatchField field) {
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
	public CellStatus winner(MatchField field) {
		// TODO TO FINISH
		if(field.getPieces() < 8) return CellStatus.EMPTY;
		
		field.get
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		Function<List<List<Cell>>,Stream<List<Cell>>> buildN = (f) -> {
			return f.stream();
		};
//		Function<List<List<Cell>>,Stream<List<Cell>>> buildNW = (f) -> {
//			
//            i = posizione[0] - 1;
//            int k = posizione[1] - 1;
//
//            while (darifare && i >= 0 && k >= 0)
//            {
//                switch (griglia[i, k])
//                {
//                    case 0:
//                        darifare = false;
//                        dacambiare = false;
//                        break;
//                    case 1:
//                        darifare = false;
//                        dacambiare = true;
//                        break;
//                    case 2:
//                        darifare = true;
//                        dacambiare = false;
//                        break;
//                }
//                i--;
//                k--;
//            }
//			List<List<Cell>> toStream = new LinkedList<>();
//			
//			for( int i = 0 ; i < field.size() ; i++ ) {
//				toStream.add(List.of(f.get(i).get(i)));
//			}
//			
//			
//			return f.stream();
//		};
		
		Function<List<List<Cell>>,Stream<List<Cell>>> buildNW = (f) -> {
			List<List<Cell>> toStream = new LinkedList<>();
			for (int i = 0; i < field.size(); i++) {
				LinkedList<Cell> newDiagonal = new LinkedList<>();
				for (int j = 0; j < field.get(0).size(); j++) {
					newDiagonal.add(f.get(i).get(j));
				}
				toStream.add(newDiagonal);
			}
			return toStream.stream();
		};

		
		Stream<List<Cell>> s = Stream.iterate(field., arg1);
		
				
				
				
				
				
				
				
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
