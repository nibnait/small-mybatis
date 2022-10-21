package cc.tianbin.mybatis.scripting.xmltags.node;

import cc.tianbin.mybatis.scripting.xmltags.DynamicContext;

import java.util.List;

/**
 * 混合 SQL 节点
 * Created by nibnait on 2022/10/21
 */
public class MixedSqlNode implements SqlNode {

    // 组合模式，拥有一个 SQLNode 的 List
    private List<SqlNode> contents;

    public MixedSqlNode(List<SqlNode> contents) {
        this.contents = contents;
    }

    @Override
    public boolean apply(DynamicContext context) {
        // 依次调用 list 里每个元素的 apply
        contents.forEach(node -> node.apply(context));
        return true;
    }
}
