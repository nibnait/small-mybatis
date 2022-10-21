package cc.tianbin.mybatis.builder;

import cc.tianbin.mybatis.mapping.SqlSource;
import cc.tianbin.mybatis.parsing.GenericTokenParser;
import cc.tianbin.mybatis.parsing.TokenHandler;
import cc.tianbin.mybatis.reflection.MetaObject;
import cc.tianbin.mybatis.session.Configuration;
import cc.tianbin.mybatis.mapping.ParameterMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SQL 源码构建器
 * Created by nibnait on 2022/10/21
 */
public class SqlSourceBuilder extends BaseBuilder {

    private static final String parameterProperties = "javaType,jdbcType,mode,numericScale,resultMap,typeHandler,jdbcTypeName";

    public SqlSourceBuilder(Configuration configuration) {
        super(configuration);
    }

    public SqlSource parse(String originalSql, Class<?> parameterType, Map<String, Object> additionalParameters) {
        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler(configuration, parameterType, additionalParameters);
        GenericTokenParser parser = new GenericTokenParser("#{", "}", handler);
        String sql = parser.parse(originalSql);
        // 返回静态 SQL
        return new StaticSqlSource(sql, handler.getParameterMappings(), configuration);
    }

    private static class ParameterMappingTokenHandler extends BaseBuilder implements TokenHandler {

        private List<ParameterMapping> parameterMappings = new ArrayList<>();
        private Class<?> parameterType;
        private MetaObject metaParameters;

        public ParameterMappingTokenHandler(Configuration configuration, Class<?> parameterType, Map<String, Object> additionalParameters) {
            super(configuration);
            this.parameterType = parameterType;
            this.metaParameters = configuration.newMetaObject(additionalParameters);
        }

        public List<ParameterMapping> getParameterMappings() {
            return parameterMappings;
        }

        @Override
        public String handleToken(String content) {
            parameterMappings.add(buildParameterMapping(content));
            return "?";
        }

        // 构建参数映射
        private ParameterMapping buildParameterMapping(String content) {
            // 先解析参数映射,就是转化成一个 HashMap | #{favouriteSection,jdbcType=VARCHAR}
            Map<String, String> propertiesMap = new ParameterExpression(content);
            String property = propertiesMap.get("property");
            Class<?> propertyType = parameterType;
            return ParameterMapping.builder()
                    .configuration(configuration)
                    .property(property)
                    .javaType(propertyType)
                    .build();
        }

    }
}
