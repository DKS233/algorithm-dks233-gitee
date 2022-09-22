package other;

import java.util.HashMap;

/**
 * leetcode1640. 能否连接形成数组
 *
 * @author dks233
 * @create 2022-09-22-9:28
 */
@SuppressWarnings("ALL")
public class WhetherConnectToFormArray {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        // 用hashmap记录表头
        // key:表头元素 value:在pieces数组中的索引
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < pieces.length; i++) {
            map.put(pieces[i][0], i);
        }
        int index = 0;
        while (index < arr.length) {
            if (!map.containsKey(arr[index])) {
                return false;
            }
            for (Integer value : pieces[map.get(arr[index])]) {
                if (arr[index] == value) {
                    index++;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
