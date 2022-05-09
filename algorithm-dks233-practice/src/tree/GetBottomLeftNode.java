package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指offer专项突击版：剑指OfferII045：二叉树最底层最左边的值
 * 给定一个二叉树的根节点 root，请找出该二叉树的最底层最左边节点的值。假设二叉树中至少有一个节点。
 *
 * @author dks233
 * @create 2022-05-08-21:43
 */
public class GetBottomLeftNode {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 方法1：按层遍历，List<List<Integer>>存每一层的元素，然后取最后一层的第一个元素
    // 时间复杂度：O(N)
    // 空间复杂度：O(N)
    public static class MethodOne {
        public int findBottomLeftValue(TreeNode head) {
            List<List<Integer>> lists = getList(head);
            return lists.get(lists.size() - 1).get(0);
        }

        public List<List<Integer>> getList(TreeNode head) {
            List<List<Integer>> lists = new LinkedList<>();
            List<Integer> singleList = new LinkedList<>();
            if (head == null) {
                return new ArrayList<>();
            }
            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode curEnd = null; // 当前层末尾
            TreeNode nextEnd = null; // 下一层末尾
            queue.offer(head);
            curEnd = head;
            while (!queue.isEmpty()) {
                head = queue.poll();
                singleList.add(head.val);
                if (head.left != null) {
                    queue.offer(head.left);
                    nextEnd = head.left;
                }
                if (head.right != null) {
                    queue.offer(head.right);
                    nextEnd = head.right;
                }
                // 遍历到当前层的末尾，即将进入下一层的遍历
                if (head == curEnd) {
                    curEnd = nextEnd;
                    nextEnd = null;
                    lists.add(new ArrayList<>(singleList));
                    singleList.clear();
                }
            }
            return lists;
        }
    }
}
