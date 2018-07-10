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
public class PieceFactory extends AbstractFactory {
	private int id;
	
	public PieceFactory() {
		this.id = 0;
	}
	
	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.factories.AbstractFactory#getPlayer(java.lang.String, java.io.InputStream, java.io.PrintStream)
	 */
	@Override
	public Player getPlayer(PlayerType type , String name, InputStream in, PrintStream out) {
		return null;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.factories.AbstractFactory#getPlayer(java.lang.String)
	 */
	@Override
	public Player getPlayer(PlayerType type , String name) {
		return getPlayer(type , name, null,null);
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.factories.AbstractFactory#getPiece(it.unicam.cs.pa.ConnectFour.CellStatus)
	 */
	@Override
	public Piece getPiece(CellStatus color) {
		return new Piece(this.id++, color);
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.factories.AbstractFactory#getReferee(it.unicam.cs.pa.ConnectFour.RuleSetType)
	 */
	@Override
	public RuleSet getReferee(RuleSetType ruleset) {
		return null;
	}

}
