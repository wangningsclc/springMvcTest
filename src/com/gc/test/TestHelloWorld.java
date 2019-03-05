package com.gc.test;

import com.gc.action.*;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.io.FileNotFoundException;

public class TestHelloWorld {

	static Logger log  = Logger.getLogger(TestHelloWorld.class);
	public static void main(String args[]) throws BeansException, FileNotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		ApplicationContext context = AppContext.getAppliCationContext();
//		context.registerShutdownHook();
//		EnHello hw = (EnHello) context.getBean("EnHello");
//		hw = (Hello) context.getBean("ChHello");
//		System.out.println(hw.doSalutation());
//		Hello hw = new EnHello();
//		BeanWrapper wrapper = new BeanWrapperImpl(hw);
//		wrapper.setPropertyValue("msg", "hello");
//		System.out.println(hw.doSalutation());
//		Object []object = new Object[]{"大人",HelloWorld.sdf.format(new Date())};
//		String msg = context.getMessage("SayHello", object, Locale.CHINA);
//		System.out.println(msg);
		ChHello hello = (ChHello) context.getBean("ChHello");
		System.out.println(hello.getMsg());
		LogProxy logProxy = (LogProxy) context.getBean("logProxy");
		SkyDogSkill skyDogProxy = (SkyDogSkill) logProxy.bind(new SkyDogImpl());
		skyDogProxy.castSkill("飓风");
//		SkyDogSkill skyDog = (SkyDogSkill) context.getBean("logProxyFactory");
//		skyDog.castSkill("飓风");
//		skyDog.awake("大天狗");
//		UserDao userDao = (UserDao) context.getBean("userDao");
//		userDao.insert("");
//		System.out.println(userDao.query());
//		User user = new User();
//		user.setId(9L);
//		user.setName("ji1");
//		user.setSkill("masaka");
//		user.setCreateTime(new Date());
//		UserIbatisDao userDao = (UserIbatisDao) context.getBean("userIbatisDao");
//		System.out.println(userDao.insert(user));
	}
}
