package cn.sunyog.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Author: jerrylee
 * @Date: 2020/9/27 4:35 下午
 * @Desc: 实现接口的Bean
 */
public class SimpleBean extends AnnotationBean implements InitializingBean, DisposableBean {
    public void destroy() throws Exception {
        System.out.println("实现 DisposableBean 接口的 销毁回调 方法");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("实现 InitializingBean 接口的 初始化回调 方法");
    }
}
