package it.unicam.cs.pa.ConnectFour.core;


import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unicam.cs.pa.ConnectFour.exception.UnitializedSingleton;
import it.unicam.cs.pa.ConnectFour.piece.Piece;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;

/**
 * @author giacchè
 *
 */

public final class MatchField {
	//REPORT singleton
	public static final MatchField INSTANCE = new MatchField();
	private boolean initialized;
	private static final UnitializedSingleton e = new UnitializedSingleton("MatchField");
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

	public int getPieces() {
		return this.pieces;
	}

	public static MatchField getInstance() {
		return INSTANCE;
	}
	
	/**
	 * @param size the field size
	 * @throws IllegalArgumentException 'size' is not valid
	 */
	public boolean initMatchField(String size /*, String ruleset*/) throws IllegalArgumentException {
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
	public CellStatus getCellStatus ( int row , int column ) throws UnitializedSingleton {
		checkInit();
		return this.field.get(column).get(row).getStatus();
	}
	
	/**
	 * Inserts a piece in the field
	 * @return true if all's OK, false otherwise
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public boolean insert ( PieceLocation location , Piece piece ) throws UnitializedSingleton {
		checkInit();
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
	public BiFunction<Integer, Integer, CellStatus> getView( RuleSet referee ) throws UnitializedSingleton {
		checkInit();
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
	public List<Cell> getColumn(int column) throws UnitializedSingleton {
		checkInit();
		return field.get(column);
	}
	
	public List<Cell> getRow(int row) throws UnitializedSingleton {
		checkInit();
		List<Cell> toReturn = new ArrayList<>();
		for (List<Cell> column : this.field) {
			toReturn.add(column.get(row));
		}
		return toReturn;	
	}

	// TODO COLLAPSE DIAGONALS (MOST OF ALL THE CELL COLORS)
	
	public List<Cell> getNWDiagonal ( int row , int column ) {
		Cell redCell = findRedCell(row,column);
		Cell blueCell = findBlueCell(row,column);
		
		List<Cell> toReturn = Stream
				.iterate(redCell 
						, (x) -> !x.equals(blueCell) 
						, (x) -> field.get(x.getColumn() + 1).get(x.getRow() + 1))
				.collect(Collectors.toCollection(ArrayList<Cell>::new));
		toReturn.add(blueCell);
		
		return toReturn;
//		List<Cell> toReturn = new ArrayList<>();
//		while(redCell.getColumn() <= blueCell.getColumn() && redCell.getRow() <= blueCell.getRow()) {
//			toReturn.add(redCell);
//			redCell = field.get(redCell.getColumn() + 1).get(redCell.getRow() + 1);
//		}
//		return toReturn;
	}
	
	/**
	 * @param row
	 * @param column
	 * @return
	 */
	private Cell findRedCell(int row, int col) {
		while(row > 0 && col > 0) { row-- ; col--; };
		return field.get(col).get(row);
	}
	
	/**
	 * @param row
	 * @param column
	 * @return
	 */
	private Cell findBlueCell(int row, int col) {
		while(row < getRows() - 1 && col < getColumns() - 1) { row++ ; col++; };
		return field.get(col).get(row);
	}


	public List<Cell> getNEDiagonal ( int row , int column ) {
		Cell purpleCell = findPurpleCell(row,column);
		Cell greenCell = findGreenCell(row,column);
	
		List<Cell> toReturn = Stream
				.iterate(purpleCell 
						, (x) -> !x.equals(greenCell) 
						, (x) -> field.get(x.getColumn() + 1).get(x.getRow() - 1))
				.collect(Collectors.toCollection(ArrayList<Cell>::new));
		toReturn.add(greenCell);
		
		return toReturn;
		/* while(purpleCell.getColumn() <= greenCell.getColumn() && purpleCell.getRow() >= greenCell.getRow()) {
			toReturn.add(purpleCell);
			purpleCell = field.get(purpleCell.getColumn() + 1).get(purpleCell.getRow() - 1); */
		}

	/**
	 * @param row
	 * @param column
	 * @return
	 */
	private Cell findPurpleCell(int row, int col) {
		while(row < getRows() - 1 && col > 0) { row++ ; col--; };
		return field.get(col).get(row);
	}
	
	/**
	 * @param row
	 * @param column
	 * @return
	 */
	private Cell findGreenCell(int row, int col) {
		while(row > 0 && col < getColumns()) { row++ ; col--; };
		return field.get(col).get(row);
	}

	/**
	 * Replace a column with another column
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public void setColumn(List<Cell> newColumn, int column) throws UnitializedSingleton {
		checkInit();
		for(int i = 0 ; i < newColumn.size() ; i++) {
			field.get(column).set(i, newColumn.get(i));
		}
	}

	/**
	 * @return The field as Cells matrix
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public List<List<Cell>> getField() throws UnitializedSingleton {
		checkInit();
		return this.field;
	}

	/**
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public int getRows() throws UnitializedSingleton {
		checkInit();
		return this.size[0];
	}

	/**
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public int getColumns() throws UnitializedSingleton {
		checkInit();
		return this.size[1];
	}

	/**
	 * Makes rows * columns Cells in the field
	 */
	private void fill() {
		for(int i = 0; i < getColumns() ; i++) {
			List<Cell> toInsert = new ArrayList<>();
			for(int j = 0 ; j < getRows(); j++) {
				toInsert.add(new Cell( j , i ));
			}
			field.add(toInsert);
		}
	}
	
	private void checkInit() throws UnitializedSingleton {
		if(!initialized) throw e;
	}
}
