package cc.tianbin.mybatis.session;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Connection;

/**
 * 事物隔离级别
 * Created by nibnait on 2022/10/19
 */
@AllArgsConstructor
@Getter
public enum TransactionIsolationLevel {

    // 包括JDBC支持的5个级别
    NONE(Connection.TRANSACTION_NONE),
    READ_COMMITTED(Connection.TRANSACTION_READ_COMMITTED),
    READ_UNCOMMITTED(Connection.TRANSACTION_READ_UNCOMMITTED),
    REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ),
    SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE),
    ;

    private final int level;
}
