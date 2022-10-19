package cc.tianbin.mybatis.transaction;

import cc.tianbin.mybatis.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 事务工厂
 * 以工厂方法模式包装 JDBC 事务实现，为每一个事务实现都提供一个对应的工厂。
 * Created by nibnait on 2022/10/19
 */
public interface TransactionFactory {

    /**
     * 根据 Connection 创建 Transaction
     */
    Transaction newTransaction(Connection connection);

    /**
     * 根据数据源和事物隔离级别创建 Transaction
     */
    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit);

}
