package other;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 剑指offer专项突击版：剑指 Offer II 007. 数组中和为 0 的三个数
 *
 * @author dks233
 * @create 2022-07-18-10:36
 */
@SuppressWarnings("ALL")
public class ThreeEqualsToZero {
    List<List<Integer>> list = new ArrayList<>();

    // 排序+去重
    // 思路：固定index位置的数，index位置后面设置左边界和右边界，在左右边界范围内进行求和
    // 去重：index位置去重，left去重，right去重
    // 时间复杂度：O(N*N)
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return list;
        }
        Arrays.sort(nums);
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] > 0) {
                break;
            }
            // index位置去重，该位置的数所有结果已经添加到list中
            if (index > 0 && nums[index] == nums[index - 1]) {
                continue;
            }
            int left = index + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[index] + nums[left] + nums[right];
                if (sum == 0) {
                    list.add(new ArrayList<>(Arrays.asList(nums[index], nums[left], nums[right])));
                    // left位置去重
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // right位置去重
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return list;
    }
}


















