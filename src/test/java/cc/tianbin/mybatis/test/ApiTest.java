package cc.tianbin.mybatis.test;

import cc.tianbin.mybatis.io.Resources;
import cc.tianbin.mybatis.session.SqlSession;
import cc.tianbin.mybatis.session.SqlSessionFactory;
import cc.tianbin.mybatis.session.SqlSessionFactoryBuilder;
import cc.tianbin.mybatis.test.dao.UserDao;
import cc.tianbin.mybatis.test.mock.mockexpect.UserPOExpect;
import cc.tianbin.mybatis.test.po.UserPO;
import io.github.nibnait.common.utils.DataUtils;
import io.github.nibnait.common.utils.compare.CompareUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;


/**
 * Created by nibnait on 2022/05/30
 */
@Slf4j
public class ApiTest {

    @Test
    public void test() throws IOException {
        // 从 SqlSessionFactory 中获取 SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取映射器对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        // query
        UserPO userPO = userDao.queryUserInfo(1L);
        log.info("查询结果 userPO: {}", DataUtils.toJsonStringObject(userPO));
        Assert.assertTrue(CompareUtils.match(userPO, UserPOExpect.get()));
    }

}
