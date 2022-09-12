package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode589. N 叉树的前序遍历
 *
 * @author dks233
 * @create 2022-09-12-19:00
 */
@SuppressWarnings("ALL")
public class PreorderTraversalOfNTree {
    List<Integer> list = new ArrayList<>();

    // 前序遍历：头-子-子-子
    public List<Integer> preorder(Node root) {
        process(root);
        return list;
    }

    public void process(Node head) {
        if (head == null) {
            return;
        }
        list.add(head.val);
        for (Node child : head.children) {
            process(child);
        }
    }

    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int val) {
            val = val;
        }

        public Node(int val, List<Node> children) {
            val = val;
            children = children;
        }
    }
}
