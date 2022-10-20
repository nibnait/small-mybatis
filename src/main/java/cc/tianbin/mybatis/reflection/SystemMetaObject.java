package cc.tianbin.mybatis.reflection;

import cc.tianbin.mybatis.reflection.factory.DefaultObjectFactory;
import cc.tianbin.mybatis.reflection.factory.ObjectFactory;
import cc.tianbin.mybatis.reflection.wrapper.DefaultObjectWrapperFactory;
import cc.tianbin.mybatis.reflection.wrapper.ObjectWrapperFactory;

/**
 * 一些系统级别的元对象
 * Created by nibnait on 2022/10/20
 */
public class SystemMetaObject {

    public static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    public static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    public static final MetaObject NULL_META_OBJECT = MetaObject.forObject(NullObject.class, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);

    public SystemMetaObject() {
        // Prevent Instantiation of Static Class
    }

    /**
     * 空对象
     */
    private static class NullObject {
    }

    public static MetaObject forObject(Object object) {
        return MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
    }
}
