package class13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一棵二叉树的头节点head，和另外两个节点a和b，返回a和b的最低公共祖先
 *
 * @author dks233
 * @create 2022-04-30-11:32
 */
public class LowestAncestor {
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    // 方法1
    // hashMap存节点+父节点，然后通过hashMap分别找a和b的父节点，父节点的父节点，直到找到同一个节点，返回
    // 如果直到head都没找到，说明没没有最低公共祖先
    public static Node getLowestAncestorOne(Node head, Node nodeOne, Node nodeTwo) {
        if (head == null) {
            return null;
        }
        // 准备hashMap，把所有节点和其父节点存到hashMap中
        HashMap<Node, Node> parentHashMap = new HashMap<>();
        parentHashMap.put(head, null);
        fillParentHashMap(head, parentHashMap);
        // 准备hashSet，把nodeOne和其祖先节点都放到set中
        HashSet<Node> hashSet = new HashSet<>();
        hashSet.add(nodeOne);
        while (nodeOne != null) {
            nodeOne = parentHashMap.get(nodeOne);
            hashSet.add(nodeOne);
        }
        // 遍历nodeTwo和其祖先节点，看是否已经在hashSet中，如果在，说明找到了公共祖先节点
        while (nodeTwo != null) {
            if (hashSet.contains(nodeTwo)) {
                return nodeTwo;
            }
            nodeTwo = parentHashMap.get(nodeTwo);
        }
        return null;
    }

    private static void fillParentHashMap(Node head, HashMap<Node, Node> parentHashMap) {
        if (head.left != null) {
            parentHashMap.put(head.left, head);
            fillParentHashMap(head.left, parentHashMap);
        }
        if (head.right != null) {
            parentHashMap.put(head.right, head);
            fillParentHashMap(head.right, parentHashMap);
        }
    }

    // 方法2：二叉树递归套路做
    // 设head为每次递归过程中的头节点
    // 情况1：head是nodeOne和nodeTwo的最低公共祖先
    // 可能：左树右树各有一个a或者b；a位于head；b位于head
    // 情况2：head不是nodeOne和nodeTwo的最低公共祖先
    // 可能：最低公共祖先在head左树上；最低公共祖先在head右树上；head为头的二叉子树中nodeOne和nodeTwo不全
    // 需要的信息：左树右树有没有nodeOne，左树右树有没有nodeTwo，左树右树的需要求的最低公共祖先
    public static Node getLowestAncestorTwo(Node head, Node nodeOne, Node nodeTwo) {
        if (head == null) {
            return null;
        }
        return process(head, nodeOne, nodeTwo).lowestNode;
    }

    public static Info process(Node head, Node nodeOne, Node nodeTwo) {
        if (head == null) {
            return new Info(false, false, null);
        }
        // 得到左右子树的信息
        Info leftInfo = process(head.left, nodeOne, nodeTwo);
        Info rightInfo = process(head.right, nodeOne, nodeTwo);
        // 根据左右子树的信息得到以head为头节点的二叉子树信息
        boolean hasNodeOne = head == nodeOne || leftInfo.hasNodeOne || rightInfo.hasNodeOne;
        boolean hasNodeTwo = head == nodeTwo || leftInfo.hasNodeTwo || rightInfo.hasNodeTwo;
        // 求最低公共祖先节点
        Node lowestNode = null;
        // 情况1：head是nodeOne和nodeTwo的最低公共祖先节点
        // 可能：左树右树各有一个a和b；a位于head；b位于head
        if ((leftInfo.hasNodeOne && rightInfo.hasNodeTwo || leftInfo.hasNodeTwo && rightInfo.hasNodeOne)
                || head == nodeOne || head == nodeTwo) {
            lowestNode = head;
        }
        // 情况2：head不是nodeOne和nodeTwo的最低公共祖先节点
        // 可能：最低公共祖先节点在左树上，最低公共节点在右树上
        else if (leftInfo.lowestNode != null) {
            lowestNode = leftInfo.lowestNode;
        } else if (rightInfo.lowestNode != null) {
            lowestNode = rightInfo.lowestNode;
        }
        return new Info(hasNodeOne, hasNodeTwo, lowestNode);
    }

    public static class Info {
        boolean hasNodeOne; // 左树右树上有没有节点1
        boolean hasNodeTwo; // 左树右树上有没有节点2
        Node lowestNode; // 左树右树上节点1和节点2的最低公共祖先

        public Info(boolean hasNodeOne, boolean hasNodeTwo, Node lowestNode) {
            this.hasNodeOne = hasNodeOne;
            this.hasNodeTwo = hasNodeTwo;
            this.lowestNode = lowestNode;
        }
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLevel = 20;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            Node head = randomBinaryTree(maxLevel, maxValue);
            Node nodeOne = randomNode(head);
            Node nodeTwo = randomNode(head);
            Node comparator = comparator(head, nodeOne, nodeTwo);
            Node ancestorOne = getLowestAncestorOne(head, nodeOne, nodeTwo);
            Node ancestorTwo = getLowestAncestorTwo(head, nodeOne, nodeTwo);
            if (comparator != ancestorOne || comparator != ancestorTwo) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    /**
     * 生成随机二叉树
     *
     * @param maxLevel 最大层级
     * @param maxValue 最大值
     * @return 随机二叉树
     */
    public static Node randomBinaryTree(int maxLevel, int maxValue) {
        return process(1, maxLevel, maxValue);
    }

    public static Node process(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * (maxValue + 1)));
        head.left = process(level + 1, maxLevel, maxValue);
        head.right = process(level + 1, maxLevel, maxValue);
        return head;
    }

    /**
     * 返回二叉树的随机节点
     *
     * @param head 二叉树头节点
     * @return 随机节点
     */
    public static Node randomNode(Node head) {
        if (head == null) {
            return head;
        }
        ArrayList<Node> nodeList = new ArrayList<>();
        nodeList.add(head);
        fillNodeList(head, nodeList);
        int index = (int) (Math.random() * (nodeList.size() - 1));
        return nodeList.get(index);
    }

    public static void fillNodeList(Node head, ArrayList<Node> nodeList) {
        if (head.left != null) {
            nodeList.add(head.left);
            fillNodeList(head.left, nodeList);
        }
        if (head.right != null) {
            nodeList.add(head.right);
            fillNodeList(head.right, nodeList);
        }
    }

    // 对数器：左程云：同方法1
    public static Node comparator(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        // key的父节点是value
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }
}
