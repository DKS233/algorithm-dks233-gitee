package other;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * leetcode2349. 设计数字容器系统
 *
 * @author dks233
 * @create 2022-07-28-21:21
 */
public class DesignDigitalContainerSystem {
    public static class NumberContainers {
        // key：索引  value：数值
        HashMap<Integer, Integer> indexMap;
        // key：数值 value：对应的索引
        HashMap<Integer, TreeSet<Integer>> numberMap;

        public NumberContainers() {
            indexMap = new HashMap<>();
            numberMap = new HashMap<>();
        }

        public void change(int index, int number) {
            if (indexMap.containsKey(index)) {
                Integer cur = indexMap.get(index);
                indexMap.put(index, number);
                numberMap.get(cur).remove(index);
                if (!numberMap.containsKey(number)) {
                    numberMap.put(number, new TreeSet<>());
                }
                numberMap.get(number).add(index);
            } else {
                indexMap.put(index, number);
                if (!numberMap.containsKey(number)) {
                    numberMap.put(number, new TreeSet<>());
                }
                numberMap.get(number).add(index);
            }
        }

        public int find(int number) {
            if (numberMap.containsKey(number) && numberMap.get(number).size() > 0) {
                return numberMap.get(number).first();
            } else {
                return -1;
            }
        }
    }
}
