/**
 *
 */
package it.unicam.cs.pa.ConnectFour.ruleSet;

import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.exception.UnknownEnumValue;

/**
 * Winner Enum
 * 
 * @author Alessandro Giacche`
 *
 */
public enum Winner {
	/**
	 * Player1
	 */
	P1,
	/**
	 * Player2
	 */
	P2,
	/**
	 * Both Player
	 */
	BOTH,
	/**
	 * None have won but the Match is end
	 */
	TIE,
	/**
	 * Match is not end
	 */
	NONE;

	/**
	 * Convert cellStatus into Winner type, if possible
	 * 
	 * @param cellStatus The {@link CellStatus}
	 * @return The associated Winner
	 * @throws UnknownEnumValue if the player is not valid
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
