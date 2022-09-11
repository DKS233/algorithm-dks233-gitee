package tree;

import tree.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode257. 二叉树的所有路径
 *
 * @author dks233
 * @create 2022-09-11-20:40
 */
@SuppressWarnings("ALL")
public class AllPathsOfBinaryTree {
    List<String> list = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        process(root, "");
        List<String> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            result.add(list.get(i).substring(2, list.get(i).length()));
        }
        return result;
    }

    // 分析：左右子节点都等于null的时候结束递归
    public void process(TreeNode head, String curStr) {
        if (head == null) {
            return;
        }
        if (head.left == null && head.right == null) {
            list.add(curStr + "->" + head.val);
            return;
        }
        if (head.left != null) {
            process(head.left, curStr + "->" + head.val);
        }
        if (head.right != null) {
            process(head.right, curStr + "->" + head.val);
        }
    }
}
