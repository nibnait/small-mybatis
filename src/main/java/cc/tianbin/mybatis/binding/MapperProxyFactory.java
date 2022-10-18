package cc.tianbin.mybatis.binding;

import cc.tianbin.mybatis.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * Created by nibnait on 2022/05/31
 */
public class MapperProxyFactory<T> {

    /**
     * 想要实例化的 dao 接口
     */
    private Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(SqlSession sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }

}
