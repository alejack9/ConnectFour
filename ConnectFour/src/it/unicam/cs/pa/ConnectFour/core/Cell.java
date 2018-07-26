package it.unicam.cs.pa.ConnectFour.core;

import it.unicam.cs.pa.ConnectFour.piece.AbstractPiece;
import it.unicam.cs.pa.ConnectFour.piece.NullPiece;
import it.unicam.cs.pa.ConnectFour.piece.Piece;

/**
 * Represents a case of AbstractPiece with coordinates
 * 
 * @author giacche`
 *
 */
public class Cell {

	private AbstractPiece piece;
	private CellLocation location;

	/**
	 * Constructor
	 * 
	 * @param location Cell location in the {@link MatchField}
	 */
	public Cell(CellLocation location) {
		this.location = location;
		this.piece = new NullPiece();
	}

	/**
	 * Constructor
	 */
	public Cell(int row, int column) {
		this(new CellLocation(row, column));
	}

	/**
	 * Returns the {@link CellStatus color} of the piece in the cell
	 * 
	 * @return The {@link CellStatus color} of the piece in the cell
	 */
	public CellStatus getStatus() {
		return piece.getColor();
	}

	/**
	 * Returns {@code true} if it is empty, {@code false} otherwise
	 * 
	 * @return {@code True} if it is empty, {@code false} otherwise
	 */
	public boolean isEmpty() {
		return this.piece.isNull();
	}

	/**
	 * Returns the contained {@link Piece}, removing it
	 * 
	 * @return The contained {@link Piece}, removing it
	 */
	public AbstractPiece pop() {
		AbstractPiece toReturn = getPiece();
		this.piece = new NullPiece();
		return toReturn;
	}

	/**
	 * Returns the {@link CellLocation}
	 *
	 * @return The {@link CellLocation}
	 */
	public CellLocation getLocation() {
		return this.location;
	}

	/**
	 * Returns the contained {@link AbstractPiece piece}
	 * 
	 * @return The contained {@link AbstractPiece piece}
	 */
	public AbstractPiece getPiece() {
		return this.piece;
	}

	/**
	 * Replace a {@link Piece} with another one, if possible
	 *
	 * @return {@code True} if the set has been possible, {@code false} otherwise
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
