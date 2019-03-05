package com.gc.action;

import java.util.Date;

import com.gc.impl.Hello;

public class EnHello extends HelloWorld implements Hello {

	private Sama sama;
	
	private Date createTime;
	
	
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Sama getSama() {
		return sama;
	}

	public void setSama(Sama sama) {
		this.sama = sama;
	}

	@Override
	public String doSalutation() {
		return "hello "+getMsg()+" "+getDate();
	}

	public EnHello (){
		super();
	}

	public EnHello(String msg){
		this.msg = msg;
	}

	public EnHello(Date createTime, Date date){
		this.createTime = createTime;
		this.date = date;
	}

	public void init() {
//		System.out.println("init....");
//		this.msg = "jim";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
//		System.out.println("afterPropertiesSet....");
//		this.msg = "green";
	}

	@Override
	public void destroy() throws Exception {
//		this.msg = null;
//		System.out.println("destroy");
	}


	public void customDestroy() {
//		this.msg = null;
//		System.out.println("customDestroy");
	}


}
