package com.gc.test;

import com.gc.action.ChHello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wn on 2018/3/30.
 */

public class SpringTest {
    public static void main(String[] args) {
        try{
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-mvc.xml");
//            UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
//            System.out.println(userService.sayHello());
            ChHello hello = (ChHello) applicationContext.getBean("ChHello");
            System.out.println(hello.getMsg());

        }catch (Exception e){
            /*
            java8 与spring3 兼容问题
            Note that the Java 8 bytecode level (-target 1.8, as required by -source 1.8) is only fully supported as of Spring Framework 4.0.
            In particular, Spring 3.2 based applications need to be compiled with a maximum of Java 7 as the target,
            even if they happen to be deployed onto a Java 8 runtime. Please upgrade to Spring 4 for Java 8 based applications.
             */
            e.printStackTrace();
        }

//        File file = new File(path);
//        System.out.println(file.getPath());
    }

}
