package com.gc.action;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class LogBeforeAdvice  implements MethodBeforeAdvice{

	@Override
	public void before(Method m, Object[] args, Object target)
			throws Throwable {
		System.out.println("开始之前。。。"+target.getClass());
	}

}
