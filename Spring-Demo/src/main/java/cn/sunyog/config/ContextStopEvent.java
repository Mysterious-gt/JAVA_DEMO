package cn.sunyog.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * @Author: jerrylee
 * @Date: 2020/10/19 11:25 上午
 * @Desc: 容器停止事件
 */
public class ContextStopEvent implements ApplicationListener<ContextStoppedEvent> {

    @Override
    public void onApplicationEvent(ContextStoppedEvent event) {
        System.out.println("====容器停止stop事件执行====");
    }
}
