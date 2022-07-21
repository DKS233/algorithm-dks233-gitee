package linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指offer专项突击版：剑指 Offer II 028. 展平多级双向链表
 *
 * @author dks233
 * @create 2022-07-20-21:28
 */
@SuppressWarnings("ALL")
public class FlattenMultilevelBidirectionalLinkedlist {
    public static class Node {
        int val;
        Node next;
        Node prev;
        Node child;

        public Node(int val) {
            this.val = val;
        }
    }

    List<Node> list = new ArrayList<>();

    public Node flatten(Node head) {
        if (head == null) {
            return head;
        }
        // 把所有节点按照顺序添加到list中
        process(head);
        if (list.size() == 1) {
            return head;
        }
        int left = 0;
        int right = 1;
        while (right < list.size()) {
            list.get(left).next = list.get(right);
            list.get(right).prev = list.get(left);
            left++;
            right++;
        }
        // 第一个节点和最后一个节点处理
        list.get(0).prev = null;
        list.get(list.size() - 1).next = null;
        // 所有节点的child置null
        for (int index = 0; index < list.size(); index++) {
            list.get(index).child = null;
        }
        return list.get(0);
    }

    public void process(Node head) {
        if (head == null) {
            return;
        }
        list.add(head);
        // head有child，就先遍历child
        if (head.child != null) {
            process(head.child);
        }
        // head有next，遍历next
        if (head.next != null) {
            process(head.next);
        }
    }
}
