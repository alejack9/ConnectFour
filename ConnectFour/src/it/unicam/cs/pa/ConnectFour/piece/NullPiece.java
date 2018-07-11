/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.piece;

import it.unicam.cs.pa.ConnectFour.core.CellStatus;

/**
 * @author giacchè
 *
 */
public class NullPiece extends AbstractPiece {

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.AbstractPiece#getColor()
	 */
	@Override
	public CellStatus getColor() {
		return CellStatus.EMPTY;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.AbstractPiece#getId()
	 */
	@Override
	public int getId() {
		return -1;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.AbstractPiece#isNull()
	 */
	@Override
	public boolean isNull() {
		return true;
	}

}
