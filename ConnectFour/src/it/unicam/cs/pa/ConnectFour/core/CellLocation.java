package it.unicam.cs.pa.ConnectFour.core;

/**
 * @author giacchè
 *
 */
public class CellLocation {
	
	private final int row;
	private final int column;
	
	public CellLocation ( int row , int column ) {
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
