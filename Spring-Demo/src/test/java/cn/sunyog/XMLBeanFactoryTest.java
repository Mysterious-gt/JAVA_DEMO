package cn.sunyog;

import cn.sunyog.bean.AutowireBean;
import cn.sunyog.bean.XMLBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: jerrylee
 * @Date: 2020/9/27 11:30 上午
 * @Desc: xml-bean-factory测试类
 */
public class XMLBeanFactoryTest {
    private ClassPathXmlApplicationContext context = null;

    @Before
    public void doBefore() {
        this.context = new ClassPathXmlApplicationContext("/config/beans.xml");
    }

    @After
    public void doAfter() {
        //销毁所有bean
        this.context.registerShutdownHook();
    }

    @Test
    public void defaultBeanTest() {
        XMLBean bean = (XMLBean) this.context.getBean("Bean");
        XMLBean bean2 = (XMLBean) this.context.getBean("Bean");
        PrintTool.printBeanInfo(bean, bean2);
    }

    @Test
    public void singletonBeanTest() {
        XMLBean bean = (XMLBean) this.context.getBean("Singleton-Bean");
        XMLBean bean2 = (XMLBean) this.context.getBean("Singleton-Bean");
        XMLBean bean3 = (XMLBean) this.context.getBean("Bean");

        PrintTool.printBeanInfo(bean, bean2, bean3);
    }

    @Test
    public void consAutowireBeanTest(){
        AutowireBean bean = (AutowireBean) this.context.getBean("cons-bean");
        PrintTool.printBeanInfo(bean);
    }

    @Test
    public void setAutowireBeanTest(){
        AutowireBean bean = (AutowireBean) this.context.getBean("set-bean");
        PrintTool.printBeanInfo(bean);
    }

    @Test
    public void autowireTest(){
        AutowireBean bean1 = (AutowireBean) this.context.getBean("autowire-name-bean");
        AutowireBean bean2 = (AutowireBean) this.context.getBean("autowire-type-bean");
        AutowireBean bean3 = (AutowireBean) this.context.getBean("autowire-cons-bean");
    }
}
