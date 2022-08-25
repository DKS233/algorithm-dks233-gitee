package other;

/**
 * leetcode134. 加油站
 * 参考文档：https://leetcode.cn/problems/gas-station/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--30/
 *
 * @author dks233
 * @create 2022-08-25-13:03
 */
@SuppressWarnings("ALL")
public class GasStation {
    // 暴力解法
    public static class MethodOne {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            // start出发能不能到达下一个点
            for (int start = 0; start < gas.length; start++) {
                // j：start出发能到达的最远距离
                int j = start;
                int curGas = gas[start];
                while (curGas >= cost[j]) {
                    curGas = curGas - cost[j] + gas[(j + 1) % n];
                    j = (j + 1) % n;
                    if (j == start) {
                        return j;
                    }
                }
            }
            return -1;
        }
    }

    public static class MethodTwo {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            // start出发能不能到达下一个点
            for (int start = 0; start < gas.length; start++) {
                // j：start出发能到达的最远距离
                int j = start;
                int curGas = gas[start];
                while (curGas >= cost[j]) {
                    curGas = curGas - cost[j] + gas[(j + 1) % n];
                    j = (j + 1) % n;
                    if (j == start) {
                        return j;
                    }
                }
                // 如果start到达不了起点，最远到达了j
                // i   j
                // i最远到j，则i->j范围内都无法绕圈
                // 分析：如果i+1可以绕圈，i+1可以到达j+1，i可以到达i+1，即i可以到达j+1，但是i最远可以到达j，所以i可以直接跳到j+1位置
                // j   i
                // i最远到j，所有数都不能绕圈
                if (j < start) {
                    return -1;
                }
                start = j;
            }
            return -1;
        }
    }
}
