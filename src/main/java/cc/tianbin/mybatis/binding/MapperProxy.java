package cc.tianbin.mybatis.binding;

import cc.tianbin.mybatis.session.SqlSession;
import io.github.nibnait.common.utils.DataUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by nibnait on 2022/05/31
 */
@Slf4j
public class MapperProxy<T> implements InvocationHandler, Serializable {

    /**
     * key: dao 接口名
     * value: sql语句
     */
    private SqlSession sqlSession;

    /**
     * 想要代理的 dao 接口
     */
    private Class<T> mapperInterface;

    /**
     * 方法-映射器 缓存
     */
    private final Map<Method, MapperMethod> methodCache;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("toString".equalsIgnoreCase(method.getName())) {
            return method.invoke(this, args);
        }
        log.info("开始查询db。method: {}, args: {}", mapperInterface.getName() + "." + method.getName(),
                DataUtils.toJsonStringArray(args));

        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            final MapperMethod mapperMethod = cachedMapperMethod(method);
            return mapperMethod.execute(sqlSession, args);
        }
    }

    /**
     * 去缓存中找 MapperMethod
     */
    private MapperMethod cachedMapperMethod(Method method) {
        MapperMethod mapperMethod = methodCache.get(method);
        if (mapperMethod == null) {
            // 找不到才去 new
            mapperMethod = new MapperMethod(sqlSession.getConfiguration(), mapperInterface, method);
            methodCache.put(method, mapperMethod);
        }
        return mapperMethod;
    }
}
