/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.exception;

import it.unicam.cs.pa.ConnectFour.core.MatchField;

/**
 * @author Alessandro Giacche`
 *
 */
public class IllegalColumnException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;
	private int column;
	private MatchField field;
	
	public IllegalColumnException(int column, MatchField field) {
		this.column = column;
		this.field = field;
	}

	/**
	 * @return The column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @return The {@link MatchField}
	 */
	public MatchField getField() {
		return field;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return String.format("Column %d is not valid for field %s (with %d columns)", getColumn(),getField(),getField().getColumns());
	}
}
