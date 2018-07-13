/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.exception;

/**
 * @author giacchè
 *
 */
public class UnitializedSingleton extends IllegalStateException {

	private static final long serialVersionUID = 1L;
	
	public final String obj;
	
	public UnitializedSingleton(String obj) {
		super("'" + obj + "' must be initialized");
		this.obj = obj;
	}

	public String getId() {
		return this.obj;
	}

}
