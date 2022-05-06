package tree;

/**
 * 剑指offer68：二叉树的最近公共祖先
 * 给定头节点head，节点a，节点b，返回a和b的最低公共祖先
 * 分析：用二叉树递归套路做
 * 分析：最低公共祖先的两种情况
 * 设head为每轮递归过程中的头节点
 * 情况1：head是a和b的最低公共祖先
 * a和b可能一个在左树上，一个在右树上，可能a就是最低公共祖先(head)，可能b就是最低公共祖先(head)
 * 情况2：head不是a和b的最低公共祖先
 * 最低公共祖先在左树上，最低公共祖先在右树上
 * 递归套路中只考虑能找到最低公共祖先的情况，不符合情况1和情况2的状况：head为头节点的树中a和b不全，表示在head为头的
 * 子树中未找到a和b的最低公共祖先，该子树返回的最低公共祖先是null
 * 需要知道左树右树的信息：左树右树有没有a，左树右树有没有b，左树右树中a和b的最低公共祖先
 * 需要返回的子树信息：有没有a，有没有b，a和b的最低公共祖先
 *
 * @author dks233
 * @create 2022-05-06-14:39
 */
public class LowestCommonAncestor {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

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
