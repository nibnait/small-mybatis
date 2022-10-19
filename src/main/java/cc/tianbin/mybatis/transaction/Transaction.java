package cc.tianbin.mybatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 标准事物接口
 * Created by nibnait on 2022/10/19
 */
public interface Transaction {

    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;

}
