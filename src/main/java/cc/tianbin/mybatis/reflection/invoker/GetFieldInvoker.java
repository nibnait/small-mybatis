package cc.tianbin.mybatis.reflection.invoker;

import java.lang.reflect.Field;

/**
 * getter 调用者
 * Created by nibnait on 2022/10/20
 */
public class GetFieldInvoker implements Invoker{

    private Field field;

    public GetFieldInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        return field.get(target);
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
