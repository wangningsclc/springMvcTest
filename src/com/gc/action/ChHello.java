package com.gc.action;

import com.gc.impl.Hello;

public class ChHello extends HelloWorld implements Hello {

	@Override
	public String doSalutation() {
		return "你好 "+getMsg()+" "+sdf.format(getDate());
	}
	public ChHello(){
		super();
	}
	public ChHello(String msg){
		this.msg = msg;
	}

	public void init() {
		// TODO Auto-generated method stub
//		this.msg = "你好";
//		System.out.println("自定义初始化。。。");
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
//		this.msg = "你好";
//		System.out.println("自动初始化。。。");
	}

	public void destroy() throws Exception {
		// TODO Auto-generated method stub
//		this.msg = null;
//		System.out.println("自动销毁");
	}

	public void customDestroy() {
		// TODO Auto-generated method stub
//		this.msg = null;
//		System.out.println("自定义销毁");
	}
}
