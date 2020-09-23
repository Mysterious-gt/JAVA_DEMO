package cn.sunyog.mapper;

import cn.sunyog.pojo.Book;

/**
 * @Author: jerrylee
 * @Date: 2019/10/21 4:45 下午
 * @Desc: book接口
 */
public interface BookMapper {
    void insertBook(Book book);
    Book getBookById(Integer id);
}
