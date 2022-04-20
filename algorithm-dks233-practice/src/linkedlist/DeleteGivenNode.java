package linkedlist;

/**
 * 剑指offer18
 * 给定单链表的头指针和一个要删除的节点的值，删除节点，返回删除后的头结点（链表节点的值不相同）
 * leetcode203
 * 给定单链表头结点和一个需要删除的节点的值，删除节点，返回删除后的头结点（链表节点值可以相同）
 *
 * @author dks233
 * @create 2022-04-19-16:13
 */
public class DeleteGivenNode {
    // leetcode203，链表节点值可以相同的情况
    // 注意头节点需要删除的情况：方法1：头节点移动，方法2：添加虚拟头节点
    // 方法1：头节点如果需要删除，头节点向后移动
    public static ListNode leetcodeDeleteOne(ListNode head, int val) {
        // 定位到第一个不需要删除的位置
        while (head != null) {
            if (head.val != val) {
                break;
            }
            head = head.next;
        }
        // 到这里head==null或head!=null
        // head==null 说明head本身就为null或者head被删除
        // head!=null head就是新链表的头节点
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val == val) {
                // 该步执行完后，不执行cur=cur.next，因为不知道cur.next位置需不需要删除，这需要下轮判断
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    // 方法2：增加虚拟头节点
    // 头节点一直是dummyHead.next，head需要删除和其他元素删除步骤一致
    public static ListNode leetcodeDeleteTwo(ListNode head, int val) {
        ListNode dummyHead = new ListNode(val - 1);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }

    // 剑指offer18（链表中的节点值不重复）
    // 改写leetcode203写法，删除掉第一个节点后结束方法，返回头节点
    // 方法1：头节点移动
    public static ListNode offerDeleteOne(ListNode head, int val) {
        // 定位到第一不需要删除的节点
        while (head != null) {
            if (head.val != val) {
                break;
            }
            head = head.next;
        }
        // 到这儿head==null或者head!=null
        // head==null 可能head本身就是null，可能head被删除
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    // 方法2：设置虚拟头节点
    public static ListNode offerDeleteTwo(ListNode head, int val) {
        ListNode dummyHead = new ListNode(val - 1);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
