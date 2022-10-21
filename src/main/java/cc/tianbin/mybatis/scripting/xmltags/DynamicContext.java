package cc.tianbin.mybatis.scripting.xmltags;


import cc.tianbin.mybatis.reflection.MetaObject;
import cc.tianbin.mybatis.session.Configuration;
import ognl.OgnlContext;
import ognl.OgnlException;
import ognl.OgnlRuntime;
import ognl.PropertyAccessor;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态上下文
 * Created by nibnait on 2022/10/21
 */
public class DynamicContext {

    public static final String PARAMETER_OBJECT_KEY = "_parameter";
    public static final String DATABASE_ID_KEY = "_databaseId";

    static {
        // 定义属性 -> getter 方法映射。ContextMap 到 ContextAccessor 的映射，注册到 ognl 运行时
        // 参考 http://commons.apache.org/proper/commons-ognl/developer-guide.html
        OgnlRuntime.setPropertyAccessor(ContextMap.class, new ContextAccessor());
        // 将传入的参数对象统一封装为 ContextMap 对象（继承了 HashMap 对象），
        // 然后 Ognl 运行时环境在动态计算 sql 语句时，
        // 会按照 ContextAccessor 中描述的 Map 接口的方式来访问和读取 ContextMap 对象，获取计算过程中需要的参数。
        // ContextMap 对象内部可能封装了一个普通的 POJO 对象，也可以是直接传递的 Map 对象，当然从外部是看不出来的，因为都是使用 Map 的接口来读取数据。

    }

    private final ContextMap bindings;
    private final StringBuilder sqlBuilder = new StringBuilder();
    private int uniqueNumber = 0;

    // 在 DynamicContext 的构造函数中，根据传入的参数对象是否为 Map 类型，有两个不同构造 ContextMap 的方式。
    // 而 ContextMap 作为一个继承了 HashMap 的对象，作用就是用于统一参数的访问方式：用 Map 接口方法来访问数据。
    // 具体来说，当传入的参数对象不是 Map 类型时，Mybatis 会将传入的 POJO 对象用 MetaObject 对象来封装，
    // 当动态计算 sql 过程需要获取数据时，用 Map 接口的 get方法包装 MetaObject 对象的取值过程。
    public DynamicContext(Configuration configuration, Object parameterObject) {
        // 绝大多数调用的地方parameterObject为null
        if (parameterObject != null && !(parameterObject instanceof Map)) {
            // 如果是map型  ??  这句是 如果不是map型
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            bindings = new ContextMap(metaObject);
        } else {
            bindings = new ContextMap(null);
        }
        bindings.put(PARAMETER_OBJECT_KEY, parameterObject);
        bindings.put(DATABASE_ID_KEY, configuration.getDatabaseId());
    }

    //----------------------------- getter and setter ------------------------//
    public Map<String, Object> getBindings() {
        return bindings;
    }

    public void bind(String name, Object value) {
        bindings.put(name, value);
    }

    public void appendSql(String sql) {
        sqlBuilder.append(sql);
        sqlBuilder.append(" ");
    }

    public String getSql() {
        return sqlBuilder.toString().trim();
    }

    public int getUniqueNumber() {
        return uniqueNumber++;
    }

    /**
     * 上下文map，静态内部类
     */
    static class ContextMap extends HashMap<String, Object> {
        private static final long serialVersionUID = 2977601501966151582L;

        private MetaObject parameterMetaObject;
        public ContextMap(MetaObject parameterMetaObject) {
            this.parameterMetaObject = parameterMetaObject;
        }

        @Override
        public Object get(Object key) {
            String strKey = (String) key;
            // 先去map里找
            if (super.containsKey(strKey)) {
                return super.get(strKey);
            }

            // 如果没找到，再用ognl表达式去取值
            // 如person[0].birthdate.year
            if (parameterMetaObject != null) {
                // issue #61 do not modify the context when reading
                return parameterMetaObject.getValue(strKey);
            }

            return null;
        }
    }

    /**
     * 上下文访问器，静态内部类,实现OGNL的PropertyAccessor
     */
    static class ContextAccessor implements PropertyAccessor {

        @Override
        public Object getProperty(Map context, Object target, Object name)
                throws OgnlException {
            Map map = (Map) target;

            Object result = map.get(name);
            if (result != null) {
                return result;
            }

            Object parameterObject = map.get(PARAMETER_OBJECT_KEY);
            if (parameterObject instanceof Map) {
                return ((Map)parameterObject).get(name);
            }

            return null;
        }

        @Override
        public void setProperty(Map context, Object target, Object name, Object value)
                throws OgnlException {
            Map<Object, Object> map = (Map<Object, Object>) target;
            map.put(name, value);
        }

        @Override
        public String getSourceAccessor(OgnlContext arg0, Object arg1, Object arg2) {
            return null;
        }

        @Override
        public String getSourceSetter(OgnlContext arg0, Object arg1, Object arg2) {
            return null;
        }
    }

}
