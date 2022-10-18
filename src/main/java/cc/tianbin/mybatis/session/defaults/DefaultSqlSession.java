package cc.tianbin.mybatis.session.defaults;

import cc.tianbin.mybatis.mapping.MappedStatement;
import cc.tianbin.mybatis.session.Configuration;
import cc.tianbin.mybatis.session.SqlSession;
import io.github.nibnait.common.utils.DataUtils;

/**
 * SqlSession 默认实现类
 * Created by nibnait on 2022/10/18
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) DataUtils.format("你被代理了：{}", statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        return (T) DataUtils.format("你被代理了！ \n 方法: {}\n 入参: {}\n 待执行SQL: {}",
                statement,
                DataUtils.toJsonStringObject(parameter),
                mappedStatement.getSql()
        );
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
