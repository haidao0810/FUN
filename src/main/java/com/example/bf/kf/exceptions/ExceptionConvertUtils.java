package com.example.bf.kf.exceptions;

public class ExceptionConvertUtils {

	public static void change2ServerExcetion(final Exception e) throws AndroidServerException{
		e.printStackTrace();
		throw new AndroidServerException(e);
	}
	
	public static void change2CacheExcetion(final Exception e) throws AndroidCacheException{
		e.printStackTrace();
		throw new AndroidCacheException(e);
	}
	
	public static void change2HttpException(final Exception e) throws AndroidHttpException{
		e.printStackTrace();
		throw new AndroidHttpException(e);
	}
	
	public static void change2HttpException(final Error e) throws AndroidHttpException{
		e.printStackTrace();
		throw new AndroidHttpException(e);
	}
	
}
