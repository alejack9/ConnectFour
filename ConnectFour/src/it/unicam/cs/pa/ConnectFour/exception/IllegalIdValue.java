/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.exception;

/**
 * Exception that alert that the passed id value is not valid
 * 
 * @author giacche`
 *
 */
public class IllegalIdValue extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public final int id;

	/**
	 * Constructor
	 * 
	 * @param id The invalid id
	 */
	public IllegalIdValue(int id) {
		super("'id' value '" + id + "' is not allowed");
		this.id = id;
	}

	/**
	 * Returns the id
	 * 
	 * @return The id
	 */
	public int getId() {
		return this.id;
	}
}
