package cc.tianbin.mybatis.reflection.wrapper;

import cc.tianbin.mybatis.reflection.MetaObject;
import cc.tianbin.mybatis.reflection.factory.ObjectFactory;
import cc.tianbin.mybatis.reflection.property.PropertyTokenizer;

import java.util.List;

/**
 * 对象包装器
 * Created by nibnait on 2022/10/20
 */
public interface ObjectWrapper {

    // get
    Object get(PropertyTokenizer prop);

    // set
    void set(PropertyTokenizer prop, Object value);

    // 查找属性
    String findProperty(String name, boolean useCamelCaseMapping);

    // 取得 getter 的名字列表
    String[] getGetterNames();

    // 取得 setter 的名字列表
    String[] getSetterNames();

    // 取得 setter 的类型
    Class<?> getSetterType(String name);

    // 取得 getter 的类型
    Class<?> getGetterType(String name);

    // 是否有指定的 setter
    boolean hasSetter(String name);

    // 是否有指定的 getter
    boolean hasGetter(String name);

    // 实例化属性
    MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory);

    // 是否是集合
    boolean isCollection();

    // 添加属性
    void add(Object element);

    // 添加属性
    <E> void addAll(List<E> element);

}
