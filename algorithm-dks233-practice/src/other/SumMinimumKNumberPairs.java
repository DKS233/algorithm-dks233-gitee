package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 剑指offer专项突击版：剑指 Offer II 061. 和最小的 k 个数对
 * 参考文档：https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/solution/tong-ge-lai-shua-ti-la-you-xian-ji-dui-l-fw7y/
 *
 * @author dks233
 * @create 2022-07-24-20:18
 */
@SuppressWarnings("ALL")
public class SumMinimumKNumberPairs {
    public static class MethodTwo {
        // 时间复杂度：O(klogk)
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            // heap中存索引数组 [nums1的索引，nums2的索引]
            PriorityQueue<int[]> heap = new PriorityQueue<>((arr1, arr2) ->
                    (nums1[arr1[0]] + nums2[arr1[1]] - nums1[arr2[0]] - nums2[arr2[1]]));
            List<List<Integer>> list = new ArrayList<>();
            // 初始把[nums1[index],nums2[0]]全部放到堆中
            for (int index = 0; index < nums1.length; index++) {
                heap.offer(new int[]{index, 0});
            }
            // 每次弹出一个，放入一个
            // 弹出的是nums1[poll[0]], nums2[poll[1]]，大于它的最小组合是nums1[poll[0]], nums2[poll[1] + 1]
            while (k > 0 && !heap.isEmpty()) {
                int[] poll = heap.poll();
                list.add(Arrays.asList(nums1[poll[0]], nums2[poll[1]]));
                if (poll[1] + 1 < nums2.length) {
                    heap.add(new int[]{poll[0], poll[1] + 1});
                }
                k--;
            }
            return list;
        }
    }

    // 暴力解法：计算所有数对和，然后排序，筛选
    public static class MethodOne {
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            Node[] nodes = new Node[nums1.length * nums2.length];
            int index = 0;
            for (int i = 0; i < nums1.length; i++) {
                for (int j = 0; j < nums2.length; j++) {
                    nodes[index++] = new Node(nums1[i], nums2[j]);
                }
            }
            Arrays.sort(nodes, (nodeOne, nodeTwo) -> nodeOne.numberTwo + nodeOne.numberOne - nodeTwo.numberOne - nodeTwo.numberTwo);
            List<List<Integer>> list = new ArrayList<>();
            for (int count = 0; count < k && count < nodes.length; count++) {
                List<Integer> singleList = new ArrayList<>();
                singleList.add(nodes[count].numberOne);
                singleList.add(nodes[count].numberTwo);
                list.add(new ArrayList<>(singleList));
            }
            return list;
        }

        public static class Node {
            int numberOne;
            int numberTwo;

            public Node(int numberOne, int numberTwo) {
                this.numberOne = numberOne;
                this.numberTwo = numberTwo;
            }
        }
    }

    public static void main(String[] args) {
        MethodTwo methodTwo = new MethodTwo();
        methodTwo.kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3);
    }
}
