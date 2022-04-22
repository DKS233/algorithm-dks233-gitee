package linkedlist;

/**
 * leetcode2:两个非空链表，节点val为非负整数，每位数字都是按照逆序的方式存储的
 * 将两数相加，并返回一个表示和的链表
 * 2->4->3  5->6->4 返回  7->0->8  注：342+465=807
 * 9->9->9->9->9->9->9  9->9->9->9 返回  8->9->9->9->0->0->0->1 注：9999+9999999=10009998
 *
 * @author dks233
 * @create 2022-04-22-15:08
 */
public class TwoNumbersAdd {
    // 考虑进位
    // 999+9 看做 999+009，即把9->9->9和9  看做 9->9->9和9->0->9
    public static ListNode addOne(ListNode headOne, ListNode headTwo) {
        // 进位
        int carry = 0;
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        // headOne!=null headTwo!=null 当前位置两个链表val都有数
        // headOne!=null headTwo==null 当前位置链表1有数，链表2没数，看成0
        // headOne==null headTwo!=null 当前位置链表2有数，链表1没数，看成0
        while (headOne != null || headTwo != null) {
            // 当前位置上没数，看成0
            int numberOne = headOne == null ? 0 : headOne.val;
            int numberTwo = headTwo == null ? 0 : headTwo.val;
            // 当前位上俩数的和（考虑了进位） carry是初始进位或上一次循环进上来的
            int sum = numberOne + numberTwo + carry;
            // 需要往下一个位置进的数，满10进1
            carry = sum / 10;
            // 节点位置应该存放的值
            int value = sum % 10;
            cur.next = new ListNode(value);
            // headOne和headTwo谁不为空，谁移动
            if (headOne != null) {
                headOne = headOne.next;
            }
            if (headTwo != null) {
                headTwo = headTwo.next;
            }
            cur = cur.next;
        }
        // 最后一位发生了进位
        if (carry == 1) {
            cur.next = new ListNode(1);
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

    public static void main(String[] args) {
        ListNode headOne = new ListNode(2);
        headOne.next = new ListNode(4);
        headOne.next.next = new ListNode(3);
        ListNode headTwo = new ListNode(5);
        headTwo.next = new ListNode(6);
        headTwo.next.next = new ListNode(4);
        ListNode listNode = addOne(headOne, headTwo);
        while (listNode != null) {
            System.out.print(listNode.val + "   ");
            listNode = listNode.next;
        }
        System.out.println();
    }
}
