package other;

/**
 * leetcode640. 求解方程
 *
 * @author dks233
 * @create 2022-08-30-16:00
 */
@SuppressWarnings("ALL")
public class SolveFunction {
    // ax+b=0
    // 分析带x的几种情况：+x +2x -x -2x
    // 分析常数项的几种情况：1 +1 -1
    public String solveEquation(String equation) {
        int a = 0, b = 0;
        // flag=1代表正数 flag=-1代表负数
        int flag = 1;
        char[] str = equation.toCharArray();
        int i = 0;
        while (i < str.length) {
            if (str[i] == '+') {
                flag = 1;
                i++;
            } else if (str[i] == '-') {
                flag = -1;
                i++;
            } else if (str[i] == '=') {
                a = -a;
                b = -b;
                flag = 1;
                i++;
            } else {
                // 两种情况：带x，更新a，不带x，更新b
                int j = i;
                while (j < str.length && str[j] != '+' && str[j] != '-' && str[j] != '=') {
                    j++;
                }
                // 特殊情况：x -x 前面没有系数
                if (str[j - 1] == 'x') {
                    a += flag * ((i < j - 1) ? Integer.parseInt(equation.substring(i, j - 1)) : 1);
                } else {
                    b += flag * Integer.parseInt(equation.substring(i, j));
                }
                i = j;
            }
        }
        if (a == 0) {
            return b == 0 ? "Infinite solutions" : "No solution";
        }
        return "x=" + (-b / a);
    }
}
