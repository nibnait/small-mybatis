package cc.tianbin.mybatis.session.defaults;

import cc.tianbin.mybatis.binding.MapperRegistry;
import cc.tianbin.mybatis.session.SqlSession;
import io.github.nibnait.common.utils.DataUtils;

/**
 * SqlSession 默认实现类
 * Created by nibnait on 2022/10/18
 */
public class DefaultSqlSession implements SqlSession {

    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) DataUtils.format("你被代理了：{}", statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) DataUtils.format("你被代理了！ statement: {}, param: {}", statement, DataUtils.toJsonStringObject(parameter));
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }
}
