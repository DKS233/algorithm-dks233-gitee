package tree;

import javax.swing.tree.TreeModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode623.在二叉树中增加一行
 *
 * @author dks233
 * @create 2022-08-29-23:27
 */
@SuppressWarnings("ALL")
public class AddARowInBinaryTree {
    // 方法2：dfs过程中进行插入，不用全部统计
    public static class MethodTwo {
        public TreeNode addOneRow(TreeNode root, int val, int depth) {
            if (depth == 1) {
                TreeNode newHead = new TreeNode(val);
                newHead.left = root;
                return newHead;
            }
            dfs(root, 1, depth, val);
            return root;
        }

        public void dfs(TreeNode head, int curDepth, int depth, int val) {
            if (head == null) {
                return;
            }
            if (curDepth == depth - 1) {
                TreeNode leftNode = new TreeNode(val);
                TreeNode rightNode = new TreeNode(val);
                leftNode.left = head.left;
                rightNode.right = head.right;
                head.left = leftNode;
                head.right = rightNode;
            } else {
                dfs(head.left, curDepth + 1, depth, val);
                dfs(head.right, curDepth + 1, depth, val);
            }
        }
    }

    // 方法1：统计每个节点的节点深度，然后进行插入
    public static class MethodOne {
        // 根节点深度为1
        // 分析：map存每个节点对应的深度
        HashMap<TreeNode, Integer> map = new HashMap<>();

        public TreeNode addOneRow(TreeNode root, int val, int depth) {
            if (depth == 1) {
                TreeNode newHead = new TreeNode(val);
                newHead.left = root;
                return newHead;
            }
            map.put(root, 1);
            // 计算每个节点对应的深度
            process(root);
            // 找到深度为depth-1的节点和depth的节点，中间加入val代表节点
            List<TreeNode> preNodes = new ArrayList<>();
            for (Map.Entry<TreeNode, Integer> entry : map.entrySet()) {
                TreeNode curNode = entry.getKey();
                Integer curDepth = entry.getValue();
                if (curDepth == depth - 1) {
                    preNodes.add(curNode);
                }
            }
            for (int i = 0; i < preNodes.size(); i++) {
                TreeNode preNode = preNodes.get(i);
                if (preNode.left != null) {
                    TreeNode left = preNode.left;
                    preNode.left = new TreeNode(val);
                    preNode.left.left = left;
                } else {
                    preNode.left = new TreeNode(val);
                }
                if (preNode.right != null) {
                    TreeNode right = preNode.right;
                    preNode.right = new TreeNode(val);
                    preNode.right.right = right;
                } else {
                    preNode.right = new TreeNode(val);
                }
            }
            return root;
        }

        public void process(TreeNode head) {
            if (head.left != null) {
                map.put(head.left, map.get(head) + 1);
                process(head.left);
            }
            if (head.right != null) {
                map.put(head.right, map.get(head) + 1);
                process(head.right);
            }
        }
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
