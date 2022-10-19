package cc.tianbin.mybatis.builder;

import cc.tianbin.mybatis.session.Configuration;
import cc.tianbin.mybatis.type.TypeAliasRegistry;

/**
 * Created by nibnait on 2022/10/18
 */
public class BaseBuilder {

    protected final Configuration configuration;
    protected final TypeAliasRegistry typeAliasRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
