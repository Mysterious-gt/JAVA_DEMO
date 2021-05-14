package cn.sunyog.config;

import cn.sunyog.intercepter.AuthSessionIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: MysteriousGT
 * @Date: 2021/5/6 11:43 上午
 * @Desc: 拦截器配置
 */
@Configuration
public class InterceperConfig implements WebMvcConfigurer {
    /**
     * @Desc: 基于session的认证
     * @Author: MysteriousGT
     * @Date: 2021/5/6
     * @Param: [registry]
     * @Return: void
     */
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AuthSessionIntercepter()).addPathPatterns("/user/url");
//    }
}
