package cc.tianbin.mybatis.session.defaults;

import cc.tianbin.mybatis.executor.Executor;
import cc.tianbin.mybatis.mapping.BoundSql;
import cc.tianbin.mybatis.mapping.MappedStatement;
import cc.tianbin.mybatis.session.Configuration;
import cc.tianbin.mybatis.session.SqlSession;
import io.github.nibnait.common.utils.DataUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * SqlSession 默认实现类
 * Created by nibnait on 2022/10/18
 */
@Slf4j
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) DataUtils.format("你被代理了：{}", statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        BoundSql boundSql = mappedStatement.getBoundSql();

//            log.info("selectOne \n 方法: {}\n 入参: {}\n 待执行SQL: {}",
//                    statement,
//                    DataUtils.toJsonStringObject(parameter),
//                    boundSql.getSql()
//            );

        List<T> list = executor.query(mappedStatement, parameter, Executor.NO_RESULT_HANDLER, boundSql);
        return list.get(0);
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
