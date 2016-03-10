package com.parkingmanage.tools;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Log {
	private static Logger log;
	public static Logger getLog(String name){
		if(log==null){
			log=Logger.getLogger("abc");
			BasicConfigurator.configure ();
		}
		return log;
	}
}
