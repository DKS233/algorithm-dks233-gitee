package prefixtree;

/**
 * 剑指offer专项突击版：剑指 Offer II 067. 最大的异或
 *
 * @author dks233
 * @create 2022-08-15-18:33
 */
public class MaximumXor {
    public int findMaximumXOR(int[] nums) {
        MyPrefixTree myPrefixTree = new MyPrefixTree();
        for (int number : nums) {
            myPrefixTree.insert(number);
        }
        return myPrefixTree.result;
    }

    public static class MyPrefixTree {
        Node root;
        int result; // 最大异或结果

        public MyPrefixTree() {
            result = 0;
            this.root = new Node();
        }

        // 更新前缀树并更新最大异或结果
        public void insert(Integer number) {
            add(number);
            update(number);
        }

        public void add(Integer number) {
            int[] digits = getDigit(number);
            int path = 0;
            Node node = root;
            for (int index = 0; index < digits.length; index++) {
                path = digits[index];
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node();
                }
                node = node.nexts[path];
            }
        }

        public void update(Integer number) {
            int[] digits = getDigit(number);
            // 字符串形式的当前最大异或结果
            StringBuilder curResult = new StringBuilder();
            int path = 0;
            Node node = root;
            for (int index = 0; index < digits.length; index++) {
                path = digits[index];
                // path=0 应该尽量和1进行异或
                if (path == 0) {
                    if (node.nexts[1] == null) {
                        node = node.nexts[0];
                        curResult.append(0);
                    } else {
                        node = node.nexts[1];
                        curResult.append(1);
                    }
                }
                // path=1 应该尽量和0进行异或
                else {
                    if (node.nexts[0] == null) {
                        node = node.nexts[1];
                        curResult.append(0);
                    } else {
                        node = node.nexts[0];
                        curResult.append(1);
                    }
                }
            }
            result = Math.max(Integer.parseInt(curResult.toString(), 2), result);
        }

        // 获得number的二进制位数组
        // digits[0] 表示最高位
        public int[] getDigit(Integer number) {
            int[] digits = new int[32];
            for (int index = 0; index < 31; index++) {
                digits[31 - index] = (number >> index) & 1;
            }
            return digits;
        }
    }

    public static class Node {
        // nexts[0] 表示数位为0的下一个节点
        // nexts[1] 表示数位为1的下一个节点
        Node[] nexts;

        public Node() {
            this.nexts = new Node[2];
        }
    }

    public static void main(String[] args) {
        MaximumXor maximumXor = new MaximumXor();
        System.out.println(maximumXor.findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
    }
}
