package other;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * leetcode406. 根据身高重建队列
 *
 * @author dks233
 * @create 2022-07-14-17:42
 */
public class RebuildQueueByHeight {

    public int[][] reconstructQueue(int[][] people) {
        // 首先根据 h 降序排列，然后根据 k 升序排列
        Arrays.sort(people, (p1, p2) -> p1[0] == p2[0] ? p1[1] - p2[1] : p2[0] - p1[0]);
        // 从左往右遍历排好序的数组，根据 k 进行插入，k是几就插入到哪个位置
        LinkedList<int[]> list = new LinkedList<>();
        for (int[] person : people) {
            list.add(person[1], person);
        }
        return list.toArray(new int[list.size()][2]);
    }
}
