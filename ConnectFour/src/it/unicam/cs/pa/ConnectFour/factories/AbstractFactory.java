/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.factories;

import java.io.InputStream;
import java.io.PrintStream;

import it.unicam.cs.pa.ConnectFour.CellStatus;
import it.unicam.cs.pa.ConnectFour.Piece;
import it.unicam.cs.pa.ConnectFour.Player;

/**
 * @author giacchè
 *
 */
public abstract class AbstractFactory {

	abstract Player getPlayer(PlayerType type , String name , InputStream in , PrintStream out);
	abstract Player getPlayer(PlayerType type , String name );
	abstract Piece getPiece(CellStatus color);
	
}
