package com.gc.action;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Log  implements ApplicationContextAware{

	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationcontext)
			throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext  = applicationcontext;
	}

	public int log(String log){
		LogEvent logEvent = new LogEvent(log);
		this.applicationContext.publishEvent(logEvent);
		return 0;
	}
}
