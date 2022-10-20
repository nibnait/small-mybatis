package cc.tianbin.mybatis.reflection.invoker;

/**
 * 调用者
 * Created by nibnait on 2022/10/20
 */
public interface Invoker {

    Object invoke(Object target, Object[] args) throws Exception;

    Class<?> getType();

}
