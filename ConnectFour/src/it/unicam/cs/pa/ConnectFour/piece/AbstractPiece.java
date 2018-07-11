/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.piece;

import java.util.Optional;

import it.unicam.cs.pa.ConnectFour.core.CellStatus;

/**
 * @author giacchè
 *
 */
// REPORT Null Object pattern to Pieces
public abstract class AbstractPiece {

	public abstract CellStatus getColor();

	/**
	 * @return The id, if there's any, an empty Optional otherwise
	 */
	public abstract Optional<Integer> getId();

	public abstract boolean isNull();

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getColor().toString();
	}

}
