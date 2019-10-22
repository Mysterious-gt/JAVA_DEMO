package com.hatten.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: jerrylee
 * @Date: 2019/10/21 4:42 下午
 * @Desc: 对应book表，用于测试读取blob类型数据
 */
@Data
@Accessors(chain = true)
public class Book {
    private Integer bookId;
    private String authorName;
    private String bookName;
    private byte[] bookContent;
}
