package tree;

/**
 * 剑指 Offer68-I：二叉搜索树的最近公共祖先
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * @author dks233
 * @create 2022-05-06-14:39
 */
public class LowestAncestorBst {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class MethodOne {
        // 考虑搜索二叉树的性质，递归实现
        // 出现最低公共祖先节点的三种情况
        // 情况1：最低公共节点在head为头的二叉树的左子树中，nodeOne和nodeTwo的值都小于head.val
        // 情况2：最低公共节点在head为头的二叉树的右子树中，nodeOne和nodeTwo的值都大于head.val
        // 情况3：最低公共节点就是head，俩节点分别位于左子树和右子树，或者某一个节点位于head
        // 时间复杂度：O(N)
        // 空间复杂度：O(1)
        public TreeNode lowestCommonAncestor(TreeNode head, TreeNode nodeOne, TreeNode nodeTwo) {
            if (head == null) {
                return null;
            }
            if (nodeOne.val < head.val && nodeTwo.val < head.val) {
                return lowestCommonAncestor(head.left, nodeOne, nodeTwo);
            }
            if (nodeOne.val > head.val && nodeTwo.val > head.val) {
                return lowestCommonAncestor(head.right, nodeOne, nodeTwo);
            }
            return head;
        }
    }

    public static class MethodTwo {
        // 考虑搜索二叉树的性质，迭代实现
        // 情况1：最低公共节点在head为头的二叉树的左子树中，nodeOne和nodeTwo的值都小于head.val
        // 情况2：最低公共节点在head为头的二叉树的右子树中，nodeOne和nodeTwo的值都大于head.val
        // 情况3：最低公共节点就是head，俩节点分别位于左子树和右子树，或者某一个节点位于head
        // 时间复杂度：O(N)
        // 空间复杂度：O(N)
        public TreeNode lowestCommonAncestor(TreeNode head, TreeNode nodeOne, TreeNode nodeTwo) {
            if (head == null) {
                return null;
            }
            while (head != null) {
                if (nodeOne.val < head.val && nodeTwo.val < head.val) {
                    head = head.left;
                } else if (nodeOne.val > head.val && nodeTwo.val > head.val) {
                    head = head.right;
                } else {
                    return head;
                }
            }
            return null;
        }
    }

    // 不考虑二叉搜索树的性质，按照普通二叉树来做
    public static class MethodThree {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode nodeOne, TreeNode nodeTwo) {
            if (root == null) {
                return null;
            }
            return process(root, nodeOne, nodeTwo).minAncestor;
        }

        public Info process(TreeNode head, TreeNode nodeOne, TreeNode nodeTwo) {
            if (head == null) {
                return new Info(false, false, null);
            }
            Info leftInfo = process(head.left, nodeOne, nodeTwo);
            Info rightInfo = process(head.right, nodeOne, nodeTwo);
            boolean hasNodeOne = head == nodeOne || leftInfo.hasNodeOne || rightInfo.hasNodeOne;
            boolean hasNodeTwo = head == nodeTwo || leftInfo.hasNodeTwo || rightInfo.hasNodeTwo;
            TreeNode minAncestor = null;
            // 情况1：head是a和b的最低公共祖先
            if (leftInfo.hasNodeOne && rightInfo.hasNodeTwo || leftInfo.hasNodeTwo && rightInfo.hasNodeOne
                    || head == nodeOne || head == nodeTwo) {
                minAncestor = head;
            }
            // 情况2：最低公共祖先在左树上
            else if (leftInfo.minAncestor != null) {
                minAncestor = leftInfo.minAncestor;
            }
            // 情况2：最低公共祖先在右树上
            else if (rightInfo.minAncestor != null) {
                minAncestor = rightInfo.minAncestor;
            }
            // 刨除情况1和情况2，在head为头节点的树上找不到a和b的最低公共祖先，minAncestor=null
            return new Info(hasNodeOne, hasNodeTwo, minAncestor);
        }

        public static class Info {
            boolean hasNodeOne;
            boolean hasNodeTwo;
            TreeNode minAncestor;

            public Info(boolean hasNodeOne, boolean hasNodeTwo, TreeNode minAncestor) {
                this.hasNodeOne = hasNodeOne;
                this.hasNodeTwo = hasNodeTwo;
                this.minAncestor = minAncestor;
            }
        }
    }
}
