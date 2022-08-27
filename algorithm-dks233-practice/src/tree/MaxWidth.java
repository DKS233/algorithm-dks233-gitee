package tree;

import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode662. 二叉树最大宽度
 *
 * @author dks233
 * @create 2022-08-27-0:01
 */
@SuppressWarnings("ALL")
public class MaxWidth {
    public static class MethodOne {
        public int widthOfBinaryTree(TreeNode head) {
            if (head == null) {
                return 1;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(head);
            List<Integer> indexList = new ArrayList<>();
            indexList.add(0);
            int maxSize = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    head = queue.poll();
                    Integer curIndex = indexList.remove(0);
                    if (head.left != null) {
                        queue.offer(head.left);
                        indexList.add(curIndex * 2 + 1);
                    }
                    if (head.right != null) {
                        queue.offer(head.right);
                        indexList.add(curIndex * 2 + 2);
                    }
                }
                if (indexList.size() > 1) {
                    maxSize = Math.max(indexList.get(indexList.size() - 1) - indexList.get(0) + 1, maxSize);
                }
            }
            return maxSize;
        }
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
