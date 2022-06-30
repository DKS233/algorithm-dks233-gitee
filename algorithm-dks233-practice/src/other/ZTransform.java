package other;

/**
 * leetcode6. Z 字形变换
 * 参考文档：https://leetcode.cn/problems/zigzag-conversion/solution/zzi-xing-bian-huan-by-jyd/
 *
 * @author dks233
 * @create 2022-06-30-10:07
 */
public class ZTransform {
    // 思路：用flag控制走向
    public String convert(String s, int numRows) {
        if (s == null || numRows < 2) {
            return s;
        }
        char[] str = s.toCharArray();
        // 存储每一行的字符
        StringBuilder[] rowChars = new StringBuilder[numRows];
        for (int index = 0; index < rowChars.length; index++) {
            rowChars[index] = new StringBuilder();
        }
        // flag=-1向上走，flag=1向下走
        int flag = -1;
        // 当前遍历到第几行了
        int rowNum = 0;
        for (char c : str) {
            rowChars[rowNum].append(c);
            if (rowNum == 0 || rowNum == numRows - 1) {
                flag = -flag;
            }
            rowNum += flag;
        }
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < rowChars.length; index++) {
            builder.append(rowChars[index]);
        }
        return builder.toString();
    }
}
