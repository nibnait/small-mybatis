package cc.tianbin.mybatis.datasource.pooled;

import cc.tianbin.mybatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * 有连接池的数据源工厂
 * Created by nibnait on 2022/10/19
 */
public class PooledDataSourceFactory extends UnpooledDataSourceFactory {

    public PooledDataSourceFactory() {
        this.dataSource = new PooledDataSource();
    }
}
