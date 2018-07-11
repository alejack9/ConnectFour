package it.unicam.cs.pa.ConnectFour;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author giacchè
 *
 */

public class MatchField {

	/**
	 * first List: columns list<br />
	 * second List: rows list
	 */
	// FIXME should be 'final' removed?
	private final List<List<Cell>> field;
	/**
	 * [0] = rows<br />
	 * [1] = columns
	 */
	private int[] size;
	private int pieces;

	/**
	 * @param size the filed size
	 */
	public MatchField (String size) {
		/**
		 * better get and set than LinkedList, worst add but we don't care
		 */
		this.field = new ArrayList<>();
		this.size = Utils.sizeParse(size);
		fill();
	}

	public CellStatus getStatus ( int row , int column ) {
		return this.field.get(column).get(row).getStatus();
	}
	
	/**
	 * Inserts a piece in the filed
	 * @return true if all's OK, false otherwise
	 */
	public boolean insert ( PieceLocation location , Piece piece ) {
		if(pieces < getColumns() * getRows()) {
			if(this.field.get(location.getColumn()).get(location.getRow()).setPiece(piece)) {
				pieces++;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return The viewer BiFunction
	 */
	public BiFunction<Integer, Integer, CellStatus> getView(RuleSet referee) {
		return ( row , column ) -> {
			if ( !referee.isInBound( column ) ) {
				return null;
			}
			return getStatus( row , column );
		};
	}

	/**
	 * @return The column as Cell list
	 */
	public List<Cell> getColumn(int column) {
		return field.get(column);
	}

	/**
	 * Replace a column with another column
	 */
	public void setColumn(List<Cell> newColumn, int column) {
		for(int i = 0 ; i < newColumn.size() ; i++) {
			field.get(column).set(i, newColumn.get(i));
		}
	}

	/**
	 * @return The field as Cells matrix
	 */
	public Cell[][] getCells() {
		Cell[][] toReturn = new Cell[getRows()][getColumns()];
		for(int j = 0 ; j < getColumns() ; j++) {
			for(int i = 0 ; i < getRows() ; i++) {
				toReturn[i][j] = field.get(j).get(i);
			}
		}
		return toReturn;
	}

	public int getRows() {
		return size[0];
	}

	public int getColumns() {
		return size[1];
	}

	/**
	 * Makes rows * columns Cells in the field
	 */
	private void fill() {
		for(int i = 0; i < getColumns() ; i++) {
			List<Cell> toInsert = new ArrayList<>();
			for(int j = 0 ; j < getRows(); j++) {
				toInsert.add(new Cell());
			}
			field.add(toInsert);
		}
	}
	
}
