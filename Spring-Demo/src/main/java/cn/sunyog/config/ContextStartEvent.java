package cn.sunyog.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/**
 * @Author: jerrylee
 * @Date: 2020/10/19 11:24 上午
 * @Desc: 容器启动事件
 */
public class ContextStartEvent implements ApplicationListener<ContextStartedEvent> {
    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        System.out.println("====容器启动start事件执行====");
    }
}
