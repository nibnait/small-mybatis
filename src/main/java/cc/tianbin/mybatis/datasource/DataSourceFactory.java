package cc.tianbin.mybatis.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 数据源工厂
 * Created by nibnait on 2022/10/19
 */
public interface DataSourceFactory {

    void setProperties(Properties properties);

    DataSource getDataSource();

}
