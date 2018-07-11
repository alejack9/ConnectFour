package it.unicam.cs.pa.ConnectFour.core;

import it.unicam.cs.pa.ConnectFour.piece.AbstractPiece;
import it.unicam.cs.pa.ConnectFour.piece.NullPiece;
import it.unicam.cs.pa.ConnectFour.piece.Piece;

/**
 * @author giacchè
 *
 */
public class Cell {
	
	private AbstractPiece piece;

	public Cell() {
		piece = new NullPiece();
	}
	
	public CellStatus getStatus() {
		return piece.getColor();
	}

	/**
	 * @return true if the set is possible, false otherwise
	 */
	public boolean setPiece( AbstractPiece piece ) {
		if (!this.piece.isNull()) {
			return false;
		} else {
			this.piece = piece;
			return true;
		}
	}
	
	/**
	 * @return the {@link Piece}, removing it
	 */
	public AbstractPiece pop () {
		AbstractPiece toReturn = getPiece();
		this.piece = null;
		return toReturn;
	}
	
	public AbstractPiece getPiece() {
		return this.piece;
	}
	
	public boolean isEmpty() {
		return this.piece.isNull();
	}
	
}
