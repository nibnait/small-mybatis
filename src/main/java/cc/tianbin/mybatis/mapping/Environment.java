package cc.tianbin.mybatis.mapping;

import cc.tianbin.mybatis.transaction.TransactionFactory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.sql.DataSource;

/**
 * Created by nibnait on 2022/10/19
 */
@AllArgsConstructor
@Getter
@Builder
public class Environment {

    // 环境id
    private final String id;
    // 事物工厂
    private final TransactionFactory transactionFactory;
    // 数据源
    private final DataSource dataSource;

}
