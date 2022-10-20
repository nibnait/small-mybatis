package cc.tianbin.mybatis.reflection.wrapper;

import cc.tianbin.mybatis.reflection.MetaObject;

/**
 * 对象包装工厂
 * Created by nibnait on 2022/10/20
 */
public interface ObjectWrapperFactory {

    /**
     * 判断有没有包装器
     */
    boolean hasWrapperFor(Object object);

    /**
     * 得到包装器
     */
    ObjectWrapper getWrapperFor(MetaObject metaObject, Object object);

}
