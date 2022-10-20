package cc.tianbin.mybatis.reflection.invoker;

import java.lang.reflect.Method;

/**
 * method 的调用者处理
 * Created by nibnait on 2022/10/20
 */
public class MethodInvoker implements Invoker {

    public Class<?> type;
    private Method method;

    public MethodInvoker(Method method) {
        this.method = method;

        // 如果只有一个参数，返回参数的类型，返回 return 的类型
        if (method.getParameterTypes().length == 1) {
            type = method.getParameterTypes()[0];
        } else {
            type = method.getReturnType();
        }
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        return method.invoke(target, args);
    }

    @Override
    public Class<?> getType() {
        return type;
    }
}
