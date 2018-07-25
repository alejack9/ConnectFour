/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.exception;

import java.util.List;

import it.unicam.cs.pa.ConnectFour.core.Cell;
import it.unicam.cs.pa.ConnectFour.core.CellStatus;

/**
 * @author Alessandro Giacche`
 *
 */
public class IllegalColumnException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;
	private List<Cell> column;
	private CellStatus player;
	
	public IllegalColumnException(List<Cell> column, CellStatus player) {
		this.column = column;
		this.player = player;
	}

	/**
	 * @return the column
	 */
	public List<Cell> getColumn() {
		return column;
	}

	/**
	 * @return the player
	 */
	public CellStatus getPlayer() {
		return player;
	}

}
