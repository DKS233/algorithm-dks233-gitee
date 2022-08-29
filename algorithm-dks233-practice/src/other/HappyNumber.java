package other;

/**
 * leetcode202. 快乐数
 *
 * @author dks233
 * @create 2022-08-29-11:39
 */
@SuppressWarnings("ALL")
public class HappyNumber {
    // 快慢指针，一直求下一个快乐数，如果能形成环就不是快乐数
    // 如果形不成环，到达1，就说明n是快乐数
    public boolean isHappy(int n) {
        int slow = n;
        int fast = getCurHappyNumber(n);
        while (fast != 1 && slow != fast) {
            slow = getCurHappyNumber(slow);
            fast = getCurHappyNumber(getCurHappyNumber(fast));
        }
        if (fast == 1) {
            return true;
        }
        return false;
    }

    // 返回cur的每个位置上的数字的平方和
    public int getCurHappyNumber(int cur) {
        int digitCount = getDigitCount(cur);
        int result = 0;
        for (int i = 1; i <= digitCount; i++) {
            int j = getDigit(cur, i);
            result += j * j;
        }
        return result;
    }

    // 有几位数
    public int getDigitCount(int n) {
        int digit = 0;
        while (n > 0) {
            digit++;
            n /= 10;
        }
        return digit;
    }

    // 每位上是几，1表示个位
    public int getDigit(int n, int cur) {
        return (n / (int) (Math.pow(10, cur - 1))) % 10;
    }

    public static void main(String[] args) {
        HappyNumber happyNumber = new HappyNumber();
        happyNumber.isHappy(1);
    }
}
