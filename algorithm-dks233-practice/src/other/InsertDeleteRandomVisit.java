package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 剑指offer专项突击版：剑指 Offer II 030. 插入、删除和随机访问都是 O(1) 的容器
 *
 * @author dks233
 * @create 2022-07-20-22:26
 */
public class InsertDeleteRandomVisit {
    public static class RandomizedSet {
        // 元素数组
        List<Integer> list;
        // key：元素值  value：对应数组索引
        // value等于-1表示元素已经被删除
        HashMap<Integer, Integer> hashMap;

        public RandomizedSet() {
            list = new ArrayList<>();
            hashMap = new HashMap<>();
        }

        public boolean insert(int val) {
            // 如果存在元素，返回false
            if (hashMap.containsKey(val) && hashMap.get(val) != -1) {
                return false;
            }
            // 不存在元素，插入，返回true
            list.add(val);
            hashMap.put(val, list.size() - 1);
            return true;
        }

        public boolean remove(int val) {
            if (hashMap.containsKey(val) && hashMap.get(val) != -1) {
                // 找到val所在的索引位置
                Integer valIndex = hashMap.get(val);
                // val和数组最后一个位置交换，更新数组有效长度
                swap(list, valIndex, list.size() - 1);
                hashMap.put(list.get(valIndex), valIndex);
                list.remove(list.size() - 1);
                hashMap.put(val, -1);
                return true;
            }
            return false;
        }

        public void swap(List<Integer> list, int a, int b) {
            int one = list.get(a);
            int two = list.get(b);
            list.set(a, two);
            list.set(b, one);
        }


        public int getRandom() {
            int randomIndex = (int) (Math.random() * list.size());
            return list.get(randomIndex);
        }
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(0); // 1
        randomizedSet.insert(1); // 01
        randomizedSet.remove(0); // 1
        randomizedSet.insert(2); // 12
        randomizedSet.remove(1); // 2
        System.out.println(randomizedSet.getRandom());
    }
}
