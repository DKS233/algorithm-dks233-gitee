package class35;

/**
 * AVL树实现
 * 注：平衡因子=左子树高度-右子树高度
 *
 * @author dks233
 * @create 2022-06-02-16:54
 */
@SuppressWarnings("ALL")
public class AvlTree {
    public static class AvlNode<K extends Comparable<K>, V> {
        int height; // 高度
        AvlNode<K, V> left;
        AvlNode<K, V> right;
        K key;
        V value;

        public AvlNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    public static class MyAvlTree<K extends Comparable<K>, V> {
        public AvlNode<K, V> root;
        public int size;

        public MyAvlTree() {
            root = null;
            size = 0;
        }

        // 右旋操作
        // cur为头的子树是不平衡的，平衡因子大于1
        public AvlNode<K, V> rotateRight(AvlNode<K, V> cur) {
            AvlNode<K, V> left = cur.left;
            cur.left = left.right;
            left.right = cur;
            // 右旋过程中cur和left的高度发生了变化
            cur.height = Math.max(cur.left == null ? 0 : cur.left.height, cur.right == null ? 0 : cur.right.height) + 1;
            left.height = Math.max(left.left == null ? 0 : left.left.height, left.right == null ? 0 : left.right.height) + 1;
            return left;
        }

        // 左旋操作
        // cur为头的子树是不平衡的，平衡因子小于-1
        public AvlNode<K, V> rotateLeft(AvlNode<K, V> cur) {
            AvlNode<K, V> right = cur.right;
            cur.right = right.left;
            right.left = cur;
            // 左旋过程中right和cur的高度发生了变化
            cur.height = Math.max(cur.left == null ? 0 : cur.left.height, cur.right == null ? 0 : cur.right.height) + 1;
            right.height = Math.max(right.left == null ? 0 : right.left.height, right.right == null ? 0 : right.right.height) + 1;
            return right;
        }

        // 维持cur为头的AVL树的平衡性
        public AvlNode<K, V> maintain(AvlNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            // 分析左右子树的高度，判断AVL树是否平衡
            int leftHeight = cur.left == null ? 0 : cur.left.height;
            int rightHeight = cur.right == null ? 0 : cur.right.height;
            // 平衡因子大于1，需要右旋
            if (leftHeight - rightHeight > 1) {
                int leftLeftHeight = cur.left != null && cur.left.left != null ? cur.left.left.height : 0;
                int leftRightHeight = cur.left != null && cur.left.right != null ? cur.left.right.height : 0;
                // leftLeftHeight >= leftRightHeight表示两种情况
                // 情况1：LL型
                // 情况2：LL型和LR型同时出现，按照LL型处理
                // LL型需要一次旋转，LR型需要两次旋转
                // LL型也可以理解为cur和其左子树的平衡因子符号相等
                if (leftLeftHeight >= leftRightHeight) {
                    cur = rotateRight(cur);
                }
                // leftLeftHeight < leftRightHeight表示LR型，需要两次旋转
                // LR型，也可以理解为cur和其左子树的平衡因子符号不相等
                else {
                    cur.left = rotateLeft(cur.left);
                    cur = rotateRight(cur);
                }
            }
            // 平衡因子小于-1，需要左旋
            else if (leftHeight - rightHeight < -1) {
                int rightLeftHeight = cur.right != null && cur.right.left != null ? cur.right.left.height : 0;
                int rightRightHeight = cur.right != null && cur.right.right != null ? cur.right.right.height : 0;
                // rightLeftHeight >= rightRightHeight表示两种情况
                // 情况1：RL型
                // 情况2：RL型和RR型同时出现，按照RR型处理
                // RR型需要一次旋转，RL型需要两次旋转
                // RR型也可以理解为cur和其右子树的平衡因子符号相等
                if (rightRightHeight >= rightLeftHeight) {
                    cur = rotateLeft(cur);
                }
                // rightLeftHeight < rightRightHeight表示RL型，需要两次旋转
                // RL型，也可以理解为cur和其右子树的平衡因子符号不相等
                else {
                    cur.right = rotateRight(cur.right);
                    cur = rotateLeft(cur);
                }
            }
            return cur;
        }

        // 向cur为头的AVL树中添加节点
        public AvlNode<K, V> add(AvlNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new AvlNode<>(key, value);
            } else {
                // 加入的节点key小于cur.key，去左子树中添加
                if (key.compareTo(cur.key) < 0) {
                    cur.left = add(cur.left, key, value);
                } else {
                    // 加入的节点key大于cur.key，去右子树中添加
                    cur.right = add(cur.right, key, value);
                }
                // 更新cur的高度
                cur.height = Math.max(cur.left == null ? 0 : cur.left.height, cur.right == null ? 0 : cur.right.height) + 1;
                // 节点添加完后，维持cur为头的AVL树的平衡性
                return maintain(cur);
            }
        }

        // 从cur为头的AVL树中删除节点
        public AvlNode<K, V> delete(AvlNode<K, V> cur, K key) {
            // 需要删除的key大于cur.key，去右子树上删
            if (key.compareTo(cur.key) > 0) {
                cur.right = delete(cur.right, key);
            }
            // 需要删除的key小于cur.key，去左子树上删
            else if (key.compareTo(cur.key) < 0) {
                cur.left = delete(cur.left, key);
            }
            // 需要删除的key就是cur.key
            else {
                // cur没左右子树，直接删除
                if (cur.left == null && cur.right == null) {
                    return null;
                }
                // cur有左子树，没右子树，删除cur，用cur.left代替，然后调整树
                // 按照处理二叉搜索树的删除逻辑，这里应该是用中序遍历的前驱代替，不过因为有树的调整，结果是一样的
                else if (cur.left != null && cur.right == null) {
                    cur = cur.left;
                }
                // cur有右子树，没左子树，删除cur，用cur.right代替，然后调整树
                // 按照处理二叉搜索树的删除逻辑，这里应该是用中序遍历的后继代替，不过因为有树的调整，结果是一样的
                else if (cur.right != null && cur.left == null) {
                    cur = cur.right;
                }
                // cur有左右子树，删除cur，用中序遍历的前驱/后继节点代替cur，以后继节点为例
                else {
                    AvlNode<K, V> successor = cur.right;
                    while (successor.left != null) {
                        successor = successor.left;
                    }
                    // 先从cur的右子树中删除successor
                    cur.right = delete(cur.right, successor.key);
                    // 然后用得到的successor代替cur
                    successor.left = cur.left;
                    successor.right = cur.right;
                    cur = successor;
                }
            }
            if (cur != null) {
                cur.height = Math.max(cur.left == null ? 0 : cur.left.height, cur.right == null ? 0 : cur.right.height) + 1;
            }
            // 节点删除后调整AVL树
            return maintain(cur);
        }

        // 找到离key最近的节点（最近：该节点的key和key差距最小）
        public AvlNode<K, V> findLastIndex(K key) {
            AvlNode<K, V> pre = root;
            AvlNode<K, V> cur = root;
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

        // 找到>=key的离key最近的节点（最近：该节点的key和key差距最小）
        // 即>=key的最大节点
        public AvlNode<K, V> findLastNoSmallIndex(K key) {
            AvlNode<K, V> ans = null;
            AvlNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.key) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    ans = cur;
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return ans;
        }

        // 找到<=key的离key最近的节点（最近：该节点的key和key差距最小）
        // 即<=key的最小节点
        public AvlNode<K, V> findLastNoBigIndex(K key) {
            AvlNode<K, V> ans = null;
            AvlNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.key) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    cur = cur.left;
                } else {
                    ans = cur;
                    cur = cur.right;
                }
            }
            return ans;
        }

        public int getSize() {
            return size;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            AvlNode<K, V> node = findLastIndex(key);
            return node != null && node.key.compareTo(key) == 0 ? true : false;
        }

        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            AvlNode<K, V> node = findLastIndex(key);
            // key节点已经存在
            if (node != null && key.compareTo(node.key) == 0) {
                node.value = value;
            } else {
                root = add(root, key, value);
                size++;
            }
        }

        public void remove(K key) {
            if (key == null) {
                return;
            }
            if (containsKey(key)) {
                size--;
                root = delete(root, key);
            }
        }

        public V get(K key) {
            if (key == null) {
                return null;
            }
            AvlNode<K, V> node = findLastIndex(key);
            if (node != null && key.compareTo(key) == 0) {
                return node.value;
            }
            return null;
        }

        // 中序遍历第一个key
        public K firstKey() {
            if (root == null) {
                return null;
            }
            AvlNode<K, V> cur = root;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur.key;
        }

        // 中序遍历最后一个key
        public K lastKey() {
            if (root == null) {
                return null;
            }
            AvlNode<K, V> cur = root;
            while (cur.right != null) {
                cur = cur.right;
            }
            return cur.key;
        }

        // <=key的最大节点
        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            AvlNode<K, V> node = findLastNoBigIndex(key);
            return node == null ? null : node.key;
        }

        // >=key的最小节点
        public K ceilingKey(K key) {
            if (key == null) {
                return null;
            }
            AvlNode<K, V> node = findLastNoSmallIndex(key);
            return node == null ? null : node.key;
        }
    }

}
