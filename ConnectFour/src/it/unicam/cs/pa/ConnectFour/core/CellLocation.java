package it.unicam.cs.pa.ConnectFour.core;

/**
 * @author giacche`
 *
 */
public class CellLocation {

	private final int row;
	private final int column;

	/**
	 * @param row
	 * @param column
	 */
	public CellLocation(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * @return The column
	 */
	public int getColumn() {
		return column;
	}

	/**
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
