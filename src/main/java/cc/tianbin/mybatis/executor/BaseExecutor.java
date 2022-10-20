package cc.tianbin.mybatis.executor;

import cc.tianbin.mybatis.mapping.BoundSql;
import cc.tianbin.mybatis.mapping.MappedStatement;
import cc.tianbin.mybatis.session.Configuration;
import cc.tianbin.mybatis.session.ResultHandler;
import cc.tianbin.mybatis.transaction.Transaction;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;

/**
 * 执行器抽象基类
 * Created by nibnait on 2022/10/20
 */
@Slf4j
public abstract class BaseExecutor implements Executor {

    protected Configuration configuration;
    protected Transaction transaction;
    protected Executor wrapper;

    private boolean closed;

    public BaseExecutor(Configuration configuration, Transaction transaction) {
        this.configuration = configuration;
        this.transaction = transaction;
        this.wrapper = this;
    }

    @Override
    public <E> List<E> query(MappedStatement mappedStatement, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        if (closed) {
            throw new ExecutorException("Executor was closed.");
        }
        return doQuery(mappedStatement, parameter, resultHandler, boundSql);
    }

    protected abstract <E> List<E> doQuery(MappedStatement mappedStatement, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

    @Override
    public Transaction getTransaction() {
        if (closed) {
            throw new ExecutorException("Executor was closed.");
        }
        return transaction;
    }

    @Override
    public void commit(boolean required) throws SQLException {
        if (closed) {
            throw new ExecutorException("Cannot commit. transaction is already closed.");
        }
        if (required) {
            transaction.commit();
        }
    }

    @Override
    public void rollback(boolean required) throws SQLException {
        if (!closed && required) {
            transaction.rollback();
        }
    }

    @Override
    public void close(boolean forceRollback) {
        try {
            try {
                rollback(forceRollback);
            } finally {
                transaction.close();
            }
        } catch (SQLException e) {
            log.warn("Unexpected exception on closing transaction. Cause: ", e);
        } finally {
            transaction = null;
            closed = true;
        }
    }
}
