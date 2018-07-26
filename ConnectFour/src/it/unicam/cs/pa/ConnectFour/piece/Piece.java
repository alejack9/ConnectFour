package it.unicam.cs.pa.ConnectFour.piece;

import java.util.Optional;

import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.exception.IllegalIdValue;

/**
 * @author giacche`
 *
 */
public class Piece extends AbstractPiece {

	private final int id;
	private final CellStatus color;

	/**
	 * @throws IllegalIdValue
	 * @throws IllegalArgumentException Disallowed color
	 */
	/**
	 * @param id    - Must be positive
	 * @param color - {@link CellStatus#EMPTY} is not allowed, use {@link NullPiece}
	 *              instead
	 * @throws IllegalIdValue           - if the id is negative
	 * @throws IllegalArgumentException - if the color is not allowed
	 */
	public Piece(int id, CellStatus color) throws IllegalIdValue, IllegalArgumentException {
		if (id >= 0) {
			this.id = id;
		} else
			throw new IllegalIdValue(id);
		if (color != CellStatus.EMPTY) {
			this.color = color;
		} else
			throw new IllegalArgumentException("'color' not allowed");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.piece.AbstractPiece#isNull()
	 */
	@Override
	public boolean isNull() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.piece.AbstractPiece#getColor()
	 */
	@Override
	public CellStatus getColor() {
		return this.color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.piece.AbstractPiece#getId()
	 */
	public Optional<Integer> getId() {
		return Optional.of(this.id);
	}
}
