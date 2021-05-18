package cn.sunyog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: MysteriousGT
 * @Date: 2021/5/4 4:31 下午
 * @Desc:
 */
@Controller
@RequestMapping("/page")
public class PageController {
    @GetMapping("/login")
    public String doLogin(){
        return "/login.html";
    }
}
