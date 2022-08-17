package linkedlist;

import javax.management.DynamicMBean;
import java.util.PriorityQueue;

/**
 * 剑指offer专项突击版：剑指 Offer II 077. 链表排序
 * 剑指offer专项突击版：剑指 Offer II 078. 合并排序链表
 *
 * @author dks233
 * @create 2022-08-17-15:45
 */
@SuppressWarnings("ALL")
public class LinkedListSort {
    public static class ProblemOne {
        public static class ListNode {
            int val;
            ListNode next;

            public ListNode(int val) {
                this.val = val;
            }
        }

        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            // 奇数长度：slow到中点位置，偶数长度：slow到左中点位置
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            // 根据slow位置，将链表切割成两个链表，分别排序，然后进行归并
            ListNode next = slow.next;
            slow.next = null;
            ListNode headOne = sortList(head);
            ListNode headTwo = sortList(next);
            return merge(headOne, headTwo);
        }

        public ListNode merge(ListNode headOne, ListNode headTwo) {
            ListNode dummyHead = new ListNode(-1);
            ListNode cur = dummyHead;
            while (headOne != null && headTwo != null) {
                if (headOne.val < headTwo.val) {
                    cur.next = headOne;
                    cur = cur.next;
                    headOne = headOne.next;
                } else {
                    cur.next = headTwo;
                    cur = cur.next;
                    headTwo = headTwo.next;
                }
            }
            while (headOne != null) {
                cur.next = headOne;
                cur = cur.next;
                headOne = headOne.next;
            }
            while (headTwo != null) {
                cur.next = headTwo;
                cur = cur.next;
                headTwo = headTwo.next;
            }
            return dummyHead.next;
        }
    }

    public static class ProblemTwoMethodOne {
        public static class ListNode {
            int val;
            ListNode next;

            public ListNode(int val) {
                this.val = val;
            }
        }

        // 方法1：两两归并
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            if (lists.length < 2) {
                return lists[0];
            }
            ListNode curHead = merge(lists[0], lists[1]);
            for (int index = 2; index < lists.length; index++) {
                curHead = merge(curHead, lists[index]);
            }
            return curHead;
        }

        public ListNode merge(ListNode headOne, ListNode headTwo) {
            ListNode dummyHead = new ListNode(-1);
            ListNode cur = dummyHead;
            while (headOne != null && headTwo != null) {
                if (headOne.val < headTwo.val) {
                    cur.next = headOne;
                    headOne = headOne.next;
                    cur = cur.next;
                } else {
                    cur.next = headTwo;
                    headTwo = headTwo.next;
                    cur = cur.next;
                }
            }
            if (headOne != null) {
                cur.next = headOne;
            }
            if (headTwo != null) {
                cur.next = headTwo;
            }
            return dummyHead.next;
        }
    }

    public static class ProblemTwoMethodTwo {
        public static class ListNode {
            int val;
            ListNode next;

            public ListNode(int val) {
                this.val = val;
            }
        }

        // 方法1：小根堆
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            PriorityQueue<ListNode> heap = new PriorityQueue<>((node1, node2) -> node1.val - node2.val);
            for (ListNode node : lists) {
                if (node != null) {
                    heap.offer(node);
                }
            }
            ListNode dummyHead = new ListNode(-1);
            ListNode cur = dummyHead;
            while (!heap.isEmpty()) {
                ListNode poll = heap.poll();
                cur.next = poll;
                cur = cur.next;
                if (poll.next != null) {
                    heap.offer(poll.next);
                }
            }
            return dummyHead.next;
        }
    }
}
