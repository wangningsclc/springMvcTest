package com.gc.action;

import java.util.Date;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;



public class LogListener implements ApplicationListener {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof LogEvent){
			System.out.println(HelloWorld.sdf.format(new Date())+" 内容:"+ event );
		}
	}

}
