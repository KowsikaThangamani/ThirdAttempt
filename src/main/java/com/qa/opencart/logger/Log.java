package com.qa.opencart.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
	
	private static final Logger logger = LogManager.getLogger(Log.class);
	
	public static void info (String msg) {
		logger.info(msg);
	}
	
	public static void error (String msg) {
		logger.error(msg);
	}
	
	public static void error (String msg, Exception e) {
		logger.error(msg, e);
	}
	
	public static void warn (String msg) {
		logger.warn(msg);
	}
	
	public static void debug (String msg) {
		logger.debug(msg);
	}

}
