package cn.sunyog.service;

import cn.sunyog.entity.AuthenticationRequest;
import cn.sunyog.entity.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: MysteriousGT
 * @Date: 2021/5/6 10:54 上午
 * @Desc: 认证接口
 */
@Service
public class AuthenticationService {
    List<UserDto> users=new ArrayList<UserDto>();
    {
        UserDto user = new UserDto();
        user.setId(1L).setUsername("abc").setPassword("abc").setFullName("full Name").setMobile("119").setPermission(new String[0]);
        users.add(user);

        UserDto user2 = new UserDto(2L, "aaa", "aaa", "full nammmmmmme","110",new String[]{"url"});
        users.add(user2);
    }

    //认证方法
    public UserDto authentication(AuthenticationRequest request){
        String username = request.getUsername();
        if(StringUtils.isEmpty(username)){
            throw new RuntimeException("用户名、密码均不能为空");
        }
        UserDto userDB=null;
        for (UserDto item : users) {
            if(item.getUsername().equals(username)){
                userDB=item;
            }
        }
        if(userDB==null){
            throw new RuntimeException("查无此用户");
        }
        if(userDB.getPassword().equals(request.getPassword())){
            return userDB;
        }else{
            throw new RuntimeException("用户名或密码错误");
        }
    }
}
