package cn.sunyog;

import cn.sunyog.config.SimpleConfig;
import cn.sunyog.entity.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: jerrylee
 * @Date: 2020/10/19 10:08 上午
 * @Desc: 注解配置测试类
 */
public class AnnoConfigTest {
    private AnnotationConfigApplicationContext ctx;

    @Before
    public void doBefore(){
        ctx=new AnnotationConfigApplicationContext();
    }

    @After
    public void doAfter(){
        ctx.registerShutdownHook();
    }

    @Test
    public void test01(){
        ctx.register(SimpleConfig.class);
        ctx.refresh();
        Student student = ctx.getBean(Student.class);
        System.out.println(student);
    }
}
