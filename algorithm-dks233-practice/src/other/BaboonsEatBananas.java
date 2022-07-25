package other;

/**
 * 剑指offer专项突击版：剑指 Offer II 073. 狒狒吃香蕉
 *
 * @author dks233
 * @create 2022-07-25-12:50
 */
public class BaboonsEatBananas {
    public int minEatingSpeed(int[] nums, int h) {
        // 找到最大速度和最小速度
        int minSpead = 1;
        int maxSpead = nums[0];
        for (int index = 0; index < nums.length; index++) {
            maxSpead = Math.max(maxSpead, nums[index]);
        }
        // 二分法找到符合条件的最小速度
        return process(nums, minSpead, maxSpead, h);
    }

    public int process(int[] nums, int left, int right, int h) {
        int minSpead = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int curTime = 0;
            for (int index = 0; index < nums.length; index++) {
                if (nums[index] % mid == 0) {
                    curTime += nums[index] / mid;
                } else {
                    // 题目要求：如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉
                    curTime += nums[index] / mid + 1;
                }
            }
            // 当前mid所用的时间小于等于h，符合要求，应该尝试速度更慢的情况，mid左移
            // 当前mid所用的时间大于h，不符合要求，应该尝试速度更快的情况，mid右移
            if (curTime <= h) {
                minSpead = Math.min(mid, minSpead);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return minSpead;
    }
}
