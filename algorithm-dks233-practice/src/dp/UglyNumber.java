package dp;

/**
 * 剑指 Offer 49. 丑数
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 *
 * @author dks233
 * @create 2022-06-18-22:09
 */
public class UglyNumber {
    // 暴力解法
    public static class MethodOne {
        public int nthUglyNumber(int n) {
            // 丑数计数
            int count = 0;
            // 从0开始遍历所有的数，直到找到第n个丑数
            int number = 0;
            // 1 2 3 4 5 6 8 9 19 12
            // 1->number=1->count=1
            // 2->number=2->count=2
            // number->count=n
            while (count < n) {
                number++;
                if (isUglyNumber(number)) {
                    count++;
                }
            }
            return number;
        }

        public boolean isUglyNumber(int number) {
            if (number == 1) {
                return true;
            }
            while (number % 2 == 0) {
                number /= 2;
            }
            while (number % 3 == 0) {
                number /= 3;
            }
            while (number % 5 == 0) {
                number /= 5;
            }
            return number == 1;
        }
    }

    // 三指针法
    // 其实三指针法就是动态规划
    // 时间和空间复杂度都是O(N)
    public static class MethodTwo {
        // 1之后的丑数都是由前面的丑数*2,*3,*5得来的
        public int nthUglyNumber(int n) {
            int[] uglyNumberArr = new int[n];
            int indexTwo = 0;
            int indexThree = 0;
            int indexFive = 0;
            uglyNumberArr[0] = 1;
            int index = 1;
            while (index < n) {
                uglyNumberArr[index] = Math.min(Math.min(uglyNumberArr[indexTwo] * 2,
                        uglyNumberArr[indexThree] * 3),
                        uglyNumberArr[indexFive] * 5);
                if (uglyNumberArr[index] == uglyNumberArr[indexTwo] * 2) {
                    indexTwo++;
                }
                if (uglyNumberArr[index] == uglyNumberArr[indexThree] * 3) {
                    indexThree++;
                }
                if (uglyNumberArr[index] == uglyNumberArr[indexFive] * 5) {
                    indexFive++;
                }
                index++;
            }
            return uglyNumberArr[n - 1];
        }
    }

    // 三指针法给变量换个名字，即动态规划写法
    // dp[i] 表示i位置的丑数值
    public static class MethodThree {
        // 1之后的丑数都是由前面的丑数*2,*3,*5得来的
        public int nthUglyNumber(int n) {
            int[] dp = new int[n];
            int indexTwo = 0;
            int indexThree = 0;
            int indexFive = 0;
            dp[0] = 1;
            int index = 1;
            while (index < n) {
                dp[index] = Math.min(Math.min(dp[indexTwo] * 2,
                        dp[indexThree] * 3),
                        dp[indexFive] * 5);
                if (dp[index] == dp[indexTwo] * 2) {
                    indexTwo++;
                }
                if (dp[index] == dp[indexThree] * 3) {
                    indexThree++;
                }
                if (dp[index] == dp[indexFive] * 5) {
                    indexFive++;
                }
                index++;
            }
            return dp[n - 1];
        }
    }
}
