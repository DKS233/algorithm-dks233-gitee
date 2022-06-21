package other;

/**
 * 剑指 Offer第二版 20. 表示数值的字符串
 * TODO：该题暂时跳过，正确解法应该用有限状态自动机
 * TODO：参考文档：https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/solution/mian-shi-ti-20-biao-shi-shu-zhi-de-zi-fu-chuan-y-2/
 *
 * @author dks233
 * @create 2022-06-21-20:59
 */
public class StringRepresentNumber {
    public boolean isNumber(String str) {
        if (str.contains("f") || str.contains("F")) {
            return false;
        }
        if (str.contains("D") || str.contains("d")) {
            return false;
        }
        try {
            Float.valueOf(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
