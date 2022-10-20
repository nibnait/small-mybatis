package cc.tianbin.mybatis.reflection.invoker;

import java.lang.reflect.Field;

/**
 * setter 调用者
 * Created by nibnait on 2022/10/20
 */
public class SetFieldInvoker implements Invoker {

    private Field field;

    public SetFieldInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        field.set(target, args[0]);
        return null;
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
