package other;

import java.util.Scanner;

/**
 * 360笔试题：编号为i的钥匙用于开启第i道门，而且只有在开启第i道门后才能开启第i+1道门
 * 每天能获得一把打开某道门的锁，请问每道门分别在哪天被打开
 * 输入：第一行一个正整数n表示n的数量，接下来一行包含n个正整数，表示第i天获得钥匙的编号
 * 输出：输出一行n个数，nums[i]表示第i道门在第nums[i]天被打开
 * 示例输入：5
 * 5 3 1 2 4
 * 示例输出：3 4 4 5 5
 *
 * @author dks233
 * @create 2022-09-09-16:26
 */
@SuppressWarnings("ALL")
public class OpenDoorWithLock {
    // 门的编号：1 2 3 4 5
    // 每天的锁：5 3 1 2 4
    // ans[i]表示第i道门的锁是第ans[i]天获得的  ans=[3 4 2 5 5]
    // 然后通过修正ans，将ans变为第i道门在第ans[i]天被打开
    // 如果ans[i]<ans[i+1]，前面的门开完可以开后面的门
    // 如果ans[i]>ans[i+1]，后面的锁先获得，但是得等前面的锁开完，所以修正ans[i+1]为ans[i]
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        int[] ans = new int[n];
        for (int i = 0; i < nums.length; i++) {
            ans[nums[i] - 1] = i + 1;
        }
        for (int i = 0; i < ans.length - 1; i++) {
            if (ans[i] > ans[i + 1]) {
                ans[i + 1] = ans[i];
            }
        }
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}
