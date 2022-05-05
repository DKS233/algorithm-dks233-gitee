package tree.serialize;


import java.util.*;

/**
 * 剑指offer37：序列化二叉树
 * null序列化为None，也可以序列化为null
 * 可以序列化为String，也可以序列化为queue，只要反序列化结果对就行
 * leetcode测试规则：
 * Your Codec object will be instantiated and called as such:
 * Codec codec = new Codec();
 * codec.deserialize(codec.serialize(root));
 * 预先复习：二叉树的先序遍历、后序遍历、按层遍历的序列化和反序列化
 * PreSerializeAndDeserialize.java 先序遍历
 * PostSerializeAndDeserialize.java 后序遍历
 * LevelSerializeAndDeserialize.java 按层遍历
 *
 * @author dks233
 * @create 2022-05-02-11:13
 */
public class LeetcodeSerializeAndDeserialize {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 先序遍历方法进行序列化和反序列化
    // 时间复杂度：O(N)
    // 空间复杂度：O(N)
    public static class PreMethod {
        public StringBuffer stringBuffer = new StringBuffer("");

        // 序列化过程
        // 先序遍历：头左右
        public String serialize(TreeNode root) {
            process(root);
            return stringBuffer.toString();
        }

        public void process(TreeNode head) {
            if (head == null) {
                stringBuffer.append("None").append(",");
            } else {
                stringBuffer.append(head.val).append(",");
                process(head.left);
                process(head.right);
            }
        }

        // 反序列化过程
        // 前序遍历：头左右
        public TreeNode deserialize(String data) {
            if (data == null) {
                return null;
            }
            String[] split = data.split(",");
            ArrayList<String> dataList = new ArrayList<>(Arrays.asList(split));
            return process(dataList);
        }

        public TreeNode process(List<String> dataList) {
            String headValue = dataList.remove(0);
            if ("None".equals(headValue)) {
                return null;
            }
            TreeNode head = new TreeNode(Integer.parseInt(headValue));
            head.left = process(dataList);
            head.right = process(dataList);
            return head;
        }
    }

    // 后序遍历方法进行序列化和反序列化
    // 时间复杂度：O(N)
    // 空间复杂度：O(N)
    public static class PostMethod {
        // 序列化过程
        // 后序遍历：左右头
        public String serialize(TreeNode root) {
            StringBuilder stringBuilder = new StringBuilder();
            process(root, stringBuilder);
            return String.valueOf(stringBuilder);
        }

        public void process(TreeNode head, StringBuilder stringBuilder) {
            if (head == null) {
                stringBuilder.append("None").append(",");
            } else {
                process(head.left, stringBuilder);
                process(head.right, stringBuilder);
                stringBuilder.append(head.val).append(",");
            }
        }

        // 反序列化过程
        // 后序遍历：左右头
        // 前序遍历是头左右，头左右->头右左->左右头
        // 准备一个栈，把序列化后的字符串依次加入到栈中，入栈顺序左右头，出栈顺序为头右左
        public TreeNode deserialize(String data) {
            if (data == null) {
                return null;
            }
            String[] split = data.split(",");
            ArrayList<String> dataList = new ArrayList<>(Arrays.asList(split));
            Stack<String> stack = new Stack<>();
            for (int i = 0; i < dataList.size(); i++) {
                stack.push(dataList.get(i));
            }
            return process(stack);
        }

        public TreeNode process(Stack<String> stack) {
            String headValue = stack.pop();
            if ("None".equals(headValue)) {
                return null;
            }
            TreeNode head = new TreeNode(Integer.parseInt(headValue));
            head.right = process(stack);
            head.left = process(stack);
            return head;
        }
    }

    // 按层遍历方法进行序列化和反序列化
    // 根据LevelSerializeAndDeserialize.java改写
    public static class LevelMethod {
        // 按层遍历方式序列化
        public String serialize(TreeNode root) {
            StringBuilder stringBuilder = new StringBuilder();
            Queue<TreeNode> nodeQueue = new LinkedList<>();
            TreeNode cur = root;
            if (cur == null) {
                stringBuilder.append("None").append(",");
            } else {
                nodeQueue.offer(cur);
                stringBuilder.append(cur.val).append(",");
                while (!nodeQueue.isEmpty()) {
                    cur = nodeQueue.poll();
                    // 每弹出一个头，将其左右子节点序列化
                    if (cur.left != null) {
                        stringBuilder.append(cur.left.val).append(",");
                        nodeQueue.offer(cur.left);
                    } else {
                        stringBuilder.append("None").append(",");
                    }
                    if (cur.right != null) {
                        stringBuilder.append(cur.right.val).append(",");
                        nodeQueue.offer(cur.right);
                    } else {
                        stringBuilder.append("None").append(",");
                    }
                }
            }
            return String.valueOf(stringBuilder);
        }

        public TreeNode deserialize(String data) {
            if (data == null) {
                return null;
            }
            String[] split = data.split(",");
            ArrayList<String> dataList = new ArrayList<>(Arrays.asList(split));
            TreeNode head = getNode(dataList.remove(0));
            if (head == null) {
                return null;
            }
            TreeNode cur = head;
            Queue<TreeNode> nodeQueue = new LinkedList<>();
            nodeQueue.offer(head);
            while (!nodeQueue.isEmpty()) {
                // 每弹出一个头节点对其左右子节点进行反序列化
                cur = nodeQueue.poll();
                cur.left = getNode(dataList.remove(0));
                cur.right = getNode(dataList.remove(0));
                if (cur.left != null) {
                    nodeQueue.offer(cur.left);
                }
                if (cur.right != null) {
                    nodeQueue.offer(cur.right);
                }
            }
            return head;
        }

        public TreeNode getNode(String value) {
            if ("None".equals(value)) {
                return null;
            }
            return new TreeNode(Integer.parseInt(value));
        }
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.right.left = new TreeNode(4);
        head.right.right = new TreeNode(5);
        PreMethod preMethod = new PreMethod();
        String data = preMethod.serialize(head);
        TreeNode treeNode = preMethod.deserialize(data);
        LevelMethod levelMethod = new LevelMethod();
        String serialize = levelMethod.serialize(head);
        TreeNode deserialize = levelMethod.deserialize(serialize);
    }
}
