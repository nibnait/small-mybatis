package cc.tianbin.mybatis.builder.xml;

import cc.tianbin.mybatis.builder.BaseBuilder;
import cc.tianbin.mybatis.mapping.MappedStatement;
import cc.tianbin.mybatis.mapping.SqlCommandType;
import cc.tianbin.mybatis.mapping.SqlSource;
import cc.tianbin.mybatis.scripting.LanguageDriver;
import cc.tianbin.mybatis.session.Configuration;
import org.dom4j.Element;

import java.util.Locale;

/**
 * XML 语句构建器
 * Created by nibnait on 2022/10/21
 */
public class XMLStatementBuilder extends BaseBuilder {

    private String currentNamespace;
    private Element element;

    public XMLStatementBuilder(Configuration configuration, Element element, String currentNamespace) {
        super(configuration);
        this.element = element;
        this.currentNamespace = currentNamespace;
    }

    //解析语句(select|insert|update|delete)
    //<select
    //  id="selectPerson"
    //  parameterType="int"
    //  parameterMap="deprecated"
    //  resultType="hashmap"
    //  resultMap="personResultMap"
    //  flushCache="false"
    //  useCache="true"
    //  timeout="10000"
    //  fetchSize="256"
    //  statementType="PREPARED"
    //  resultSetType="FORWARD_ONLY">
    //  SELECT * FROM PERSON WHERE ID = #{id}
    //</select>
    public void parseStatementNode() {
        String id = element.attributeValue("id");
        // 参数类型
        String parameterType = element.attributeValue("parameterType");
        Class<?> parameterTypeClass = resolveAlias(parameterType);
        // 结果类型
        String resultType = element.attributeValue("resultType");
        Class<?> resultTypeClass = resolveAlias(resultType);
        // 获取命令类型(select|insert|update|delete)
        String nodeName = element.getName();
        SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));

        // 获取默认语言驱动器
        Class<?> langClass = configuration.getLanguageRegistry().getDefaultDriverClass();
        LanguageDriver langDriver = configuration.getLanguageRegistry().getDriver(langClass);

        SqlSource sqlSource = langDriver.createSqlSource(configuration, element, parameterTypeClass);

        MappedStatement mappedStatement = MappedStatement.builder()
                .configuration(configuration)
                .id(currentNamespace + "." + id)
                .sqlCommandType(sqlCommandType)
                .sqlSource(sqlSource)
                .resultType(resultTypeClass)
                .build();

        // 添加解析 SQL
        configuration.addMappedStatement(mappedStatement);
    }

}
