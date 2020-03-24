package com.example.bf.kf.log;


public final class LogManager {

    private static Logger logger = new LoggerDefault();

    public static String TAG = "【Seven's LOG】";


    private static boolean DEBUG = true;
    
    public static boolean i = true;
    public static boolean v = true;
	public static boolean e = true;
	public static boolean d = true;
	public static boolean w = true;
    
    public static void setLogger(Logger newLogger) {
        logger = newLogger;
    }

    public static Logger getLogger() {
        return logger;
    }

	public static boolean isDEBUG() {
		return DEBUG;
	}

	public static void setDEBUG(boolean dEBUG) {
		DEBUG = dEBUG;
	}

    
}
