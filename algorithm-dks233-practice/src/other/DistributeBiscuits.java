package other;

import java.util.Arrays;

/**
 * leetcode455. 分发饼干
 *
 * @author dks233
 * @create 2022-09-12-8:49
 */
@SuppressWarnings("ALL")
public class DistributeBiscuits {
    // 每个孩子 g[i] 胃口值
    // 每块饼干 s[j] 尺寸
    // 分析：将s和g排序，双指针从大到小尝试
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(s);
        Arrays.sort(g);
        int count = 0;
        int gIndex = g.length - 1;
        int sIndex = s.length - 1;
        while (sIndex >= 0 && gIndex >= 0) {
            if (s[sIndex] >= g[gIndex]) {
                count++;
                sIndex--;
                gIndex--;
            } else {
                gIndex--;
            }
        }
        return count;
    }
}
