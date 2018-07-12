/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.factory;

import java.io.InputStream;
import java.io.PrintStream;

import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.core.Match;
import it.unicam.cs.pa.ConnectFour.exception.UnknownEnumValue;
import it.unicam.cs.pa.ConnectFour.piece.Piece;
import it.unicam.cs.pa.ConnectFour.player.Player;
import it.unicam.cs.pa.ConnectFour.player.PlayerType;
import it.unicam.cs.pa.ConnectFour.ruleSet.DefaultRuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSetType;

/**
 * @author giacchè
 *
 */
public class RefereeFactory implements AbstractFactory {

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.factories.AbstractFactory#getPlayer(it.unicam.cs.pa.ConnectFour.factories.PlayerType, java.lang.String, java.io.InputStream, java.io.PrintStream)
	 */
	@Override
	public Player getPlayer(PlayerType type, String name, InputStream in, PrintStream out) {
		return null;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.factories.AbstractFactory#getPlayer(it.unicam.cs.pa.ConnectFour.factories.PlayerType, java.lang.String)
	 */
	@Override
	public Player getPlayer(PlayerType type, String name) {
		return null;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.factories.AbstractFactory#getPiece(it.unicam.cs.pa.ConnectFour.CellStatus)
	 */
	@Override
	public Piece getPiece(CellStatus color) {
		return null;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.factories.AbstractFactory#getReferee(it.unicam.cs.pa.ConnectFour.RuleSetType)
	 */
	@Override
	public RuleSet getReferee(RuleSetType ruleset) throws UnknownEnumValue {
		// TODO decomment the others
		switch(ruleset) {
		case DEFAULT:	return new DefaultRuleSet();
	//	case POP:	return new PopRuleSet();
	//	case FIVEINROW:	return new FiveInRowRuleSet();
		default:	throw new UnknownEnumValue(ruleset);
			
		}
	}

}
