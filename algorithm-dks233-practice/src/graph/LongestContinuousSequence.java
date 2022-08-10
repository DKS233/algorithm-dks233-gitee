package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 剑指offer专项突击版：剑指 Offer II 119. 最长连续序列
 * 参考文档：https://leetcode.cn/problems/longest-consecutive-sequence/solution/xiao-bai-lang-ha-xi-ji-he-ha-xi-biao-don-j5a2/
 *
 * @author dks233
 * @create 2022-08-10-17:46
 */
public class LongestContinuousSequence {
    public int longestConsecutive(int[] nums) {
        UnionSet unionSet = new UnionSet(nums);
        int maxSize = 0;
        for (int index = 0; index < nums.length; index++) {
            unionSet.union(nums[index], nums[index] + 1);
        }
        for (Integer size : unionSet.sizeMap.values()) {
            maxSize = Math.max(size, maxSize);
        }
        return maxSize;
    }

    public static class UnionSet {
        // key:节点 value:节点的父亲节点
        public HashMap<Integer, Integer> parents;
        // 一个代表节点代表一个集合
        // key:代表节点 value:集合大小
        public HashMap<Integer, Integer> sizeMap;

        public UnionSet(int[] nums) {
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (Integer value : nums) {
                parents.put(value, value);
                sizeMap.put(value, 1);
            }
        }

        // 返回当前节点的代表节点
        public Integer getNode(Integer cur) {
            if (!parents.containsKey(cur)) {
                return null;
            }
            // 优化：链条扁平化处理，每次找当前节点的代表节点，将沿途的节点都指向代表节点
            // 先将沿途的节点记录到栈中
            Stack<Integer> stack = new Stack<>();
            // 代表节点的父节点是它本身
            while (!cur.equals(parents.get(cur))) {
                stack.push(cur);
                cur = parents.get(cur);
            }
            while (!stack.isEmpty()) {
                // 将沿途的节点从栈中弹出，父节点都变成代表节点
                parents.put(stack.pop(), cur);
            }
            return cur;
        }

        // 判断a和b在不在相同的集合
        public boolean isSameSet(Integer a, Integer b) {
            return getNode(a).equals(getNode(b));
        }

        // 将a和b所在集合进行合并
        public void union(Integer a, Integer b) {
            // 先找到a和b的代表节点
            Integer headA = getNode(a);
            Integer headB = getNode(b);
            if (headA == null || headB == null) {
                return;
            }
            if (!headA.equals(headB)) {
                // 比较两个集合的大小，将小集合的代表节点连到大的集合的代表节点上
                Integer sizeA = sizeMap.get(headA);
                Integer sizeB = sizeMap.get(headB);
                if (sizeA > sizeB) {
                    // b所在集合较小，其代表节点的父节点变为headA
                    parents.put(headB, headA);
                    sizeMap.put(headA, sizeA + sizeB);
                    // b集合并到a集合里，b集合不存在代表节点
                    sizeMap.remove(headB);
                } else {
                    parents.put(headA, headB);
                    sizeMap.put(headB, sizeA + sizeB);
                    sizeMap.remove(headA);
                }
            }
        }

        public int getSize() {
            return sizeMap.size();
        }
    }
}
