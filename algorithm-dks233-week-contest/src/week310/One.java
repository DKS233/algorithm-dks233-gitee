package week310;

/**
 * 6176. 出现最频繁的偶数元素
 *
 * @author dks233
 * @create 2022-09-11-10:25
 */
@SuppressWarnings("ALL")
public class One {
    public int mostFrequentEven(int[] nums) {
        int maxCount = 0;
        int[] counts = new int[100001];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                counts[nums[i]]++;
                maxCount = Math.max(maxCount, counts[nums[i]]);
            }
        }
        if (maxCount == 0) {
            return -1;
        }
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == maxCount) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        One one = new One();
        System.out.println(one.mostFrequentEven(new int[]{29, 47, 21, 41, 13, 37, 25, 7}));
        System.out.println(one.mostFrequentEven(new int[]{4, 4, 4, 9, 2, 4}));
    }
}
