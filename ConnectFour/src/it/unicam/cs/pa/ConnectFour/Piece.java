/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

/**
 * @author giacchè
 *
 */
public class Piece {

	private final int id;
	private final CellStatus color;
	
	public Piece ( int id , CellStatus color ) {
		this.id = id;
		this.color = color;
	}
	
}
