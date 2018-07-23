/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.piece;

import java.util.Optional;

import it.unicam.cs.pa.ConnectFour.core.CellStatus;

/**
 * @author giacche`
 *
 */
public class NullPiece extends AbstractPiece {

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.AbstractPiece#isNull()
	 */
	@Override
	public boolean isNull() {
		return true;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.AbstractPiece#getColor()
	 */
	@Override
	public CellStatus getColor() {
		return CellStatus.EMPTY;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.AbstractPiece#getId()
	 */
	@Override
	public Optional<Integer> getId() {
		return Optional.empty();
	}

}
