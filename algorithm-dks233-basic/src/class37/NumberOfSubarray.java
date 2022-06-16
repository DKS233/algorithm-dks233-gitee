package class37;

import java.util.HashSet;

/**
 * leetcode327：区间和的个数
 * 给定一个数组arr，和两个整数a和b（a<=b）。求arr中有多少个子数组，累加和在[a,b]这个范围上。返回达标的子数组数量
 *
 * @author dks233
 * @create 2022-06-16-10:15
 */
public class NumberOfSubarray {
    public int countRangeSum(int[] nums, int lower, int upper) {
        SbTree sbTree = new SbTree();
        long sum = 0; // 前缀和
        int ans = 0; // 符合条件的子数组数量
        // 初始情况下前缀和是0
        sbTree.add(0);
        // 结果=以i位置的数为结尾的情况下，前缀和落在[sum-upper,sum-lower]范围内的数量
        // 前缀和落在[sum-upper,sum-lower]范围内的前缀和数量=前缀和<sum-lower+1的前缀和数量-前缀和<sum-upper的前缀和数量
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int bigSumCount = sbTree.lessKeySize(sum - lower + 1);
            int smallSumCount = sbTree.lessKeySize(sum - upper);
            ans += bigSumCount - smallSumCount;
            // 将该前缀和添加到SB树中
            sbTree.add(sum);
        }
        return ans;
    }

    public static class SbNode {
        long key;
        SbNode left;
        SbNode right;
        int size; // 节点数量（不包括重复压入节点的数量）
        int all; // 总节点数量（包括重复压入节点的数量，前缀和可能发生重复）

        public SbNode(long key) {
            this.key = key;
            this.size = 1;
            this.all = 1;
        }
    }

    public static class SbTree {
        SbNode root;
        HashSet<Long> set = new HashSet<>();

        public SbNode rightRotate(SbNode cur) {
            // cur节点有几个（重复节点）
            int curSize = cur.all - (cur.left == null ? 0 : cur.left.all) - (cur.right == null ? 0 : cur.right.all);
            SbNode leftNode = cur.left;
            cur.left = leftNode.right;
            leftNode.right = cur;
            leftNode.size = cur.size;
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
            leftNode.all = cur.all;
            // cur包括的节点数量（包括重复压入的节点） = 左子树包括的节点数量 + 右子树包括的节点数量 + cur节点数量
            cur.all = (cur.left == null ? 0 : cur.left.all) + (cur.right == null ? 0 : cur.right.all) + curSize;
            return leftNode;
        }

        public SbNode leftRotate(SbNode cur) {
            // cur节点有几个（重复节点）
            int curSize = cur.all - (cur.left == null ? 0 : cur.left.all) - (cur.right == null ? 0 : cur.right.all);
            SbNode rightNode = cur.right;
            cur.right = rightNode.left;
            rightNode.left = cur;
            rightNode.size = cur.size;
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
            rightNode.all = cur.all;
            // cur包括的节点数量（包括重复压入的节点） = 左子树包括的节点数量 + 右子树包括的节点数量 + cur节点数量
            cur.all = (cur.left == null ? 0 : cur.left.all) + (cur.right == null ? 0 : cur.right.all) + curSize;
            return rightNode;
        }

        public SbNode maintain(SbNode cur) {
            int leftSize = cur.left == null ? 0 : cur.left.size;
            int rightSize = cur.right == null ? 0 : cur.right.size;
            int leftLeftSize = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
            int leftRightSize = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
            int rightLeftSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;
            int rightRightSize = cur.right != null && cur.right.right != null ? cur.right.right.size : 0;
            // LL LR RL RR
            // LL 右旋
            if (leftLeftSize > rightSize) {
                cur = rightRotate(cur);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            // LR
            else if (leftRightSize > rightSize) {
                cur.left = leftRotate(cur.left);
                cur = rightRotate(cur);
                cur.right = maintain(cur.right);
                cur.left = maintain(cur.left);
                cur = maintain(cur);
            }
            // RR
            else if (rightRightSize > leftSize) {
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur = maintain(cur);
            }
            // RL
            else if (rightLeftSize > leftSize) {
                cur.right = rightRotate(cur.right);
                cur = leftRotate(cur);
                cur.right = maintain(cur.right);
                cur.left = maintain(cur.left);
                cur = maintain(cur);
            }
            return cur;
        }

        // 将一个前缀和添加到SB树中
        // 如果set中已经有sum，说明树中已经有sum，进行all的更新
        // 如果set中没有sum，说明树中没有sum，需要进行添加操作，size和all都需要更新
        public void add(long sum) {
            boolean containsKey = set.contains(sum);
            root = add(root, sum, containsKey);
            set.add(sum);
        }

        // containsKey:cur为头结点的数中是否已有了sum这个key
        // 如果没有sum
        public SbNode add(SbNode cur, long sum, boolean containsKey) {
            if (cur == null) {
                return new SbNode(sum);
            } else {
                cur.all++;
                if (cur.key == sum) {
                    return cur;
                } else {
                    // 如果当前树中不包括sum，沿途节点的size都要++
                    // 如果当前树中包括sum，沿途节点size不变
                    if (!containsKey) {
                        cur.size++;
                    }
                    // cur.key > sum 去左子树添加，cur.size变大
                    if (cur.key > sum) {
                        cur.left = add(cur.left, sum, containsKey);
                    }
                    // cur.key < cum 去右子树添加，cur.size变大
                    else {
                        cur.right = add(cur.right, sum, containsKey);
                    }
                }
                return maintain(cur);
            }
        }

        // 求key（前缀和）小于sum的节点数量（包括重复压入的节点）
        public int lessKeySize(long sum) {
            SbNode cur = this.root;
            int ans = 0;
            while (cur != null) {
                // 当前key等于sum，ans+=左子节点的all，然后return
                if (cur.key == sum) {
                    ans += (cur.left == null ? 0 : cur.left.all);
                    return ans;
                }
                // 当前key小于sum，去右子节点找，ans+=当前节点和其左子树的all
                else if (cur.key < sum) {
                    ans += cur.all - (cur.right == null ? 0 : cur.right.all);
                    cur = cur.right;
                }
                // 当前key大于sum，去左子节点找，ans不累加
                else {
                    cur = cur.left;
                }
            }
            return ans;
        }

        // 求key（前缀和）大于sum的节点数量（包括重复压入的节点）
        // (整棵树的节点数量) - (<=sum+1的节点数量)
        public int moreKeySize(long sum) {
            return root == null ? 0 : root.all - lessKeySize(sum + 1);
        }
    }
}
