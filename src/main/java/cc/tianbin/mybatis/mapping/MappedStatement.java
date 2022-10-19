package cc.tianbin.mybatis.mapping;

import cc.tianbin.mybatis.session.Configuration;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private BoundSql boundSql;

    public static Builder builder() {
        return new MappedStatement.Builder();
    }

    public static class Builder {

        private MappedStatement mappedStatement;

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

        public Builder boundSql(BoundSql boundSql) {
            mappedStatement.boundSql = boundSql;
            return this;
        }

        public MappedStatement build() {
            assert mappedStatement.configuration != null;
            assert mappedStatement.id != null;
            return mappedStatement;
        }
    }
}
