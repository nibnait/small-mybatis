package cc.tianbin.mybatis.mapping;

/**
 * SQL源码
 * Created by nibnait on 2022/10/21
 */
public interface SqlSource {

    BoundSql getBoundSql(Object parameterObject);

}
