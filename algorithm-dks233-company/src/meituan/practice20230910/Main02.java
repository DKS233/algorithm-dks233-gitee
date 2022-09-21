package meituan.practice20230910;

import java.util.Scanner;

/**
 * 美团9月10号笔试题目2
 * 某天，小美在调试她的神经网络模型，这个模型共有
 * n个参数，目前这些参数分别为a1、a2、…、an，为了让参数看起来更加随机而且增加训练效果，
 * 她需要调节参数使所有参数满足a1+a2+…+an≠0且a1*a2*…*an≠0，她每次可以选择一个参数ai并将其变为ai+1，
 * 小美请你帮她计算一下，最少需要几次操作才能使参数达到她的要求
 * <p>
 * 输入描述
 * 第一行一个正整数n，表示参数个数。
 * 第二行为n个正整数a1, a2,...... an，其中ai表示第i个参数初始值。
 * 1 ≤ n ≤ 30000，-1000 ≤ ai ≤ 1000。
 * 输出描述
 * 输出一个正整数，表示最少需要的操作次数
 *
 * @author dks233
 * @create 2022-09-10-15:57
 */
@SuppressWarnings("ALL")
public class Main02 {
    // 思路：首先把0都变成1，次数为zeroCunt次，再次计算数组和，如果和还是0，操作次数再加个1
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        int sum = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
            if (nums[i] == 0) {
                count++;
                nums[i] += 1;
            }
            sum += nums[i];
        }
        if (sum == 0) {
            count += 1;
        }
        System.out.println(count);
    }
}
