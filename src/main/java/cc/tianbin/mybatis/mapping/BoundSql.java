package cc.tianbin.mybatis.mapping;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * 绑定的SQL，是从 SqlSource 而来。
 * 将动态内容都处理完成得到的 SQL 语句字符串
 * 其中包括 ? , 还有绑定的参数
 * Created by nibnait on 2022/10/19
 */
@Getter
@Builder
public class BoundSql {

    private String sql;

    private Map<Integer, String> parameterMappings;

    private String parameterType;

    private String resultType;

}
