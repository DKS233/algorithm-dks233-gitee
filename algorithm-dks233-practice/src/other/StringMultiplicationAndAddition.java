package other;

/**
 * leetcode43. 字符串相乘
 * leetcode415. 字符串相加
 *
 * @author dks233
 * @create 2022-07-08-21:18
 */
@SuppressWarnings("ALL")
public class StringMultiplicationAndAddition {
    // num1和num2的每一位相乘(注意补0)，然后相加
    public String multiply(String num1, String num2) {
        if (num1.length() < num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        // 当前遍历到的num2的索引位置
        int indexTwo = num2.length() - 1;
        // 当前位置相乘结束后，后面需要补几个0
        int zeroNeed = 0;
        // 进位
        int carry = 0;
        // 乘积运算后当前位结果
        int curNumber = 0;
        // 用num1和num2的每一位进行计算，得到(num2.length-1)个字符串
        String[] strs = new String[num2.length()];
        while (indexTwo >= 0) {
            StringBuilder builder = new StringBuilder();
            for (int indexOne = num1.length() - 1; indexOne >= 0; indexOne--) {
                int curMultiplyResult = (num1.charAt(indexOne) - '0') * (num2.charAt(indexTwo) - '0') + carry;
                carry = curMultiplyResult / 10;
                curNumber = curMultiplyResult % 10;
                builder.append(curNumber);
            }
            if (carry > 0) {
                builder.append(carry);
            }
            builder.reverse();
            for (int zeroCount = 0; zeroCount < zeroNeed; zeroCount++) {
                builder.append("0");
            }
            strs[indexTwo] = builder.toString();
            indexTwo--;
            zeroNeed++;
            carry = 0;
        }
        // 将每一个字符串进行相加
        String ans = "";
        for (int index = 0; index < strs.length; index++) {
            ans = addStrings(ans, strs[index]);
        }
        return ans;
    }

    // 双指针法
    // indexOne表示num1遍历到的位置，indexTwo表示num2遍历到的位置
    // cur表示当前位的累加值%10（即结果中当前位上的值）
    // carry表示进位
    // ans表示当前字符串拼接结果（num1和num2是从右往左遍历，所以拼接结果是反过来的累加和）
    // 时间复杂度：O(max(M,N))
    // 空间复杂度：O(1)
    public String addStrings(String num1, String num2) {
        int indexOne = num1.length() - 1;
        int indexTwo = num2.length() - 1;
        int cur = 0;
        int carry = 0;
        StringBuilder ans = new StringBuilder("");
        while (indexOne >= 0 || indexTwo >= 0) {
            int curOne = indexOne >= 0 ? num1.charAt(indexOne) - '0' : 0;
            int curTwo = indexTwo >= 0 ? num2.charAt(indexTwo) - '0' : 0;
            int curSum = curOne + curTwo + carry;
            // 根据上一轮的进位计算这一轮的累加和
            cur = curSum % 10;
            // 更新进位
            carry = curSum / 10;
            ans.append(cur);
            indexOne--;
            indexTwo--;
        }
        if (carry == 1) {
            ans.append(carry);
        }
        return ans.reverse().toString();
    }

    public static void main(String[] args) {
        StringMultiplicationAndAddition addition = new StringMultiplicationAndAddition();
        addition.addStrings("456", "77");
        addition.multiply("999", "999");
    }
}
