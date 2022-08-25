package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode103. 二叉树的锯齿形层序遍历
 *
 * @author dks233
 * @create 2022-08-24-23:38
 */
public class ZigZagTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    List<List<Integer>> list = new ArrayList<>();

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        levelOrder(root);
        return list;
    }

    public void levelOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        // 当前层最后一个节点
        TreeNode curEnd = null;
        // 下一层最后一个节点
        TreeNode nextEnd = null;
        // 当前遍历到了哪一层
        int curLevel = 0;
        // 当前层的节点
        List<Integer> curList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        curEnd = head;
        while (!queue.isEmpty()) {
            head = queue.poll();
            curList.add(head.val);
            if (head.left != null) {
                queue.offer(head.left);
                nextEnd = head.left;
            }
            if (head.right != null) {
                queue.offer(head.right);
                nextEnd = head.right;
            }
            if (head == curEnd) {
                curEnd = nextEnd;
                if (curLevel % 2 == 0) {
                    list.add(new ArrayList<>(curList));
                } else {
                    List<Integer> cur = new ArrayList<>();
                    for (int i = curList.size() - 1; i >= 0; i--) {
                        cur.add(curList.get(i));
                    }
                    list.add(cur);
                }
                curList.clear();
                curLevel += 1;
            }
        }
    }
}
