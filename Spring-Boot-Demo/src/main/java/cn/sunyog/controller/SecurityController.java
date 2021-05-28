package cn.sunyog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: MysteriousGT
 * @Date: 2021/5/28 2:20 下午
 * @Desc: springsecurity相关controller
 */
@RestController
@RequestMapping("/security")
public class SecurityController {
    @GetMapping("/p1")
    public String p1Auth(){
        return "p1";
    }

    @GetMapping("/p2")
    public String p2Auth(){
        return "p2";
    }
}
