package tree;

import sun.reflect.generics.tree.Tree;

import java.util.HashMap;

/**
 * leetcode105. 从前序与中序遍历序列构造二叉树
 *
 * @author dks233
 * @create 2022-07-11-23:26
 */
public class PreAndMidToBst {
    // 存中序遍历所有节点所在的索引
    HashMap<Integer, Integer> map = new HashMap<>();
    int[] preOrder;

    // 前序遍历：头 左 右
    // 中序遍历：左 头 右
    // 根据前序遍历找到头节点，然后再去中序中找到头节点，中序遍历头节点左边就是左子树，右边就是右子树
    // 根据中序遍历中左右子树的数目去前序遍历中定位到左右子树的头节点，然后递归
    public TreeNode buildTree(int[] preOrder, int[] midOrder) {
        this.preOrder = preOrder;
        // 中序遍历索引存到map集合中
        for (int index = 0; index < midOrder.length; index++) {
            map.put(midOrder[index], index);
        }
        return process(0, 0, midOrder.length - 1);
    }

    /**
     * @param headPreOrder  子树头节点在前序遍历中的索引
     * @param leftMidOrder  子树在中序遍历中的左边界
     * @param rightMidOrder 子树在中序遍历中的右边界
     * @return 头节点
     */
    public TreeNode process(int headPreOrder, int leftMidOrder, int rightMidOrder) {
        if (leftMidOrder > rightMidOrder) {
            return null;
        }
        TreeNode head = new TreeNode(preOrder[headPreOrder]);
        // 找到头节点在中序遍历中的位置
        Integer headMidOrder = map.get(preOrder[headPreOrder]);
        // 根据子树的左右边界计算左右子树的大小
        int leftSize = headMidOrder - leftMidOrder;
        int rightSize = rightMidOrder - headMidOrder;
        // 根据左右子树的大小去前序遍历中定位左右子树的头节点
        head.left = process(headPreOrder + 1, leftMidOrder, headMidOrder - 1);
        head.right = process(headPreOrder + leftSize + 1, headMidOrder + 1, rightMidOrder);
        return head;
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
