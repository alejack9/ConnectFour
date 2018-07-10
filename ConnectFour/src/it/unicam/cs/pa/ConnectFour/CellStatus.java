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
	
	/**
	 * @param p must be 0 or 1
	 * @return the relative player
	 * @throws IllegalArgumentException if p is not 0 or 1
	 */
	public static CellStatus parse( int p ) throws IllegalArgumentException {
		switch ( p ) {
			case 0: return CellStatus.P1;
			case 1: return CellStatus.P2;
			default: 
				throw new IllegalArgumentException("'p' must be 0 or 1, '" + p + "' is not allowed.");
		}
	}
}
