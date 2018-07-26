/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.exception;

/**
 * Exception that alert that an internal exception is occurred
 *
 * @author giacche`
 *
 */
public class InternalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InternalException(Throwable e) {
		super(e);
	}

}
