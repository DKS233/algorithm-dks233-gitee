package class23;

/**
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，
 * 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
 * 给定一个整数n，返回n皇后的摆法有多少种。
 * n=1，返回1
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 *
 * @author dks233
 * @create 2022-05-24-20:27
 */
public class NQueen {
    public static int getCountOne(int n) {
        if (n == 1) {
            return 1;
        }
        // record[i]=j 表示第i行第j列的记录
        int[] record = new int[n];
        return process(0, record, n);
    }

    // 当前走到了第i行，i:[0,n-1]
    // record表示之前的记录
    // 返回：i和之后的行有多少方法数
    // 尝试到某一行不行的时候就回退，然后再往下递归处理（record）
    public static int process(int i, int[] record, int n) {
        // 之前做的决定都没冲突，可以到达越界位置，摆法累加1
        if (i == n) {
            return 1;
        }
        int ans = 0;
        // 将i行皇后放到j列，j:[0,n]
        for (int j = 0; j < n; j++) {
            // 如果和之前的记录没冲突，当前位置可以走
            if (isValid(record, i, j)) {
                // (i,j）记录为有效路径
                record[i] = j;
                // 进入下一行
                ans += process(i + 1, record, n);
            }
        }
        return ans;
    }

    // 判断(i,j)是否和record已经存在的记录有冲突，不同行不同列不同斜线
    // 同斜线：(1,1) (0,0) (0,2) (2,0) (2,2)
    // 同斜线：(0,1) (1,0) (1,2)
    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (record[k] == j || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 15;
        System.out.println(getCountOne(n));
    }
}
