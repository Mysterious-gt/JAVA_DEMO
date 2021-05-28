package cn.sunyog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @Author: MysteriousGT
 * @Date: 2021/5/28 1:50 下午
 * @Desc: spring security认证授权配置
 * 在spring项目中需要增加@EnableSecurity注解
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //定义用户信息服务，自定义userDetailService之后可以去掉
//    @Bean
//    public UserDetailsService getUserDetailService(){
//        InMemoryUserDetailsManager manager=new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("tony").password("tony").authorities("p1").build());
//        manager.createUser(User.withUsername("jack").password("jack").authorities("p2").build());
//        return manager;
//    }
    //定义密码编码器
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        //￿字符串比较
        //return NoOpPasswordEncoder.getInstance();
        //bcrypt密码编码器
        return new BCryptPasswordEncoder();
    }
    //定义安全拦截
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                //配置权限，没有权限访问会返回403
                .antMatchers("/security/p1").hasAuthority("p1")
                .antMatchers("/security/p2").hasAuthority("p2")
                //所有/user/**和/redis/**请求必须认证通过
                .antMatchers("/user/**","/redis/**").authenticated()
                //其他请求可以访问
                .anyRequest().permitAll()
                //允许表单登录
                .and().formLogin()
                //自定义登录成功的地址,post方式
                .successForwardUrl("/user/login-success");
    }
}
