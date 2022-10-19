package cc.tianbin.mybatis.session.defaults;

import cc.tianbin.mybatis.mapping.BoundSql;
import cc.tianbin.mybatis.mapping.Environment;
import cc.tianbin.mybatis.mapping.MappedStatement;
import cc.tianbin.mybatis.session.Configuration;
import cc.tianbin.mybatis.session.SqlSession;
import io.github.nibnait.common.utils.DataUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SqlSession 默认实现类
 * Created by nibnait on 2022/10/18
 */
@Slf4j
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
        try {
            MappedStatement mappedStatement = configuration.getMappedStatement(statement);
            BoundSql boundSql = mappedStatement.getBoundSql();

            log.info("selectOne \n 方法: {}\n 入参: {}\n 待执行SQL: {}",
                    statement,
                    DataUtils.toJsonStringObject(parameter),
                    boundSql.getSql()
            );

            Environment environment = configuration.getEnvironment();
            Connection connection = environment.getDataSource().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql());
            Object[] parameters = (Object[]) parameter;
            preparedStatement.setLong(1, Long.parseLong(parameters[0].toString()));
            ResultSet resultSet = preparedStatement.executeQuery();

            List<T> objList = resultSet2Obj(resultSet, Class.forName(boundSql.getResultType()));
            return objList.get(0);
        } catch (Exception e) {
            log.error("DefaultSqlSession selectOne error ", e);
            return null;
        }
    }

    private <T> List<T> resultSet2Obj(ResultSet resultSet, Class<?> clazz) {
        List<T> list = new ArrayList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                T obj = (T) clazz.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    Object value = resultSet.getObject(i);
                    String columnName = metaData.getColumnName(i);
                    String setMethod = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                    Method method;
                    if (value instanceof Timestamp) {
                        method = clazz.getMethod(setMethod, Date.class);
                    } else {
                        method = clazz.getMethod(setMethod, value.getClass());
                    }
                    method.invoke(obj, value);
                }
                list.add(obj);
            }
        } catch (Exception e) {
            log.error("DefaultSqlSession resultSet2Obj error ", e);
        }
        return list;
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
