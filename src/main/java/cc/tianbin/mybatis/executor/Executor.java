package cc.tianbin.mybatis.executor;


import cc.tianbin.mybatis.mapping.BoundSql;
import cc.tianbin.mybatis.mapping.MappedStatement;
import cc.tianbin.mybatis.session.ResultHandler;
import cc.tianbin.mybatis.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * SQL 执行器
 * 封装事物、连接、检测环境
 * Created by nibnait on 2022/10/20
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    <E> List<E> query(MappedStatement mappedStatement, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);

}
