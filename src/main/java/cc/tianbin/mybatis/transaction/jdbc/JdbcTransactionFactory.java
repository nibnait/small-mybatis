package cc.tianbin.mybatis.transaction.jdbc;

import cc.tianbin.mybatis.session.TransactionIsolationLevel;
import cc.tianbin.mybatis.transaction.Transaction;
import cc.tianbin.mybatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by nibnait on 2022/10/19
 */
public class JdbcTransactionFactory implements TransactionFactory {

    @Override
    public Transaction newTransaction(Connection connection) {
        return new JdbcTransaction(connection);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }
}
