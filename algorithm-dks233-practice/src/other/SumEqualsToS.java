package other;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer第二版 57 - II. 和为s的连续正数序列
 * 参考文档：https://leetcode.cn/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/solution/shi-yao-shi-hua-dong-chuang-kou-yi-ji-ru-he-yong-h/
 *
 * @author dks233
 * @create 2022-06-19-11:14
 */
public class SumEqualsToS {
    // 滑动窗口，左闭右开的一个区间
    // 滑动窗口的和小于target，右边界右移
    // 滑动窗口的和大于target，左边界左移
    // 滑动窗口的和等于target，添加记录，左边界右移
    public int[][] findContinuousSequence(int target) {
        List<List<Integer>> list = new ArrayList<>();
        // 初始化：滑动窗口长度为0，滑动窗口的和是0
        int left = 1, right = 1, sum = 0;
        // 最多左窗口滑到target/2，之后滑动窗口的和肯定大于target
        while (left <= target / 2) {
            if (sum < target) {
                sum += right;
                right++;
            } else if (sum > target) {
                sum -= left;
                left++;
            } else {
                List<Integer> singleList = new ArrayList<>();
                for (int i = left; i < right; i++) {
                    singleList.add(i);
                }
                list.add(singleList);
                sum -= left;
                left++;
            }
        }
        int[][] ans = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            List<Integer> singleList = list.get(i);
            ans[i] = new int[singleList.size()];
            for (int index = 0; index < ans[i].length; index++) {
                ans[i][index] = singleList.get(index);
            }
        }
        return ans;
    }
}
