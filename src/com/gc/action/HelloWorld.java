package com.gc.action;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class HelloWorld implements InitializingBean,DisposableBean{
	protected String msg;
	protected Date date;
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public Date getDate() {
		return date;
	}

	public abstract void init();
	
	public abstract void customDestroy();
	public void setDate(Date date) {
		this.date = date;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
