package cc.tianbin.mybatis.binding;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by nibnait on 2022/05/31
 */
@AllArgsConstructor
@Slf4j
public class MapperProxy<T> implements InvocationHandler {

    /**
     * key: dao 接口名
     * value: sql语句
     */
    private Map<String, String> sqlSession;

    /**
     * 想要代理的 dao 接口
     */
    private Class<T> mapperInterface;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("开始查询db。sql:" + sqlSession.get(mapperInterface.getName() + "." + method.getName()).replace("#{userId}", args[0].toString()));

        if ("queryUserName".equalsIgnoreCase(method.getName())) {
            return "Tom";
        } else {
            return 111;
        }
    }
}
