/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.exception;

import it.unicam.cs.pa.ConnectFour.core.CellLocation;
import it.unicam.cs.pa.ConnectFour.core.MatchField;

/**
 * Exception that alert that the passed enum value is unknown
 *
 * @author giacche`
 *
 */
public class IllegalPieceLocation extends RuntimeException {

	private CellLocation cellLocation;
	private MatchField field;

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param cellLocation The illegal {@link CellLocation}
	 * @param field        The {@link MatchField}
	 */
	public IllegalPieceLocation(CellLocation cellLocation, MatchField field) {
		super("CellLocation '" + cellLocation + "' in selected field is not valid.");
		this.cellLocation = cellLocation;
		this.field = field;
	}

	/**
	 * Returns the {@link cellLocation}
	 * 
	 * @return The {@link cellLocation}
	 */
	public CellLocation getCellLocation() {
		return cellLocation;
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
