/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

import java.util.function.BiFunction;

/**
 * @author giacchè
 *
 */

// FIXME should each game rule have a specific size or is it better to allow the user to choose the match's size?

public class MatchField {

	private final Cell[][] field;
	private int[] size;
	private int pieces;
	private AbstractRuleSet referee;

	public MatchField ( int[] size , AbstractRuleSet referee ) {
		this.size = size;
		this.referee = referee;
		this.field = new Cell[getRows()][getColums()];
		fill();
	}
	public MatchField ( int[] size ) {
		this( size , new DefaultRuleSet() );
	}
	public MatchField ( AbstractRuleSet referee ) {
		this( referee.getDefaultSize() , referee );
	}
	public MatchField ( ) {
		this(new DefaultRuleSet());
	}
	
	public CellStatus getStatus ( int row , int column ) {
		return this.field[row][column].getStatus();
	}
	
	/**
	 * @param column
	 * @return
	 */
	public boolean insert ( int column , Piece piece ) {
		if(this.referee.isValidInsert( this.field, column )) {
			this.insert(column, piece, referee.getInsertFun());
			this.pieces++;
			return true;
		}
		return false;
	}
	
	private void insert ( int column, Piece piece, BiFunction<Cell[][],Integer,PieceLocation> insertFun) {
		// FIXME use insertFun to get the location, than add the piece to the cell.
	}
	
	private void fill() {
		for( int i=0 ; i< this.size[0] ; i++ ) {
			for( int j=0 ; j<this.size[1] ; j++ ) {
				this.field[i][j] = new Cell();
			}
		}
	}
	/**
	 * @return
	 */
	public BiFunction<Integer, Integer, CellStatus> getView() {
		return (x,y) -> {
			if (!referee.isInBound(y)) {
				return null;
			}
			return getStatus(x, y);
		};
	}
	/**
	 * @return
	 */
	public int getRows() {
		return size[0];
	}
	/**
	 * @return
	 */
	public int getColums() {
		return size[1];
	}
	
}
