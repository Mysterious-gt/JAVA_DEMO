package cn.sunyog.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @Author: jerrylee
 * @Date: 2020/10/19 11:29 上午
 * @Desc: 容器刷新事件
 */
public class ContextRefreshEvent implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("====容器刷新refresh事件执行====");
    }
}
