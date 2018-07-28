package it.unicam.cs.pa.ConnectFour.core;

import it.unicam.cs.pa.ConnectFour.piece.Piece;

/**
 * Pieces producer
 * 
 * @author giacche`
 *
 */
public class PieceFactory {
	// REPORT SINGLETON BECASE ID MUST BE DIFFERENT FOR EACH PIECE
	private final static PieceFactory INSTANCE = new PieceFactory();

	private int id;

	private PieceFactory() {
		restart();
	}

	/**
	 * Returns the instance
	 * 
	 * @return The instance
	 */
	public static PieceFactory getIstance() {
		return INSTANCE;
	}

	/**
	 * Returns a piece of a specific 'color'
	 * 
	 * @param color The {@link CellStatus color} of the piece to return
	 * @return A new {@link Piece}
	 * @throws IllegalArgumentException if {@link Piece} cannot be created
	 */
	public Piece getPiece(CellStatus color) throws IllegalArgumentException {
		return new Piece(this.id++, color);
	}

	/**
	 * Restart the PieceFactorys
	 */
	public void restart() {
		this.id = 0;
	}
}
