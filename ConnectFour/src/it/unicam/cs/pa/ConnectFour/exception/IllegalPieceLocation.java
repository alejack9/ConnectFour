/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.exception;

import java.util.List;
import java.util.function.Supplier;

import it.unicam.cs.pa.ConnectFour.core.Cell;
import it.unicam.cs.pa.ConnectFour.core.MatchField;

/**
 * @author giacchè
 *
 */
public class IllegalPieceLocation extends RuntimeException {

	private int column;
	private MatchField field;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalPieceLocation ( int column , MatchField field ) {
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
	public MatchField getField() {
		return field;
	}

}
