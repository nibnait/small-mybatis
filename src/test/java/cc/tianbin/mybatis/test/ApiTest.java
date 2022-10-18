package cc.tianbin.mybatis.test;

import cc.tianbin.mybatis.binding.MapperRegistry;
import cc.tianbin.mybatis.session.SqlSession;
import cc.tianbin.mybatis.session.defaults.DefaultSqlSessionFactory;
import cc.tianbin.mybatis.test.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


/**
 * Created by nibnait on 2022/05/30
 */
@Slf4j
public class ApiTest {

    @Test
    public void testMapperProxyFactory() {
        // 注册 Mapper
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("cc.tianbin.mybatis.test.dao");

        // 从 SqlSession 工厂获取 Session
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取映射器对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        String userName = userDao.queryUserName(1L);
        log.info("userName: {}", userName);
//        Assert.assertEquals("Tom", userName);

    }

}
