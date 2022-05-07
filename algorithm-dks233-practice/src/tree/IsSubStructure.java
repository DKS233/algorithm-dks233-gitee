package tree;

/**
 * 剑指Offer26：树的子结构
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 * 分析：B是A的子结构三种情况
 * 情况1：A的根节点和B的根节点一样（以A为根节点的子树包含树B）
 * 情况2：树B是树A左子树的子结构
 * 情况3：树B是树A右子树的子结构
 * 时间复杂度O(MN) M,N分别为树A和树B的节点数量，先序遍历树A占用O(M)，每次调用process判断占用O(N)
 *
 * @author dks233
 * @create 2022-05-06-22:00
 */
public class IsSubStructure {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // headOne:A
    // headTwo:B
    // 判断B是不是A的子结构
    // 分析：前序遍历：相同的结构最先是头
    public boolean isSubStructure(TreeNode headOne, TreeNode headTwo) {
        // 约定空树不是任意一个树的子结构
        if (headTwo == null) {
            return false;
        }
        // 非空树肯定不是空树的子结构
        if (headOne == null) {
            return false;
        }
        // 情况1：A的根节点和B的根节点一样（以A为根节点的子树包含树B）
        if (headOne.val == headTwo.val && process(headOne.left, headTwo.left)
                && process(headOne.right, headTwo.right)) {
            return true;
        }
        // 情况2：树B是树A左子树的子结构
        // 情况3：树B是树A右子树的子结构
        return isSubStructure(headOne.left, headTwo) || isSubStructure(headOne.right, headTwo);
    }

    /**
     * 判断以headOne为根节点的子树是否包含树headTwo
     * 即headOne和headTwo的两棵树根节点一样
     *
     * @param headOne A
     * @param headTwo B
     * @return 以headOne为根节点的子树是否包含树headTwo
     */
    public boolean process(TreeNode headOne, TreeNode headTwo) {
        // 当节点headTwo为空：说明树headTwo已匹配完成（越过叶子节点），因此返回 true
        if (headTwo == null) {
            return true;
        }
        // 当节点headOne为空，说明树headOne匹配失败，返回false
        if (headOne == null) {
            return false;
        }
        if (headOne.val == headTwo.val) {
            return process(headOne.left, headTwo.left) && process(headOne.right, headTwo.right);
        } else {
            return false;
        }
    }
}
