package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * leetcode349. 两个数组的交集
 * leetcode350. 两个数组的交集 II
 *
 * @author dks233
 * @create 2022-08-28-23:07
 */
@SuppressWarnings("ALL")
public class IntersectionOfTwoArray {
    // 问题1
    // 方法1：set存两个数组中的元素，然后返回两个数组中都存在的元素
    public static class ProblemOneMethodOne {
        public int[] intersection(int[] nums1, int[] nums2) {
            HashSet<Integer> setOne = new HashSet<>();
            HashSet<Integer> setTwo = new HashSet<>();
            for (int i = 0; i < nums1.length; i++) {
                setOne.add(nums1[i]);
            }
            for (int i = 0; i < nums2.length; i++) {
                if (setOne.contains(nums2[i])) {
                    setTwo.add(nums2[i]);
                }
            }
            int[] result = new int[setTwo.size()];
            int i = 0;
            for (Integer cur : setTwo) {
                result[i++] = cur;
            }
            return result;
        }
    }

    // 问题1
    // 方法2：排序+双指针
    public static class ProblemOneMethodTwo {
        public int[] intersection(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int i = 0;
            int j = 0;
            HashSet<Integer> set = new HashSet<>();
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] == nums2[j]) {
                    set.add(nums1[i]);
                    i++;
                    j++;
                } else if (nums1[i] < nums2[j]) {
                    i++;
                } else {
                    j++;
                }
            }
            return set.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    // 问题2
    // 排序+双指针
    public static class ProblemTwoMethodOne {
        public int[] intersect(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int i = 0;
            int j = 0;
            List<Integer> list = new ArrayList<>();
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] == nums2[j]) {
                    list.add(nums1[i]);
                    i++;
                    j++;
                } else if (nums1[i] > nums2[j]) {
                    j++;
                } else {
                    i++;
                }
            }
            return list.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}


























