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

	public abstract Player getPlayer(PlayerType type , String name , InputStream in , PrintStream out);
	public abstract Player getPlayer(PlayerType type , String name );
	public abstract Piece getPiece(CellStatus color);
	public abstract RuleSet getReferee(RuleSetType ruleset);
	
}
