package cn.sunyog.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Author: jerrylee
 * @Date: 2020/9/27 4:52 下午
 * @Desc: 后置处理器Bean
 */
public class PostProcessorBean implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SimpleBean){
            System.out.println(beanName+": 前置处理方法 执行");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof SimpleBean){
            System.out.println(beanName+": 后置处理方法 执行");
        }
        return bean;
    }
}
