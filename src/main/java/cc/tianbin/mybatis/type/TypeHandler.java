package cc.tianbin.mybatis.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 类型处理器
 * Created by nibnait on 2022/10/21
 */
public interface TypeHandler<T> {

    /**
     * 设置参数
     */
    void setParameter(PreparedStatement preparedStatement, int i, T parameter, JdbcType jdbcType) throws SQLException;

}
