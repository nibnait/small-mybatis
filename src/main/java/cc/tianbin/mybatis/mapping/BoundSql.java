package cc.tianbin.mybatis.mapping;

import cc.tianbin.mybatis.reflection.MetaObject;
import cc.tianbin.mybatis.session.Configuration;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 绑定的SQL，是从 SqlSource 而来。
 * 将动态内容都处理完成得到的 SQL 语句字符串
 * 其中包括 ? , 还有绑定的参数
 * Created by nibnait on 2022/10/19
 */
@Getter
public class BoundSql {

    private String sql;
    private List<ParameterMapping> parameterMappings;
    private Object parameterObject;
    private Map<String, Object> additionParameters;
    private MetaObject metaParameters;

    public BoundSql(Configuration configuration, String sql, List<ParameterMapping> parameterMappings, Object parameterObject) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.parameterObject = parameterObject;
        this.additionParameters = new HashMap<>();
        this.metaParameters = configuration.newMetaObject(additionParameters);
    }

    public boolean hasAdditionalParameter(String name) {
        return metaParameters.hasGetter(name);
    }

    public void setAdditionalParameter(String name, Object value) {
        metaParameters.setValue(name, value);
    }

    public Object getAdditionalParameter(String name) {
        return metaParameters.getValue(name);
    }
}
