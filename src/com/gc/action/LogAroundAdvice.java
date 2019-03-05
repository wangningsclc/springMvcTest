package com.gc.action;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogAroundAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println(invocation.getArguments()[0]+"技能施放预备...");
		Object rval = invocation.proceed();
		System.out.println(invocation.getArguments()[0]+"技能施放结束...");
		return rval;
	}

}
