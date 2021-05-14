package cn.sunyog.entity;

import lombok.Data;

/**
 * @Author: MysteriousGT
 * @Date: 2021/5/6 11:03 上午
 * @Desc: 认证请求
 */
@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
