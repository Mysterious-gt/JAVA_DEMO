package mybatis;

import cn.sunyog.mapper.PersonMapper;
import cn.sunyog.pojo.Person;
import cn.sunyog.service.BatchUpdateService;
import cn.sunyog.service.BlobService;
import cn.sunyog.utils.SqlSessionFactoryUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: jerrylee
 * @Date: 2019/10/25 3:52 下午
 * @Desc: 测试SqlSession
 */
public class Test01 {
    private Logger logger=Logger.getLogger(this.getClass());
    @Test
    public void testSQLSession() throws IOException {
        //mybatisByXML();
        //mybatisBySigniture();
        mybatisBlobService();
        //batchUpdateService();
        //testLog4J();
    }

    private void batchUpdateService() throws IOException {
        final BatchUpdateService batchService = new BatchUpdateService();
        batchService.doBatchUpdate();
    }

    /**
     * @Desc: 测试blob字段读写
     * @Author: Jerry
     * @Date: 2019/10/22
     * @Param: []
     * @Return: void
     */
    private void mybatisBlobService() throws IOException {
        BlobService service = new BlobService();
        //service.mybatisBookInsert();
        service.mybatisBookRead();
    }

    /**
     * @Desc: 单例模式获取sqlsessionfactory
     * @Author: Jerry
     * @Date: 2019/10/22
     * @Param: []
     * @Return: void
     */
    private void mybatisBySigniture() throws IOException {
        final SqlSession session = SqlSessionFactoryUtil.getSqlSession();
        final PersonMapper mapper = session.getMapper(PersonMapper.class);
        final Person person = mapper.getPersonById(1);
        logger.info(person.toString());
        SqlSessionFactoryUtil.closeSqlSession(session);
    }

    /**
     * @Desc: xml方式配置mybatis
     * @Author: Jerry
     * @Date: 2019/10/22
     * @Param: []
     * @Return: void
     */
    private void mybatisByXML() throws IOException {
        String path = "mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(path);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);

        SqlSession session = null;
        try {
            session = factory.openSession();
            final PersonMapper mapper = session.getMapper(PersonMapper.class);
            final Person person = mapper.getPersonById(1);
            System.out.println(person.toString());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private void testLog4J(){
        final Log log = LogFactory.getLog(Test01.class);
        log.info("==========test log4j!==========");
        log.debug("==========test debug!==========");
        log.error("==========test error!==========");
        log.trace("==========test trace!==========");
        log.fatal("==========test fatal!==========");
    }
}
