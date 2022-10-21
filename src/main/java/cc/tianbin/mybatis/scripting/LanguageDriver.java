package cc.tianbin.mybatis.scripting;


import cc.tianbin.mybatis.mapping.SqlSource;
import cc.tianbin.mybatis.session.Configuration;
import org.dom4j.Element;

/**
 * 脚本语言驱动
 * Created by nibnait on 2022/10/21
 */
public interface LanguageDriver {

    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);

}
