package cc.tianbin.mybatis.builder;

import cc.tianbin.mybatis.session.Configuration;

/**
 * Created by nibnait on 2022/10/18
 */
public class BaseBuilder {

    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
