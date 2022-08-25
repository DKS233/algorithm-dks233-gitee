package other;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode658. 找到 K 个最接近的元素
 *
 * @author dks233
 * @create 2022-08-25-0:19
 */
@SuppressWarnings("ALL")
public class ClosestKElement {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        // 需要删除n-k个元素
        int count = arr.length - k;
        int left = 0;
        int right = arr.length - 1;
        while (count > 0) {
            if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                right--;
            } else {
                left++;
            }
            count--;
        }
        for (int i = left; i <= right; i++) {
            list.add(arr[i]);
        }
        return list;
    }
}
