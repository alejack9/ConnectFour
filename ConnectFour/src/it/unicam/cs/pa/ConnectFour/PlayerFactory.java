package it.unicam.cs.pa.ConnectFour;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * @author giacchè
 *
 */
public class PlayerFactory extends AbstractFactory {

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.factories.AbstractFactory#getPlayer(java.lang.String, java.io.InputStream, java.io.PrintStream)
	 */
	@Override
	public Player getPlayer(PlayerType type , String name, InputStream in, PrintStream out) {
		switch(type) {
		case INTERACTIVE:	return new InteractivePlayer( name , in , out );
		case RANDOM:		return new RandomPlayer( name , in , out );
		default:	throw new IllegalArgumentException("Type '" + type + "' is unknown.");
		}
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.factories.AbstractFactory#getPlayer(java.lang.String)
	 */
	@Override
	public Player getPlayer(PlayerType type , String name) {
		return getPlayer( type, name, System.in , System.out );
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
		return null;
	}


}
