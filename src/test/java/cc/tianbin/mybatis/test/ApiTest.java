package cc.tianbin.mybatis.test;

import cc.tianbin.mybatis.io.Resources;
import cc.tianbin.mybatis.session.SqlSession;
import cc.tianbin.mybatis.session.SqlSessionFactory;
import cc.tianbin.mybatis.session.SqlSessionFactoryBuilder;
import cc.tianbin.mybatis.test.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;


/**
 * Created by nibnait on 2022/05/30
 */
@Slf4j
public class ApiTest {

    @Test
    public void testMapperProxyFactory() throws IOException {
        // 从 SqlSessionFactory 中获取 SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取映射器对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        // query
        String userName = userDao.queryUserName(1L);
        log.info("userName: {}", userName);
//        Assert.assertEquals("Tom", userName);

    }

}
