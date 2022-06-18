package other;

/**
 * 剑指 Offer第二版  44. 数字序列中某一位的数字
 * 参考文档：https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/solution/mian-shi-ti-44-shu-zi-xu-lie-zhong-mou-yi-wei-de-6/
 *
 * @author dks233
 * @create 2022-06-18-15:57
 */
public class SpecificDigitInSequence {
    public int findNthDigit(int n) {
        if (n == 0) {
            return 0;
        }
        // 将数字按照位数分成不同的组
        // 某个分组内数字的位数
        int digit = 1;
        // 某个分组内起始数字的值
        long start = 1;
        // 某个分组内含有的数位数量
        long count = 9 * digit * start;
        // 三步走
        // 第一步：判断n在哪个分组内
        while (n > count) {
            n -= count;
            start *= 10;
            digit += 1;
            count = 9 * digit * start;
        }
        // 跳出循环的时候已经n已经定位到了所在的分组内
        // 此时的n表示目标数在该分组内在第几个数位，从1开始，start表示目标数所在分组的起始值，digit表示目标数所在分组的数字位数
        // 第二步：判断n代表的是哪个数字
        // 比如：分组为10到99，start=10，n=4时，代表number=10+(4-1)/2=12
        // n=3时，代表number=10+(3-1)/2=11
        // n=5时，代表number=10+(5-1)/2=12
        long number = start + (n - 1) / digit;
        // 第三步：判断n在该数字内对应的数位（从0开始）
        return Long.toString(number).charAt((n - 1) % digit) - '0';
    }

    // 10->2 22->2 333->3
    public int getDigitCount(int number) {
        int ans = 0;
        while (number >= 1) {
            number /= 10;
            ans += 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        SpecificDigitInSequence sequence = new SpecificDigitInSequence();
        System.out.println(sequence.getDigitCount(1));
        System.out.println(sequence.getDigitCount(10));
        System.out.println(sequence.getDigitCount(33));
        System.out.println(sequence.getDigitCount(233));
    }
}
