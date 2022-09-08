package other;

/**
 * leetcode277. 搜寻名人
 *
 * @author dks233
 * @create 2022-09-08-14:34
 */
@SuppressWarnings("ALL")
public class SearchForCelebrities {
    // 统计每个节点的出度和入度，出度为0且入度为n-1的节点即为所求
    public int findCelebrity(int n) {
        // 第一次遍历，先尝试找到出度为0的点(如果有认识的人，说明出度不是0)
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (knows(ans, i)) {
                ans = i;
            }
        }
        // 第二次遍历，判断该点的入度是不是n-1，出度是不是0
        for (int i = 0; i < n; i++) {
            if (i != ans) {
                if (!knows(i, ans)) {
                    return -1;
                }
                if (knows(ans, i)) {
                    return -1;
                }
            }
        }
        return ans;
    }

    // 只为测试
    public boolean knows(int i, int j) {
        return false;
    }
}
