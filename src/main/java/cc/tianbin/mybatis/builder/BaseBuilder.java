package cc.tianbin.mybatis.builder;

import cc.tianbin.mybatis.session.Configuration;
import cc.tianbin.mybatis.type.TypeAliasRegistry;
import cc.tianbin.mybatis.type.TypeHandlerRegistry;

/**
 * Created by nibnait on 2022/10/18
 */
public class BaseBuilder {

    protected final Configuration configuration;
    protected final TypeAliasRegistry typeAliasRegistry;
    protected final TypeHandlerRegistry typeHandlerRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
        this.typeHandlerRegistry = this.configuration.getTypeHandlerRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    protected Class<?> resolveAlias(String alias) {
        return typeAliasRegistry.resolveAlias(alias);
    }
}
