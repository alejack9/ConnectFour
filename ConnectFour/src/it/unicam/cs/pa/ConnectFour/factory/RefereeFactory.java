/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.factory;

import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.exception.UnknownEnumValue;
import it.unicam.cs.pa.ConnectFour.piece.Piece;
import it.unicam.cs.pa.ConnectFour.ruleSet.DefaultRuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSetType;

/**
 * @author giacchè
 *
 */
public class RefereeFactory implements AbstractFactory {

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.factories.AbstractFactory#getPiece(it.unicam.cs.pa.ConnectFour.CellStatus)
	 */
	@Override
	public Piece getPiece(CellStatus color) throws IllegalArgumentException {
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
		case POP:	return null;
		case FIVEINROW:	return null;
		default:	throw new UnknownEnumValue(ruleset);
			
		}
	}

}
