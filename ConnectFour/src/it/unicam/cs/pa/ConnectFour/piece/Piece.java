package it.unicam.cs.pa.ConnectFour.piece;

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

	public CellStatus getColor() {
		return this.color;
	}

	public int getId() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.AbstractPiece#isNull()
	 */
	@Override
	public boolean isNull() {
		return false;
	}
}
