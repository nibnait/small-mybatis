package cc.tianbin.mybatis.builder;

import cc.tianbin.mybatis.mapping.BoundSql;
import cc.tianbin.mybatis.mapping.ParameterMapping;
import cc.tianbin.mybatis.mapping.SqlSource;
import cc.tianbin.mybatis.session.Configuration;

import java.util.List;

/**
 * 静态 SQL 源码
 * Created by nibnait on 2022/10/21
 */
public class StaticSqlSource implements SqlSource {

    private String sql;
    private List<ParameterMapping> parameterMappings;
    private Configuration configuration;

    public StaticSqlSource(String sql, Configuration configuration) {
        this(sql, null, configuration);
    }

    public StaticSqlSource(String sql, List<ParameterMapping> parameterMappings, Configuration configuration) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.configuration = configuration;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return new BoundSql(configuration, sql, parameterMappings, parameterObject);
    }
}
