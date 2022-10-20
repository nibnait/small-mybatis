package cc.tianbin.mybatis.reflection.wrapper;


import cc.tianbin.mybatis.reflection.MetaObject;
import cc.tianbin.mybatis.reflection.ReflectionException;

/**
 * 默认对象包装工厂
 * Created by nibnait on 2022/10/20
 */
public class DefaultObjectWrapperFactory implements ObjectWrapperFactory {

    @Override
    public boolean hasWrapperFor(Object object) {
        return false;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        throw new ReflectionException("The DefaultObjectWrapperFactory should never be called to provide an ObjectWrapper.");
    }
}
