package com.gc.action;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class LogExceptionAdvice implements ThrowsAdvice{
	
	public void afterThrowing(Method method, Object[] args, Object target, Throwable subclass) throws Exception{
		System.out.println(target.getClass()+"."+method.getName()+"[param:"+args[0]+"]异常抛出..."+subclass.getMessage());
		subclass.printStackTrace();
	}
}
