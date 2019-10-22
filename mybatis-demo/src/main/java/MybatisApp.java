import com.hatten.mapper.PersonMapper;
import com.hatten.pojo.Person;
import com.hatten.service.BatchUpdateService;
import com.hatten.service.BlobService;
import com.hatten.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: jerrylee
 * @Date: 2019/10/21 9:42 上午
 * @Desc: Mybatis测试
 */
public class MybatisApp {
    public static void main(String[] args) throws IOException {
        //mybatisByXML();
        //mybatisBySigniture();
        //mybatisBlobService();
        batchUpdateService();
    }

    private static void batchUpdateService() throws IOException {
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
    private static void mybatisBlobService() throws IOException {
        BlobService service=new BlobService();
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
    private static void mybatisBySigniture() throws IOException {
        final SqlSession session = SqlSessionFactoryUtil.getSqlSession();
        final PersonMapper mapper = session.getMapper(PersonMapper.class);
        final Person person = mapper.getPersonById(1);
        System.out.println(person.toString());
        SqlSessionFactoryUtil.closeSqlSession(session);
    }

    /**
     * @Desc: xml方式配置mybatis
     * @Author: Jerry
     * @Date: 2019/10/22
     * @Param: []
     * @Return: void
     */
    private static void mybatisByXML() throws IOException {
        String path="mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(path);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);

        SqlSession session=null;
        try {
            session=factory.openSession();
            final PersonMapper mapper = session.getMapper(PersonMapper.class);
            final Person person = mapper.getPersonById(1);
            System.out.println(person.toString());
        }finally {
            if (session!=null){
                session.close();
            }
        }
    }
}
