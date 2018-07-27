/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.piece;

import java.util.Optional;

import it.unicam.cs.pa.ConnectFour.core.CellStatus;

/**
 * Represents a null piece, used "NullObject Pattern"
 * 
 * @author giacche`
 *
 */
public class NullPiece extends AbstractPiece {

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.piece.AbstractPiece#isNull()
	 */
	@Override
	public boolean isNull() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.piece.AbstractPiece#getColor()
	 */
	@Override
	public CellStatus getColor() {
		return CellStatus.EMPTY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.piece.AbstractPiece#getId()
	 */
	@Override
	public Optional<Integer> getId() {
		return Optional.empty();
	}
}
