/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

/**
 * @author giacchè
 *
 */
public class Cell {
	
	private Piece piece;

	public Cell() {
	}

	public CellStatus getStatus() {
		return ( this.piece == null ? CellStatus.EMPTY : this.piece.getColor() );
	}

	public boolean setPiece( Piece piece ) {
		if (this.piece != null) {
			return false;
		} else {
			this.piece = piece;
			return true;
		}
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public boolean isEmpty() {
		return this.piece == null;
	}
	
}
