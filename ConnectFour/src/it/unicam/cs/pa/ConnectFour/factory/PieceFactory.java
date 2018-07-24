package it.unicam.cs.pa.ConnectFour.factory;

import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.piece.Piece;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSetType;

/**
 * @author giacche`
 *
 */
public class PieceFactory {
	
	private final static PieceFactory INSTANCE = new PieceFactory();
	
	private int id;
	
	private PieceFactory() {
		this.id = 0;
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
//
//	/* (non-Javadoc)
//	 * @see it.unicam.cs.pa.ConnectFour.factories.AbstractFactory#getReferee(it.unicam.cs.pa.ConnectFour.RuleSetType)
//	 */
//	@Override
//	public RuleSet getReferee(RuleSetType ruleset) {
//		return null;
//	}

}
