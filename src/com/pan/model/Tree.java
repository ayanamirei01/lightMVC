package com.pan.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-04 19:57
 * @Version 1.0
 */
@Setter
@Getter
public class Tree {
    private String  id;//节点ID，对加载远程数据很重要。
    private String text;//显示节点文本。
    private String state = "closed"; //节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
    private boolean checked;//表示该节点是否被选中。
    private Object attributes;// 被添加到节点的自定义属性。
    private List<Tree> children = new ArrayList<Tree>();// 一个节点数组声明了若干节点。
    private String pid;
    private String iconCls;//显示的节点图标CSS类ID。

}
