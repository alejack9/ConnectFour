/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.exception;

/**
 * Exception that alert that the singleton is not initialized
 * 
 * @author giacche`
 *
 */
public class UnitializedSingleton extends IllegalStateException {

	private static final long serialVersionUID = 1L;

	public final String obj;

	public UnitializedSingleton(String obj) {
		super("'" + obj.getClass().getName() + "' must be initialized");
		this.obj = obj;
	}

	public String getObj() {
		return this.obj;
	}

}
