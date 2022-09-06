package other;

import java.util.LinkedList;
import java.util.List;

/**
 * leetcode163. 缺失的区间
 *
 * @author dks233
 * @create 2022-09-05-11:07
 */
@SuppressWarnings("ALL")
public class MissingInterval {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        LinkedList<String> list = new LinkedList<>();
        // 单独判断两边
        if (nums.length == 0) {
            list.add(getStr(lower, upper));
            return list;
        }
        int index = 0;
        while (index < nums.length) {
            while (index < nums.length - 1 && nums[index] + 1 == nums[index + 1]) {
                index++;
            }
            if (index == nums.length - 1) {
                break;
            }
            int leftNum = nums[index] + 1;
            int rightNum = nums[index + 1] - 1;
            list.add(getStr(leftNum, rightNum));
            index++;
        }
        if (nums[0] > lower) {
            list.addFirst(getStr(lower, nums[0] - 1));
        }
        if (nums[nums.length - 1] < upper) {
            list.addLast(getStr(nums[nums.length - 1] + 1, upper));
        }
        return list;
    }

    public String getStr(int num1, int num2) {
        if (num1 == num2) {
            return num1 + "";
        } else {
            return num1 + "->" + num2;
        }
    }
}
