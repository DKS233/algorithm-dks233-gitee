package class14;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集结构实现
 *
 * @author dks233
 * @create 2022-05-03-16:45
 */
public class UnionSetStructure {
    public static class Node<V> {
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    @SuppressWarnings("MapOrSetKeyShouldOverrideHashCodeEquals")
    public static class UnionSet<V> {
        // 一个value对应其Node
        public HashMap<V, Node<V>> nodes;
        // key:节点 value:节点的父亲节点
        public HashMap<Node<V>, Node<V>> parents;
        // 一个代表节点代表一个集合
        // key:代表节点 value:集合大小
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionSet(List<V> list) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : list) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 返回当前节点的代表节点
        public Node<V> getNode(Node<V> cur) {
            // 优化：链条扁平化处理，每次找当前节点的代表节点，将沿途的节点都指向代表节点
            // 先将沿途的节点记录到栈中
            Stack<Node<V>> stack = new Stack<>();
            // 代表节点的父节点是它本身
            while (cur != parents.get(cur)) {
                stack.push(cur);
                cur = parents.get(cur);
            }
            while (!stack.isEmpty()) {
                // 将沿途的节点从栈中弹出，父节点都变成代表节点
                parents.put(stack.pop(), cur);
            }
            return cur;
        }

        // 判断a和b在不在相同的集合
        public boolean isSameSet(V a, V b) {
            return getNode(nodes.get(a)) == getNode(nodes.get(b));
        }

        // 将a和b所在集合进行合并
        public void union(V a, V b) {
            // 先找到a和b的代表节点
            Node<V> headA = getNode(nodes.get(a));
            Node<V> headB = getNode(nodes.get(b));
            if (headA != headB) {
                // 比较两个集合的大小，将小集合的代表节点连到大的集合的代表节点上
                Integer sizeA = sizeMap.get(headA);
                Integer sizeB = sizeMap.get(headB);
                if (sizeA > sizeB) {
                    // b所在集合较小，其代表节点的父节点变为headA
                    parents.put(headB, headA);
                    sizeMap.put(headA, sizeA + sizeB);
                    // b集合并到a集合里，b集合不存在代表节点
                    sizeMap.remove(headB);
                } else {
                    parents.put(headA, headB);
                    sizeMap.put(headB, sizeA + sizeB);
                    sizeMap.remove(headA);
                }
            }
        }

        public int getSize() {
            return sizeMap.size();
        }
    }
}
