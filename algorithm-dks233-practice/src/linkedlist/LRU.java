package linkedlist;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.function.Consumer;

/**
 * leetcode146. LRU 缓存
 *
 * @author dks233
 * @create 2022-07-21-10:14
 */
@SuppressWarnings("ALL")
public class LRU {
    // 双向链表+HashMap
    public static class LRUCache {
        int capacity;
        HashMap<Integer, Node> hashMap; // key值->节点
        DoubleLinkedList linkedList; // 节点双向链表，最新的节点存在链表尾部

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.hashMap = new HashMap<>();
            this.linkedList = new DoubleLinkedList();
        }

        // 如果hashMap中已存在key，通过hashMap获取节点，然后去链表中检索，将该节点提前到链表尾部
        // 如果hashMap中不存在key，返回-1
        public int get(int key) {
            if (!hashMap.containsKey(key)) {
                return -1;
            }
            Node node = hashMap.get(key);
            updateToTail(node);
            return node.value;
        }

        // 如果hashMap中已存在key，进行value替换，然后将节点提前到链表尾部
        // 如果hashMap中不存在key，检查容量是否满了，如果满了，删除链表头部元素，在链表尾部插入节点
        // 如果没满，在链表尾部插入节点
        public void put(int key, int value) {
            if (hashMap.containsKey(key)) {
                Node node = hashMap.get(key);
                node.value = value;
                updateToTail(node);
            } else {
                if (linkedList.size == capacity) {
                    Node firstNode = linkedList.removeFirst();
                    hashMap.remove(firstNode.key);
                }
                Node node = new Node(key, value);
                linkedList.addLast(node);
                hashMap.put(key, node);
            }
        }

        // 将最新的节点更新到链表尾部
        public void updateToTail(Node node) {
            linkedList.remove(node);
            linkedList.addLast(node);
        }

        public static class Node {
            int key;
            int value;
            Node pre;
            Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        public static class DoubleLinkedList {
            Node head, tail;
            int size;

            public DoubleLinkedList() {
                head = new Node(-1, -1);
                tail = new Node(-1, -1);
                size = 0;
                head.next = tail;
                tail.pre = head;
            }

            public Node removeFirst() {
                if (head.next == tail) {
                    return null;
                }
                Node first = head.next;
                remove(first);
                return first;
            }

            public void addLast(Node node) {
                node.pre = tail.pre;
                node.next = tail;
                tail.pre.next = node;
                tail.pre = node;
                size++;
            }

            public void remove(Node node) {
                node.pre.next = node.next;
                node.next.pre = node.pre;
                size--;
            }
        }
    }

    // 用LinkedHashMap
    public static class LRUCache2 {
        int capacity;
        LinkedHashMap<Integer, Integer> hashMap = new LinkedHashMap<>();

        public LRUCache2(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            if (!hashMap.containsKey(key)) {
                return -1;
            }
            Integer value = hashMap.get(key);
            updateToLast(key);
            return value;
        }

        // 如果hashMap中已存在key，进行value替换，然后将节点提前到链表尾部
        // 如果hashMap中不存在key，检查容量是否满了，如果满了，删除链表头部元素，在链表尾部插入节点
        // 如果没满，在链表尾部插入节点
        public void put(int key, int value) {
            if (hashMap.containsKey(key)) {
                hashMap.put(key, value);
                updateToLast(key);
            } else {
                if (hashMap.size() == capacity) {
                    Integer firstKey = hashMap.keySet().iterator().next();
                    hashMap.remove(firstKey);
                }
                hashMap.put(key, value);
            }
        }

        public void updateToLast(int key) {
            Integer value = hashMap.get(key);
            hashMap.remove(key);
            hashMap.put(key, value);
        }
    }
}
