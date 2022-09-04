package other;

/**
 * leetcode1582. 二进制矩阵中的特殊位置
 *
 * @author dks233
 * @create 2022-09-04-8:05
 */
public class SpecialPositionInBinaryMatrix {
    // 时间复杂度：O(MN)
    // 空间复杂度：O(M+N)
    public int numSpecial(int[][] mat) {
        int row = mat.length;
        int column = mat[0].length;
        // 标记第i行第j列有几个1
        int[] numsI = new int[row];
        int[] numsJ = new int[column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                numsI[i] += mat[i][j] == 1 ? 1 : 0;
                numsJ[j] += mat[i][j] == 1 ? 1 : 0;
            }
        }
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (numsI[i] == 1 && numsJ[j] == 1 && mat[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }
}
