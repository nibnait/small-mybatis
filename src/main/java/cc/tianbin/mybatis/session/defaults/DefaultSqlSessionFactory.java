package cc.tianbin.mybatis.session.defaults;

import cc.tianbin.mybatis.executor.Executor;
import cc.tianbin.mybatis.mapping.Environment;
import cc.tianbin.mybatis.session.*;
import cc.tianbin.mybatis.transaction.Transaction;
import cc.tianbin.mybatis.transaction.TransactionFactory;

import java.sql.SQLException;

/**
 * 默认的 DefaultSqlSessionFactory
 * Created by nibnait on 2022/10/18
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Transaction transaction = null;
        try {
            Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            transaction = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolationLevel.READ_COMMITTED, false);
            // 创建执行器
            final Executor executor = configuration.newExecutor(transaction);
            // 创建 DefaultSqlSession
            return new DefaultSqlSession(configuration, executor);
        } catch (Exception e) {
            try {
                assert transaction != null;
                transaction.close();
            } catch (SQLException ignore) {}
            throw new SqlSessionException(e, "Error opening session. Cause: ");
        }
    }
}
