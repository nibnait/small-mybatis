package cc.tianbin.mybatis.test;

import cc.tianbin.mybatis.binding.MapperProxyFactory;
import cc.tianbin.mybatis.test.dao.UserDao;
import cc.tianbin.mybatis.test.mock.mockexpect.UserPOExpect;
import cc.tianbin.mybatis.test.po.UserPO;
import io.github.nibnait.common.utils.DataUtils;
import io.github.nibnait.common.utils.compare.CompareUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by nibnait on 2022/05/30
 */
@Slf4j
public class ApiTest {

    @Test
    public void day01MapperProxyFactory() {
        MapperProxyFactory<UserDao> userDaoMapperProxyFactory = new MapperProxyFactory<>(UserDao.class);

        Map<String, String> sqlSession = new HashMap<>();
        sqlSession.put("cc.tianbin.mybatis.test.dao.UserDao.queryUserName", "select name from user where id = #{userId}");
        sqlSession.put("cc.tianbin.mybatis.test.dao.UserDao.queryUserAge", "select age from user where id = #{userId}");
        UserDao userDao = userDaoMapperProxyFactory.newInstance(sqlSession);

        String userName = userDao.queryUserName(1L);
        log.info("userName: {}", userName);
        Assert.assertEquals("Tom", userName);

        Integer arg = userDao.queryUserAge(2L);
        log.info("userAge: {}", arg);
        Assert.assertEquals(Integer.valueOf(111), arg);
    }

    @Test
    public void day00SqlSessionFactory() throws IOException {

        // 1. 从 SqlSessionFactory 中获取 SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        // 2. 开启Session
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取映射起对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        // 4. 测试验证
        UserPO userPO = userDao.queryUserInfo(1L);
        log.info("userPO: {}", DataUtils.toJsonStringObject(userPO));

        Assert.assertTrue(CompareUtils.match(userPO, UserPOExpect.get()));
    }

}
