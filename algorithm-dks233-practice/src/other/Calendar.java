package other;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * 剑指offer专项突击版：剑指 Offer II 058. 日程表
 * TODO:线段树解法
 *
 * @author dks233
 * @create 2022-07-23-21:58
 */
public class Calendar {
    public static class MyCalendar {
        TreeMap<Integer, Integer> treeMap;

        public MyCalendar() {
            // key:start
            // value:end
            this.treeMap = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            if (treeMap.isEmpty()) {
                treeMap.put(start, end);
                return true;
            }
            // 找到<=start的最大entry，如果value大于start，发生重合
            // 找到>=start的最小entry，如果key小于end，发生重合
            Map.Entry<Integer, Integer> ceilingEntry = treeMap.ceilingEntry(start);
            Map.Entry<Integer, Integer> floorEntry = treeMap.floorEntry(start);
            if (ceilingEntry != null && ceilingEntry.getKey() < end) {
                return false;
            }
            if (floorEntry != null && floorEntry.getValue() > start) {
                return false;
            }
            treeMap.put(start, end);
            return true;
        }
    }
}
