package cn.sunyog.service;

import cn.sunyog.mapper.BookMapper;
import cn.sunyog.pojo.Book;
import cn.sunyog.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * @Author: jerrylee
 * @Date: 2019/10/21 4:50 下午
 * @Desc: 测试blob字段的读取和写入
 */
public class BlobService {
    private Logger logger=Logger.getLogger(this.getClass());

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
        final String contentS = new String(content);
        logger.info(book.toString());
        logger.info(contentS);
    }
}
