package week303;

import java.util.*;

/**
 * 6127. 优质数对的数目
 * 参考文档：https://leetcode.cn/problems/number-of-excellent-pairs/solution/deng-jie-zhuan-huan-pythonjavacgo-by-end-2qzs/
 *
 * @author dks233
 * @create 2022-07-24-10:18
 */
@SuppressWarnings("ALL")
public class Four {
    // 分析：c(x) 表示x二进制中1的个数
    // c(x|y)+c(x&y)=c(x)+c(y)
    // 按照二进制中1的个数将数组元素添加到集合中
    // 时间复杂度：O(N*N)
    public static class MethodOne {
        public long countExcellentPairs(int[] nums, int k) {
            // 数组元素去重
            HashSet<Integer> set = new HashSet<>();
            for (int index = 0; index < nums.length; index++) {
                set.add(nums[index]);
            }
            // key：二进制中1的个数
            // value：对应的数组元素
            HashMap<Integer, Integer> map = new HashMap<>();
            for (Integer number : set) {
                int bitCount = Integer.bitCount(number);
                map.put(bitCount, map.getOrDefault(bitCount, 0) + 1);
            }
            long count = 0;
            // 注：这里遍历包括自己和自己做运算
            for (Map.Entry<Integer, Integer> firstEntry : map.entrySet()) {
                for (Map.Entry<Integer, Integer> secondEntry : map.entrySet()) {
                    if (firstEntry.getKey() + secondEntry.getKey() >= k) {
                        count += (long) firstEntry.getValue() * secondEntry.getValue();
                    }
                }
            }
            return count;
        }
    }

}
