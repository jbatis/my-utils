/**
 * 
 */
package com.moustercorp.exceptions;

/**
 * @author jbatis
 */
public class InitializationException extends Exception {

	/**
	 * serialVersionUID = -663972942750774261L
	 */
	private static final long serialVersionUID = -663972942750774261L;

	/**
	 * 
	 */
	public InitializationException() {
		super();
	}

	/**
	 * @param message
	 */
	public InitializationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public InitializationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InitializationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InitializationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
