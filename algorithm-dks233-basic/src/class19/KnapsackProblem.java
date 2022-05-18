package class19;

/**
 * 背包问题
 * 给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表 i号物品的重量和价值
 * weight>=0 value>0
 * 给定一个正数bag，表示一个载重bag的袋子，装的物品不能超过这个重量
 * 返回能装下的最大价值
 *
 * @author dks233
 * @create 2022-05-18-9:57
 */
public class KnapsackProblem {
    public static int getMaxValueOne(int[] weights, int[] values, int bag) {
        if (weights == null || values == null ||
                values.length == 0 || weights.length != values.length || bag < 0) {
            return -1;
        }
        return process(weights, values, bag, 0);
    }

    /**
     * 从index位置开始能获得的最大价值
     *
     * @param weights 重量数组
     * @param values  价值数组
     * @param rest    背包剩余容量
     * @param index   当年选择位置
     * @return 从index位置开始能获得的最大价值
     */
    public static int process(int[] weights, int[] values, int rest, int index) {
        // 当前选择无效
        // weight[i] <= 0  所以bag等于0的时候可能可以继续选
        // 不能返回0，因为返回0上游不知道这条选择是无效的
        // bag<0时得让上游知道这条选择无效，无效的情况下不能让上游做选择，返回一个-1，让上游去校验
        // 如果返回0，上游选完背包为负，结果返回0，代表还可以继续选
        if (rest < 0) {
            return -1;
        }
        // index越界
        if (index == weights.length) {
            return 0;
        }
        // index没有越界且背包容量没超过限制
        // 不要当前物品
        int p1 = process(weights, values, rest, index + 1);
        // 要当前物品
        // 先检查加上index位置物品后背包容量是否超过限制
        // 如果返回-1，当前选择无效，不能要当前物品
        int next = process(weights, values, rest - weights[index], index + 1);
        int p2 = 0;
        if (next != -1) {
            p2 = values[index] + next;
        }
        return Math.max(p1, p2);
    }

    // 看getMaxValueOne可变参数：rest,index
    // 看getMaxValueOne重复调用：有
    // 分析rest和index变化范围  rest:[0,bag] rest<0经过判断剔除 index:[0,arr.length]
    // 分析位置依赖，怎么填表
    public static int getMaxValueTwo(int[] weights, int[] values, int bag) {
        if (weights == null || values == null ||
                values.length == 0 || weights.length != values.length || bag < 0) {
            return -1;
        }
        // dp[index][rest] 表示剩余背包容量为rest情况下，从index位置开始能获得的最大价值
        int[][] dp = new int[weights.length + 1][bag + 1];
        // dp除最后一行外每个位置都依赖下一行，从下往上根据下一行推上一行
        for (int index = weights.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                // rest<0通过dp[index+1][rest]取不到，通过判断避免
                int next = rest - weights[index] < 0 ? -1 : dp[index + 1][rest - weights[index]];
                int p2 = 0;
                if (next != -1) {
                    p2 = values[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(getMaxValueOne(weights, values, bag));
        System.out.println(getMaxValueTwo(weights, values, bag));
    }
}