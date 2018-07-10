/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author giacchè
 *
 */

public class MatchField {

	/**
	 * first List: columns list
	 * second List: rows list
	 */
	// FIXME should be 'final' removed?
	private final List<List<Cell>> field;
	/**
	 * [0] = rows
	 * [1] = columns
	 */
	private int[] size;
	private int pieces;

	/**
	 * @param size the filed size
	 */
	public MatchField (String size) {
		this.field = new LinkedList<>();
		this.size = Utils.sizeParse(size);
		fill();
	}

	public CellStatus getStatus ( int row , int column ) {
		return this.field.get(column).get(row).getStatus();
	}
	
	/**
	 * Enters a piece in the filed
	 * @param location Piece location
	 * @param piece
	 * @return true if all's OK, false otherwise
	 */
	public boolean insert ( PieceLocation location , Piece piece ) {
		return this.field.get(location.getColumn()).get(location.getRow()).setPiece(piece);
	}
	
	/**
	 * Makes rows x columns Cells in the field
	 */
	private void fill() {
		for(int i = 0; i < getColumns() ; i++) {
			List<Cell> toInsert = new LinkedList<>();
			for(int j = 0 ; j < getRows(); j++) {
				toInsert.add(new Cell());
			}
			field.add(toInsert);
		}
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

	public int getRows() {
		return size[0];
	}
	public int getColumns() {
		return size[1];
	}

	/**
	 * @return the filed as Cells matrix
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

	/**
	 * @param column
	 * @return the column as Cell list
	 */
	public List<Cell> getColumn(int column) {
		return field.get(column);
	}

	/**
	 * Replace a column with another column
	 * @param newColumn
	 * @param column
	 */
	public void setColumn(List<Cell> newColumn, int column) {
		for(int i = 0 ; i < newColumn.size() ; i++) {
			field.get(column).set(i, newColumn.get(i));
		}
	}
	
}
