package class11;
import common.TreeUtils.Node;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树最大宽度（二叉树最宽的层有几个节点）
 *
 * @author dks233
 * @create 2022-04-25-20:34
 */
public class MaxWidth {
    // 得到二叉树最大宽度（根据按层遍历改造）
    // 图解：二叉树最大宽度.drawio
    public static int getMaxWidth(Node head) {
        if (head == null) {
            return 0;
        }
        // 当前层的节点数
        int curNodeCount = 0;
        // 当前层的最后一节点
        Node curEnd = null;
        // 下一层的最后一个节点
        Node nextEnd = null;
        // 最大节点数量
        int max = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        // 第一层最后一个节点是head
        curEnd = head;
        while (!queue.isEmpty()) {
            head = queue.poll();
            // 队列每弹出一个节点，当前层节点数+1
            curNodeCount++;
            if (head.left != null) {
                queue.offer(head.left);
                // 队列每加入一个节点，nextEnd更新一下
                nextEnd = head.left;
            }
            if (head.right != null) {
                queue.offer(head.right);
                // 队列每加入一个节点，nextEnd更新一下
                nextEnd = head.right;
            }
            // 判断弹出的是否是当前层的最后一个节点
            if (head == curEnd) {
                // 如果是，说明当前层遍历结束，需要进行下一层的遍历
                // curEnd=nextEnd  nextEnd=null
                // 计算二叉树最大宽度 当前层节点数归0
                curEnd = nextEnd;
                nextEnd = null;
                max = Math.max(max, curNodeCount);
                curNodeCount = 0;
            }
        }
        return max;
    }

    // 得到二叉树最大宽度方法2：hashMap实现
    public static int getMaxWidthTwo(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        // key:当前遍历到的节点 value:当前节点在第几层
        HashMap<Node, Integer> hashMap = new HashMap<>();
        // 当前遍历的层级，从1开始，当前层遍历结束，层级+1
        int curLevel = 1;
        hashMap.put(head, 1);
        // 当前层的节点数
        int curNodeCount = 0;
        // 最大宽度
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            head = queue.poll();
            // 当前弹出的节点位于哪一层
            Integer curNodeLevel = hashMap.get(head);
            if (head.left != null) {
                queue.offer(head.left);
                hashMap.put(head.left, curNodeLevel + 1);
            }
            if (head.right != null) {
                queue.offer(head.right);
                hashMap.put(head.right, curNodeLevel + 1);
            }
            // 弹出的节点就在当前遍历的层上
            if (curNodeLevel == curLevel) {
                curNodeCount++;
            }
            // 弹出的节点不在当前遍历的层上
            // 即当前层遍历结束，准备进入下一层的遍历
            else {
                curLevel++;
                maxWidth = Math.max(maxWidth, curNodeCount);
                curNodeCount = 1;
            }
        }
        // 最后一层的最后一个节点弹出后队列为空，跳出循环，但curNodeCount++
        maxWidth = Math.max(maxWidth, curNodeCount);
        return maxWidth;
    }

    // 复习：二叉树按层遍历
    public static void layerTraversal(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            System.out.print(head.data + "  ");
            if (head.left != null) {
                queue.offer(head.left);
            }
            if (head.right != null) {
                queue.offer(head.right);
            }
        }
    }

    public static void main(String[] args) {
        int maxLevel = 233;
        int maxValue = 2333;
        int testTimes = 100000;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            Node headOne = randomBinaryTree(maxLevel, maxValue);
            int maxWidth = getMaxWidth(headOne);
            int maxWidthTwo = getMaxWidthTwo(headOne);
            if (maxWidth != maxWidthTwo) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    // 随机生成二叉树 最大层级 最大值
    public static Node randomBinaryTree(int maxLevel, int maxValue) {
        return process(1, maxLevel, maxValue);
    }

    public static Node process(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1)));
        head.left = process(level + 1, maxLevel, maxValue);
        head.right = process(level + 1, maxLevel, maxValue);
        return head;
    }

    // 判断两棵二叉树是否相等
    public static boolean isEquals(Node headOne, Node headTwo) {
        if (headOne != null && headTwo == null || headOne == null && headTwo != null) {
            return false;
        }
        // headOne == null && headTwo == null
        if (headOne == null) {
            return true;
        }
        if (headOne.data != headTwo.data) {
            return false;
        }
        return isEquals(headOne.left, headTwo.left) && isEquals(headOne.right, headTwo.right);
    }
}
