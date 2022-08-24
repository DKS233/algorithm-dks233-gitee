package other;

/**
 * leetcode1460. 通过翻转子数组使两个数组相等
 *
 * @author dks233
 * @create 2022-08-24-18:37
 */
@SuppressWarnings("ALL")
public class ReverseSubArrayToEqual {
    // 分析：只要对应元素和出现频次相同，就可以通过翻转使两个子数组相等
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] count = new int[1001];
        for (int index = 0; index < target.length; index++) {
            count[target[index]]++;
        }
        for (int index = 0; index < arr.length; index++) {
            count[arr[index]]--;
        }
        for (int index = 0; index < count.length; index++) {
            if (count[index] != 0) {
                return false;
            }
        }
        return true;
    }
}
