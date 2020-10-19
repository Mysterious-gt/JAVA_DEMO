package cn.sunyog.config;

import cn.sunyog.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: jerrylee
 * @Date: 2020/10/19 10:00 上午
 * @Desc: 最简单的配置类
 */
@Configuration
public class SimpleConfig {
    @Bean
    public Student getStudent(){
        Student res = new Student();

        res.setsAge(18);
        res.setsClass("2");
        res.setsGender(1);
        res.setsGrade("小1");
        res.setsId(0);
        res.setsName("小王");
        return res;
    }

    @Bean
    public ContextStartEvent getStart(){
        return new ContextStartEvent();
    }

    @Bean
    public ContextRefreshEvent getRefresh(){
        return new ContextRefreshEvent();
    }

    @Bean
    public ContextStopEvent getStop(){
        return new ContextStopEvent();
    }

    @Bean
    public ContextCloseEvent getClose(){
        return new ContextCloseEvent();
    }
}
