package tree;

import java.util.*;

/**
 * leetcode652. 寻找重复的子树
 *
 * @author dks233
 * @create 2022-09-05-8:35
 */
@SuppressWarnings("ALL")
public class FindDuplicateSubtrees {
    List<TreeNode> result = new ArrayList<>();
    // key：序列化后的字符串
    HashMap<String, TreeNode> map = new HashMap<>();
    HashSet<TreeNode> set = new HashSet<>();

    // 分析：序列化二叉树，符合条件的节点序列化出来的字符串肯定是相同的
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        preOrder(root);
        return new ArrayList<>(set);
    }

    public String preOrder(TreeNode head) {
        if (head == null) {
            return "none";
        }
        String curStr = head.val + "," + preOrder(head.left) + "," + preOrder(head.right);
        // 如果已经有重复节点，添加到set中，不覆盖
        if (map.containsKey(curStr)) {
            set.add(map.get(curStr));
        }
        // 没有重复节点就添加到map中
        else {
            map.put(curStr, head);
        }
        return curStr;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
