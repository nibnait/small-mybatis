package cc.tianbin.mybatis.session;

/**
 * 用来执行SQL，获取映射器，管理事物
 * Created by nibnait on 2022/10/18
 */
public interface SqlSession {

    /**
     * 根据指定的SqlID 获取一条记录的封装对象
     */
    <T> T selectOne(String statement);

    /**
     * 根据指定的SqlID获取一条记录的封装对象，只不过这个方法容许我们可以给sql传递一些参数
     * 一般在实际使用中，这个参数传递的是pojo，或者Map或者ImmutableMap
     */
    <T> T selectOne(String statement, Object parameter);

    /**
     * get映射器类型
     */
    <T> T getMapper(Class<T> type);

    /**
     * get 配置项
     */
    Configuration getConfiguration();
}
