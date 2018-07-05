/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

/**
 * @author giacchè
 *
 */
public class MatchField {
	/**
	 * rows,columns
	 */
	public static final int[] DEFAULT_SIZE = { 6 , 7 };
	
	private final Cell[][] field;
	private int[] size;
	private int pieces;
	private RuleSet referee;
	
	public MatchField( int[] size ) {
		this.size = size;
		this.field = new Cell[size[0]][size[1]];
		fill();
	}
	public MatchField( ) {
		this(DEFAULT_SIZE);
	}
	
	public CellStatus getStatus ( int row , int column ) {
		return this.field[row][column].getStatus();
	}
	
	/**
	 * @param column
	 * @return
	 */
	public boolean insert ( int column , Piece piece ) {
		if(this.referee.isValidInsert( column , piece )) {
			this.referee.insert( this.field, column , piece );
			this.pieces++;
			return true;
		}
		return false;
	}
	
	private void fill() {
		for( int i=0 ; i< this.size[0] ; i++ ) {
			for( int j=0 ; j<this.size[1] ; j++ ) {
				this.field[i][j] = new Cell();
			}
		}
	}
	
}
