package other;

import java.util.Stack;

/**
 * leetcode394:字符串解码
 * 参考文档：https://leetcode.cn/problems/decode-string/solution/decode-string-fu-zhu-zhan-fa-di-gui-fa-by-jyd/
 *
 * @author dks233
 * @create 2022-07-13-20:28
 */
public class StringDecode {
    // 时间复杂度：O(N)+O(N)
    public String decodeString(String s) {
        char[] str = s.toCharArray();
        Stack<String> strStack = new Stack<>();
        Stack<Integer> numberStack = new Stack<>();
        int curNumber = 0;
        StringBuilder curStr = new StringBuilder();
        for (int index = 0; index < str.length; index++) {
            // 如果当前字符是数字，将字符转换成数字
            if (str[index] >= '0' && str[index] <= '9') {
                curNumber = curNumber * 10 + Integer.parseInt(str[index] + "");
            }
            // 如果当前字符是字母，将字母拼接到curStr
            if ((str[index] >= 'a' && str[index] <= 'z') || (str[index] >= 'A' && str[index] <= 'Z')) {
                curStr.append(str[index]);
            }
            // 如果当前字符是左括号，把curStr和curNumber压入栈中，然后curStr和curNumber恢复初始状态
            if (str[index] == '[') {
                numberStack.push(curNumber);
                strStack.push(String.valueOf(curStr));
                curNumber = 0;
                curStr = new StringBuilder();
            }
            // 如果当前字符是右括号，弹出两个栈的栈顶元素，更新curStr
            if (str[index] == ']') {
                StringBuilder tempBuilder = new StringBuilder();
                tempBuilder.append(strStack.pop());
                int popNumber = numberStack.pop();
                for (int count = 0; count < popNumber; count++) {
                    tempBuilder.append(curStr);
                }
                curStr = tempBuilder;
            }
        }
        return curStr.toString();
    }
}
