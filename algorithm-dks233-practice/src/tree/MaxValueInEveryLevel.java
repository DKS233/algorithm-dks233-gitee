package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指Offer专项突击版：剑指OfferII044：二叉树每层的最大值
 * 分析：按层遍历，加几个变量：当前层末尾节点，下一层末尾变量，当前层最大值
 *
 * @author dks233
 * @create 2022-05-08-21:30
 */
public class MaxValueInEveryLevel {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public List<Integer> largestValues(TreeNode head) {
        List<Integer> list = new ArrayList<>();
        if (head == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode curEnd = null; // 当前层末尾
        TreeNode nextEnd = null; // 下一层末尾
        int curLevelMaxValue = Integer.MIN_VALUE; // 当前层最大值
        queue.offer(head);
        curEnd = head;
        while (!queue.isEmpty()) {
            head = queue.poll();
            curLevelMaxValue = Math.max(curLevelMaxValue, head.val);
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
                list.add(curLevelMaxValue);
                curEnd = nextEnd;
                nextEnd = null;
                curLevelMaxValue = Integer.MIN_VALUE;
            }
        }
        return list;
    }
}
