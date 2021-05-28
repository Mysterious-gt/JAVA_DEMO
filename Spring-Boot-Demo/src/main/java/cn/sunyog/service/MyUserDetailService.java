package cn.sunyog.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * @Author: MysteriousGT
 * @Date: 2021/5/28 3:41 下午
 * @Desc: 自定义user detail service
 */
@Service
public class MyUserDetailService implements UserDetailsService {
    /**
     * 根据用户名查询用户信息，正常需要数据库查询
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //密码加密,密码加密方式要和密码编码器相匹配
        String pass="admin";
        String passDB = BCrypt.hashpw(pass, BCrypt.gensalt());
        UserDetails userDetails= User.withUsername(username).password(passDB).authorities("p1").build();
        return userDetails;
    }
}
