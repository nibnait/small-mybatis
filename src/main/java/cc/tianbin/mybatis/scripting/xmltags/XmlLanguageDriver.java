package cc.tianbin.mybatis.scripting.xmltags;

import cc.tianbin.mybatis.mapping.SqlSource;
import cc.tianbin.mybatis.scripting.LanguageDriver;
import cc.tianbin.mybatis.session.Configuration;
import org.dom4j.Element;

/**
 * Created by nibnait on 2022/10/21
 */
public class XmlLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        // 用 XML 脚本构建器解析
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }
}
