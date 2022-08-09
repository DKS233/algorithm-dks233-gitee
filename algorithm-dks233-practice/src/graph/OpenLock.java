package graph;

import java.util.*;

/**
 * 剑指offer专项突击版：剑指 Offer II 109. 开密码锁
 * 参考文档：https://leetcode.cn/problems/open-the-lock/solution/shou-hua-tu-jie-tu-bfs-lin-jie-guan-xi-7-ud8c/
 *
 * @author dks233
 * @create 2022-08-03-16:57
 */
public class OpenLock {
    public int openLock(String[] deadends, String target) {
        HashSet<String> deadSet = new HashSet<>(Arrays.asList(deadends));
        if (deadSet.contains("0000")) {
            return -1;
        }
        HashSet<String> visited = new HashSet<>();
        int count = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (target.equals(cur)) {
                    return count;
                }
                if (deadSet.contains(cur) || visited.contains(cur)) {
                    continue;
                }
                visited.add(cur);
                for (int j = 0; j < 4; j++) {
                    int up = ((cur.charAt(j) - '0') + 1) % 10;
                    int down = ((cur.charAt(j) - '0') + 9) % 10;
                    queue.offer(cur.substring(0, j) + up + cur.substring(j + 1));
                    queue.offer(cur.substring(0, j) + down + cur.substring(j + 1));
                }
            }
            count++;
        }
        return -1;
    }
}
