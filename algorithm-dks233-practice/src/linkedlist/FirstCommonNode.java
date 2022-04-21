package linkedlist;

/**
 * 剑指offer52：输入两个链表，找到它们的第一个公共节点
 *
 * @author dks233
 * @create 2022-04-21-16:30
 */
public class FirstCommonNode {

    // 方法1：双指针
    // 设公共节点数为c个，链表1长度和链表2长度分别为a和b，公共节点前长度分别为a-c和b-c
    // 让指针a先遍历链表1，到链表1尾部后定位到链表2头部位置，然后继续走b-c个距离
    // 让指针b先遍历链表2，到链表2尾部后定位到链表1头部位置，然后继续走a-c个距离
    // 走完后指针a总共走的距离是a+(b-c)，b走的距离是b+(a-c)，两节点如果相遇，返回此时headOne或headTwo所在位置
    // 如果链表没有公共节点，走完距离后headOne和headTwo值都为null
    // 时间复杂度：O(m+n)，额外空间复杂度：O(1)
    public static ListNode getCommonNodeOne(ListNode headOne, ListNode headTwo) {
        ListNode a = headOne;
        ListNode b = headTwo;
        while (a != b) {
            a = a != null ? a.next : headTwo;
            b = b != null ? b.next : headOne;
        }
        return a;
    }

    // 方法2：差值法
    // 遍历链表1和链表2，获取长度差d，长度较长的链表先走d步
    // 时间复杂度：O(m+n)，额外空间复杂度：O(1)
    public static ListNode getCommonNodeTwo(ListNode headOne, ListNode headTwo) {
        ListNode one = headOne;
        ListNode two = headTwo;
        // 遍历获取两个链表的长度
        int lengthOne = 0;
        int lengthTwo = 0;
        while (one != null) {
            lengthOne++;
            one = one.next;
        }
        while (two != null) {
            lengthTwo++;
            two = two.next;
        }
        one = headOne;
        two = headTwo;
        int d = lengthOne - lengthTwo;
        if (d > 0) {
            // 1 2 3 4
            // 1 2 3 4 5 6 7 8
            // 如果d=4 4->3,3->2,2->1,1->0走四步
            while (d-- > 0) {
                one = one.next;
            }
        } else if (d < 0) {
            d = -d;
            while (d-- > 0) {
                two = two.next;
            }
        }
        // 如果相遇，跳出循环
        // 如果遍历到链表尾部都未相遇，跳出循环，这种情况null==null
        while (one != two) {
            one = one.next;
            two = two.next;
        }
        return one;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
