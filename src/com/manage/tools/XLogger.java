package com.manage.tools;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class XLogger {

	private static Logger log;
	public static Logger getLog(){
		if(log==null){
			log=Logger.getLogger("abc");
			BasicConfigurator.configure ();
		}
		return log;
	}
}
