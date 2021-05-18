package cn.sunyog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: MysteriousGT
 * @Date: 2021/5/6 10:52 上午
 * @Desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserDto {
    public static final String USER_SESSION_KEY="SESSION_USER_AUTHENTICATION";
    private long id;
    private String username;
    private String password;
    private String fullName;
    private String mobile;
    private String[] permission;

}
