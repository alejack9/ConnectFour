package it.unicam.cs.pa.ConnectFour.core;

/**
 * Represents the cell coordinates
 * 
 * @author giacche`
 *
 */
public class CellLocation {

	private final int row;
	private final int column;

	/**
	 * Constructor
	 */
	public CellLocation(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * Returns the column
	 * 
	 * @return The column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Returns the row
	 * 
	 * @return The row
	 */
	public int getRow() {
		return row;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("<%d,%d>", getRow(), getColumn());
	}
}
