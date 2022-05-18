package class19;

/**
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 *
 * @author dks233
 * @create 2022-05-18-13:40
 */
public class ConvertNumberToCharacters {
    // 暴力尝试
    public static int getCountOne(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    // 从index为开始做决定能获得的转换结果数
    public static int process(char[] str, int index) {
        // index位置越界，说明[0,index-1]位置做的决定都是有效的，在终止位置收集转换结果
        // 如果[0,index-1]位置选择无效，遇到index位置为0的情况，直接结束，到不了越界的位置
        // 只要到越界位置，说明[0,index-1]位置做的决定肯定是有效的
        if (index == str.length) {
            return 1;
        }
        // 不能以'0'为开头做选择，题目要求1~26
        // 以'0'为开头做选择说明前面的选择有问题，不累加
        if (str[index] == '0') {
            return 0;
        }
        // index位置单独选定，或者index和index+1位置合起来选定
        // 情况1：index位置单独选定，跳到index+1位置做决定
        int p1 = process(str, index + 1);
        // 情况2：index位置和index+1位置合起来选定，跳到index+2位置做决定
        // 情况2前提：index位置不越界，且index位置和index+1位置组成的数字介于1到26之间
        int p2 = 0;
        if (index + 1 < str.length && (str[index] - '0') * 10 + (str[index + 1] - '0') < 27) {
            p2 = process(str, index + 2);
        }
        return p1 + p2;
    }

    // 一个可变参数，建一个一维数组
    // 可变参数：index，范围：[0,str.length]
    // dp[i]表示以i开始做决定能获得的转换结果数
    // 分析依赖，i位置依赖i+2位置和i+1位置的结果，从后往前填表
    public static int getCountTwo(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int[] dp = new int[chars.length + 1];
        // dp表里最先填的位置是dp[chars.length]，值为1
        dp[chars.length] = 1;
        for (int i = dp.length - 2; i >= 0; i--) {
            if (chars[i] != '0') {
                int p1 = dp[i + 1];
                int p2 = 0;
                if (i + 1 < chars.length && (chars[i] - '0') * 10 + (chars[i + 1] - '0') < 27) {
                    p2 = dp[i + 2];
                }
                dp[i] = p1 + p2;
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        String str = "1234324234234244243241234214121034224314321";
        System.out.println(getCountOne(str));
        System.out.println(getCountTwo(str));
    }
}
