package cc.young.common.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * description: TreeNode
 * date: 2020/9/21 15:29
 * author: faner
 */
@Data
public class TreeNode {

    protected Integer id;

    protected Integer pid;

    private Integer level = 1;

    protected List<TreeNode> children = new ArrayList<TreeNode>();

    public void add(TreeNode node) {
        children.add(node);
    }

}
