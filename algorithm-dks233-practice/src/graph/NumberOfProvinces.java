package graph;

/**
 * 剑指offer专项突击版：剑指 Offer II 116. 省份数量
 * 参考文档：https://leetcode.cn/problems/number-of-provinces/solution/dfs-bfs-bing-cha-ji-3-chong-fang-fa-ji-s-edkl/
 *
 * @author dks233
 * @create 2022-08-10-16:40
 */
@SuppressWarnings("ALL")
public class NumberOfProvinces {
    // 标记哪一个节点被访问过了
    boolean[] visited;

    public int findCircleNum(int[][] isConnected) {
        visited = new boolean[isConnected.length];
        int count = 0;
        for (int index = 0; index < visited.length; index++) {
            if (!visited[index]) {
                count++;
                dfs(isConnected, index);
            }
        }
        return count;
    }

    public void dfs(int[][] isConnected, int index) {
        visited[index] = true;
        // 访问与index相邻的节点
        for (int i = 0; i < isConnected.length; i++) {
            if (isConnected[index][i] == 1 && !visited[i]) {
                dfs(isConnected, i);
            }
        }
    }
}
