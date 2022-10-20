package cc.tianbin.mybatis.reflection;

import cc.tianbin.mybatis.reflection.invoker.GetFieldInvoker;
import cc.tianbin.mybatis.reflection.invoker.Invoker;
import cc.tianbin.mybatis.reflection.invoker.MethodInvoker;
import cc.tianbin.mybatis.reflection.property.PropertyTokenizer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Created by nibnait on 2022/10/20
 */
public class MetaClassHelper {

    protected static MetaClass metaClassForProperty(Reflector reflector, String name) {
        Class<?> propType = reflector.getGetterType(name);
        return MetaClass.forClass(propType);
    }

    protected static MetaClass metaClassForProperty(Reflector reflector, PropertyTokenizer prop) {
        Class<?> propType = getGetterType(reflector, prop);
        return MetaClass.forClass(propType);
    }

    protected static Class<?> getGetterType(Reflector reflector, PropertyTokenizer prop) {
        Class<?> type = reflector.getGetterType(prop.getName());
        if (prop.getIndex() != null && Collection.class.isAssignableFrom(type)) {
            Type returnType = getGenericGetterType(prop.getName(), reflector);
            if (returnType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) returnType).getActualTypeArguments();
                if (actualTypeArguments != null && actualTypeArguments.length == 1) {
                    returnType = actualTypeArguments[0];
                    if (returnType instanceof Class) {
                        type = (Class<?>) returnType;
                    } else if (returnType instanceof ParameterizedType) {
                        type = (Class<?>) ((ParameterizedType) returnType).getRawType();
                    }
                }
            }
        }
        return type;
    }

    private static Type getGenericGetterType(String propertyName, Reflector reflector) {
        try {
            Invoker invoker = reflector.getGetInvoker(propertyName);
            if (invoker instanceof MethodInvoker) {
                Field _method = MethodInvoker.class.getDeclaredField("method");
                _method.setAccessible(true);
                Method method = (Method) _method.get(invoker);
                return method.getGenericReturnType();
            } else if (invoker instanceof GetFieldInvoker) {
                Field _field = GetFieldInvoker.class.getDeclaredField("field");
                _field.setAccessible(true);
                Field field = (Field) _field.get(invoker);
                return field.getGenericType();
            }
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
        }
        return null;
    }

}
