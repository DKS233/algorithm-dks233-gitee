package dp;

/**
 * leetcode2348. 全 0 子数组的数目
 * 参考文档：https://leetcode.cn/problems/number-of-zero-filled-subarrays/solution/java-by-kayleh-s3xx/
 *
 * @author dks233
 * @create 2022-07-25-22:35
 */
@SuppressWarnings("ALL")
public class NumberOfAllZeroSubarrays {
    // 方法1和方法2代码一样
    // 方法1思路
    // 1个0  1个子数组
    // 2个0  3个子数组
    // 3个0  6个子数组
    // 4个0  10个子数组
    // 连续的n个0对答案的贡献 = 连续的n-1个0对答案的贡献 + n
    // 方法2思路
    // 考虑每个以最右一个0为结尾的子数组数
    // 1个0 以最右一个0为结尾子数组数为1
    // 2个0 以最右一个0为结尾子数组数为2
    // 3个0 以最右一个0为结尾子数组数为3
    // 4个0 以最右一个0为结尾子数组数为4
    // n个0 以最右一个0为结尾子数组数为n
    // 各个位置的0为结尾子数组数加起来就是总的子数组数
    public long zeroFilledSubarray(int[] nums) {
        long ans = 0;
        // 当前子数组0的个数
        long cur = 0;
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] == 0) {
                cur++;
                ans += cur;
            } else {
                cur = 0;
            }
        }
        return ans;
    }
}
