package other;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode22. 括号生成
 *
 * @author dks233
 * @create 2022-07-05-16:28
 */
public class BracketGenerating {
    // 深度优先遍历
    List<String> list = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return list;
        }
        dfs(n, n, "");
        return list;
    }

    /**
     * @param leftRest  左括号剩余数量
     * @param rightRest 右括号剩余数量
     * @param curStr    当前拼接的字符串
     */
    public void dfs(int leftRest, int rightRest, String curStr) {
        // 左右括号都用完，添加字符串
        if (leftRest == 0 && rightRest == 0) {
            list.add(curStr);
            return;
        }
        // 如果左括号剩余数量大于0，可以拼接左括号
        if (leftRest > 0) {
            dfs(leftRest - 1, rightRest, curStr + "(");
        }
        // 如果左括号剩余数量小于右括号剩余数量（左括号用的多），可以拼接右括号
        if (rightRest > leftRest) {
            dfs(leftRest, rightRest - 1, curStr + ")");
        }
    }
}
