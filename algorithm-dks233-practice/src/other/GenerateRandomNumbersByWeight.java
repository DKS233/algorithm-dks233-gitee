package other;

/**
 * 剑指offer专项突击版：剑指 Offer II 071. 按权重生成随机数
 *
 * @author dks233
 * @create 2022-07-25-23:44
 */
@SuppressWarnings("ALL")
public class GenerateRandomNumbersByWeight {
    // 前缀和
    // 时间复杂度：构造：O(N) pick：O(N)
    public static class SolutionOne {
        int[] preSums; // 前缀和数组，preSums[index]表示index之前元素的权重和

        public SolutionOne(int[] w) {
            preSums = new int[w.length + 1];
            preSums[0] = 0;
            int preSum = 0;
            for (int index = 0; index < w.length; index++) {
                preSum += w[index];
                preSums[index + 1] = preSum;
            }
        }

        public int pickIndex() {
            // 计算随机的权重值（之前的所有权重值的和），范围：[1,preSums[w.length]]
            int random = (int) (Math.random() * (preSums[preSums.length - 1])) + 1;
            // 判断该权重值落在哪个区间内
            for (int index = 0; index < preSums.length; index++) {
                if (preSums[index] >= random) {
                    return index - 1;
                }
            }
            return 0;
        }
    }

    // 二分法+前缀和
    // 时间复杂度：构造：O(N) pick：O(logN)
    public static class Solution {
        int[] preSums; // 前缀和数组，preSums[index]表示index之前元素的权重和

        public Solution(int[] w) {
            preSums = new int[w.length + 1];
            preSums[0] = 0;
            int preSum = 0;
            for (int index = 0; index < w.length; index++) {
                preSum += w[index];
                preSums[index + 1] = preSum;
            }
        }

        public int pickIndex() {
            // 计算随机的权重值（之前的所有权重值的和），范围：[1,preSums[w.length]]
            int random = (int) (Math.random() * (preSums[preSums.length - 1])) + 1;
            // 判断该权重值落在哪个区间内
            int left = 0;
            int right = preSums.length - 1;
            int ans = 0;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (preSums[mid] > random) {
                    ans = mid - 1;
                    right = mid - 1;
                } else if (preSums[mid] < random) {
                    left = mid + 1;
                } else {
                    ans = mid - 1;
                    break;
                }
            }
            return ans;
        }
    }
}
