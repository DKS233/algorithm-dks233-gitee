package other;


/**
 * 剑指 Offer 第二版 13. 机器人的运动范围
 *
 * @author dks233
 * @create 2022-06-17-23:01
 */
public class RobotMovingRange {
    public int movingCount(int m, int n, int k) {
        // 标记判断过的路径
        boolean[][] visit = new boolean[m][n];
        return move(0, 0, k, visit);
    }

    public int move(int row, int column, int k, boolean[][] visit) {
        if (row < 0 || row >= visit.length || column < 0 || column >= visit[0].length || visit[row][column]) {
            return 0;
        }
        visit[row][column] = true;
        if (getDigitSum(row, column) > k) {
            return 0;
        }
        int ans = 1;
        ans += move(row + 1, column, k, visit) + move(row - 1, column, k, visit)
                + move(row, column - 1, k, visit) + move(row, column + 1, k, visit);
        return ans;
    }

    // 行号为row，列号为column，返回数位之和
    public int getDigitSum(int row, int column) {
        return getDigit(row) + getDigit(column);
    }

    // 获得number的数位之和
    public int getDigit(int number) {
        // 先计算number有几位数
        int digitCount = 0;
        int copyNumber = number;
        while (copyNumber >= 1) {
            copyNumber = copyNumber / 10;
            digitCount++;
        }
        // 然后计算每一个数位的值，然后累加
        int sum = 0;
        // 9324    4 -> 9324%10    2 -> (9324/10)%10    3 -> (9324/100)%10    9 -> (9324/1000)%10
        // i = 1 代表个位
        for (int i = 1; i <= digitCount; i++) {
            sum += ((number / Math.pow(10, i - 1)) % 10);
        }
        return sum;
    }

    public static void main(String[] args) {
        RobotMovingRange robotMovingRange = new RobotMovingRange();
        System.out.println(robotMovingRange.getDigit(12233));
        System.out.println(robotMovingRange.getDigit(2342));
        System.out.println(robotMovingRange.getDigitSum(10, 20));
    }
}
