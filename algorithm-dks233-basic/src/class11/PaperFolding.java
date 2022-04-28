package class11;

/**
 * 折纸问题
 *
 * @author dks233
 * @create 2022-04-28-9:48
 */
public class PaperFolding {
    // N：折几次
    public static void paperFolding(int N) {
        process(1, N, true);
        System.out.println();
    }

    // 凹就是down，就是true
    // 凸就是up，就是false
    public static void process(int i, int N, boolean downOrUp) {
        if (i > N) {
            return;
        }
        // 所有的左子树是凹的
        process(i + 1, N, true);
        // 打印当前节点
        System.out.print(downOrUp ? "凹  " : "凸  ");
        // 所有的右子树都是凸的
        process(i + 1, N, false);
    }

    public static void main(String[] args) {
        int N = 3;
        paperFolding(N);
    }

}
