package cc.tianbin.mybatis.mapping;

import cc.tianbin.mybatis.session.Configuration;
import lombok.*;

/**
 * 映射语句类
 * Created by nibnait on 2022/10/18
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MappedStatement {

    private Configuration configuration;
    private String id;
    private SqlCommandType sqlCommandType;
    private SqlSource sqlSource;
    Class<?> resultType;

}
