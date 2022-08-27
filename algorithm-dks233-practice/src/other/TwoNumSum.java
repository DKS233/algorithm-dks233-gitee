package other;

/**
 * leetcode371. 两整数之和
 *
 * @author dks233
 * @create 2022-08-27-18:01
 */
@SuppressWarnings("ALL")
public class TwoNumSum {
    // 步骤1：计算不加进位的和
    // 步骤2：计算进位值
    // 步骤3：用步骤1和步骤2的结果重复1和2，直到进位值等于0
    public int getSum(int a, int b) {
        int noCarry = a ^ b;
        int carry = (a & b) << 1;
        while (carry != 0) {
            int temp = noCarry ^ carry;
            carry = (carry & noCarry) << 1;
            noCarry = temp;
        }
        return noCarry;
    }
}
