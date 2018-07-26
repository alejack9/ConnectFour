/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.exception;

/**
 * @author giacche`
 *
 */
public class UnknownEnumValue extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;
	
	public final Enum<?> e;
	
	public UnknownEnumValue(Enum<?> e) {
		super("Enum Value '" + e.toString() + "' is not valid.");
		this.e = e;
	}

	public Enum<?> getEnum() {
		return this.e;
	}

}
