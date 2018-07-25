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
