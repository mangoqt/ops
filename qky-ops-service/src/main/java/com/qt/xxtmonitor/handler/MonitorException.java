package com.qt.xxtmonitor.handler;

/**
 * @author Tim
 *
 */
public class MonitorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5887414129917179629L;

	/**
	 * 
	 */
	public MonitorException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MonitorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public MonitorException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MonitorException(Throwable cause) {
		super(cause);
	}

}
