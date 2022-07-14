package other;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode448. 找到所有数组中消失的数字
 * 参考文档：https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/solution/yi-zhang-dong-tu-bang-zhu-li-jie-yuan-di-uign/
 *
 * @author dks233
 * @create 2022-07-14-19:59
 */
@SuppressWarnings("ALL")
public class FindDisappearedNumbers {
    // 数组和索引对应关系：[1,2,3,4,5]->[0,1,2,3,4]
    // 第一次遍历数组
    // 对于nums[index]，如果nums[index]=4，令3位置的数变成负数，表示3位置匹配，如果3位置已经匹配，下一次不再需要变负
    // 第二次遍历数组
    // 对于nums[index]，如果nums[index]不为负，添加到list中，表示index位置匹配不上
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int index = 0; index < nums.length; index++) {
            if (nums[Math.abs(nums[index]) - 1] > 0) {
                nums[Math.abs(nums[index]) - 1] *= -1;
            }
        }
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] > 0) {
                list.add(index + 1);
            }
        }
        return list;
    }
}
