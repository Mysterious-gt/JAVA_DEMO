package cn.sunyog.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

/**
 * @Author: jerrylee
 * @Date: 2020/10/19 11:27 上午
 * @Desc: 容器关闭事件
 */
public class ContextCloseEvent implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("====容器关闭close事件执行====");
    }
}
