package week304;

/**
 * leetcode6133. 分组的最大数量
 *
 * @author dks233
 * @create 2022-07-31-10:27
 */
@SuppressWarnings("ALL")
public class Two {
    // 对数组排序
    // 按照每个组元素的数量分组，每个分组的和肯定是依次递增的
    // 第i组的元素数量是i（i从1开始），最后一组的元素不够则和倒数第二组元素合并
    // 设最后分组的数量是x,那么每个分组的元素个数和是1+2+...+x=x(x+1)/2
    // x(x+1)/2<=n 解方程
    public int maximumGroups(int[] grades) {
        return (int) ((Math.sqrt(1 + 8 * grades.length) - 1) / 2);
    }
}
