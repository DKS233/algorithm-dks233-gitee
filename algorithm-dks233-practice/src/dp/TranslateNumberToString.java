package dp;

/**
 * 剑指 Offer第二版 46. 把数字翻译成字符串
 *
 * @author dks233
 * @create 2022-06-18-20:24
 */
public class TranslateNumberToString {
    // 暴力递归
    public static class MethodOne {
        public int translateNum(int num) {
            // 获得num有几位
            int digitCount = getDigitCount(num);
            if (digitCount < 2) {
                return 1;
            }
            int[] arr = new int[digitCount];
            for (int i = 0; i < digitCount; i++) {
                arr[i] = getDigitNumber(num, i);
            }
            return process(arr, 0);
        }

        // [index,..]能获得的转换结果数
        public int process(int[] numberArr, int index) {
            // index越界说明之前的选择是有效的，在越界位置处收集结果
            if (index == numberArr.length) {
                return 1;
            }
            // 情况1：index位置单独选定作为字符串，跳到index+1位置做决定
            int p1 = process(numberArr, index + 1);
            // 情况2：index位置和index+1位置选定作字符串，跳到index+2位置做决定
            int p2 = 0;
            // 如果index位置是0，直接转换，属于情况1，所以情况2之前对其进行过滤
            if (numberArr[index] != 0) {
                if (index + 1 < numberArr.length && (numberArr[index] * 10 + numberArr[index + 1]) <= 25) {
                    p2 = process(numberArr, index + 2);
                }
            }
            return p1 + p2;
        }

        // 获得number在第digit数位上的值，从左往右第一位记为第0位
        // 234 从左往右依次记成0位，1位，2位
        // 234%10     2位  number/Math.pow(10,digit-3)%10
        // 234/10%10  1位  number/Math.pow(10,digit-2)%10
        // 234/100%10 0位  number/Math.pow(10,digit-1)%10
        public int getDigitNumber(int number, int digit) {
            return (int) ((number / Math.pow(10, getDigitCount(number) - digit - 1)) % 10);
        }

        // 获得number总共有多少数位
        public int getDigitCount(int number) {
            int ans = 0;
            while (number >= 1) {
                ans += 1;
                number /= 10;
            }
            return ans;
        }
    }

    // 暴力递归改动态规划
    // 分析可变参数：index->[0,numberArr.length]
    // dp[index] 表示[index,...]能获得的翻译方法数
    // 分析位置依赖关系，抄暴力递归过程
    public static class MethodTwo {
        public int translateNum(int num) {
            // 获得num有几位
            int digitCount = getDigitCount(num);
            if (digitCount < 2) {
                return 1;
            }
            int[] numberArr = new int[digitCount];
            for (int i = 0; i < digitCount; i++) {
                numberArr[i] = getDigitNumber(num, i);
            }
            int[] dp = new int[numberArr.length + 1];
            dp[numberArr.length] = 1;
            for (int index = numberArr.length - 1; index >= 0; index--) {
                int p1 = dp[index + 1];
                int p2 = 0;
                if (numberArr[index] != 0) {
                    if (index + 1 < numberArr.length && (numberArr[index] * 10 + numberArr[index + 1]) <= 25) {
                        p2 = dp[index + 2];
                    }
                }
                dp[index] = p1 + p2;
            }
            return dp[0];
        }

        // 获得number在第digit数位上的值，从左往右第一位记为第0位
        // 234 从左往右依次记成0位，1位，2位
        // 234%10     2位  number/Math.pow(10,digit-3)%10
        // 234/10%10  1位  number/Math.pow(10,digit-2)%10
        // 234/100%10 0位  number/Math.pow(10,digit-1)%10
        public int getDigitNumber(int number, int digit) {
            return (int) ((number / Math.pow(10, getDigitCount(number) - digit - 1)) % 10);
        }

        // 获得number总共有多少数位
        public int getDigitCount(int number) {
            int ans = 0;
            while (number >= 1) {
                ans += 1;
                number /= 10;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        MethodOne methodOne = new MethodOne();
        System.out.println(methodOne.getDigitCount(1));
        System.out.println(methodOne.getDigitCount(11));
        System.out.println(methodOne.getDigitCount(23));
        System.out.println(methodOne.getDigitCount(233));
    }
}
