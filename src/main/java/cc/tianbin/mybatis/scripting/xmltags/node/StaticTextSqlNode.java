package cc.tianbin.mybatis.scripting.xmltags.node;

import cc.tianbin.mybatis.scripting.xmltags.DynamicContext;

/**
 * 静态文本 SQL 节点
 * Created by nibnait on 2022/10/21
 */
public class StaticTextSqlNode implements SqlNode {

    private String text;

    public StaticTextSqlNode(String text) {
        this.text = text;
    }

    @Override
    public boolean apply(DynamicContext context) {
        // 将文本加入 context
        context.appendSql(text);
        return true;
    }
}
