package it.unicam.cs.pa.ConnectFour;

/**
 * @author giacchè
 *
 */
public class Cell {
	
	private Piece piece;

	public CellStatus getStatus() {
		return ( this.piece == null ? CellStatus.EMPTY : this.piece.getColor() );
	}

	/**
	 * @return true if the set is possible, false otherwise
	 */
	public boolean setPiece( Piece piece ) {
		if (this.piece != null) {
			return false;
		} else {
			this.piece = piece;
			return true;
		}
	}
	
	/**
	 * @return the {@link Piece}, removing it
	 */
	public Piece pop () {
		Piece toReturn = getPiece();
		this.piece = null;
		return toReturn;
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public boolean isEmpty() {
		return this.piece == null;
	}
	
}
