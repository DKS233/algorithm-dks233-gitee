package other;

/**
 * leetcode11. 盛最多水的容器
 * 参考文档：https://leetcode.cn/problems/container-with-most-water/solution/on-shuang-zhi-zhen-jie-fa-li-jie-zheng-que-xing-tu/
 *
 * @author dks233
 * @create 2022-06-30-16:16
 */
public class ContainerWithMostWater {
    // 双指针法
    // 双指针起始位置为两边，假设两个指针中较高的为high，较低的为low，距离为width
    // 水量=low*width，如果high向内移动，水量减小，因为水量由较低的决定，如果low向内移动，水量可能增加
    // 如果两个指针位置高度一样，同时向内移动，因为以当前边界来看（不看总体），当前边界的最大水量=高度乘宽度，移动一个只会变小
    // 时间复杂度：O(N)
    public int maxArea(int[] height) {
        if (height == null || height.length < 1) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;
        while (left < right) {
            int singleMaxWater = (right - left) * Math.min(height[left], height[right]);
            maxWater = Math.max(singleMaxWater, maxWater);
            if (height[left] < height[right]) {
                left++;
            } else if (height[left] > height[right]) {
                right--;
            } else {
                left++;
                right--;
            }
        }
        return maxWater;
    }
}
