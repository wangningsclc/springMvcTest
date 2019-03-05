package com.gc.action;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogProxy implements InvocationHandler {

	private static Log log = AppContext.getLog();
	private Object delegate;
	
	public Object bind(Object delegate){
		this.delegate = delegate;
		return Proxy.newProxyInstance(delegate.getClass().getClassLoader(), delegate.getClass().getInterfaces(), this);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		try{
			log.log("【"+args[0]+"】飞起来摇动翅膀");
			result = method.invoke(delegate, args);
			log.log("【"+args[0]+"】四段群攻伤害 ");
		} catch(Exception e){
			log.log(e.toString());
		}
		return result;
	}

}
