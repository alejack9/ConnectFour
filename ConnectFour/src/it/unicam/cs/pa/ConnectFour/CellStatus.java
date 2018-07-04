/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

/**
 * @author giacchè
 *
 */
public enum CellStatus {
	RED,
	YELLOW,
	EMPTY;
	
	@Override
	public String toString() {
		switch ( this ) {
			case RED: return "X";
			case YELLOW: return "O";
			case EMPTY: return " ";
		}
		return "";
	}
}
