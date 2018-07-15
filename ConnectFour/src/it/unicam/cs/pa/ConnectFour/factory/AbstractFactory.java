package it.unicam.cs.pa.ConnectFour.factory;

import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.piece.Piece;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSetType;

/**
 * @author giacchè
 *
 */
public interface AbstractFactory {

	/**
	 * @param color Piece color
	 */
	public Piece getPiece(CellStatus color);

	public RuleSet getReferee(RuleSetType ruleset);
	
}
