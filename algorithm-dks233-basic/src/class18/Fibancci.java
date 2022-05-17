package class18;

/**
 * 斐波那锲数列
 *
 * @author dks233
 * @create 2022-05-17-8:52
 */
public class Fibancci {
    public static int getFibancci(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        return getFibancci(n - 1) + getFibancci(n - 2);
    }

    public static void main(String[] args) {
        int n = 3;
        System.out.println(getFibancci(n));
    }
}
