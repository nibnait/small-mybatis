package cc.tianbin.mybatis.scripting.xmltags.node;

import cc.tianbin.mybatis.scripting.xmltags.DynamicContext;

/**
 * Created by nibnait on 2022/10/21
 */
public interface SqlNode {

    boolean apply(DynamicContext context);
}
