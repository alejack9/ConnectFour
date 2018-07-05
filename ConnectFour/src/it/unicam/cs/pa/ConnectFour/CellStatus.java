/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

/**
 * @author giacchè
 *
 */
public enum CellStatus {
	P1,
	P2,
	EMPTY;
	
	@Override
	public String toString() {
		switch ( this ) {
			case P1: return "X";
			case P2: return "O";
			case EMPTY: return " ";
		}
		return "";
	}
}
