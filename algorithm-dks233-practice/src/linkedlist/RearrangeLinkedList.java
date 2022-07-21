package linkedlist;

import java.util.ArrayList;

/**
 * 剑指offer专项突击版：剑指 Offer II 026. 重排链表
 *
 * @author dks233
 * @create 2022-07-20-18:27
 */
@SuppressWarnings("ALL")
public class RearrangeLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    // 时间复杂度：O(N)
    // 空间复杂度：O(1)
    // 找到链表中点，将后半部分翻转，然后两个链表进行拼接
    public static class MethodThree {
        // 找到链表中点，将后半部分翻转，然后两个链表进行拼接
        public void reorderList(ListNode head) {
            // 找到链表中点
            ListNode fast = head;
            ListNode slow = head;
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            // 此时slow所在位置就是链表中点位置
            // 在中点位置分隔链表，后半部分链表翻转
            ListNode slowNext = slow.next;
            slow.next = null;
            ListNode headTwo = reverse(slowNext);
            ListNode headOne = head;
            // 将链表链表进行拼接
            add(headOne, headTwo);
        }

        public ListNode reverse(ListNode head) {
            ListNode next = null;
            ListNode pre = null;
            while (head != null) {
                next = head.next;
                head.next = pre;
                pre = head;
                head = next;
            }
            return pre;
        }

        public void add(ListNode headOne, ListNode headTwo) {
            ListNode cur = new ListNode(-1);
            while (headOne != null && headTwo != null) {
                cur.next = headOne;
                headOne = headOne.next;
                cur = cur.next;
                cur.next = headTwo;
                headTwo = headTwo.next;
                cur = cur.next;
            }
            while (headOne != null) {
                cur.next = headOne;
                headOne = headOne.next;
                cur = cur.next;
            }
            while (headTwo != null) {
                cur.next = headTwo;
                headTwo = headTwo.next;
                cur = cur.next;
            }
        }
    }

    public static class MethodTwo {
        // 时间复杂度：O(N)
        // 空间复杂度：O(N)
        // 用链表存节点，然后每一次取头取尾
        public void reorderList(ListNode head) {
            ArrayList<ListNode> list = new ArrayList<>();
            ListNode cur = head;
            while (cur != null) {
                list.add(cur);
                cur = cur.next;
            }
            int left = 0;
            int right = list.size() - 1;
            while (left < right) {
                list.get(left).next = list.get(right);
                left++;
                if (left == right) {
                    break;
                }
                list.get(right).next = list.get(left);
                right--;
            }
            list.get(left).next = null;
        }
    }


    // 从head.next开始，不断翻转链表
    // 时间复杂度：O(N)
    public static class MethodOne {
        public void reorderList(ListNode head) {
            ListNode cur = head;
            while (cur != null) {
                cur.next = reverse(cur.next);
                cur = cur.next;
            }
        }

        public ListNode reverse(ListNode head) {
            ListNode next = null;
            ListNode pre = null;
            while (head != null) {
                next = head.next;
                head.next = pre;
                pre = head;
                head = next;
            }
            return pre;
        }
    }
}
