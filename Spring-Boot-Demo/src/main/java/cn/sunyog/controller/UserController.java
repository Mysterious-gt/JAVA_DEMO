package cn.sunyog.controller;

import cn.sunyog.entity.AuthenticationRequest;
import cn.sunyog.entity.UserDto;
import cn.sunyog.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Author: MysteriousGT
 * @Date: 2021/5/6 9:35 上午
 * @Desc: 用户
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AuthenticationService authenticationService;

    /**
     * 基于session的认证方法
     * @param request
     * @param session
     * @return
     */
    @GetMapping("/login")
    public UserDto login(AuthenticationRequest request, HttpSession session){
        UserDto result = authenticationService.authentication(request);
        //存储到session
        session.setAttribute(UserDto.USER_SESSION_KEY,result);
        return result;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(UserDto.USER_SESSION_KEY);
        return "登出成功";
    }

    /**
     * 测试访问资源
     * @param session
     * @return
     */
    @GetMapping("/url")
    public String requestUrl(HttpSession session){
        String fullName="匿名";
        Object obj = session.getAttribute(UserDto.USER_SESSION_KEY);
        if(obj!=null){
            fullName=((UserDto) obj).getFullName();
        }
        return fullName+"  访问/user/url资源";
    }

}
