package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指offer专项突击版：剑指OfferII046：二叉树的右侧视图
 * 给定一个二叉树的根节点root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值
 *
 * @author dks233
 * @create 2022-05-09-9:13
 */
public class RightSideView {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 方法1：按层遍历，记录所有层的节点，然后取每层节点的最后一个
    // 时间复杂度：O(N)
    // 空间复杂度：O(N)
    public static class MethodOne {
        public List<Integer> rightSideView(TreeNode head) {
            List<List<Integer>> lists = new ArrayList<>(); // 所有层的节点
            List<Integer> singleList = new ArrayList<>(); // 当前层的节点
            if (head == null) {
                return new ArrayList<>();
            }
            TreeNode curEnd = null; // 记录当前层的最后一个节点
            TreeNode nextEnd = null; // 记录下一层的最后一个节点
            Queue<TreeNode> queue = new LinkedList<>();
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
                // 遍历到了当前层末尾，即将遍历下一层
                if (head == curEnd) {
                    lists.add(new ArrayList<>(singleList));
                    curEnd = nextEnd;
                    nextEnd = null;
                    singleList.clear();
                }
            }
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < lists.size(); i++) {
                result.add(lists.get(i).get(lists.get(i).size() - 1));
            }
            return result;
        }
    }
}












