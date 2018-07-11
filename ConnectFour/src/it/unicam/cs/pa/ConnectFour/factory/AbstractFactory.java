package it.unicam.cs.pa.ConnectFour.factory;

import java.io.InputStream;
import java.io.PrintStream;

import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.piece.Piece;
import it.unicam.cs.pa.ConnectFour.player.Player;
import it.unicam.cs.pa.ConnectFour.player.PlayerType;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSetType;

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
