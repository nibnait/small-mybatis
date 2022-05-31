package cc.tianbin.mybatis.binding;

import lombok.AllArgsConstructor;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * Created by nibnait on 2022/05/31
 */
@AllArgsConstructor
public class MapperProxyFactory<T> {

    /**
     * 想要实例化的 dao 接口
     */
    private Class<T> mapperInterface;

    public T newInstance(Map<String, String> sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }

}
