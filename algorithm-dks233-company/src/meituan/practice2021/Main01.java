package meituan.practice2021;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 美团2021校招笔试-编程题(通用编程试题,第10场)
 * 题目1：淘汰分数
 * 题目链接：https://www.nowcoder.com/test/question/9c4a4e879b4f49939dfaebea8948f976?pid=28665343&tid=61810226
 * 某比赛已经进入了淘汰赛阶段,已知共有n名选手参与了此阶段比赛，他们的得分分别是a_1,a_2….a_n,
 * 小美作为比赛的裁判希望设定一个分数线m，使得所有分数大于m的选手晋级，其他人淘汰。
 * 但是为了保护粉丝脆弱的心脏，小美希望晋级和淘汰的人数均在[x,y]之间。
 * 显然这个m有可能是不存在的，也有可能存在多个m，如果不存在，请你输出-1，如果存在多个，请你输出符合条件的最低的分数线。
 *
 * @author dks233
 * @create 2022-09-21-20:45
 */
public class Main01 {
    public static class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = scanner.nextInt();
            }
            Arrays.sort(nums);
            int curIndex = 1;
            while (curIndex < nums.length) {
                int left = curIndex + 1;
                int right = nums.length - curIndex - 1;
                if (left >= x && left <= y && right >= x && right <= y) {
                    System.out.println(nums[curIndex]);
                    return;
                }
                curIndex++;
            }
            System.out.println(-1);
        }
    }
}
