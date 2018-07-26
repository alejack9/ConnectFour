package it.unicam.cs.pa.ConnectFour.core;

/**
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
