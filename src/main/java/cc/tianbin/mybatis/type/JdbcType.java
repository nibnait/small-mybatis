package cc.tianbin.mybatis.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Types;

/**
 * Created by nibnait on 2022/10/19
 */
@AllArgsConstructor
@Getter
public enum JdbcType {

    INTEGER(Types.INTEGER),
    FLOAT(Types.FLOAT),
    DOUBLE(Types.DOUBLE),
    DECIMAL(Types.DECIMAL),
    VARCHAR(Types.VARCHAR),
    TIMESTAMP(Types.TIMESTAMP),
    ;

    private final int code;

    public static JdbcType getByCode(int code) {
        for (JdbcType value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException(String.format("%d 非法的 JdbcType", code));
    }
}
