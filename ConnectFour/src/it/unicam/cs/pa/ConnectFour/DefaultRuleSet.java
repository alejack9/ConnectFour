/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

import java.util.LinkedList;

/**
 * @author giacchè
 *
 */
public class DefaultRuleSet extends AbstractRuleSet {
	
	private static int[] DEFAULT_SIZE = { 6 , 7 };

	public DefaultRuleSet( int[] SIZE ) {
		super(
				( field , column ) -> {
					field = (Cell[][]) field;
					int row = 0;
					while (  row < SIZE[0] && field[row][column].isEmpty() ) {
						row++;
					}
					PieceLocation toReturn = new PieceLocation ( --row , column );
					return toReturn;
				} ,
				SIZE
		);
	}

	public DefaultRuleSet() {
		this(DEFAULT_SIZE);
	}
	
	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.AbstractRuleSet#isValidInsert(int, it.unicam.cs.pa.ConnectFour.Piece)
	 */
	@Override
	public boolean isValidInsert( Cell[][] field, int column ) throws IndexOutOfBoundsException {
		LinkedList<Cell> involvedCells = new LinkedList<>();
		for (Cell[] cells : field)
			involvedCells.add(cells[column]);
		return involvedCells.stream().anyMatch(Cell::isEmpty);
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.AbstractRuleSet#isInBound(int)
	 */
	@Override
	public boolean isInBound( int column ) {
		if( column >= 0 && column < getColumns() ) return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.AbstractRuleSet#getSize()
	 */
	@Override
	public int[] getDefaultSize() {
		return DEFAULT_SIZE;
	}

}
