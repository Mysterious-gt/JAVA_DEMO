package com.hatten.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: jerrylee
 * @Date: 2019/10/21 10:59 上午
 * @Desc: 单例模式获取SqlSessionFactory
 */
public class SqlSessionFactoryUtil {
    private static SqlSessionFactory factory = null;
    //类线程锁
    private static final Class CLASS_LOCK = SqlSessionFactoryUtil.class;

    /**
     * @Desc: 单例模式创建sqlSessionFactory
     * @Author: Jerry
     * @Date: 2019/10/22
     * @Param: []
     * @Return: org.apache.ibatis.session.SqlSessionFactory
     */
    public static SqlSessionFactory initSqlSessionFactory() throws IOException {
        String path = "mybatis-config.xml";
        final InputStream in = Resources.getResourceAsStream(path);
        synchronized (CLASS_LOCK) {
            if (factory == null) {
                SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
                factory=builder.build(in);
            }
        }
        return factory;
    }

    /**
     * @Desc: 获取SqlSession对象
     * @Author: Jerry
     * @Date: 2019/10/21
     * @Param: []
     * @Return: org.apache.ibatis.session.SqlSession
     */
    public static SqlSession getSqlSession() throws IOException {
        if (factory==null){
            initSqlSessionFactory();
        }
        return factory.openSession();
    }

    /**
     * @Desc: 关闭session
     * @Author: Jerry
     * @Date: 2019/10/21
     * @Param: [session]
     * @Return: void
     */
    public static void closeSqlSession(SqlSession session){
        if (session != null){
            session.close();
        }
    }
}