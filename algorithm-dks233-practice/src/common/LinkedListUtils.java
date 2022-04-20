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

    // 生成两个节点data值相同的list
    public static ArrayList<ArrayList<Node>> generateTwoNodeList(int maxLen, int maxValue) {
        ArrayList<Node> listOne = new ArrayList<>();
        ArrayList<Node> listTwo = new ArrayList<>();
        int len = (int) (Math.random() * (maxLen)) + 1;
        int j = 0;
        for (j = 0; j < len; j++) {
            int value = ArrayUtils.randomNumber(maxValue);
            Node nodeOne = new Node(value);
            Node nodeTwo = new Node(value);
            listOne.add(nodeOne);
            listTwo.add(nodeTwo);
        }
        for (j = 0; j < listOne.size() - 1; j++) {
            listOne.get(j).next = listOne.get(j + 1);
            listTwo.get(j).next = listTwo.get(j + 1);
        }
        listOne.get(listOne.size() - 1).next = null;
        listTwo.get(listTwo.size() - 1).next = null;
        ArrayList<ArrayList<Node>> nodeListList = new ArrayList<>();
        nodeListList.add(listOne);
        nodeListList.add(listTwo);
        return nodeListList;
    }

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return " " + data + " ";
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
