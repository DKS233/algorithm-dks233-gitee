package class14;

import java.util.HashSet;

/**
 * 点灯问题
 * 点亮所有需要点亮的位置，需要几盏灯
 *
 * @author dks233
 * @create 2022-05-03-15:39
 */
public class Light {
    // 策略：依次考虑每个位置放灯不放灯
    // i位置是X，直接去i+1位置
    // i位置是.
    // 情况1：i+1位置是X   i位置放灯     跳到i+2位置
    // 情况2：i+1位置是.   i+2位置是X    i位置放灯     跳到i+3位置   注：i+1位置放灯也可以
    // 情况3：i+1位置是.   i+2位置是.    i+1位置放灯     跳到i+3位置
    public static int getSmallCount(String road) {
        if (road == null || "".equals(road)) {
            return 0;
        }
        char[] chars = road.toCharArray();
        int index = 0;
        int count = 0;
        while (index < chars.length) {
            if (chars[index] == 'X') {
                index += 1;
            } else {
                if (index + 1 == chars.length) {
                    count++;
                    return count;
                }
                if (chars[index + 1] == 'X') {
                    count++;
                    index = index + 2;
                } else {
                    if (index + 2 == chars.length) {
                        count++;
                        return count;
                    }
                    if (chars[index + 1] == '.' && chars[index + 2] == 'X') {
                        count++;
                        index = index + 3;
                    } else {
                        count++;
                        index = index + 3;
                    }
                }
            }
        }
        return count;
    }

    public static int comparator(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    // str[index....]位置，自由选择放灯还是不放灯
    // str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
    // 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
    public static int process(char[] str, int index, HashSet<Integer> lights) {
        if (index == str.length) { // 结束的时候
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') { // 当前位置是点的话
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else { // str还没结束
            // i X .
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                lights.add(index);
                yes = process(str, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }

    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int testTimes = 1000000;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            String s = randomString(maxLen);
            if (getSmallCount(s) != comparator(s)) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

}
