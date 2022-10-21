package cc.tianbin.mybatis.mapping;

import cc.tianbin.mybatis.session.Configuration;
import cc.tianbin.mybatis.type.JdbcType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by nibnait on 2022/10/19
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ParameterMapping {

    private Configuration configuration;

    // property
    private String property;
    // javaType = int
    private Class<?> javaType = Object.class;
    // jdbcType=NUMERIC
    private JdbcType jdbcType;

}
