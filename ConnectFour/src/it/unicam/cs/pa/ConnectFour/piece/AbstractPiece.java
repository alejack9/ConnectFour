/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.piece;

import java.util.Optional;

import it.unicam.cs.pa.ConnectFour.core.CellStatus;

/**
 * @author giacche`
 *
 */
// REPORT Null Object pattern to Pieces
public abstract class AbstractPiece {

	/**
	 * @return {@code true} if the piece is {@code null}, {@code false} otherwise
	 */
	public abstract boolean isNull();

	/**
	 * @return the {@link CellStatus piece's color}
	 */
	public abstract CellStatus getColor();

	/**
	 * @return The id, if there's any, an empty {@link Optional} otherwise
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
