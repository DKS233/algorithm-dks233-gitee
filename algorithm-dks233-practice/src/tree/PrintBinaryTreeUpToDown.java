package tree;

import java.util.*;

/**
 * 剑指Offer32-I：从上到下打印二叉树
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印
 * 分析：按层遍历
 * 剑指Offer32-II：从上到下打印二叉树II
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行
 * 分析：按层遍历，增加变量：当前层末尾节点，下一层末尾节点
 * 剑指Offer32-III：从上到下打印二叉树III
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 * 分析：按层遍历，增加变量：当前层末尾节点，下一层末尾节点，当前层
 * 类似题：class11求二叉树最大宽度：MaxWidth.java
 *
 * @author dks233
 * @create 2022-05-07-10:53
 */
public class PrintBinaryTreeUpToDown {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 时间复杂度：O(N)
    // 额外空间复杂度：O(N)
    // 剑指Offer32-III：从上到下打印二叉树III
    public static class ProblemThree {
        // 增加变量：当前层的末尾节点，下一层的末尾节点
        public List<List<Integer>> levelOrder(TreeNode head) {
            if (head == null) {
                return new ArrayList<>();
            }
            List<List<Integer>> lists = new ArrayList<>();
            List<Integer> singleList = new ArrayList<>(); // 存单层的节点值，单层遍历完后清空，存下一层的
            TreeNode curEnd = null; // 当前层末尾节点
            TreeNode nextEnd = null; // 下一层末尾节点
            int curLevel = 1; // 当前层是第几层
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
                // 当前节点到当前行的末尾
                if (head == curEnd) {
                    curEnd = nextEnd;
                    nextEnd = null;
                    // 如果当前层是单数层，由于singleList是按照从左往右顺序添加的，直接添加singleList
                    // 如果当前层是偶数层，就把singleList反转，然后添加反转后的singleList
                    lists.add(curLevel % 2 != 0 ? copyList(singleList) : reverseList(copyList(singleList)));
                    curLevel++;
                    singleList.clear();
                }
            }
            return lists;
        }

        public List<Integer> copyList(List<Integer> list) {
            return new ArrayList<>(list);
        }

        public List<Integer> reverseList(List<Integer> list) {
            Collections.reverse(list);
            return list;
        }
    }

    // 时间复杂度：O(N)
    // 额外空间复杂度：O(N)
    // 剑指Offer32-II：从上到下打印二叉树II
    public static class ProblemTwo {
        // 增加变量：当前层的末尾节点，下一层的末尾节点
        public List<List<Integer>> levelOrder(TreeNode head) {
            if (head == null) {
                return new ArrayList<>();
            }
            List<List<Integer>> lists = new ArrayList<>();
            List<Integer> singleList = new ArrayList<>(); // 存单层的节点值，单层遍历完后清空，存下一层的
            TreeNode curEnd = null; // 当前层末尾节点
            TreeNode nextEnd = null; // 下一层末尾节点
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
                // 当前节点到当前行的末尾
                if (head == curEnd) {
                    curEnd = nextEnd;
                    nextEnd = null;
                    lists.add(copyList(singleList));
                    singleList.clear();
                }
            }
            return lists;
        }

        public List<Integer> copyList(List<Integer> list) {
            return new ArrayList<>(list);
        }
    }

    // 时间复杂度：O(N)
    // 额外空间复杂度：O(N)
    // 常规按层遍历
    // 剑指Offer32-I：从上到下打印二叉树
    public static class ProblemOne {
        public int[] levelOrder(TreeNode head) {
            if (head == null) {
                return new int[0];
            }
            Queue<TreeNode> queue = new LinkedList<>();
            ArrayList<Integer> list = new ArrayList<>();
            queue.offer(head);
            while (!queue.isEmpty()) {
                head = queue.poll();
                list.add(head.val);
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
            int[] arr = new int[list.size()];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = list.get(i);
            }
            return arr;
        }
    }
}
