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
public abstract class AbstractFactory {

	/**
	 * @param type
	 * @param name
	 * @param in
	 * @param out
	 * @return
	 */
	public abstract Player getPlayer(PlayerType type , String name , InputStream in , PrintStream out);
	/**
	 * @param type
	 * @param name
	 * @return
	 */
	public abstract Player getPlayer(PlayerType type , String name );
	/**
	 * @param color
	 * @return
	 */
	public abstract Piece getPiece(CellStatus color);
	/**
	 * @param ruleset
	 * @return
	 */
	public abstract RuleSet getReferee(RuleSetType ruleset);
	
}
