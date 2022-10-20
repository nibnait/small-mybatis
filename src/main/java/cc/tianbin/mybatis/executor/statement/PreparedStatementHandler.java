package cc.tianbin.mybatis.executor.statement;

import cc.tianbin.mybatis.executor.Executor;
import cc.tianbin.mybatis.mapping.BoundSql;
import cc.tianbin.mybatis.mapping.MappedStatement;
import cc.tianbin.mybatis.session.ResultHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 预处理语句处理器（PREPARED）
 * Created by nibnait on 2022/10/20
 */
public class PreparedStatementHandler extends BaseStatementHandler {

    public PreparedStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject, ResultHandler resultHandler, BoundSql boundSql) {
        super(executor, mappedStatement, parameterObject, resultHandler, boundSql);
    }

    /**
     * 实例化一个 PreparedStatement
     */
    @Override
    protected Statement instantiateStatement(Connection connection) throws SQLException {
        String sql = boundSql.getSql();
        return connection.prepareStatement(sql);
    }

    /**
     * 设置参数
     */
    @Override
    public void parameterize(Statement statement) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        Object[] parameterObjects = (Object[]) parameterObject;
        preparedStatement.setLong(1, Long.parseLong(parameterObjects[0].toString()));
    }

    /**
     * 执行 PreparedStatement
     */
    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        preparedStatement.execute();
        return resultSetHandler.handleResultSets(preparedStatement);
    }
}
