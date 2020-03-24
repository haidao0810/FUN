package com.example.bf.kf.exceptions;
/**
 * Android服务端异常
 * 
 * 数据放回，或解析异常
 * 
 * @author Chutianfan
 *
 */

public class AndroidSystemException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public AndroidSystemException() {
		super();
	}

	public AndroidSystemException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public AndroidSystemException(String detailMessage) {
		super(detailMessage);
	}

	public AndroidSystemException(Throwable throwable) {
		super(throwable);
	}

	
}
