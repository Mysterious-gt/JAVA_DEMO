package com.hatten.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @Author: jerrylee
 * @Date: 2019/10/21 9:49 上午
 * @Desc: test_person映射实体类
 */
@Data
@Accessors(chain = true)
@Alias("person")
public class Person {
    private Integer personId;
    private String personName;
    private Integer personAge;
    private String personSex;
    private Date createDate;
}
