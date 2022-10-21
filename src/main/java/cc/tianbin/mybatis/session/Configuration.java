package cc.tianbin.mybatis.session;

import cc.tianbin.mybatis.binding.MapperRegistry;
import cc.tianbin.mybatis.datasource.druid.DruidDataSourceFactory;
import cc.tianbin.mybatis.datasource.pooled.PooledDataSourceFactory;
import cc.tianbin.mybatis.datasource.unpooled.UnpooledDataSourceFactory;
import cc.tianbin.mybatis.executor.Executor;
import cc.tianbin.mybatis.executor.SimpleExecutor;
import cc.tianbin.mybatis.executor.resultset.DefaultResultSetHandler;
import cc.tianbin.mybatis.executor.resultset.ResultSetHandler;
import cc.tianbin.mybatis.executor.statement.PreparedStatementHandler;
import cc.tianbin.mybatis.executor.statement.StatementHandler;
import cc.tianbin.mybatis.mapping.BoundSql;
import cc.tianbin.mybatis.mapping.Environment;
import cc.tianbin.mybatis.mapping.MappedStatement;
import cc.tianbin.mybatis.reflection.MetaObject;
import cc.tianbin.mybatis.reflection.factory.DefaultObjectFactory;
import cc.tianbin.mybatis.reflection.factory.ObjectFactory;
import cc.tianbin.mybatis.reflection.wrapper.DefaultObjectWrapperFactory;
import cc.tianbin.mybatis.reflection.wrapper.ObjectWrapperFactory;
import cc.tianbin.mybatis.scripting.LanguageDriverRegistry;
import cc.tianbin.mybatis.transaction.Transaction;
import cc.tianbin.mybatis.transaction.jdbc.JdbcTransactionFactory;
import cc.tianbin.mybatis.type.TypeAliasRegistry;
import cc.tianbin.mybatis.type.TypeHandlerRegistry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by nibnait on 2022/10/18
 */
public class Configuration {

    // 环境
    protected Environment environment;

    // 映射注册机
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    // 映射的语句 存在Map里
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    // 类型别名注册机
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
    protected final LanguageDriverRegistry languageRegistry = new LanguageDriverRegistry();

    // 类型处理器注册机
    protected final TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();

    // 对象工厂和对象包装器工厂
    protected ObjectFactory objectFactory = new DefaultObjectFactory();
    protected ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();

    protected final Set<String> loadedResources = new HashSet<>();

    protected String databaseId;

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);

        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);
    }

    //----------------------------- 操作方法 ------------------------//

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement mappedStatement) {
        mappedStatements.put(mappedStatement.getId(), mappedStatement);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    //----------------------------- getter and setter ------------------------//

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    //----------------------------- SQL 执行器 相关 ----------------------------//

    /**
     * 创建结果处理器
     */
    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, boundSql);
    }

    /**
     * 生成执行器
     */
    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this, transaction);
    }

    /**
     * 创建语句处理器
     */
    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(executor, mappedStatement, parameter, resultHandler, boundSql);
    }

    // 创建元对象
    public MetaObject newMetaObject(Object object) {
        return MetaObject.forObject(object, objectFactory, objectWrapperFactory);
    }

    // 类型处理器注册机
    public TypeHandlerRegistry getTypeHandlerRegistry() {
        return typeHandlerRegistry;
    }

    public boolean isResourceLoaded(String resource) {
        return loadedResources.contains(resource);
    }

    public void addLoadedResource(String resource) {
        loadedResources.add(resource);
    }

    public LanguageDriverRegistry getLanguageRegistry() {
        return languageRegistry;
    }

}
