package cn.sunyog;

import cn.sunyog.config.SimpleConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: jerrylee
 * @Date: 2020/10/19 11:31 上午
 * @Desc: 容器事件测试类
 */
public class ContextEventTest {
    private ConfigurableApplicationContext ctx;

    @Before
    public void doBefore(){
        ctx=new AnnotationConfigApplicationContext(SimpleConfig.class);
    }

    @After
    public void doAfter(){
        ctx.registerShutdownHook();
    }

    @Test
    public void test(){
        ctx.start();
        ctx.stop();
        ctx.close();
    }
}
