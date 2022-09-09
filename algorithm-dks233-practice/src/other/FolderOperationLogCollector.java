package other;

/**
 * leetcode1598. 文件夹操作日志搜集器
 *
 * @author dks233
 * @create 2022-09-09-8:36
 */
@SuppressWarnings("ALL")
public class FolderOperationLogCollector {
    // 计算深度
    // 情况1：a/ depth-1
    // 情况2：../ 如果当前depth大于0，depth-1，如果当前depth等于0，depth不变
    public int minOperations(String[] logs) {
        int depth = 0;
        for (int i = 0; i < logs.length; i++) {
            String curStr = logs[i];
            if (curStr.charAt(0) == '.' && curStr.charAt(1) == '.') {
                depth -= 1;
            }
            if (curStr.charAt(0) != '.') {
                depth += 1;
            }
        }
        return depth > 0 ? depth : 0;
    }
}
