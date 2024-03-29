package class18;

import java.util.Scanner;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数
 *
 * @author dks233
 * @create 2022-05-17-16:06
 */
@SuppressWarnings("ALL")
public class TakeCards {
    // 获胜者的分数：暴力递归
    public static int getWinnerScoreOne(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = first(arr, 0, arr.length - 1);
        int second = second(arr, 0, arr.length - 1);
        // max(先手能拿到的最大分熟，后手能拿到的最大分数)
        return Math.max(first, second);
    }

    // left->right范围内先拿能获得的最大分数
    public static int first(int[] arr, int left, int right) {
        // 只剩一张牌，先后先拿（先手后手是随着剩余牌数不断变化的）
        if (left == right) {
            return arr[left];
        }
        // 先拿left，在[left+1,right]中作为后手再拿
        // p1=arr[left]+在剩下的牌中作为后手能拿到的最大分数
        int p1 = arr[left] + second(arr, left + 1, right);
        // 先拿right，在[left,right-1]中作为后手在拿
        // p2=arr[right]+在剩下的牌中作为后手能拿到的最大分数
        int p2 = arr[right] + second(arr, left, right - 1);
        // 先手具有主动权，选择分数较大的
        return Math.max(p1, p2);
    }

    // left->right范围内后拿能获得的最大分数
    public static int second(int[] arr, int left, int right) {
        // 只剩一张牌，后手拿不到（先手后手是随着剩余牌数不断变化的）
        if (left == right) {
            return 0;
        }
        // 对手先拿left,后手作为[left+1,right]范围的先手能拿到的最大分数
        int p1 = first(arr, left + 1, right);
        // 对手先拿right,后手作为[left,right-1]范围的先手能拿到的最大分数
        int p2 = first(arr, left, right - 1);
        // 后手只能尽量拿最大，但是先手不会给你机会拿最大，后手没有主动权
        return Math.min(p1, p2);
    }

    // 获胜者的分数：动态规划
    // 分析可变参数的变化范围，加缓存
    // 可变参数：left right
    // 加缓存：两个表，first[n][n]，second[n][n]，存先手和后手处理过的区域
    public static int getWinnerScoreTwo(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 初始化为-1
        // firstArea[i][j]==-1说明先手该区域未被处理过
        // firstArea[i][j]!=-1 说明先手该区域已经被处理过，直接从缓存里取就行
        // secondArea[i][j]==-1说明后手该区域未被处理过
        // secondArea[i][j]!=-1 说明后手该区域已经被处理过，直接从缓存里取就行
        int[][] firstArea = new int[arr.length][arr.length];
        int[][] secondArea = new int[arr.length][arr.length];
        for (int i = 0; i < firstArea.length; i++) {
            for (int j = 0; j < firstArea[0].length; j++) {
                firstArea[i][j] = -1;
                secondArea[i][j] = -1;
            }
        }
        int first = first(arr, 0, arr.length - 1, firstArea, secondArea);
        int second = second(arr, 0, arr.length - 1, firstArea, secondArea);
        // max(先手能拿到的最大分熟，后手能拿到的最大分数)
        return Math.max(first, second);
    }

    // left->right范围内先拿能获得的最大分数
    public static int first(int[] arr, int left, int right, int[][] firstArea, int[][] secondArea) {
        // firstArea[left][right]处理过，直接从缓存里取
        if (firstArea[left][right] != -1) {
            return firstArea[left][right];
        }
        int ans = 0;
        // 只剩一张牌，先后先拿（先手后手是随着剩余牌数不断变化的）
        if (left == right) {
            ans = arr[left];
        } else {
            // 先拿left，在[left+1,right]中作为后手再拿
            // p1=arr[left]+在剩下的牌中作为后手能拿到的最大分数
            int p1 = arr[left] + second(arr, left + 1, right);
            // 先拿right，在[left,right-1]中作为后手在拿
            // p2=arr[right]+在剩下的牌中作为后手能拿到的最大分数
            int p2 = arr[right] + second(arr, left, right - 1);
            // 先手具有主动权，选择分数较大的
            ans = Math.max(p1, p2);
        }
        // firstArea[left,right]存到缓存里，便于后续取用
        firstArea[left][right] = ans;
        return ans;
    }

    // left->right范围内后拿能获得的最大分数
    public static int second(int[] arr, int left, int right, int[][] firstArea, int[][] secondArea) {
        // secondArea[left][right]处理过，直接从缓存里取
        if (secondArea[left][right] != -1) {
            return secondArea[left][right];
        }
        int ans = 0;
        // 只剩一张牌，后手拿不到（先手后手是随着剩余牌数不断变化的）
        if (left == right) {
            ans = 0;
        } else {
            // 对手先拿left,后手作为[left+1,right]范围的先手能拿到的最大分数
            int p1 = first(arr, left + 1, right);
            // 对手先拿right,后手作为[left,right-1]范围的先手能拿到的最大分数
            int p2 = first(arr, left, right - 1);
            // 后手只能尽量拿最大，但是先手不会给你机会拿最大，后手没有主动权
            ans = Math.min(p1, p2);
        }
        // secondArea[left,right]存到缓存里，便于后续取用
        secondArea[left][right] = ans;
        return ans;
    }

    // 获胜者的分数：动态规划
    // getWinnerScoreTwo优化
    // 分析位置依赖（根据getWinnerScoreOne）
    // 图解：拿纸牌-动态规划.drawio
    public static int getWinnerScoreThree(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] firstMap = new int[arr.length][arr.length];
        int[][] secondMap = new int[arr.length][arr.length];
        // 对角线赋值
        for (int i = 0; i < firstMap.length; i++) {
            firstMap[i][i] = arr[i];
        }
        // firstMap和secondMap一层一层向上求值
        // 第一层对角线：(0,1) (1,2) (2,3) (3,4)
        // 第二层对角线：(0,2) (1,3) (2,4)
        // 第三层对角线：(0,3) (1,4)
        // 最终的值：(0,4)
        for (int column = 1; column < arr.length; column++) {
            int left = 0;
            int right = column;
            while (right < arr.length) {
                firstMap[left][right] = Math.max(arr[left] + secondMap[left + 1][right],
                        arr[right] + secondMap[left][right - 1]);
                secondMap[left][right] = Math.min(firstMap[left + 1][right], firstMap[left][right - 1]);
                left++;
                right++;
            }
        }
        return Math.max(firstMap[0][arr.length - 1], secondMap[0][arr.length - 1]);
    }

    // 动态规划对角线依次填表的另一种写法
    public static int getWinnerScoreFour(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] firstArea = new int[arr.length][arr.length];
        int[][] secondArea = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            firstArea[i][i] = arr[i];
        }
        // firstArea每个位置依赖secondArea的左边位置和下边位置
        // secondArea每个位置依赖firstArea的左边位置和下边位置
        // arr.length=5
        // (0,1)->(1,2)->(2,3)->(3,4) gap=1
        // (0,2)->(1,3)->(2,4) gap=2
        // (0,3)->(1,4) gap=3
        // (0,4) gap=4
        for (int gap = 1; gap < arr.length; gap++) {
            for (int left = 0; left < arr.length - gap; left++) {
                int p1 = arr[left] + secondArea[left + 1][left + gap];
                int p2 = arr[left + gap] + secondArea[left][left + gap - 1];
                firstArea[left][left + gap] = Math.max(p1, p2);
                int p3 = firstArea[left + 1][left + gap];
                int p4 = firstArea[left][left + gap - 1];
                secondArea[left][left + gap] = Math.min(p3, p4);
            }
        }
        return Math.max(firstArea[0][arr.length - 1], secondArea[0][arr.length - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(getWinnerScoreOne(arr));
        System.out.println(getWinnerScoreTwo(arr));
        System.out.println(getWinnerScoreThree(arr));
        System.out.println(getWinnerScoreFour(arr));
    }

    // 在线测试
    // 链接：https://www.nowcoder.com/questionTerminal/19c98d950b3347d19f991d10bde12288
    public static class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                int value = scanner.nextInt();
                arr[i] = value;
            }
            System.out.println(bestMethod(arr));
        }

        // 下 左
        public static int bestMethod(int[] arr) {
            int[][] firstHand = new int[arr.length][arr.length];
            int[][] secondHand = new int[arr.length][arr.length];
            for (int i = 0; i < firstHand.length; i++) {
                firstHand[i][i] = arr[i];
                secondHand[i][i] = 0;
            }
            for (int right = 1; right < arr.length; right++) {
                for (int left = right - 1; left >= 0; left--) {
                    firstHand[left][right] = Math.max(arr[left] + secondHand[left + 1][right], arr[right] + secondHand[left][right - 1]);
                    secondHand[left][right] = Math.min(firstHand[left + 1][right], firstHand[left][right - 1]);
                }
            }
            return Math.max(firstHand[0][arr.length - 1], secondHand[0][arr.length - 1]);
        }
    }
}
