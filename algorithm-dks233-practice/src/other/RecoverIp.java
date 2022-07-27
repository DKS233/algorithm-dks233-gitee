package other;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指offer专项突击版：剑指 Offer II 087. 复原 IP
 * 参考文档：https://leetcode.cn/problems/restore-ip-addresses/solution/shou-hua-tu-jie-huan-yuan-dfs-hui-su-de-xi-jie-by-/
 *
 * @author dks233
 * @create 2022-07-27-10:40
 */
@SuppressWarnings("ALL")
public class RecoverIp {
    public static class MethodOne {
        List<String> list = new ArrayList<>();

        public List<String> restoreIpAddresses(String s) {
            process(s.toCharArray(), 0, new ArrayList<>());
            return list;
        }

        // [index...]范围内的字符如果可以组成ip，将其添加到curList中
        public void process(char[] str, int index, List<String> curList) {
            // ip超过4位，直接返回
            // ip满4位，但是index未覆盖全部字符，直接返回
            if (curList.size() > 4 || (index < str.length && curList.size() == 4)) {
                return;
            }
            // ip是4位，且覆盖全部字符，将结果添加到list中
            if (index == str.length) {
                if (curList.size() == 4) {
                    fill(curList);
                }
                return;
            }
            // 从index位置开始，最多可以选择三位字符，依次尝试
            // index位置如果是0，将0添加到curlist中，去下一个位置做决定
            if (str[index] == '0') {
                curList.add(String.valueOf(0));
                process(str, index + 1, curList);
                curList.remove(curList.size() - 1);
                return;
            }
            // index位置如果不是0，从index位置尝试最多三位的拼接
            int number = 0;
            for (int i = index; i < str.length; i++) {
                number += str[i] - '0';
                if (number >= 0 && number <= 255) {
                    curList.add(String.valueOf(number));
                    process(str, i + 1, curList);
                    curList.remove(curList.size() - 1);
                    number *= 10;
                }
            }
        }

        // 把合法的ip添加到list中
        public void fill(List<String> curList) {
            StringBuilder builder = new StringBuilder();
            for (String str : curList) {
                builder.append(str).append(".");
            }
            builder.deleteCharAt(builder.length() - 1);
            list.add(builder.toString());
        }

    }
}
