package it.unicam.cs.pa.ConnectFour.piece;

import java.util.Optional;

import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.exception.IllegalIdValue;

/**
 * @author giacchè
 *
 */
public class Piece extends AbstractPiece {

	private final int id;
	private final CellStatus color;
	

	/**
	 * @param id Must be positive
	 * @param color EMPTY is not allowed, use {@link NullPiece} instead
	 * @throws IllegalIdValue
	 * @throws IllegalArgumentException Disallowed color
	 */
	public Piece(int id, CellStatus color) throws IllegalIdValue , IllegalArgumentException {
		if(id >= 0) {
			this.id = id;
		} else throw new IllegalIdValue(id);
		if(color != CellStatus.EMPTY) {
			this.color = color;
		} else throw new IllegalArgumentException("'color' not allowed");
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.AbstractPiece#getColor()
	 */
	@Override
	public CellStatus getColor() {
		return this.color;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.AbstractPiece#getId()
	 */
	public Optional<Integer> getId() {
		return Optional.of(this.id);
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.AbstractPiece#isNull()
	 */
	@Override
	public boolean isNull() {
		return false;
	}
}
