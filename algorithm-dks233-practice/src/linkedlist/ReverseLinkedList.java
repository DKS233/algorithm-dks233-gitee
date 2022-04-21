package linkedlist;

/**
 * 剑指offer24：反转链表  输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 * leetcode92: 反转链表2 给你单链表的头指针head和两个整数left和right，其中left<=right。
 * 请你反转从位置left到位置right的链表节点，返回反转后的链表。
 *
 * @author dks233
 * @create 2022-04-20-15:36
 */
public class ReverseLinkedList {
    // 剑指offer24：反转链表
    // 方法1：迭代实现
    // 时间复杂度：O(N)，额外空间复杂度：O(1)
    public static ListNode reverseOne(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    // 剑指offer24：反转链表
    // TODO:方法2：递归实现
    public static ListNode reverseTwo(ListNode head) {
        return null;
    }

    // leetcode92：链表局部反转
    // 方法1：局部反转，然后拼接
    // 头节点可能发生变化（反转）的情况用虚拟头节点
    public static ListNode reverseThree(ListNode head, int left, int right) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // 假设局部区域为[start,end]，先找到局部左边界的前一个节点
        ListNode beforeStart = dummyHead;
        for (int i = 0; i < left - 1; i++) {
            beforeStart = beforeStart.next;
        }
        // 找到局部区域的右边界节点
        ListNode end = beforeStart;
        for (int i = 0; i < right - left + 1; i++) {
            end = end.next;
        }
        // 切割局部区域
        ListNode start = beforeStart.next;
        ListNode afterEnd = end.next;
        beforeStart.next = null;
        end.next = null;
        // 局部反转
        start = reverseOne(start);
        // 恢复连接（找到反转后局部链表的头节点和尾结点，和原链表进行连接）
        // 先找到反转后链表的头结点，然后根据长度找反转后链表的尾结点
        beforeStart.next = start;
        end = start;
        for (int i = 0; i < right - left; i++) {
            end = end.next;
        }
        end.next = afterEnd;
        return dummyHead.next;
    }

    // 方法1的另一种写法：注意局部链表反转的处理
    public static ListNode anotherReverseThree(ListNode head, int left, int right) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // 假设局部区域为[start,end]，先找到局部左边界的前一个节点
        ListNode beforeStart = dummyHead;
        for (int i = 0; i < left - 1; i++) {
            beforeStart = beforeStart.next;
        }
        // 找到局部区域的右边界节点
        ListNode end = beforeStart;
        for (int i = 0; i < right - left + 1; i++) {
            end = end.next;
        }
        // 切割局部区域
        ListNode start = beforeStart.next;
        ListNode afterEnd = end.next;
        beforeStart.next = null;
        end.next = null;
        // 局部反转
        reverse(start);
        // 恢复连接（找到反转后局部链表的头节点和尾结点，和原链表进行连接）
        // 最终返回的头节点是end，reverse过程对start和end的位置没影响，知识指向发生了变化
        // 详细分析见链表反转.drawio
        beforeStart.next = end;
        start.next = afterEnd;
        return dummyHead.next;
    }

    // 方法2
    // 找到局部区域的左边界和左边界的前一个节点，将左边界之后的元素一个个删除，然后插到左边界的前一个节点后面
    public static ListNode reverseFour(ListNode head, int left, int right) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // 找到局部区域的左边界和左边界的前一个节点
        ListNode beforeStart = dummyHead;
        for (int i = 0; i < left - 1; i++) {
            beforeStart = beforeStart.next;
        }
        ListNode start = beforeStart.next;
        // 将start后的元素一个个删除，插入到beforeStart之后
        for (int i = 0; i < right - left; i++) {
            ListNode deleteNode = start.next;
            start.next = start.next.next;
            deleteNode.next = beforeStart.next;
            beforeStart.next = deleteNode;
        }
        return dummyHead.next;
    }

    public static void reverse(ListNode head) {
        ListNode next = null;
        ListNode pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
