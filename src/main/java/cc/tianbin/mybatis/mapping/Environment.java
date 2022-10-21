package cc.tianbin.mybatis.mapping;

import cc.tianbin.mybatis.transaction.TransactionFactory;
import cn.hutool.core.lang.Assert;
import lombok.Getter;

import javax.sql.DataSource;

/**
 * Created by nibnait on 2022/10/19
 */
@Getter
public class Environment {

    // 环境id
    private final String id;
    // 事物工厂
    private final TransactionFactory transactionFactory;
    // 数据源
    private final DataSource dataSource;

    public Environment(String id, TransactionFactory transactionFactory, DataSource dataSource) {
        this.id = id;
        this.transactionFactory = transactionFactory;
        this.dataSource = dataSource;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private TransactionFactory transactionFactory;
        private DataSource dataSource;

        public Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder transactionFactory(TransactionFactory transactionFactory) {
            this.transactionFactory = transactionFactory;
            return this;
        }

        public Builder dataSource(DataSource dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public String id() {
            return this.id;
        }

        public Environment build() {
            Assert.notBlank(this.id);
            return new Environment(this.id, this.transactionFactory, this.dataSource);
        }

    }
}
