package it.unicam.cs.pa.ConnectFour.core;

import it.unicam.cs.pa.ConnectFour.piece.AbstractPiece;
import it.unicam.cs.pa.ConnectFour.piece.NullPiece;
import it.unicam.cs.pa.ConnectFour.piece.Piece;

/**
 * @author giacche`
 *
 */
public class Cell {

	private AbstractPiece piece;
	private CellLocation location;

	/**
	 * @param location - cell location in the {@link MatchField}
	 */
	public Cell(CellLocation location) {
		this.location = location;
		this.piece = new NullPiece();
	}

	/**
	 * @param row
	 * @param column
	 */
	public Cell(int row, int column) {
		this(new CellLocation(row, column));
	}

	/**
	 * @return the {@link CellStatus color} of the piece in the cell
	 */
	public CellStatus getStatus() {
		return piece.getColor();
	}

	/**
	 * @return {@code true} if it is empty, {@code false} otherwise
	 */
	public boolean isEmpty() {
		return this.piece.isNull();
	}

	/**
	 * @return the contained {@link Piece}, removing it
	 */
	public AbstractPiece pop() {
		AbstractPiece toReturn = getPiece();
		this.piece = new NullPiece();
		return toReturn;
	}

	/**
	 * @return the {@link CellLocation}
	 */
	public CellLocation getLocation() {
		return this.location;
	}

	/**
	 * @return the contained {@link AbstractPiece piece}
	 */
	public AbstractPiece getPiece() {
		return this.piece;
	}

	/**
	 * @return {@code true} if the set has been possible, {@code true} otherwise
	 */
	public boolean setPiece(AbstractPiece piece) {
		if (!this.piece.isNull()) {
			return false;
		} else {
			this.piece = piece;
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Cell otherCell = (Cell) obj;
		if (otherCell.getLocation().getColumn() == this.getLocation().getColumn()
				&& otherCell.getLocation().getRow() == this.getLocation().getRow()
				&& this.getPiece().equals(otherCell.getPiece()))
			return true;

		return false;
	}

}
