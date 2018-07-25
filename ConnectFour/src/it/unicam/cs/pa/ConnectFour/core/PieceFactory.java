package it.unicam.cs.pa.ConnectFour.core;

import it.unicam.cs.pa.ConnectFour.piece.Piece;

/**
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
	
	public static PieceFactory getIstance() {
		return INSTANCE;
	}
	
	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.factories.AbstractFactory#getPiece(it.unicam.cs.pa.ConnectFour.CellStatus)
	 */
	
	public Piece getPiece(CellStatus color) throws IllegalArgumentException {
		return new Piece(this.id++, color);
	}

	/**
	 * 
	 */
	public void restart() {
		this.id = 0;
	}
}
