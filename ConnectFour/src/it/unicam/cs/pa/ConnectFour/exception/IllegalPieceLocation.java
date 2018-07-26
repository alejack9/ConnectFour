/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.exception;

import it.unicam.cs.pa.ConnectFour.core.CellLocation;
import it.unicam.cs.pa.ConnectFour.core.MatchField;

/**
 * @author giacche`
 *
 */
public class IllegalPieceLocation extends RuntimeException {

	private CellLocation cellLocation;
	private MatchField field;

	private static final long serialVersionUID = 1L;

	public IllegalPieceLocation ( CellLocation cellLocation , MatchField field ) {
		super("CellLocation '" + cellLocation + "' in selected field is not valid.");
		this.cellLocation = cellLocation;
		this.field = field;
	}

	/**
	 * @return The column
	 */
	public CellLocation getCellLocation() {
		return cellLocation;
	}

	/**
	 * @return The {@link MatchField}
	 */
	public MatchField getField() {
		return field;
	}
}
