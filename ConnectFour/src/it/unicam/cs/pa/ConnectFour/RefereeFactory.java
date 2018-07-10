/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * @author giacchè
 *
 */
public class RefereeFactory extends AbstractFactory {

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
	public RuleSet getReferee(RuleSetType ruleset) {
		switch(ruleset) {
		case DEFAULT:	return new DefaultRuleSet();
	//	case POP:	return new PopRuleSet();
	//	case FIVEINROW:	return new FiveInRowRuleSet();
		default:	throw new IllegalArgumentException("'"+ ruleset + "'" + " is unknown.");
			
		}
	}

}
