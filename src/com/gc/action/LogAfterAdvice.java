package com.gc.action;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class LogAfterAdvice implements AfterReturningAdvice {



	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2,
			Object arg3) throws Throwable {
		System.out.println("结束之后。。。"+arg1.getName());
	}
}
