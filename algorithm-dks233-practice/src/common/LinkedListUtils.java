package common;

import java.util.ArrayList;

/**
 * @author dks233
 * @create 2022-04-18-14:42
 */
public class LinkedListUtils {

    /**
     * 生成一个随机list，list元素类型为Node，每个Node串起来形成一个单链表
     *
     * @param maxLen   链表最大长度
     * @param maxValue 链表节点data最大值
     * @return randomNodeList
     */
    public static ArrayList<Node> randomNodeList(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen)) + 1;
        ArrayList<Node> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int value = ArrayUtils.randomNumber(maxValue);
            Node node = new Node(value);
            list.add(node);
        }
        for (int j = 0; j < list.size() - 1; j++) {
            list.get(j).next = list.get(j + 1);
        }
        list.get(list.size() - 1).next = null;
        return list;
    }

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static class DoubleNode {
        public int data;
        public DoubleNode pre;
        public DoubleNode next;

        public DoubleNode(int data) {
            this.data = data;
        }
    }

}
