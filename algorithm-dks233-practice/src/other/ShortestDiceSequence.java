package other;

import java.util.HashSet;

/**
 * leetcode2350. 不可能得到的最短骰子序列
 * 参考文档：https://leetcode.cn/problems/shortest-impossible-sequence-of-rolls/solution/by-heren1229-bgnx/
 *
 * @author dks233
 * @create 2022-07-28-22:57
 */
@SuppressWarnings("ALL")
public class ShortestDiceSequence {
    // 分析：length长度的子序列符合条件需要满足：
    // rolls中存在至少length个包含1-k的子序列
    // 注：这些子序列可以有重复的数，但是一定要完整的包含1-k
    // 注：这些子序列边界不可以重合
    public int shortestSequence(int[] rolls, int k) {
        int curLen = 1;
        HashSet<Integer> set = new HashSet<>();
        int index = 0;
        int count = 0;
        while (true) {
            while (index < rolls.length) {
                set.add(rolls[index]);
                index++;
                if (set.size() == k) {
                    count++;
                    set.clear();
                }
                if (count == curLen) {
                    break;
                }
            }
            if (count < curLen) {
                return curLen;
            }
            curLen++;
        }
    }

}
