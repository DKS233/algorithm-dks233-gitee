package meituan.practice2021;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 题目链接：https://www.nowcoder.com/question/next?pid=28665343&qid=1371128&tid=61831118
 * 小美和小团所在公司的食堂有N张餐桌，从左到右摆成一排，每张餐桌有2张餐椅供至多2人用餐，公司职员排队进入食堂用餐。
 * 小美发现职员用餐的一个规律并告诉小团：当男职员进入食堂时，他会优先选择已经坐有1人的餐桌用餐，只有当每张餐桌要么空着要么坐满2人时，他才会考虑空着的餐桌；
 * 当女职员进入食堂时，她会优先选择未坐人的餐桌用餐，只有当每张餐桌都坐有至少1人时，她才会考虑已经坐有1人的餐桌；
 * 无论男女，当有多张餐桌供职员选择时，他会选择最靠左的餐桌用餐。现在食堂内已有若干人在用餐，另外M个人正排队进入食堂，
 * 小团会根据小美告诉他的规律预测排队的每个人分别会坐哪张餐桌。
 * <p>
 * 输入：
 * 第一行输入一个整数T（1<=T<=10），表示数据组数。
 * 每组数据占四行，第一行输入一个整数N（1<=N<=500000）；
 * 第二行输入一个长度为N且仅包含数字0、1、2的字符串，第i个数字表示左起第i张餐桌已坐有的用餐人数；
 * 第三行输入一个整数M（1<=M<=2N且保证排队的每个人进入食堂时都有可供选择的餐桌）；
 * 第四行输入一个长度为M且仅包含字母M、F的字符串，若第i个字母为M，则排在第i的人为男性，否则其为女性。
 * <p>
 * 输出：
 * 每组数据输出占M行，第i行输出一个整数j（1<=j<=N），表示排在第i的人将选择左起第j张餐桌用餐。
 *
 * @author dks233
 * @create 2022-09-22-9:33
 */
@SuppressWarnings("ALL")
public class Main03 {
    // 分析：0人1人2人的餐桌建立三个小根堆
    // 堆元素为餐桌的索引
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(reader.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(reader.readLine());
            String sit = reader.readLine();
            int m = Integer.parseInt(reader.readLine());
            String human = reader.readLine();
            int[] nums = process(sit.toCharArray(), human.toCharArray());
            for (int j = 0; j < nums.length; j++) {
                writer.write(String.valueOf(nums[j]));
                writer.newLine();
            }
        }
        writer.flush();
    }

    public static int[] process(char[] sit, char[] human) {
        PriorityQueue<Integer> zero = new PriorityQueue<>();
        PriorityQueue<Integer> one = new PriorityQueue<>();
        for (int i = 0; i < sit.length; i++) {
            if (sit[i] == '0') {
                zero.add(i + 1);
            } else if (sit[i] == '1') {
                one.add(i + 1);
            }
        }
        int[] ans = new int[human.length];
        for (int i = 0; i < human.length; i++) {
            if (human[i] == 'M') {
                if (one.size() > 0) {
                    ans[i] = one.poll();
                } else {
                    ans[i] = zero.poll();
                    one.add(ans[i]);
                }
            } else {
                if (zero.size() > 0) {
                    ans[i] = zero.poll();
                    one.add(ans[i]);
                } else {
                    ans[i] = one.poll();
                }
            }
        }
        return ans;
    }
}
