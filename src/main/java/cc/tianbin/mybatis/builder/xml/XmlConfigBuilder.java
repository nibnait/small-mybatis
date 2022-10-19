package cc.tianbin.mybatis.builder.xml;

import cc.tianbin.mybatis.builder.BaseBuilder;
import cc.tianbin.mybatis.builder.BuilderException;
import cc.tianbin.mybatis.datasource.DataSourceFactory;
import cc.tianbin.mybatis.mapping.BoundSql;
import cc.tianbin.mybatis.mapping.Environment;
import cc.tianbin.mybatis.mapping.MappedStatement;
import cc.tianbin.mybatis.mapping.SqlCommandType;
import cc.tianbin.mybatis.session.Configuration;
import cc.tianbin.mybatis.transaction.TransactionFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import javax.sql.DataSource;
import java.io.Reader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * XML配置构建器，建造者模式
 * Created by nibnait on 2022/10/18
 */
@Slf4j
public class XmlConfigBuilder extends BaseBuilder {

    private Element root;

    public XmlConfigBuilder(Reader reader) {
        // 调用父类初始化 Configuration
        super(new Configuration());

        // dom4j 处理 xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            root = document.getRootElement();
        } catch (DocumentException e) {
            log.error("XmlConfigBuilder init error", e);
        }
    }

    /**
     * 解析配置：类型别名、插件、对象工厂、对象包装工厂、设置、环境、类型转换、映射器
     */
    public Configuration parse() {
        try {
            // 环境
            environmentsElement(root.element("environments"));
            // 解析映射器
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new BuilderException(e, "Error parsing SQL Mapper Configuration. Cause: ");
        }
        return configuration;
    }

    /**
     * 解析 environments
     */
    private void environmentsElement(Element context) throws Exception {
        String environment = context.attributeValue("default");

        List<Element> environmentList = context.elements("environment");
        for (Element e : environmentList) {
            String id = e.attributeValue("id");
            if (!environment.equals(id)) {
                continue;
            }

            // 事物管理器
            TransactionFactory txFactory = (TransactionFactory) typeAliasRegistry.resolveAlias(e.element("transactionManager").attributeValue("type")).newInstance();

            // 数据源
            Element dataSourceElement = e.element("dataSource");
            DataSourceFactory dataSourceFactory = (DataSourceFactory) typeAliasRegistry.resolveAlias(dataSourceElement.attributeValue("type")).newInstance();
            List<Element> propertyList = dataSourceElement.elements("property");
            Properties props = new Properties();
            for (Element property : propertyList) {
                props.setProperty(property.attributeValue("name"), property.attributeValue("value"));
            }
            dataSourceFactory.setProperties(props);
            DataSource dataSource = dataSourceFactory.getDataSource();

            // 构建环境
            configuration.setEnvironment(Environment.builder()
                    .id(id)
                    .transactionFactory(txFactory)
                    .dataSource(dataSource)
                    .build());
        }

    }

    /**
     * 解析 mappers
     */
    private void mapperElement(Element mappers) throws Exception {
        List<Element> mapperList = mappers.elements("mapper");
        for (Element element : mapperList) {
            String resource = element.attributeValue("resource");
            Reader reader = Resources.getResourceAsReader(resource);

            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new InputSource(reader));
            Element root = document.getRootElement();
            // 命名空间
            String namespace = root.attributeValue("namespace");

            // SELECT
            parseSelectStatement(root.elements("select"), configuration, namespace);

            // 注册 Mapper 映射器
            configuration.addMapper(Resources.classForName(namespace));
        }
    }

    private void parseSelectStatement(List<Element> selectNodes, Configuration configuration, String namespace) {
        for (Element node : selectNodes) {
            String id = node.attributeValue("id");
            String parameterType = node.attributeValue("parameterType");
            String resultType = node.attributeValue("resultType");
            String sql = node.getText();

            // ? 匹配
            Map<Integer, String> parameter = new HashMap<>();
            Pattern pattern = Pattern.compile("(#\\{(.*?)})");
            Matcher matcher = pattern.matcher(sql);
            for (int i = 1; matcher.find(); i++) {
                String g1 = matcher.group(1);
                String g2 = matcher.group(2);
                parameter.put(i, g2);
                sql = sql.replace(g1, "?");
            }

            String nodeName = node.getName();
            MappedStatement mappedStatement = MappedStatement.builder()
                    .configuration(configuration)
                    .id(namespace + "." + id)
                    .sqlCommandType(SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH)))
                    .boundSql(BoundSql.builder()
                            .sql(sql)
                            .resultType(resultType)
                            .parameterMappings(parameter)
                            .parameterType(parameterType)
                            .build())
                    .build();
            configuration.addMappedStatement(mappedStatement);
        }
    }
}
