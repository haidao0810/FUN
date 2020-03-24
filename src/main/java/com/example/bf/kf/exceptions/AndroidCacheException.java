package com.example.bf.kf.exceptions;

/**
 * Android缓存异常
 * 
 * 注意在程序中，即使发生此异常也不会产生影响，不放到上层处理
 * 
 * @author Chutianfan
 *
 */
public class AndroidCacheException extends Exception {

	/**
	 */
	private static final long serialVersionUID = 1L;

	public AndroidCacheException() {
		super();
	}

	public AndroidCacheException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public AndroidCacheException(String detailMessage) {
		super(detailMessage);
	}

	public AndroidCacheException(Throwable throwable) {
		super(throwable);
	}



	
}
