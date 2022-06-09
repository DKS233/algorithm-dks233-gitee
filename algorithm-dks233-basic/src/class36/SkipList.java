package class36;

import java.util.ArrayList;

/**
 * 跳表SkipList实现
 *
 * @author dks233
 * @create 2022-06-08-15:43
 */
public class SkipList {
    public static class SkipListNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        // 能够到达的节点
        // nextNodes.get(i) 表示在第i层能到达的节点  i>=0
        public ArrayList<SkipListNode<K, V>> nextNodes;

        public SkipListNode(K key, V value) {
            this.key = key;
            this.value = value;
            nextNodes = new ArrayList<>();
        }

        // 当前节点的key是否比otherKey小
        // key == null的情况：key是head，head的key认为是最小，返回true
        public boolean isKeyLess(K otherKey) {
            if (otherKey == null) {
                return false;
            }
            return key == null || key.compareTo(otherKey) < 0;
        }

        // 当前节点的key是否和otherKey相等
        public boolean isKeyEqual(K otherKey) {
            return (key == null && otherKey == null) ||
                    (key != null && otherKey != null && key.compareTo(otherKey) == 0);
        }
    }

    public static class MySkipList<K extends Comparable<K>, V> {
        public SkipListNode<K, V> head; // head的key是null，认为是最小的key
        public int maxLevel; // 跳表最大层数，从0开始
        public int size;
        public final double PROBABILITY = 0.5;

        public MySkipList() {
            head = new SkipListNode<>(null, null);
            head.nextNodes.add(null);
            size = 0;
            maxLevel = 0;
        }

        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            // 找到第0层小于key的最右节点
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            // find：第0层less节点右边节点
            SkipListNode<K, V> find = less.nextNodes.get(0);
            // key节点已经存在
            if (find != null && find.isKeyEqual(key)) {
                find.value = value;
            } else {
                size++;
                // 新加入节点随机出一个高度，随机出1的概率为0.5，随机出2的概率是0.5^2，随机出3的概率是0.5^3
                int newNodeLevel = 0;
                while (Math.random() < PROBABILITY) {
                    newNodeLevel++;
                }
                // 更新head的高度
                while (newNodeLevel > maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                SkipListNode<K, V> newNode = new SkipListNode<>(key, value);
                for (int i = 0; i <= newNodeLevel; i++) {
                    newNode.nextNodes.add(null);
                }
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                while (level >= 0) {
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    if (level <= newNodeLevel) {
                        newNode.nextNodes.set(level, pre.nextNodes.get(level));
                        pre.nextNodes.set(level, newNode);
                    }
                    level--;
                }
            }
        }

        public void remove(K key) {
            if (containsKey(key)) {
                size--;
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                while (level >= 0) {
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    SkipListNode<K, V> next = pre.nextNodes.get(level);
                    if (next != null && next.isKeyEqual(key)) {
                        pre.nextNodes.set(level, next.nextNodes.get(level));
                    }
                    // 在level层只有一个节点了，就是默认节点head
                    if (level != 0 && pre == head && pre.nextNodes.get(level) == null) {
                        head.nextNodes.remove(level);
                        maxLevel--;
                    }
                    level--;
                }
            }
        }

        // 最小key
        public K firstKey() {
            return head.nextNodes.get(0) != null ? head.nextNodes.get(0).key : null;
        }

        // 最大key
        public K lastKey() {
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                SkipListNode<K, V> next = cur.nextNodes.get(level);
                while (next != null) {
                    cur = next;
                    next = cur.nextNodes.get(level);
                }
                level--;
            }
            return cur.key;
        }

        // >=key的最小key
        public K ceilingKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null ? next.key : null;
        }

        // <=key的最大key
        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.key : less.key;
        }

        public int size() {
            return size;
        }

        public V get(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.value : null;
        }

        // 从最高层开始，直到找到第0层小于key的最右节点
        public SkipListNode<K, V> mostRightLessNodeInTree(K key) {
            if (key == null) {
                return null;
            }
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                cur = mostRightLessNodeInLevel(key, cur, level--);
            }
            return cur;
        }

        // 在level层里小于key的最右节点
        public SkipListNode<K, V> mostRightLessNodeInLevel(K key, SkipListNode<K, V> cur, int level) {
            SkipListNode<K, V> next = cur.nextNodes.get(level);
            while (next != null && next.isKeyLess(key)) {
                cur = next;
                next = cur.nextNodes.get(level);
            }
            return cur;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            // 第0层小于key的最右节点
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            // 看第0层less节点右边是不是key节点
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key);
        }
    }

    // for test
    public static void printAll(MySkipList<String, String> obj) {
        for (int i = obj.maxLevel; i >= 0; i--) {
            System.out.print("Level " + i + " : ");
            SkipListNode<String, String> cur = obj.head;
            while (cur.nextNodes.get(i) != null) {
                SkipListNode<String, String> next = cur.nextNodes.get(i);
                System.out.print("(" + next.key + " , " + next.value + ") ");
                cur = next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MySkipList<String, String> test = new MySkipList<>();
        printAll(test);
        System.out.println("======================");
        test.put("A", "10");
        printAll(test);
        System.out.println("======================");
        test.remove("A");
        printAll(test);
        System.out.println("======================");
        test.put("E", "E");
        test.put("B", "B");
        test.put("A", "A");
        test.put("F", "F");
        test.put("C", "C");
        test.put("D", "D");
        printAll(test);
        System.out.println("======================");
        System.out.println(test.containsKey("B"));
        System.out.println(test.containsKey("Z"));
        System.out.println(test.firstKey());
        System.out.println(test.lastKey());
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));
        System.out.println("======================");
        test.remove("D");
        printAll(test);
        System.out.println("======================");
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));
    }
}
