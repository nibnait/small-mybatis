package cc.tianbin.mybatis.binding;

import cc.tianbin.mybatis.session.SqlSession;
import io.github.nibnait.common.utils.DataUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

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

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
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
            return sqlSession.selectOne(method.getName(), args);
        }
    }
}
