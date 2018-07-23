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
	
	final List<Integer> size;
	
	public Size(Integer[] size) throws IllegalArgumentException {
		if(size.length != 2) throw new IllegalArgumentException("'size' must be an array of two Integer: [ ROWS , COLUMNS ]");
		this.size = List.of(size[0] , size[1]);
	}
	
	public Size(Integer rows , Integer columns ) {
		this(new Integer[] { rows , columns });
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Size(this.size.toArray(new Integer[2]));
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(this == obj) return true;
		if(getClass() != obj.getClass()) return false;
		Size otherSize = (Size)obj;
		if(otherSize.getSize().equals(this.getSize())) return true;		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.size.get(0) + "x" + this.size.get(1);
	}
	
	/**
	 * @return the size
	 */
	public List<Integer> getSize() {
		return this.size;
	}

	public int getRows () {
		return this.size.get(0);
	}
	
	public int getColumns () {
		return this.size.get(1);
	}
}
