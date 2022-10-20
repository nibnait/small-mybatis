package cc.tianbin.mybatis.executor.statement;

import cc.tianbin.mybatis.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 语句处理器
 * Created by nibnait on 2022/10/20
 */
public interface StatementHandler {

    /**
     * 准备语句
     */
    Statement prepare(Connection connection) throws SQLException;

    /**
     * 参数化传递参数
     */
    void parameterize(Statement statement) throws SQLException;

    /**
     * 执行SQL查询 & 封装结果
     */
    <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException;

}
