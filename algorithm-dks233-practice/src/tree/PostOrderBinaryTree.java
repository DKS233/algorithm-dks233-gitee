package tree;

/**
 * 剑指 Offer第二版 33. 二叉搜索树的后序遍历序列
 *
 * @author dks233
 * @create 2022-06-21-20:26
 */
public class PostOrderBinaryTree {
    // 时间复杂度：O(N*N)
    public boolean verifyPostorder(int[] postorder) {
        return isPostOrder(postorder, 0, postorder.length - 1);
    }

    // 判断left到right树上是否满足左子树的值小于root，右子树上的值大于root
    public boolean isPostOrder(int[] nums, int left, int right) {
        if (left >= right) {
            return true;
        }
        // 找到根节点
        int root = nums[right];
        // 在nums中找到第一个比root小的值mid
        // 如果tree是二叉搜索树，mid及其之后的值（除了root）都是root的右树上的节点，值大于root
        // mid之前的值都是root的左树上的节点，值小于root
        int mid = left;
        while (nums[mid] < root) {
            mid++;
        }
        // 判断mid及其之后的值是不是比root大（除了root节点）
        // 特殊情况：没有右子树，mid定位到了right位置
        for (int index = mid; index < right; index++) {
            if (nums[index] < root) {
                return false;
            }
        }
        // 判断mid之前的值是不是比root小
        for (int index = left; index < mid; index++) {
            if (nums[index] > root) {
                return false;
            }
        }
        // 当前mid满足条件，递归判断mid左边和mid右边（root左子树和右子树）
        return isPostOrder(nums, left, mid - 1) && isPostOrder(nums, mid, right - 1);
    }
}
