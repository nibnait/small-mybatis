package cc.tianbin.mybatis.executor;

import cc.tianbin.mybatis.executor.statement.StatementHandler;
import cc.tianbin.mybatis.mapping.BoundSql;
import cc.tianbin.mybatis.mapping.MappedStatement;
import cc.tianbin.mybatis.session.Configuration;
import cc.tianbin.mybatis.session.ResultHandler;
import cc.tianbin.mybatis.transaction.Transaction;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 简单执行器
 * Created by nibnait on 2022/10/20
 */
@Slf4j
public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement mappedStatement, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        try {
            Configuration configuration = mappedStatement.getConfiguration();
            StatementHandler handler = configuration.newStatementHandler(this, mappedStatement, parameter, resultHandler, boundSql);
            Connection connection = transaction.getConnection();
            Statement statement = handler.prepare(connection);
            handler.parameterize(statement);
            return handler.query(statement, resultHandler);
        } catch (SQLException e) {
            log.error("SimpleExecutor doQuery error ", e);
            return null;
        }
    }

}
