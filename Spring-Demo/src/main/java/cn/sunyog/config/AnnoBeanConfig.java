package cn.sunyog.config;

import cn.sunyog.bean.AnnotationBean;
import cn.sunyog.bean.PostProcessorBean;
import cn.sunyog.bean.SimpleBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * @Author: jerrylee
 * @Date: 2020/9/27 3:10 下午
 * @Desc: 基于注解的方式配置Bean
 */
@Configuration
public class AnnoBeanConfig {
    @Bean
    public BeanPostProcessor getPostProcessorBean() {return new PostProcessorBean();}

    @Bean(name = "Anno-Bean", initMethod = "initFunc", destroyMethod = "destroyFunc")
    @Scope("singleton")//作用域
    @Lazy//懒加载
    public AnnotationBean getAnnotationBean() {
        AnnotationBean bean = new AnnotationBean();
        bean.setMessage("基于注解的配置");
        return bean;
    }

    @Bean(name = "Simple-Bean", initMethod = "initFunc", destroyMethod = "destroyFunc")
    public SimpleBean getSimpleBean() {
        SimpleBean bean = new SimpleBean();
        bean.setMessage("实现初始化回调和销毁回调方法");
        return bean;
    }
}
