package cc.tianbin.mybatis.binding;

import cc.tianbin.mybatis.mapping.MappedStatement;
import cc.tianbin.mybatis.mapping.SqlCommandType;
import cc.tianbin.mybatis.session.Configuration;
import cc.tianbin.mybatis.session.SqlSession;

import java.lang.reflect.Method;

/**
 * 映射器方法
 * Created by nibnait on 2022/10/18
 */
public class MapperMethod {

    private final SqlCommand command;

    public MapperMethod(Configuration configuration, Class<?> mapperInterface, Method method) {
        this.command = new SqlCommand(configuration, mapperInterface, method);
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        Object result = null;
        switch (command.getType()) {
            case INSERT:
                break;
            case DELETE:
                break;
            case UPDATE:
                break;
            case SELECT:
                result = sqlSession.selectOne(command.getName(), args);
                break;
            default:
                throw new BindingException("Unknown execution method for: {}", command.getName());
        }
        return result;
    }

    /**
     * SQL 指令
     */
    public static class SqlCommand {
        private final String name;
        private final SqlCommandType type;

        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            String statementName = mapperInterface.getName() + "." + method.getName();
            MappedStatement mappedStatement = configuration.getMappedStatement(statementName);
            name = mappedStatement.getId();
            type = mappedStatement.getSqlCommandType();
        }

        public String getName() {
            return name;
        }

        public SqlCommandType getType() {
            return type;
        }
    }
}
