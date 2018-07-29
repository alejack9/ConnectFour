/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.exception;

import it.unicam.cs.pa.ConnectFour.core.MatchField;

/**
 * Exception that alert that the passed column value is not valid
 *
 * @author giacche`
 *
 */
public class IllegalColumnException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;
	private int column;
	private MatchField field;

	/**
	 * Constructor
	 * 
	 * @param column The invalid column
	 * @param field  The {@link MatchField}
	 */
	public IllegalColumnException(int column, MatchField field) {
		super(String.format("Column %d is not valid for field %s (with %d columns)", column, field,
				field.getColumns()));
		this.column = column;
		this.field = field;
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
	 * Returns the {@link MatchField}
	 * 
	 * @return The {@link MatchField}
	 */
	public MatchField getField() {
		return field;
	}
}
