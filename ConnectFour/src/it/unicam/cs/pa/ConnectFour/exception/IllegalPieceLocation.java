/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.exception;

import java.util.List;
import java.util.function.Supplier;

import it.unicam.cs.pa.ConnectFour.core.Cell;

/**
 * @author giacchè
 *
 */
public class IllegalPieceLocation extends RuntimeException {

	private int column;
	private List<List<Cell>> field;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalPieceLocation ( int column , List<List<Cell>> field ) {
		super("Column '" + column + "' in selected field is not valid.");
		this.column = column;
		this.field = field;
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @return the field
	 */
	public List<List<Cell>> getField() {
		return field;
	}

}
