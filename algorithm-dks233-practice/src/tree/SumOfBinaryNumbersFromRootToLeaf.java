package tree;

import tree.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode1022. 从根到叶的二进制数之和
 *
 * @author dks233
 * @create 2022-09-15-15:23
 */
@SuppressWarnings("ALL")
public class SumOfBinaryNumbersFromRootToLeaf {
    List<String> list = new ArrayList<>();

    public int sumRootToLeaf(TreeNode root) {
        process(root, "");
        int result = 0;
        for (int i = 0; i < list.size(); i++) {
            result += singleNum(list.get(i));
        }
        return result;
    }

    public int singleNum(String s) {
        char[] str = s.toCharArray();
        int ans = 0;
        int cur = 1;
        for (int i = str.length - 1; i >= 0; i--) {
            ans += cur * (str[i] - '0');
            cur <<= 1;
        }
        return ans;
    }

    public void process(TreeNode head, String curStr) {
        if (head == null) {
            return;
        }
        if (head.left == null && head.right == null) {
            list.add(curStr + head.val);
            return;
        }
        process(head.left, curStr + head.val);
        process(head.right, curStr + head.val);
    }
}
