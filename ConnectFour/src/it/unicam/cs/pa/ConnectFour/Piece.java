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
	
	/**
	 * @param color Piece color
	 */
	public Piece(int id, CellStatus color) {
		this.id = id;
		this.color = color;
	}

	/**
	 * @return the color
	 */
	public CellStatus getColor() {
		return this.color;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
}
