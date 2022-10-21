package cc.tianbin.mybatis.scripting.defaults;

import cc.tianbin.mybatis.builder.SqlSourceBuilder;
import cc.tianbin.mybatis.mapping.BoundSql;
import cc.tianbin.mybatis.mapping.SqlSource;
import cc.tianbin.mybatis.scripting.xmltags.DynamicContext;
import cc.tianbin.mybatis.scripting.xmltags.node.SqlNode;
import cc.tianbin.mybatis.session.Configuration;

import java.util.HashMap;

/**
 * 原始 SQL 源码，比 DynamicSQLSource 动态 SQL 处理快
 * Created by nibnait on 2022/10/21
 */
public class RawSqlSource implements SqlSource {

    private final SqlSource sqlSource;

    public RawSqlSource(Configuration configuration, SqlNode rootSqlNode, Class<?> parameterType) {
        this(configuration, getSql(configuration, rootSqlNode), parameterType);
    }

    private static String getSql(Configuration configuration, SqlNode rootSqlNode) {
        DynamicContext context = new DynamicContext(configuration, null);
        rootSqlNode.apply(context);
        return context.getSql();
    }

    public RawSqlSource(Configuration configuration, String sql, Class<?> parameterType) {
        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        Class<?> clazz = parameterType == null ? Object.class : parameterType;
        sqlSource = sqlSourceParser.parse(sql, clazz, new HashMap<>());
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return sqlSource.getBoundSql(parameterObject);
    }
}
