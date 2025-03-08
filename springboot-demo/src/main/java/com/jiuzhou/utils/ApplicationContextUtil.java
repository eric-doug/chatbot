package com.jiuzhou.utils;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

public class ApplicationContextUtil {

	private static ApplicationContext applicationContext;
	
	private static BeanDefinitionRegistry beanDefinitionRegistry;
	
	private ApplicationContextUtil(){}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
    
    //通过名字获取上下文中的bean
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }
    
    public static void registerBean(String beanId,String className) {
    	BeanDefinitionBuilder beanDefinitionBuilder =
        BeanDefinitionBuilder.genericBeanDefinition(className);
        BeanDefinition beanDefinition=beanDefinitionBuilder.getBeanDefinition();
        beanDefinitionRegistry.registerBeanDefinition(beanId,beanDefinition);
    }
    
    public static void unregisterBean(String beanName){
    	beanDefinitionRegistry.removeBeanDefinition(beanName);
    }
    
    //通过类型获取上下文中的bean
    public static <T> T getBean(Class<T> requiredType){
        return applicationContext.getBean(requiredType);
    }

	@Component
	public static class ApplicationContextAware implements ApplicationListener<ContextRefreshedEvent> {

		@Override
		public void onApplicationEvent(ContextRefreshedEvent event) {
			if (applicationContext == null) {
				applicationContext = event.getApplicationContext();
				ConfigurableApplicationContext configurableContext = (ConfigurableApplicationContext)applicationContext;
				beanDefinitionRegistry = (DefaultListableBeanFactory) configurableContext.getBeanFactory();
			}
		}

	}

}
