package orderedlist;

/**
 * 剑指Offer第二版：59-I.滑动窗口的最大值
 *
 * @author dks233
 * @create 2022-06-16-22:20
 */
public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        SbTree<Node> sbTree = new SbTree<>();
        if (k == 0) {
            return new int[0];
        }
        // 将前k-1个元素添加到SB树中
        for (int i = 0; i < k - 1; i++) {
            sbTree.put(new Node(i, nums[i]));
        }
        int[] ans = new int[nums.length - k + 1];
        int index = 0;
        // 从k-1位置开始，进一个，出一个，计算中位数
        for (int i = k - 1; i < nums.length; i++) {
            // 右边的数先进窗口
            sbTree.put(new Node(i, nums[i]));
            // 找出滑动窗口中的最大值
            ans[index++] = sbTree.getIndexKey(sbTree.getSize() - 1).value;
            // 左边的数出窗口
            sbTree.remove(new Node(i - k + 1, nums[i - k + 1]));
        }
        return ans;
    }

    public static class Node implements Comparable<Node> {
        int index;
        int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value != o.value ? Integer.compare(value, o.value) : Integer.compare(index, o.index);
        }
    }

    public static class SbTree<K extends Comparable<K>> {
        SbNode<K> root;

        public SbNode<K> rightRotate(SbNode<K> cur) {
            SbNode<K> left = cur.left;
            cur.left = left.right;
            left.right = cur;
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
            left.size = (left.left == null ? 0 : left.left.size) + left.right.size + 1;
            return left;
        }

        public SbNode<K> leftRotate(SbNode<K> cur) {
            SbNode<K> right = cur.right;
            cur.right = right.left;
            right.left = cur;
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
            right.size = right.left.size + (right.right == null ? 0 : right.right.size) + 1;
            return right;
        }

        // 从下往上更新size，保持平衡
        // sb树：叔叔节点的节点数不小于任意一个侄子节点的节点数
        public SbNode<K> maintain(SbNode<K> cur) {
            if (cur == null) {
                return null;
            }
            int leftSize = cur.left == null ? 0 : cur.left.size;
            int rightSize = cur.right == null ? 0 : cur.right.size;
            int leftLeftSize = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
            int leftRightSize = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
            int rightLeftSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;
            int rightRightSize = cur.right != null && cur.right.right != null ? cur.right.right.size : 0;
            // LL/LL+LR LR RL RR/RL+RR
            // LL / LL+LR  右旋操作
            if (leftLeftSize > rightSize) {
                cur = rightRotate(cur);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            // LR 先左旋再右旋
            else if (leftRightSize > rightSize) {
                cur.left = leftRotate(cur.left);
                cur = rightRotate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            // RR / RL+RR  左旋操作
            else if (rightRightSize > leftSize) {
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur = maintain(cur);
            }
            // RL
            else if (rightLeftSize > leftSize) {
                cur.right = rightRotate(cur.right);
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            return cur;
        }

        // 找到key值和key最接近的节点
        public SbNode<K> findLastIndex(K key) {
            SbNode<K> pre = this.root;
            SbNode<K> cur = this.root;
            while (cur != null) {
                // 只要cur不为null，pre就跟上，cur向左子节点和右子节点走都是尽量接近key的
                pre = cur;
                if (key.compareTo(cur.key) == 0) {
                    break;
                } else if (key.compareTo(cur.key) > 0) {
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }
            }
            return pre;
        }

        public SbNode<K> add(SbNode<K> cur, K key) {
            if (cur == null) {
                return new SbNode<>(key);
            }
            if (key.compareTo(cur.key) > 0) {
                cur.right = add(cur.right, key);
            } else {
                cur.left = add(cur.left, key);
            }
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
            return maintain(cur);
        }

        public SbNode<K> delete(SbNode<K> cur, K key) {
            if (cur == null) {
                return null;
            }
            // 需要删除的节点在右子树上
            if (key.compareTo(cur.key) > 0) {
                cur.right = delete(cur.right, key);
            }
            // 需要删除的节点在左子树上
            else if (key.compareTo(cur.key) < 0) {
                cur.left = delete(cur.left, key);
            }
            // cur就是需要删除的节点
            else {
                // cur有右子树，没左子树
                // 方案1：cur=cur.right
                // 方案2：用中序遍历后继节点代替cur，然后删除后继节点
                if (cur.right != null && cur.left == null) {
                    cur = cur.right;
                }
                // cur有左子树，没右子树
                // 方案1：cur=cur.left
                // 方案2：用中序遍历前驱节点代替cur，然后删除前驱节点
                else if (cur.right == null && cur.left != null) {
                    cur = cur.left;
                }
                // cur既没有左子树，也没有右子树
                else if (cur.left == null) {
                    cur = null;
                }
                // cur既有左子树，也有右子树
                // 用中序遍历前驱或后继节点代替cur，然后删除前驱或后继节点
                else {
                    SbNode<K> successor = cur.right;
                    while (successor.left != null) {
                        successor = successor.left;
                    }
                    cur.right = delete(cur.right, successor.key);
                    cur.key = successor.key;
                }
            }
            if (cur != null) {
                cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
            }
            return maintain(cur);
        }

        public int getSize() {
            return root == null ? 0 : root.size;
        }

        // 中序遍历第kth个元素，kth大于0
        public SbNode<K> getIndex(SbNode<K> cur, int kth) {
            if (cur == null || kth > getSize()) {
                return null;
            }
            int leftSize = cur.left == null ? 0 : cur.left.size;
            if (kth == leftSize + 1) {
                return cur;
            } else if (kth > leftSize + 1) {
                return getIndex(cur.right, kth - leftSize - 1);
            } else {
                return getIndex(cur.left, kth);
            }
        }

        // 获得第index个节点的key index>=0
        public K getIndexKey(int index) {
            if (index >= getSize()) {
                return null;
            } else {
                SbNode<K> node = getIndex(root, index + 1);
                return node == null ? null : node.key;
            }
        }

        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            SbNode<K> lastNode = findLastIndex(key);
            return lastNode != null && key.compareTo(lastNode.key) == 0;
        }

        public void put(K key) {
            if (key == null) {
                return;
            }
            SbNode<K> lastNode = findLastIndex(key);
            if (lastNode == null || key.compareTo(lastNode.key) != 0) {
                root = add(root, key);
            }
        }

        public void remove(K key) {
            if (key == null) {
                return;
            }
            if (containsKey(key)) {
                root = delete(root, key);
            }
        }
    }

    public static class SbNode<K extends Comparable<K>> {
        SbNode<K> left;
        SbNode<K> right;
        K key;
        int size;

        public SbNode(K key) {
            this.key = key;
            this.size = 1;
        }
    }
}
