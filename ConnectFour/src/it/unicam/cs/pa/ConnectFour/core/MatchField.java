package it.unicam.cs.pa.ConnectFour.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import it.unicam.cs.pa.ConnectFour.exception.UnitializedSingleton;
import it.unicam.cs.pa.ConnectFour.piece.Piece;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;

/**
 * @author giacch�
 *
 */

public final class MatchField {
	//REPORT singleton
	public static final MatchField INSTANCE = new MatchField();
	private boolean initialized;
	/**
	 * first List: columns list<br />
	 * second List: rows list
	 */
	private final List<List<Cell>> field;
	/**
	 * [0] = rows<br />
	 * [1] = columns
	 */
	private int[] size;
	private int pieces;

	public static MatchField getInstance() {
		return INSTANCE;
	}
	
	/**
	 * @param size the field size
	 * @throws IllegalArgumentException 'size' is not valid
	 */
	public boolean initMatch(String size /*, String ruleset*/) throws IllegalArgumentException {
		if(!initialized) {
			/**
			 * ArrayList has better 'get' and 'set' than LinkedList, worst 'add' but we don't care
			 */
			this.size = Utils.sizeParse(size);
			fill();
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 */
	private MatchField() {
		this.field = new ArrayList<>();
		this.initialized = false;
	}

	/**
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public CellStatus getCellStatus ( int row , int column ) {
		if(!initialized) throw new UnitializedSingleton("MatchField");
		return this.field.get(column).get(row).getStatus();
	}
	
	/**
	 * Inserts a piece in the field
	 * @return true if all's OK, false otherwise
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public boolean insert ( PieceLocation location , Piece piece ) {
		if(!initialized) throw new UnitializedSingleton("MatchField");
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
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public BiFunction<Integer, Integer, CellStatus> getView( RuleSet referee ) {
		if(!initialized) throw new UnitializedSingleton("MatchField");
		return ( row , column ) -> {
			if ( !referee.isInBound( column ) ) {
				return null;
			}
			return getCellStatus( row , column );
		};
	}

	/**
	 * @return The column as Cell list
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public List<Cell> getColumn(int column) {
		if(!initialized) throw new UnitializedSingleton("MatchField");
		return field.get(column);
	}

	/**
	 * Replace a column with another column
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public void setColumn(List<Cell> newColumn, int column) {
		if(!initialized) throw new UnitializedSingleton("MatchField");
		for(int i = 0 ; i < newColumn.size() ; i++) {
			field.get(column).set(i, newColumn.get(i));
		}
	}

	/**
	 * @return The field as Cells matrix
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public Cell[][] getCells() {
		if(!initialized) throw new UnitializedSingleton("MatchField");
		Cell[][] toReturn = new Cell[getRows()][getColumns()];
		for(int j = 0 ; j < getColumns() ; j++) {
			for(int i = 0 ; i < getRows() ; i++) {
				toReturn[i][j] = field.get(j).get(i);
			}
		}
		return toReturn;
	}

	/**
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public int getRows() {
		if(!initialized) throw new UnitializedSingleton("MatchField");
		return this.size[0];
	}

	/**
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public int getColumns() {
		if(!initialized) throw new UnitializedSingleton("MatchField");
		return this.size[1];
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
