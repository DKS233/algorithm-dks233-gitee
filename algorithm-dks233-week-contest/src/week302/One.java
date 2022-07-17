package week302;

/**
 * leetcode6120. 数组能形成多少数对
 *
 * @author dks233
 * @create 2022-07-17-10:23
 */
public class One {
    public int[] numberOfPairs(int[] nums) {
        int[] counts = new int[101];
        for (int index = 0; index < nums.length; index++) {
            counts[nums[index]]++;
        }
        int numOfPairs = 0;
        int rest = 0;
        for (int index = 0; index < counts.length; index++) {
            if (counts[index] == 1) {
                rest += 1;
            }
            if (counts[index] > 1) {
                rest += counts[index] % 2;
                numOfPairs += counts[index] / 2;
            }
        }
        return new int[]{numOfPairs, rest};
    }
}
