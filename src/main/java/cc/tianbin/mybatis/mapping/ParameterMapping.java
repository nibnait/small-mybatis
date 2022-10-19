package cc.tianbin.mybatis.mapping;

import cc.tianbin.mybatis.session.Configuration;
import cc.tianbin.mybatis.type.JdbcType;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by nibnait on 2022/10/19
 */
@Builder
@Getter
public class ParameterMapping {

    private Configuration configuration;

    private String property;
    private Class<?> javaType = Object.class;
    private JdbcType jdbcType;

}
