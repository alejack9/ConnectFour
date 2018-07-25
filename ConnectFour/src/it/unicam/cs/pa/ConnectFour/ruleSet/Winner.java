/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.ruleSet;

import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.exception.UnknownEnumValue;

/**
 * @author Alessandro Giacche`
 *
 */
public enum Winner {
	P1, P2, BOTH, NONE, TIE;

	/**
	 * Convert cellStatus into Winner type
	 * 
	 * @param cellStatus the {@link CellStatus}
	 * @return the associated {@link Winner}
	 * @throws UnknownEnumValue - if the player is not valid
	 */
	public static Winner convert(CellStatus cellStatus) throws UnknownEnumValue {
		switch (cellStatus) {
		case P1:
			return Winner.P1;
		case P2:
			return Winner.P2;
		default:
			throw new UnknownEnumValue(cellStatus);
		}
	}
}
