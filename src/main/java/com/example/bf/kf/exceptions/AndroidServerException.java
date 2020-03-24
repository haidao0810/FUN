package com.example.bf.kf.exceptions;
/**
 * Android服务端异常
 * 
 * 数据放回，或解析异常
 * 
 * @author Chutianfan
 *
 */

public class AndroidServerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AndroidServerException() {
		super();
	}

	public AndroidServerException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public AndroidServerException(String detailMessage) {
		super(detailMessage);
	}

	public AndroidServerException(Throwable throwable) {
		super(throwable);
	}

	
}
