package other;

/**
 * 剑指 Offer第二版 43. 1～n 整数中 1 出现的次数
 *
 * @author dks233
 * @create 2022-06-21-10:42
 */
public class OneAppearNumberFromOneToN {
    // 3101592
    // 1出现的次数等于个位上1出现的次数+十位上1出现的次数+百位上1出现的次数+...
    // 设当前位左边的数为a，右边的数为b，base：十位上表示10，百位上表示100，cur为当前位上的数

    // 百位上1出现的次数（百位上等于1时有多少个数）
    // cur>1 a=3101 b=92 左边取值范围是[0,3101] 右边取值范围是[0,99]  百位上1出现的次数=(a+1)*(base)

    // 千位上1出现的次数（千位上等于1时有多少个数）
    // cur==1 a=310 b=592
    // 左边取值范围在[0,309]时 右边取值范围是[0,999]
    // 左边取值为310时 右边取值范围是[0,592]
    // 千位上1出现的次数=a*base+(1)*(b+1)=a*base+b+1

    // 万位上1出现的次数（万位上等于1时有多少个数）
    // cur<1 a=31 b=1592
    // 左边取值范围是[0,30] 右边取值范围是[0,999]
    // 万位上1出现的次数=a*base
    public int countDigitOne(int n) {
        long base = 1;
        int ans = 0;
        while (base <= n) {
            long a = n / base / 10;
            long b = n % base;
            long cur = (n / base) % 10;
            if (cur > 1) {
                ans += (a + 1) * base;
            } else if (cur == 1) {
                ans += a * base + b + 1;
            } else {
                ans += a * base;
            }
            base = base * 10;
        }
        return ans;
    }
}
