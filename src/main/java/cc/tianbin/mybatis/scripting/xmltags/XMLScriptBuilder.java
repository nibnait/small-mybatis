package cc.tianbin.mybatis.scripting.xmltags;

import cc.tianbin.mybatis.builder.BaseBuilder;
import cc.tianbin.mybatis.mapping.SqlSource;
import cc.tianbin.mybatis.scripting.defaults.RawSqlSource;
import cc.tianbin.mybatis.scripting.xmltags.node.MixedSqlNode;
import cc.tianbin.mybatis.scripting.xmltags.node.SqlNode;
import cc.tianbin.mybatis.scripting.xmltags.node.StaticTextSqlNode;
import cc.tianbin.mybatis.session.Configuration;
import com.google.common.collect.Lists;
import org.dom4j.Element;

import java.util.List;

/**
 * Created by nibnait on 2022/10/21
 */
public class XMLScriptBuilder extends BaseBuilder {

    private Element element;
    private boolean isDynamic;
    private Class<?> parameterType;

    public XMLScriptBuilder(Configuration configuration, Element element, Class<?> parameterType) {
        super(configuration);
        this.element = element;
        this.parameterType = parameterType;
    }

    public SqlSource parseScriptNode() {
        List<SqlNode> contents = parseDynamicTags(element);
        MixedSqlNode rootSqlNode = new MixedSqlNode(contents);
        return new RawSqlSource(configuration, rootSqlNode, parameterType);
    }

    private List<SqlNode> parseDynamicTags(Element element) {
        // 拿到 sql
        String data = element.getText();
        return Lists.newArrayList(new StaticTextSqlNode(data));
    }
}
