package cn.sunyog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: jerrylee
 * @Date: 2020/10/21 3:50 下午
 * @Desc: aop配置文件
 */
@Configuration
@ComponentScan("cn.sunyog.aop")
@EnableAspectJAutoProxy
public class AopConfig {
}
