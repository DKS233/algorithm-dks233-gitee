package class02;

/**
 * 一个数组中，有一个数出现了K次，其他数都出现了M次，M>1，K<M，找到出现了K次的数
 * 要求：时间复杂度：O(N)，空间复杂度：O(1)
 *
 * @author dks233
 * @create 2022-08-16-15:46
 */
@SuppressWarnings("ALL")
public class AppearKAndMTimes {
    public static int appearKAndMTimes(int[] arr, int K, int M) {
        // 存二进制位的数组
        // digits[0] 表示个位
        int[] digits = new int[32];
        for (int index = 0; index < arr.length; index++) {
            for (int digit = 0; digit < 31; digit++) {
                digits[digit] += (arr[index] >> digit) & 1;
            }
        }
        // 设出现了K次的数是number
        // 遍历digits，如果某位上二进制累加和是M的整数倍，说明number在该位上是0，否则number在该位上是1
        int number = 0;
        for (int digit = 0; digit < 31; digit++) {
            if (digits[digit] % M != 0) {
                number += (1 << digit);
            }
        }
        // 如果出现了K次的数是0，需要特殊处理
        int count = 0;
        if (number == 0) {
            for (int index = 0; index < arr.length; index++) {
                if (arr[index] == 0) {
                    count++;
                }
            }
            if (count != K) {
                return -1;
            }
        }
        return number;
    }

    public static void main(String[] args) {
        AppearKAndMTimes times = new AppearKAndMTimes();
        System.out.println(times.appearKAndMTimes(new int[]{3, 4, 3, 3}, 1, 3));
    }
}
