package other;

/**
 * 剑指 Offer第二版 56 - I. 数组中数字出现的次数
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * 剑指 Offer第二版 56 - II. 数组中数字出现的次数 II
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 *
 * @author dks233
 * @create 2022-06-19-9:36
 */
public class ArrayElementAppearFrequency {
    // 题目1
    // 设这两个数字分别为a和b
    // aEorB对所有数进行异或，得到的结果为a^b
    // 由于a!=b，所以a^b!=0，对应的二进制位中肯定有一位是1，设从右往左数第三位为1，根据该位是1还是0将数组中的数分成两类
    // 第一类是第三位是0的数，假设a属于这类，第二类是第三位为1的数，假设b属于这类
    // eor=0对第一类数进行异或，最终结果为eor=a，然后eor^aEorB=a^a^b=b
    public int[] singleNumbers(int[] nums) {
        int aEorB = 0;
        for (int i = 0; i < nums.length; i++) {
            aEorB ^= nums[i];
        }
        // 提取出二进制数最右侧的1
        int right = aEorB & (~aEorB + 1);
        int eor = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((right & nums[i]) != 0) {
                eor ^= nums[i];
            }
        }
        return new int[]{eor, eor ^ aEorB};
    }

    // 题目2
    // 通用：一个数出现K次，其他数出现M次，K<M，求这个数（体系学习班class02）
    // 准备一个长度为32的数组，数组中每个数的二进制位进行累加
    // 假设K=3,M=7，如果某个二进制位累加=7的整数倍，所求的数在该位上肯定是0，否则所求的数在该位上肯定是1
    // 0出现k次的情况需要特殊处理
    public int singleNumber(int[] nums) {
        return general(nums, 1, 3);
    }

    public int general(int[] nums, int k, int m) {
        // help[digit] digit位置的1出现了几个
        int[] help = new int[32];
        for (int i = 0; i < nums.length; i++) {
            for (int digit = 0; digit < 32; digit++) {
                help[digit] += (nums[i] >> digit) & 1;
            }
        }
        // help[digit]如果是7的整数倍，该数在digit位上肯定是0
        // help[digit]如果不是7的整数倍，该数在digit位上肯定是1
        int ans = 0;
        for (int digit = 0; digit < help.length; digit++) {
            if (help[digit] % m != 0) {
                ans |= (1 << digit);
            }
        }
        // 处理0出现k次的特殊情况
        if (ans == 0) {
            int zeroCount = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    zeroCount++;
                }
            }
            if (zeroCount != k) {
                return -1;
            }
        }
        return ans;
    }

}
