/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

import java.util.function.BiFunction;

/**
 * @author giacchè
 *
 */

public class MatchField {

	private final Cell[][] field;
	private int pieces;
	private AbstractRuleSet referee;

	public MatchField ( AbstractRuleSet referee ) {
		this.referee = referee;
		this.field = new Cell[getRows()][getColums()];
		fill();
	}
	public MatchField(int[] size) {
		this(new DefaultRuleSet(size));
	}
	public MatchField ( ) {
		this( new DefaultRuleSet() );
	}
	
	public CellStatus getStatus ( int row , int column ) {
		return this.field[row][column].getStatus();
	}
	
	/**
	 * @param column
	 * @return
	 */
	public boolean insert ( int column , Piece piece ) {
		if(!referee.isInBound( column )) return false;
		if(this.referee.isValidInsert( this.field, column ) && this.insert( piece, referee.getInsertFun().apply( field , column ))) {
			this.pieces++;
			return true;
		}
		return false;
	}
	
	private boolean insert ( Piece piece , PieceLocation location ) {
		return this.field[location.getRow()][location.getColumn()].setPiece(piece);
	}
	
	private void fill() {
		for( int i=0 ; i < this.getRows() ; i++ )
			for( int j=0 ; j < this.getColums() ; j++ )
				this.field[i][j] = new Cell();
	}
	/**
	 * @return
	 */
	public BiFunction<Integer, Integer, CellStatus> getView() {
		return ( row , column ) -> {
			if ( !referee.isInBound( column ) ) {
				return null;
			}
			return getStatus( row , column );
		};
	}
	
	public boolean isValidAt ( int column ) {
		if(referee.isInBound( column ))
			return referee.isValidInsert(field, column);
		return false;
	}
	
	/**
	 * @return
	 */
	public int getRows() {
		return referee.getRows();
	}
	/**
	 * @return
	 */
	public int getColums() {
		return referee.getColumns();
	}
	
}
