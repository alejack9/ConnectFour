package it.unicam.cs.pa.ConnectFour.piece;

import java.util.Optional;

import it.unicam.cs.pa.ConnectFour.core.CellStatus;

/**
 * @author giacchè
 *
 */
public class Piece extends AbstractPiece {

	private final int id;
	private final CellStatus color;
	

	public Piece(int id, CellStatus color) {
		this.id = id;
		this.color = color;
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
