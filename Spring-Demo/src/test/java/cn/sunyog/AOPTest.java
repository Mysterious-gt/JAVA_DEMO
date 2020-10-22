package cn.sunyog;

import cn.sunyog.aop.AopTestService;
import cn.sunyog.config.AopConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: jerrylee
 * @Date: 2020/10/21 3:40 下午
 * @Desc: aop测试类
 */
public class AOPTest {
    private ApplicationContext ctx;

    @Before
    public void doBefore(){
        ctx=new AnnotationConfigApplicationContext(AopConfig.class);
    }

    @After
    public void doAfter(){

    }

    @Test
    public void test(){
        AopTestService service = ctx.getBean(AopTestService.class);
        service.test();
        System.out.println();
        service.testThrowable();
    }
}
