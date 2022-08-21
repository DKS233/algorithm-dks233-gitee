package week307;


import java.util.*;

/**
 * 6154. 感染二叉树需要的总时间
 *
 * @author dks233
 * @create 2022-08-21-10:12
 */
@SuppressWarnings("ALL")
public class Three {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    HashMap<Integer, Set<Integer>> nodeMap = new HashMap<>();
    HashSet<Integer> visitSet = new HashSet<>();
    int time = 0;

    public int amountOfTime(TreeNode root, int start) {
        if (root.left == null && root.right == null) {
            return time;
        }
        // 先建图
        createGraph(root);
        // bfs，统计感染需要的时间
        bfs(start);
        return time;
    }

    public void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visitSet.add(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int temp = queue.poll();
                for (Integer next : nodeMap.get(temp)) {
                    if (!visitSet.contains(next)) {
                        visitSet.add(next);
                        queue.offer(next);
                    }
                }
            }
            if (!queue.isEmpty()) {
                time++;
            }
        }
    }

    public void createGraph(TreeNode head) {
        if (head == null) {
            return;
        }
        if (head.left != null) {
            nodeMap.putIfAbsent(head.val, new HashSet<>());
            nodeMap.putIfAbsent(head.left.val, new HashSet<>());
            nodeMap.get(head.val).add(head.left.val);
            nodeMap.get(head.left.val).add(head.val);
        }
        if (head.right != null) {
            nodeMap.putIfAbsent(head.val, new HashSet<>());
            nodeMap.putIfAbsent(head.right.val, new HashSet<>());
            nodeMap.get(head.val).add(head.right.val);
            nodeMap.get(head.right.val).add(head.val);
        }
        createGraph(head.left);
        createGraph(head.right);
    }
}
