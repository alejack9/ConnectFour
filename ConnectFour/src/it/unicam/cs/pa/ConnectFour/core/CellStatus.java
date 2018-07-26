package it.unicam.cs.pa.ConnectFour.core;

/**
 * Represents the status of the cell that could be Empty or contains P1 or P2
 * 
 * @author giacche`
 *
 */
public enum CellStatus {
	P1, P2, EMPTY;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		switch (this) {
		case P1:
			return "X";
		case P2:
			return "O";
		case EMPTY:
			return " ";
		}
		return "";
	}
}
