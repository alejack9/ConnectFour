package it.unicam.cs.pa.ConnectFour.core;

import it.unicam.cs.pa.ConnectFour.piece.AbstractPiece;
import it.unicam.cs.pa.ConnectFour.piece.NullPiece;
import it.unicam.cs.pa.ConnectFour.piece.Piece;

/**
 * @author giacchè
 *
 */
public class Cell {
	
	private AbstractPiece piece;
	private int row;
	private int column;

	public Cell( int row , int column ) {
		this.row = row;
		this.column = column;
		this.piece = new NullPiece();
	}
	
	public CellStatus getStatus() {
		return piece.getColor();
	}

	/**
	 * @return true if the set is possible, false otherwise
	 */
	public boolean setPiece( AbstractPiece piece ) {
		if (!this.piece.isNull()) {
			return false;
		} else {
			this.piece = piece;
			return true;
		}
	}
	
	/**
	 * @return the {@link Piece}, removing it
	 */
	public AbstractPiece pop () {
		AbstractPiece toReturn = getPiece();
		this.piece = null;
		return toReturn;
	}
	
	public AbstractPiece getPiece() {
		return this.piece;
	}
	
	public boolean isEmpty() {
		return this.piece.isNull();
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return this.row;
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return this.column;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(this == obj) return true;
		if(getClass() != obj.getClass()) return false;
		Cell otherCell = (Cell)obj;
		if(	otherCell.getColumn() == this.getColumn() &&
			otherCell.getRow() == this.getRow() &&
			this.getPiece().equals(otherCell.getPiece())) return true;
		
		return false;
	}
	
	
}
