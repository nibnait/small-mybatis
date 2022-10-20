package cc.tianbin.mybatis.executor.resultset;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 结果集处理器
 * Created by nibnait on 2022/10/20
 */
public interface ResultSetHandler {

    <E> List<E> handleResultSets(Statement statement) throws SQLException;

}
