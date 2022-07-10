package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode102. 二叉树的层序遍历
 *
 * @author dks233
 * @create 2022-07-10-21:48
 */
@SuppressWarnings("ALL")
public class LevelOrder {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    List<List<Integer>> list = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode head) {
        if (head == null) {
            return list;
        }
        // 当前层最后一个节点
        TreeNode curLastNode = head;
        // 下一层最后一个节点
        TreeNode nextLastNode = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        List<Integer> singleList = new ArrayList<>();
        while (!queue.isEmpty()) {
            head = queue.poll();
            singleList.add(head.val);
            if (head.left != null) {
                queue.offer(head.left);
                nextLastNode = head.left;
            }
            if (head.right != null) {
                queue.offer(head.right);
                nextLastNode = head.right;
            }
            // 如果弹出的是当前层最后一个节点，更新curLast，表示进入下一层
            // 将当前层节点添加到list中，清空singleList，用于记录下一层节点
            if (head == curLastNode) {
                curLastNode = nextLastNode;
                nextLastNode = null;
                list.add(new ArrayList<>(singleList));
                singleList.clear();
            }
        }
        return list;
    }
}





















