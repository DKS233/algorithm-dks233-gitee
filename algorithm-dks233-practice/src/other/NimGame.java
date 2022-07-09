package other;

/**
 * leetcode292. Nim 游戏
 *
 * @author dks233
 * @create 2022-07-09-16:09
 */
@SuppressWarnings("ALL")
public class NimGame {
    // 题目要求：你作为先手
    // 石子数为[1,3] 先手必胜
    // 石子数为4 先手必败
    // 石子数为[5,7] 先手直接拿到石子数为4，此时后手变成下一轮的先手，必败，此时胜者为第一轮的先手
    // 石子数为8 不管先手拿几个，后手都能把石子数变成4，第一轮的先手必败
    public boolean canWinNim(int n) {
        return (n % 4) != 0;
    }
}
