package cc.tianbin.mybatis.session.defaults;

import cc.tianbin.mybatis.binding.MapperRegistry;
import cc.tianbin.mybatis.session.SqlSession;
import cc.tianbin.mybatis.session.SqlSessionFactory;

/**
 * Created by nibnait on 2022/10/18
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
