package com.gc.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class AppContext {

	private static ApplicationContext applicationContext = null;

	static {
//		applicationContext =  new FileSystemXmlApplicationContext("web/config/config.xml");
	}
	public static ApplicationContext getAppliCationContext() {
		if(applicationContext == null){
			applicationContext =  new FileSystemXmlApplicationContext("springMvcTest/web/config/config.xml");
		}
		return applicationContext;
	}
	
	public static Log getLog(){
		Log log = (Log) getAppliCationContext().getBean("log");
		return log;
	}
	
}
