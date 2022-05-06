package tree;

import java.util.HashMap;

/**
 * 剑指offer07：重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字
 * 分析：前序遍历：头左右；中序遍历：左头右
 * （1）前序遍历第一个元素是根节点的值
 * （2）根据根节点的值找到根节点在中序遍历中的位置，将中序遍历数组划分为[左子树 根节点 右子树]
 * （3）根据中序遍历中左子树和右子树的元素数量将前序遍历数组划分为[根节点 左子树 右子树]，确定根节点，
 * 左子树根节点，右子树根节点
 * （4）对于左右子树，找到左右子树的根节点，划分左右子树
 * O(N)+O(N)
 *
 * @author dks233
 * @create 2022-05-06-16:14
 */
public class RebuildBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Rebuild {

        int[] preOrder; // 前序遍历数组
        HashMap<Integer, Integer> midOrderMap; // 中序遍历数组

        public TreeNode buildTree(int[] preorder, int[] midOrder) {
            this.preOrder = preorder;
            midOrderMap = new HashMap<Integer, Integer>();
            for (int i = 0; i < midOrder.length; i++) {
                midOrderMap.put(midOrder[i], i);
            }
            return process(0, 0, midOrder.length - 1);
        }

        /**
         * @param headIndexPre  前序遍历中子树头节点的索引位置
         * @param leftIndexMid  中序遍历子树左边界
         * @param rightIndexMid 中序遍历子树右边界
         * @return 递归过程依次生成的头节点
         */
        private TreeNode process(int headIndexPre, int leftIndexMid, int rightIndexMid) {
            if (leftIndexMid > rightIndexMid) {
                return null;
            }
            // 获取头节点在中序遍历数组中的位置
            int headIndexMid = midOrderMap.get(preOrder[headIndexPre]);
            // 创建根节点
            TreeNode head = new TreeNode(preOrder[headIndexPre]);
            // 获取中序遍历中左子树右子树的长度
            // 根据左右子树长度划分前序数组，找左右子树的根节点
            // 然后根据左右子树根节点进行下一轮划分
            head.left = process(headIndexPre + 1, leftIndexMid, headIndexMid - 1);
            head.right = process(headIndexPre + (headIndexMid - leftIndexMid) + 1,
                    headIndexMid + 1, rightIndexMid);
            return head;
        }
    }
}
