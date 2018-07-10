package it.unicam.cs.pa.ConnectFour;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * @author giacchè
 *
 */
public abstract class AbstractFactory {

	/**
	 * @param name Player' name
	 */
	public abstract Player getPlayer(PlayerType type , String name , InputStream in , PrintStream out);
	/**
	 * @param name Player' name
	 */
	public abstract Player getPlayer(PlayerType type , String name );
	/**
	 * @param color Piece color
	 */
	public abstract Piece getPiece(CellStatus color);

	public abstract RuleSet getReferee(RuleSetType ruleset);
	
}
