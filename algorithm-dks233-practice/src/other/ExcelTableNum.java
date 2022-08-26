package other;

/**
 * leetcode171. Excel 表列序号
 *
 * @author dks233
 * @create 2022-08-26-11:59
 */
public class ExcelTableNum {
    public int titleToNumber(String columnTitle) {
        int result = 0;
        int cur = 1;
        int len = columnTitle.length();
        for (int i = len - 1; i >= 0; i--) {
            result += cur * (columnTitle.charAt(i) - 'A' + 1);
            cur *= 26;
        }
        return result;
    }
}
