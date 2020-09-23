package cn.sunyog.service;

import cn.sunyog.mapper.PersonMapper;
import cn.sunyog.pojo.Person;
import cn.sunyog.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author: jerrylee
 * @Date: 2019/10/22 2:00 下午
 * @Desc: 批量更新Service
 */
public class BatchUpdateService {
    /**
     * @Desc: 获取支持批量更新的sqlsession
     * @Author: Jerry
     * @Date: 2019/10/22
     * @Param: []
     * @Return: org.apache.ibatis.session.SqlSession
     */
    private SqlSession getBatchSqlSession() throws IOException {
        final SqlSessionFactory factory = SqlSessionFactoryUtil.initSqlSessionFactory();
        //获取批量更新session
        final SqlSession session = factory.openSession(ExecutorType.BATCH);
        return session;
    }

    public void doBatchUpdate() throws IOException {
        final SqlSession bSession = getBatchSqlSession();
        final PersonMapper mapper = bSession.getMapper(PersonMapper.class);
        Person p = new Person();
        p.setCreateDate(new Date()).setPersonAge(18).setPersonName("老王").setPersonSex("1");
        mapper.insertPerson(p);

        //刷出内存中没有执行的SQL，执行后可以获取到更新后的数据
        bSession.flushStatements();
        p.setPersonName("OldWang").setPersonSex("0").setPersonAge(40);
        mapper.insertPerson(p);

        //以上sql执行完毕后可以select到
        final List<Person> persons = mapper.getAllPersons();
        System.out.println(persons.size());
        //所有操作完成之后需要commit
        bSession.commit();
    }
}
