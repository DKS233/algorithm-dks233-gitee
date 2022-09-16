package greedy;

import java.util.Arrays;

/**
 * leetcode1029. 两地调度
 *
 * @author dks233
 * @create 2022-09-16-8:59
 */
@SuppressWarnings("ALL")
public class TwoPlaceDispatching {
    // costs[i] -> [costA,costB]
    // 先将2n人全部飞到A市，然后再将n个人飞到B市
    // 代价是∑(costB-costA)
    // 选出代价最低的n个人飞到B市
    public int twoCitySchedCost(int[][] costs) {
        int minCost = 0;
        for (int i = 0; i < costs.length; i++) {
            minCost += costs[i][0];
        }
        Arrays.sort(costs, (a, b) -> (a[1] - a[0]) - (b[1] - b[0]));
        for (int i = 0; i < costs.length / 2; i++) {
            minCost += costs[i][1] - costs[i][0];
        }
        return minCost;
    }
}
