package cc.tianbin.mybatis.session;

import cc.tianbin.mybatis.builder.xml.XMLConfigBuilder;
import cc.tianbin.mybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * Created by nibnait on 2022/10/18
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration);
    }
}
