package other;

/**
 * 剑指offer专项突击版：剑指 Offer II 092. 翻转字符
 *
 * @author dks233
 * @create 2022-07-26-9:19
 */
@SuppressWarnings("ALL")
public class ReverseCharacter {
    // 方法1：前缀和
    // 找到一个位置，把该位置左边的1都变成0，右边的0都变成1
    public int minFlipsMonoIncr(String s) {
        int[] preSums = new int[s.length() + 1];
        int preSum = 0;
        for (int index = 0; index < s.length(); index++) {
            preSum += s.charAt(index) == '1' ? 1 : 0;
            preSums[index + 1] = preSum;
        }
        // index位置翻转次数为：[0,index-1]的和+[index+1,s.length()-1]的0的个数
        // 注：无论当前位置是1还是0，该位置都不需要翻转
        int minCount = s.length();
        for (int index = 0; index < preSums.length; index++) {
            int pre = preSums[index];
            // preSums[1]->[0]
            // preSums[3]->[0,1,2]
            // preSums[3]-preSums[1]=[1,2]
            int behind = 0;
            if (index + 2 < preSums.length) {
                behind = (s.length() - 1 - index) - (preSums[s.length()] - preSums[index + 1]);
            }
            minCount = Math.min(pre + behind, minCount);
        }
        return minCount;
    }
}
