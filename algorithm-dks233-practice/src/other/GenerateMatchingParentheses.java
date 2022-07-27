package other;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指offer专项突击版：剑指 Offer II 085. 生成匹配的括号
 *
 * @author dks233
 * @create 2022-07-27-21:12
 */
@SuppressWarnings("ALL")
public class GenerateMatchingParentheses {
    // 暴力递归
    public static class MethodOne {
        List<String> list = new ArrayList<>();

        // 原则：index位置右括号的数量不能大于左括号的数量
        public List<String> generateParenthesis(int n) {
            process(n, n, "");
            return list;
        }

        // leftRest：左括号剩余
        // rightRest：右括号剩余
        // curStr：当前拼接的字符串
        public void process(int leftRest, int rightRest, String curStr) {
            // 左右括号都用完，统计curStr
            if (leftRest == 0 && rightRest == 0) {
                list.add(curStr);
                return;
            }
            // 左括号有剩余，右括号没剩余，结束
            if (rightRest == 0) {
                return;
            }
            // 左括号没剩余，右括号有剩余，只能添加右括号
            if (leftRest == 0) {
                process(leftRest, rightRest - 1, curStr + ")");
                return;
            }
            // 左右括号都有剩余
            // 当前右括号数量多于左括号数量，结束
            // 当前右括号数量等于左括号数量，只能添加左括号
            // 当前右括号数量小于左括号数量，左右括号都可以添加
            if (rightRest < leftRest) {
                return;
            } else if (rightRest > leftRest) {
                process(leftRest - 1, rightRest, curStr + "(");
                process(leftRest, rightRest - 1, curStr + ")");
            } else {
                process(leftRest - 1, rightRest, curStr + "(");
            }
        }
    }
}
