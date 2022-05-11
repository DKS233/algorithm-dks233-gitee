package class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 宽度优先遍历
 *
 * @author dks233
 * @create 2022-05-10-14:02
 */
@SuppressWarnings("ALL")
public class BreadthFirstSearch {
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.offer(node);
        set.add(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            System.out.print(node.value + "   ");
            // 看node的直接邻居是否已经在hashSet里
            // 如果不在，将node的直接邻居加入到队列和set中
            // 如果已经在set中，说明已经node的直接邻居已经被添加到队列里过了
            // set控制同一个node只能往队列里添加一次，也只能弹出打印一次
            for (Node next : node.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.offer(next);
                }
            }
        }
    }
}





















