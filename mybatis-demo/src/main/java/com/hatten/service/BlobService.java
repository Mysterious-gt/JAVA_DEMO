package com.hatten.service;

import com.hatten.mapper.BookMapper;
import com.hatten.pojo.Book;
import com.hatten.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.*;

/**
 * @Author: jerrylee
 * @Date: 2019/10/21 4:50 下午
 * @Desc: 测试blob字段的读取和写入
 */
public class BlobService {
    private byte[] getTestBlob() throws IOException {
        File file=new File("/Users/jerrylee/Documents/code/helloworld.html");
        InputStream in=null;
        byte[] result=new byte[(int)file.length()];
        try {
            in=new FileInputStream(file);
            in.read(result);
        }finally {
            if (in != null){
                in.close();
            }
        }
        return result;
    }

    private Book bookCreator() throws IOException {
        Book book=new Book();
        final byte[] content = getTestBlob();
        book.setAuthorName("刘慈欣").setBookName("《三体》").setBookContent(content);
        return book;
    }

    /**
     * @Desc: blob字段插入
     * @Author: Jerry
     * @Date: 2019/10/22
     * @Param: []
     * @Return: void
     */
    public void mybatisBookInsert() throws IOException {
        final SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        final BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        mapper.insertBook(bookCreator());
    }

    /**
     * @Desc: blob读取
     * @Author: Jerry
     * @Date: 2019/10/22
     * @Param: []
     * @Return: void
     */
    public void mybatisBookRead() throws IOException {
        final SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        final BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        final Book book = mapper.getBookById(1);
        final byte[] content = book.getBookContent();
        System.out.println(book.toString());
        System.out.println(content.length);
    }
}
