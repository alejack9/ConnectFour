/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

/**
 * @author giacchè
 *
 */
public class PieceLocation {
	
	private final int row;
	private final int column;
	
	public PieceLocation ( int row , int column ) {
		this.row = row;
		this.column = column;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}
	
}
