package other;

/**
 * leetcode2315. 统计星号
 *
 * @author dks233
 * @create 2022-09-16-8:17
 */
@SuppressWarnings("ALL")
public class StatisticalAsterisk {
    // 遍历字符串，设置flag，表示没有出现竖线
    // 遇到竖线之前，统计星号数量
    // 第一次遇到竖线，flag为true
    // 第二次遇到竖线，flag变为false
    // 再次遇到星号之前，统计星号数量
    // 第三次遇到竖线，flag变为true
    public int countAsterisks(String s) {
        char[] str = s.toCharArray();
        boolean flag = false;
        int count = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '|') {
                flag = !flag;
            } else {
                if (!flag && str[i] == '*') {
                    count++;
                }
            }
        }
        return count;
    }
}
