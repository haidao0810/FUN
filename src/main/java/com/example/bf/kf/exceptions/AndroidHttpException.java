package com.example.bf.kf.exceptions;

public class AndroidHttpException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AndroidHttpException() {
		super();
	}

	public AndroidHttpException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public AndroidHttpException(String detailMessage) {
		super(detailMessage);
	}

	public AndroidHttpException(Throwable throwable) {
		super(throwable);
	}
	
	
}
