package com.example.bf.kf.net;

public class AjaxException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AjaxException() {
		super();
	}

	public AjaxException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public AjaxException(String detailMessage) {
		super(detailMessage);
	}

	public AjaxException(Throwable throwable) {
		super(throwable);
	}
	
	
}
