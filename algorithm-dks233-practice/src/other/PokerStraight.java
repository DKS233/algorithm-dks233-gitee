package other;

import java.util.HashSet;

/**
 * 剑指 Offer第二版 61. 扑克牌中的顺子
 * [0,0,1,2,5]是顺子，因为0可以看成癞子，变成3和4，组成顺子
 * 数组中的数字可以是不连续的，主要可以组成顺子就行
 *
 * @author dks233
 * @create 2022-06-19-18:01
 */
public class PokerStraight {
    public static class MethodOne {
        // 五张牌组成顺子的两个充分条件
        // 条件1：除0外其他牌不能有重复值
        // 条件2：除0外其他牌最大值最小值的差小于5
        // O(N)+O(N)
        public boolean isStraight(int[] nums) {
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            HashSet<Integer> hashSet = new HashSet<>();
            int otherCount = 0; // 除0外其他数的个数
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    // 如果重复值，hashSet的size肯定和otherCount不一样
                    hashSet.add(nums[i]);
                    max = Math.max(max, nums[i]);
                    min = Math.min(min, nums[i]);
                    otherCount++;
                }
            }
            // 除0外其他数有重复值，返回false
            // 除0外其他数的最大值最小值差不小于5，返回false
            if (hashSet.size() != otherCount || max >= min + 5) {
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        MethodOne methodOne = new MethodOne();
        System.out.println(methodOne.isStraight(new int[]{1, 6, 5, 4, 2}));
    }
}
