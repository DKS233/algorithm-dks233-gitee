package class36;

/**
 * SB树实现
 * 任何一个叔叔节点的节点数，不少于他的任何一个侄子节点的节点数
 *
 * @author dks233
 * @create 2022-06-07-11:19
 */
public class SizeBalancedTree {
    public static class SbNode<K extends Comparable<K>, V> {
        SbNode<K, V> left;
        SbNode<K, V> right;
        K key;
        V value;
        int size; // 节点个数

        public SbNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.size = 1;
        }
    }

    public static class SbTree<K extends Comparable<K>, V> {
        public SbNode<K, V> root;

        // 右旋操作
        public SbNode<K, V> rotateRight(SbNode<K, V> cur) {
            SbNode<K, V> left = cur.left;
            cur.left = left.right;
            left.right = cur;
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
            left.size = (left.left == null ? 0 : left.left.size) + (left.right == null ? 0 : left.right.size) + 1;
            return left;
        }

        // 左旋操作
        public SbNode<K, V> rotateLeft(SbNode<K, V> cur) {
            SbNode<K, V> right = cur.right;
            cur.right = right.left;
            right.left = cur;
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
            right.size = (right.left == null ? 0 : right.left.size) + (right.right == null ? 0 : right.right.size) + 1;
            return right;
        }

        // LL LR RL RR LL+LR=LL RL+RR=RR
        // LL：cur.left.left.size > cur.right.size
        // LR：cur.left.right.size > cur.right.size
        // RL：cur.right.left.size > cur.left.size
        // RR：cur.right.right.size > cur.left.size
        // LL+LR：cur.left.left.size > cur.right.size && cur.left.right.size > cur.right.size
        //          => LL  cur.left.left.size > cur.right.size
        // RL+RR：cur.right.left.size > cur.left.size && cur.right.right.size > cur.left.size
        //          => RR  cur.right.right.size > cur.left.size
        public SbNode<K, V> maintain(SbNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            int leftSize = cur.left == null ? 0 : cur.left.size;
            int rightSize = cur.right == null ? 0 : cur.right.size;
            int leftLeftSize = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
            int leftRightSize = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
            int rightLeftSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;
            int rightRightSize = cur.right != null && cur.right.right != null ? cur.right.right.size : 0;
            // LL型  右旋，递归检查调整
            if (leftLeftSize > rightSize) {
                cur = rotateRight(cur);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            // LR型 先左旋，再右旋，递归检查调整
            if (leftRightSize > rightSize) {
                cur.left = rotateLeft(cur.left);
                cur = rotateRight(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            // RR型  左旋，递归检查调整
            if (rightRightSize > leftSize) {
                cur = rotateLeft(cur);
                cur.left = maintain(cur.left);
                cur = maintain(cur);
            }
            // RL型 先右旋，再左旋，递归检查调整
            if (rightLeftSize > leftSize) {
                cur.right = rotateRight(cur.right);
                cur = rotateLeft(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            return cur;
        }

        // 向cur为头的树中添加节点
        // 添加完后从下到上检查和调整所有受影响的节点
        public SbNode<K, V> add(SbNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new SbNode<>(key, value);
            }
            cur.size++;
            if (key.compareTo(cur.key) < 0) {
                cur.left = add(cur.left, key, value);
            } else if (key.compareTo(cur.key) > 0) {
                cur.right = add(cur.right, key, value);
            } else {
                cur.value = value;
            }
            return maintain(cur);
        }

        // 从cur为头的树中删除节点
        // 删除完后从下到上检查和调整所有受影响的节点
        public SbNode<K, V> delete(SbNode<K, V> cur, K key) {
            if (cur == null) {
                return null;
            }
            cur.size--;
            // 需要删除的节点在cur左子树上
            if (key.compareTo(cur.key) < 0) {
                cur.left = delete(cur.left, key);
            }
            // 需要删除的节点在cur右子树上
            else if (key.compareTo(cur.key) > 0) {
                cur.left = delete(cur.right, key);
            }
            // cur就是需要删除的节点
            else {
                // cur没有左右子树，直接删除cur
                if (cur.left == null && cur.right == null) {
                    cur = null;
                }
                // cur有左子树没右子树，删除cur，用cur.left代替cur
                // 也可以用前驱节点代替cur，然后删除前驱节点(cur.key=前驱.key)
                else if (cur.left != null && cur.right == null) {
                    cur = cur.left;
                }
                // cur有右子树没左子树，删除cur，用cur.right代替cur
                // 也可以用后继节点代替cur，然后删除后继节点(cur.key=后继.key)
                else if (cur.left == null && cur.right != null) {
                    cur = cur.right;
                }
                // cur既有左子树也有右子树，删除cur，然后用前驱或后继节点代替
                else {
                    SbNode<K, V> pre = null;
                    SbNode<K, V> successor = cur.right;
                    successor.size--;
                    while (successor.left != null) {
                        pre = successor;
                        successor = successor.left;
                        successor.size--;
                    }
                    if (pre != null) {
                        pre.left = successor.right;
                        successor.right = cur.right;
                    }
                    successor.left = cur.left;
                    successor.size = successor.left.size + (successor.right == null ? 0 : successor.right.size) + 1;
                    cur = successor;
                }
            }
            // 可以不用平 return cur;
            // return maintain(cur);
            return cur;
        }

        // 找到离key最近的节点
        public SbNode<K, V> findLastIndex(K key) {
            SbNode<K, V> pre = this.root;
            SbNode<K, V> cur = this.root;
            while (cur != null) {
                pre = cur;
                if (key.compareTo(cur.key) == 0) {
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return pre;
        }

        // 找到>=key离key最近节点（即>=key的最小节点）
        public SbNode<K, V> findLastNoSmallIndex(K key) {
            SbNode<K, V> pre = null;
            SbNode<K, V> cur = this.root;
            while (cur != null) {
                if (key.compareTo(cur.key) == 0) {
                    pre = cur;
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    pre = cur;
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return pre;
        }

        // 找到<=key离key最近节点（即<=key的最大节点）
        public SbNode<K, V> findLastNoBigIndex(K key) {
            SbNode<K, V> pre = null;
            SbNode<K, V> cur = this.root;
            while (cur != null) {
                if (key.compareTo(cur.key) == 0) {
                    pre = cur;
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    cur = cur.left;
                } else {
                    pre = cur;
                    cur = cur.right;
                }
            }
            return pre;
        }

        public int getSize() {
            return root == null ? 0 : root.size;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            SbNode<K, V> node = findLastIndex(key);
            return node != null && key.compareTo(node.key) == 0;
        }

        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            SbNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.key) == 0) {
                lastNode.value = value;
            } else {
                root = add(root, key, value);
            }
        }

        public void remove(K key) {
            if (key == null) {
                return;
            }
            SbNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.key) == 0) {
                root = delete(root, key);
            }
        }

        // 最小的key
        public K firstKey() {
            if (root == null) {
                return null;
            }
            SbNode<K, V> node = root;
            while (node.left != null) {
                node = node.left;
            }
            return node.key;
        }

        // 最大的key
        public K lastKey() {
            if (root == null) {
                return null;
            }
            SbNode<K, V> node = root;
            while (node.right != null) {
                node = node.right;
            }
            return node.key;
        }

        // <=key的最大key
        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            SbNode<K, V> node = findLastNoBigIndex(key);
            if (node != null && key.compareTo(node.key) >= 0) {
                return node.key;
            }
            return null;
        }

        // >=key的最大key
        public K ceilingKey(K key) {
            if (key == null) {
                return null;
            }
            SbNode<K, V> node = findLastNoSmallIndex(key);
            if (node != null && key.compareTo(node.key) <= 0) {
                return node.key;
            }
            return null;
        }

        // 获取第kth个节点
        public SbNode<K, V> getIndex(SbNode<K, V> cur, int kth) {
            if (cur == null) {
                return null;
            }
            int leftSize = cur.left != null ? cur.left.size : 0;
            if (kth == leftSize + 1) {
                return cur;
            } else if (kth < leftSize + 1) {
                return getIndex(cur.left, kth);
            } else {
                return getIndex(cur.right, kth - leftSize - 1);
            }
        }

        // 获得index位置的key
        public K getIndexKey(int index) {
            if (index < 0 || index >= getSize()) {
                throw new RuntimeException("index out exception");
            }
            return getIndex(root, index + 1).key;
        }

        // 获得index位置的value
        public V getIndexValue(int index) {
            if (index < 0 || index >= getSize()) {
                throw new RuntimeException("index out exception");
            }
            return getIndex(root, index + 1).value;
        }

        public V get(K key) {
            if (key == null) {
                return null;
            }
            SbNode<K, V> node = findLastIndex(key);
            if (node != null && key.compareTo(node.key) == 0) {
                return node.value;
            }
            return null;
        }
    }

    // for test
    public static void printAll(SbNode<String, Integer> head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    // for test
    public static void printInOrder(SbNode<String, Integer> head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + "(" + head.key + "," + head.value + ")" + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    // for test
    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        SbTree<String, Integer> sbt = new SbTree<String, Integer>();
        sbt.put("d", 4);
        sbt.put("c", 3);
        sbt.put("a", 1);
        sbt.put("b", 2);
        // sbt.put("e", 5);
        sbt.put("g", 7);
        sbt.put("f", 6);
        sbt.put("h", 8);
        sbt.put("i", 9);
        sbt.put("a", 111);
        System.out.println(sbt.get("a"));
        sbt.put("a", 1);
        System.out.println(sbt.get("a"));
        for (int i = 0; i < sbt.getSize(); i++) {
            System.out.println(sbt.getIndexKey(i) + " , " + sbt.getIndexValue(i));
        }
        printAll(sbt.root);
        System.out.println(sbt.firstKey());
        System.out.println(sbt.lastKey());
        System.out.println(sbt.floorKey("g"));
        System.out.println(sbt.ceilingKey("g"));
        System.out.println(sbt.floorKey("e"));
        System.out.println(sbt.ceilingKey("e"));
        System.out.println(sbt.floorKey(""));
        System.out.println(sbt.ceilingKey(""));
        System.out.println(sbt.floorKey("j"));
        System.out.println(sbt.ceilingKey("j"));
        sbt.remove("d");
        printAll(sbt.root);
        sbt.remove("f");
        printAll(sbt.root);

    }
}
