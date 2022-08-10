package graph;

import java.util.*;

/**
 * 剑指offer专项突击版：剑指 Offer II 115. 重建序列
 * 参考文档：https://leetcode.cn/problems/ur2n8P/solution/zhong-jian-xu-lie-by-capital-worker-n6ti/
 *
 * @author dks233
 * @create 2022-08-10-13:24
 */
@SuppressWarnings("ALL")
public class ReconstructionSequence {
    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        // key:node value:node.nexts
        HashMap<Integer, List<Integer>> nodeMap = new HashMap<>();
        // key:node value:入度
        int[] inMap = new int[nums.length + 1];
        // 统计节点nexts和入度
        for (int index = 0; index < sequences.length; index++) {
            for (int left = 0; left < sequences[index].length - 1; left++) {
                int from = sequences[index][left];
                int to = sequences[index][left + 1];
                if (nodeMap.containsKey(from) && nodeMap.get(from).contains(to)) {
                    continue;
                }
                nodeMap.putIfAbsent(from, new ArrayList<>());
                nodeMap.get(from).add(to);
                inMap[to]++;
            }
        }
        // 拓扑排序
        // 最终拓扑排序结果
        List<Integer> sortedList = new ArrayList<>();
        // 统计入度为0的节点，加入到队列中
        LinkedList<Integer> zeroQueue = new LinkedList<>();
        for (int index = 1; index < inMap.length; index++) {
            if (inMap[index] == 0) {
                zeroQueue.offerLast(index);
            }
        }
        // 删除入度为0的节点，相对应的nexts入度删除，然后继续删除入度为0的节点
        while (!zeroQueue.isEmpty()) {
            if (zeroQueue.size() > 1) {
                return false;
            }
            Integer node = zeroQueue.pollLast();
            sortedList.add(node);
            if (nodeMap.get(node) == null) {
                continue;
            }
            for (Integer next : nodeMap.get(node)) {
                inMap[next]--;
                if (inMap[next] == 0) {
                    zeroQueue.offer(next);
                }
            }
        }
        // 比较排序后和nums是否相等
        if (sortedList.size() != nums.length) {
            return false;
        }
        for (int index = 0; index < sortedList.size(); index++) {
            if (sortedList.get(index) != nums[index]) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        ReconstructionSequence reconstructionSequence = new ReconstructionSequence();
        reconstructionSequence.sequenceReconstruction(new int[]{4, 1, 5, 2, 6, 3}, new int[][]{{5, 2, 6, 3}, {4, 1, 5, 2}});
    }
}
