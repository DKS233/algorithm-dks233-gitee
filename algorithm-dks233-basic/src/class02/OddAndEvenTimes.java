package class02;

/**
 * 题目1：一个数组中，有一种数出现了奇数次，其他数出现了偶数次，怎么找到并打印这种数
 * 奇数：odd
 * 偶数：even
 * 异或：Exclusive OR（EOR）
 * 思路：用eor=0依次异或所有数，最终的结果就是出现了奇数次的数
 * 题目2：怎么把一个int类型的数，提取出二进制最右侧的1来
 * 思路：ans=a&(-a)=a&(~a+1)
 * 题目3：一个数组中，有两种数出现了奇数次，其他数出现了偶数次，怎么找到并打印这两种数
 * 思路：设两种数为a和b，用eor=0依次异或所有数，得到的结果为eor=a^b，由于a!=b，所以eor!=0，
 * eor用二进制表示肯定存在为1的位，且a和b的二进制表示在该位上肯定一个是0，一个是1，
 * 假设eor从右往左数第三位为1，则根据第三位上式0还是1，将数组中的数分为两类，一类是第三位上为0的数，
 * 假设a属于这类，一类是第三位上为1的数，假设b属于这类，用eor'=0对第二类数依次进行异或，结果为eor'=b，
 * 这样就得到b，然后eor'^eor=b^a^b=a得到a
 *
 * @author dks233
 * @create 2022-03-20-22:08
 */
public class OddAndEvenTimes {
    // 题目一代码
    public static void printOneNumber(int[] arr) {
        int eor = 0;
        for (int arrElement : arr) {
            eor ^= arrElement;
        }
        int result = eor;
        System.out.println("出现奇数次的数为：" + result);
    }

    // 题目三代码
    public static void printTwoNumbers(int[] arr) {
        int eor = 0;
        for (int arrElement : arr) {
            // eor依次对数组中的数进行异或，结果为eor=a^b
            eor ^= arrElement;
        }
        // 将eor二进制中最右侧的1提取出来
        // eor:1100 0000 0100
        // ans:0000 0000 0100
        int ans = eor & (~eor + 1);
        // eor'
        int anotherEor = 0;
        // 假设ans从右往左数第i位是1，数组中的数根据第i位是0还是1分为两类，设a第i位上为0，b第i位上为1，先将b提取出来
        for (int arrElement : arr) {
            if ((arrElement & ans) != 0) {
                anotherEor ^= arrElement;
            }
        }
        // 最终结果为eor'=b
        int b = anotherEor;
        // 再计算a的值 a=eor^eor'=a^b^b=a
        int a = eor ^ anotherEor;
        System.out.println("出现奇数次的两个数分别为：" + a + "<----->" + b);
    }

    public static void main(String[] args) {
        int[] arrForPrintOne = new int[]{233, 233, 233, 233, 233, 3, 3, 2, 2, 4, 4, 5, 5, 5, 5, 10, 10};
        printOneNumber(arrForPrintOne);
        int[] arrForPrintTwo = new int[]{1, 1, 1, 233, 3, 3, 3, 3, 4, 4, 5, 5, 5, 5, 5, 5, 213, 213};
        printTwoNumbers(arrForPrintTwo);
    }
}
