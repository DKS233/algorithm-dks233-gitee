package class16;

import java.util.HashSet;
import java.util.Stack;

/**
 * 深度优先遍历
 *
 * @author dks233
 * @create 2022-05-10-14:37
 */

@SuppressWarnings("MapOrSetKeyShouldOverrideHashCodeEquals")
public class DepthFirstSearch {
    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(node);
        System.out.print(node.value + "   ");
        set.add(node);
        while (!stack.isEmpty()) {
            node = stack.pop();
            for (Node next : node.nexts) {
                if (!set.contains(next)) {
                    // 弹出的节点重新压回
                    stack.push(node);
                    stack.push(next);
                    System.out.print(next.value + "   ");
                    set.add(next);
                    // 发现有一个不在set里，就不遍历了，一次只走一条路，回来的时候再走别的路
                    break;
                }
            }
        }
    }
}
