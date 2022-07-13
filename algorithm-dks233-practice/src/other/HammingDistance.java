package other;

/**
 * leetcode461. 汉明距离
 *
 * @author dks233
 * @create 2022-07-13-20:19
 */
public class HammingDistance {
    public int hammingDistance(int x, int y) {
        int count = 0;
        for (int index = 0; index < 31; index++) {
            if (((x >> index) & 1) != ((y >> index) & 1)) {
                count++;
            }
        }
        return count;
    }
}
