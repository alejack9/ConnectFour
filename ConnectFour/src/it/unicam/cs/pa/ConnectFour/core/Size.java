/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.core;

import java.util.List;

/**
 * @author Alessandro Giacche`
 *
 */
public class Size {

	final List<Integer> sizes;

	/**
	 * @param sizes - The sizes array of length of 2 and with values bigger than 1
	 * @throws IllegalArgumentException if the above mentioned clause are not
	 *                                  respected
	 */
	public Size(Integer[] sizes) throws IllegalArgumentException {
		if (sizes.length != 2)
			throw new IllegalArgumentException("'sizes' must be an array of two Integer: [ ROWS , COLUMNS ]");
		this.sizes = List.of(sizes[0], sizes[1]);
		if (this.sizes.stream().anyMatch(x -> x <= 1))
			throw new IllegalArgumentException("'sizes' must have values bigger than 1");
	}

	/**
	 * @param rows    - The row size bigger than 1
	 * @param columns - The column size bigger than 1
	 * @throws IllegalArgumentException if the above mentioned clause are not
	 *                                  respected
	 */
	public Size(Integer rows, Integer columns) throws IllegalArgumentException {
		this(new Integer[] { rows, columns });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Size(this.sizes.toArray(new Integer[2]));
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
		Size otherSize = (Size) obj;
		if (otherSize.getSize().equals(this.getSize()))
			return true;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.sizes.get(0) + "x" + this.sizes.get(1);
	}

	/**
	 * @return The size
	 */
	public List<Integer> getSize() {
		return this.sizes;
	}

	/**
	 * @return The rows
	 */
	public int getRows() {
		return this.sizes.get(0);
	}

	/**
	 * @return the columns
	 */
	public int getColumns() {
		return this.sizes.get(1);
	}
}
