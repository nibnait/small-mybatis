package cc.tianbin.mybatis.mapping;

import cc.tianbin.mybatis.session.Configuration;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 映射语句
 * Created by nibnait on 2022/10/18
 */
@NoArgsConstructor
@Data
public class MappedStatement {

    private Configuration configuration;

    private String id;

    private SqlCommandType sqlCommandType;

    private String parameterType;

    private String resultType;

    private String sql;

    private Map<Integer, String> parameter;

    public static Builder builder() {
        return new MappedStatement.Builder();
    }

    public static class Builder {

        private MappedStatement mappedStatement = new MappedStatement();

        public Builder() {
            mappedStatement = new MappedStatement();
        }

        public Builder configuration(Configuration configuration) {
            mappedStatement.configuration = configuration;
            return this;
        }

        public Builder id(String id) {
            mappedStatement.id = id;
            return this;
        }

        public Builder sqlCommandType(SqlCommandType sqlCommandType) {
            mappedStatement.sqlCommandType = sqlCommandType;
            return this;
        }

        public Builder parameterType(String parameterType) {
            mappedStatement.parameterType = parameterType;
            return this;
        }

        public Builder resultType(String resultType) {
            mappedStatement.resultType = resultType;
            return this;
        }

        public Builder sql(String sql) {
            mappedStatement.sql = sql;
            return this;
        }

        public Builder parameter(Map<Integer, String> parameter) {
            mappedStatement.parameter = parameter;
            return this;
        }

        public MappedStatement build() {
            assert mappedStatement.configuration != null;
            assert mappedStatement.id != null;
            return mappedStatement;
        }
    }
}
