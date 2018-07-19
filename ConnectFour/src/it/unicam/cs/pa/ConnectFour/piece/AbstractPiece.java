/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.piece;

import java.util.Optional;

import it.unicam.cs.pa.ConnectFour.core.CellStatus;

/**
 * @author giacchè
 *
 */
// REPORT Null Object pattern to Pieces
public abstract class AbstractPiece {

	public abstract boolean isNull();

	public abstract CellStatus getColor();

	/**
	 * @return The id, if there's any, an empty Optional otherwise
	 */
	public abstract Optional<Integer> getId();

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(this == obj) return true;
		if(this.getClass() != obj.getClass()) return false;
		AbstractPiece otherPiece = (AbstractPiece) obj;
		if( otherPiece.getColor() == this.getColor() &&
			otherPiece.getId().equals(this.getId()) )
				return true;
		
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getColor().toString();
	}

}
