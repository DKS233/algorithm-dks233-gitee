package other;

/**
 * 剑指 Offer第二版 65. 不用加减乘除做加法
 * 参考文档：https://leetcode.cn/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/solution/jin-zhi-tao-wa-ru-he-yong-wei-yun-suan-wan-cheng-j/
 *
 * @author dks233
 * @create 2022-06-20-16:08
 */
public class AddWithoutOtherOperation {
    // 求和=无进位的和+进位
    public int add(int a, int b) {
        while (b != 0) {
            int noCarry = a ^ b;
            int carry = (a & b) << 1;
            a = noCarry;
            b = carry;
        }
        return a;
    }
}
