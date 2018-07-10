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

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	
}
