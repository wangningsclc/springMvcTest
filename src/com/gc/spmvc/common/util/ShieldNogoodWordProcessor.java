package com.gc.spmvc.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionVisitor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Created by wn on 2018/5/15.
 */
public class ShieldNogoodWordProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String [] beanNames = beanFactory.getBeanDefinitionNames();
        for(String beanName: beanNames){
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            BeanDefinitionVisitor visitor = new BeanDefinitionVisitor(str->{
                if(str.equals("宇宙")){
                    return "aaaaa";
                }else{
                    return str;
                }
            }
            );
            visitor.visitBeanDefinition(beanDefinition);
        }
    }
}
