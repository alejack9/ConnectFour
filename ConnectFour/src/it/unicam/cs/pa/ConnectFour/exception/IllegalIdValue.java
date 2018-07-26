/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.exception;

/**
 * @author giacche`
 *
 */
public class IllegalIdValue extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;
	
	public final int id;
	
	public IllegalIdValue(int id) {
		super("'id' value '" + id + "' is not allowed");
		this.id = id;
	}

	/**
	 * @return The id
	 */
	public int getId() {
		return this.id;
	}
}
