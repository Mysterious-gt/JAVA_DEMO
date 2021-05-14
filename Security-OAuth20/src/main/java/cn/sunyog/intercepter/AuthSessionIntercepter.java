package cn.sunyog.intercepter;

import cn.sunyog.entity.UserDto;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: MysteriousGT
 * @Date: 2021/5/6 11:36 上午
 * @Desc: 授权拦截器
 */
public class AuthSessionIntercepter implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute(UserDto.USER_SESSION_KEY);
        if(obj==null){
            this.responseMsg(response,"请登录...");
            return false;
        }
        UserDto userDB = (UserDto) obj;
        String uri = request.getRequestURI();
        for (String str : userDB.getPermission()) {
            if(uri.contains(str)){
                return true;
            }
        }
        responseMsg(response,"没有权限，无法访问");
        return false;
    }

    private void responseMsg(HttpServletResponse response, String msg) throws IOException {
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.flush();
        writer.close();
    }
}
