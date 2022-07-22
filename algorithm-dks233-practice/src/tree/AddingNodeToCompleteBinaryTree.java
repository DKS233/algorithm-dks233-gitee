package tree;

import java.util.*;

/**
 * 剑指offer专项突击版：剑指 Offer II 043. 往完全二叉树添加节点
 * 参考文档：https://leetcode.cn/problems/NaqhDT/solution/hua-luo-yue-que-shu-de-xue-xi-cong-zhe-l-ckpn/
 *
 * @author dks233
 * @create 2022-07-22-8:00
 */
@SuppressWarnings("ALL")
public class AddingNodeToCompleteBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 方法1：将所有节点添加到list中
    public static class CBTInserter {
        TreeNode root;
        List<List<TreeNode>> list; // 每一层的节点

        public CBTInserter(TreeNode root) {
            this.root = root;
            list = new ArrayList<>();
        }

        public int insert(int val) {
            levelOrder();
            // 节点只有一层，即只有根节点
            if (list.size() == 1) {
                root.left = new TreeNode(val);
                return root.val;
            }
            // 节点大于一层
            List<TreeNode> end = list.get(list.size() - 1);
            List<TreeNode> pre = list.get(list.size() - 2);
            // 如果是满二叉树，需要重新开一层
            if (end.size() == pre.size() * 2) {
                end.get(0).left = new TreeNode(val);
                return end.get(0).val;
            }
            // 如果不是满二叉树，节点插入到最后一层
            // 如果最后一层节点数是偶数，插入到左子树，如果是奇数，插入到右子树
            if (end.size() % 2 == 0) {
                pre.get(end.size() / 2).left = new TreeNode(val);
                return pre.get(end.size() / 2).val;
            } else {
                pre.get(end.size() / 2).right = new TreeNode(val);
                return pre.get(end.size() / 2).val;
            }
        }

        public TreeNode get_root() {
            return this.root;
        }

        public void levelOrder() {
            if (root == null) {
                return;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            List<TreeNode> singleList = new ArrayList<>();
            queue.offer(root);
            // 当前层节点最后一个
            TreeNode curEnd = root;
            // 下一层节点最后一个
            TreeNode nextEnd = null;
            // 当前遍历的节点
            TreeNode cur = null;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                singleList.add(cur);
                if (cur.left != null) {
                    queue.offer(cur.left);
                    nextEnd = cur.left;
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                    nextEnd = cur.right;
                }
                if (cur == curEnd) {
                    list.add(new ArrayList<>(singleList));
                    singleList.clear();
                    curEnd = nextEnd;
                    nextEnd = null;
                }
            }
        }
    }

    // 利用队列找到第一个左右孩子不全的节点
    // 不用每次插入都进行层序遍历
    // 队列中只保存孩子不全的节点
    public static class CBTInserter2 {
        TreeNode root;
        Queue<TreeNode> queue;
        boolean isLeft;

        public CBTInserter2(TreeNode root) {
            this.root = root;
            queue = new ArrayDeque<>();
            isLeft = true;
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode cur = queue.peek();
                if (cur.left != null) {
                    queue.add(cur.left);
                } else {
                    isLeft = true;
                    break;
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                    queue.poll();
                } else {
                    isLeft = false;
                    break;
                }
            }
        }

        public int insert(int val) {
            TreeNode addNode = new TreeNode(val);
            if (isLeft) {
                TreeNode node = queue.peek();
                node.left = addNode;
                queue.add(addNode);
                isLeft = !isLeft;
                return node.val;
            } else {
                TreeNode node = queue.poll();
                node.right = addNode;
                queue.add(addNode);
                isLeft = !isLeft;
                return node.val;
            }
        }

        public TreeNode get_root() {
            return this.root;
        }
    }
}
