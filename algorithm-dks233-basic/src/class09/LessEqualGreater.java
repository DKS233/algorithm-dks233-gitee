package class09;

import common.LinkedListUtils.Node;

/**
 * 给定一个单链表的头节点，给定一个整数，将链表按n划分成左边<n，中间=n，右边>n
 *
 * @author dks233
 * @create 2022-04-18-16:00
 */
public class LessEqualGreater {
    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printNodeList(head1);
        System.out.println();
        head1 = partitionOne(head1, 5);
        printNodeList(head1);
        System.out.println();
        Node head2 = new Node(7);
        head2.next = new Node(9);
        head2.next.next = new Node(1);
        head2.next.next.next = new Node(8);
        head2.next.next.next.next = new Node(5);
        head2.next.next.next.next.next = new Node(2);
        head2.next.next.next.next.next.next = new Node(5);
        printNodeList(head2);
        System.out.println();
        head2 = partitionOne(head2, 5);
        printNodeList(head2);
        System.out.println();
    }

    // 方法1：将链表放到数组里，在数组上做partition
    public static Node partitionOne(Node head, int number) {
        if (head == null) {
            return null;
        }
        int i = 0;
        Node cur = head;
        // 计算链表长度
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        // 将链表放到数组中
        Node[] nodeArray = new Node[i];
        cur = head;
        for (i = 0; i < nodeArray.length; i++) {
            nodeArray[i] = cur;
            cur = cur.next;
        }
        // 对数组做partition
        arrPartition(nodeArray, number);
        for (i = 0; i < nodeArray.length - 1; i++) {
            nodeArray[i].next = nodeArray[i + 1];
        }
        nodeArray[nodeArray.length - 1].next = null;
        // 返回partition后的头节点
        return nodeArray[0];
    }

    // 方法2：链表分为大中小三部分，再把各部分串起来
    public static Node partitionTwo(Node head, int number) {
        Node smallStart = null;
        Node smallEnd = null;
        Node equalStart = null;
        Node equalEnd = null;
        Node bigStart = null;
        Node bigEnd = null;
        Node cur = head;
        Node next = null;
        while (cur != null) {
            // 先把当前节点和后面节点断开
            next = cur.next;
            cur.next = null;
            if (cur.data < number) {
                if (smallStart == null) {
                    smallStart = cur;
                    smallEnd = cur;
                } else {
                    smallEnd.next = cur;
                    smallEnd = cur;
                }
            } else if (cur.data == number) {
                if (equalStart == null) {
                    equalStart = cur;
                    equalEnd = cur;
                } else {
                    equalEnd.next = cur;
                    equalEnd = cur;
                }
            } else {
                if (bigStart == null) {
                    bigStart = cur;
                    bigEnd = cur;
                } else {
                    bigEnd.next = cur;
                    bigEnd = cur;
                }
            }
            cur = next;
        }
        return getInitialNode(smallStart, smallEnd, equalStart, equalEnd, bigStart, bigEnd);
    }

    public static void arrPartition(Node[] arr, int number) {
        // 小于区边界和大于区边界
        int small = -1;
        int big = arr.length;
        // 当前元素所在位置
        int index = 0;
        while (index < big) {
            if (arr[index].data < number) {
                swap(arr, ++small, index++);
            } else if (arr[index].data == number) {
                index++;
            } else {
                swap(arr, index, --big);
            }
        }
    }

    // getInitialNode方法的对数器(左程云实现）
    public static Node comparator(Node smallStart, Node smallEnd,
                                  Node equalStart, Node equalEnd, Node bigStart, Node bigEnd) {
        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
        if (smallEnd != null) { // 如果有小于区域
            smallEnd.next = equalStart;
            equalEnd = equalEnd == null ? smallEnd : equalEnd; // 下一步，谁去连大于区域的头，谁就变成eT
        }
        // 下一步，一定是需要用eT 去接 大于区域的头
        // 有等于区域，eT -> 等于区域的尾结点
        // 无等于区域，eT -> 小于区域的尾结点
        // eT 尽量不为空的尾巴节点
        if (equalEnd != null) { // 如果小于区域和等于区域，不是都没有
            equalEnd.next = bigStart;
        }
        return smallStart != null ? smallStart : (equalStart != null ? equalStart : bigStart);
    }

    public static Node getInitialNode(Node smallStart, Node smallEnd,
                                      Node equalStart, Node equalEnd, Node bigStart, Node bigEnd) {
        if (smallEnd != null) {
            if (equalEnd != null) {
                // 小于区+等于区+大于区
                if (bigEnd != null) {
                    smallEnd.next = equalStart;
                    equalEnd.next = bigStart;
                    return smallStart;
                }
                // 小于区+等于区
                else {
                    smallEnd.next = equalStart;
                    return smallStart;
                }
            } else {
                // 小于区+大于区
                if (bigEnd != null) {
                    smallEnd.next = bigStart;
                    return smallStart;
                }
                // 小于区
                else {
                    return smallStart;
                }
            }
        } else {
            if (equalEnd != null) {
                // 等于区+大于区
                if (bigEnd != null) {
                    equalEnd.next = bigStart;
                    return equalStart;
                }
                // 等于区
                else {
                    return equalStart;
                }
            } else {
                // 大于区
                if (bigEnd != null) {
                    return bigStart;
                }
                // 小于等于大于区都没有元素（head==null）
                else {
                    return null;
                }
            }
        }
    }

    public static void swap(Node[] arr, int a, int b) {
        Node temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void printNodeList(Node node) {
        if (node == null) {
            return;
        }
        while (node != null) {
            System.out.print(node.data + "  ");
            node = node.next;
        }
    }
}
