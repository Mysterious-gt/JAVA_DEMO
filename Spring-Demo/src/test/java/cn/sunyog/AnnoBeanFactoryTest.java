package cn.sunyog;

import cn.sunyog.bean.AnnotationBean;
import cn.sunyog.bean.SimpleBean;
import cn.sunyog.config.AnnoBeanConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: jerrylee
 * @Date: 2020/9/27 3:28 下午
 * @Desc: 基于注解的Bean配置测试
 */
public class AnnoBeanFactoryTest {
    AnnotationConfigApplicationContext context=null;

    @Before
    public void doBefore(){
        this.context=new AnnotationConfigApplicationContext(AnnoBeanConfig.class);
    }

    @Test
    public void testAnnoBean(){
        AnnotationBean bean = (AnnotationBean) context.getBean("Anno-Bean");
        PrintTool.printBeanInfo(bean);
    }

    @Test
    public void testSimpleBean(){
        SimpleBean bean = (SimpleBean) context.getBean("Simple-Bean");
        PrintTool.printBeanInfo(bean);
    }

    @After
    public void doAfter(){
        this.context.registerShutdownHook();
    }

}
