package cc.tianbin.mybatis.session.defaults;

import cc.tianbin.mybatis.session.Configuration;
import cc.tianbin.mybatis.session.SqlSession;
import cc.tianbin.mybatis.session.SqlSessionFactory;

/**
 * Created by nibnait on 2022/10/18
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
