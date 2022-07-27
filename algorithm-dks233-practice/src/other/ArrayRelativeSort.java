package other;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 剑指offer专项突击版：剑指 Offer II 075. 数组相对排序
 *
 * @author dks233
 * @create 2022-07-27-12:00
 */
public class ArrayRelativeSort {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        int[] nums = new int[arr1.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int index = 0; index < arr2.length; index++) {
            map.put(arr2[index], 0);
        }
        for (int index = 0; index < arr1.length; index++) {
            if (map.containsKey(arr1[index])) {
                map.put(arr1[index], map.get(arr1[index]) + 1);
            }
        }
        int curIndex = 0;
        for (int index = 0; index < arr2.length; index++) {
            if (map.containsKey(arr2[index])) {
                Integer count = map.get(arr2[index]);
                for (int i = 0; i < count; i++) {
                    nums[curIndex++] = arr2[index];
                }
            }
        }
        for (int index = 0; index < arr1.length; index++) {
            if (!map.containsKey(arr1[index])) {
                nums[curIndex++] = arr1[index];
            }
        }
        return nums;
    }
}
