package unionset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * LeetCode 305. 岛屿数量II
 * 指定往哪些位置添加陆地，每次添加后都可以得到总的岛屿数量
 * 分析：动态初始化，陆地加入的时候进行初始化，然后和上下左右进行合并
 *
 * @author dks233
 * @create 2022-05-04-16:57
 */
public class NumberOfIslandsTwo {
    /**
     * 每添加一次陆地返回一次岛屿数
     * 时间复杂度：
     * 初始化时间复杂度：O(M*N)
     * 添加元素后需要上下左右进行合并，单次union复杂度为O(1)，所以添加元素时间复杂度为O(K)，K为positions数组长度
     * 所以时间复杂度为O(M*N)+O(K)=O(M*N)
     *
     * @param m         行数
     * @param n         列数
     * @param positions 在哪个位置添加陆地
     * @return 每次添加后岛屿数
     */
    public static List<Integer> numIslands(int m, int n, int[][] positions) {
        ArrayList<Integer> list = new ArrayList<>();
        MyUnionSet set = new MyUnionSet(m, n);
        for (int i = 0; i < positions.length; i++) {
            int sets = set.addElement(positions[i][0], positions[i][1]);
            list.add(sets);
        }
        return list;
    }

    public static class MyUnionSet {
        public int[] parents; // 记录每个节点的父节点，parents[i]=k 表示i的父节点是k
        public int[] help; // 辅助数组
        public int[] size; // 记录每个集合的大小，只有i为代表节点时size[i]才有意义
        public int row; // 行数
        public int column; // 列数
        public int sets; // 集合数量

        public MyUnionSet(int m, int n) {
            row = m;
            column = n;
            parents = new int[m * n];
            size = new int[m * n];
            help = new int[m * n];
            sets = 0;
        }

        // 查找index所在集合的代表节点
        public int getNode(int index) {
            int i = 0;
            // index一步步往上走，直到直到代表节点
            // 找代表节点的过程中用辅助数组记录过程中的节点
            while (index != parents[index]) {
                help[i++] = index;
                index = parents[index];
            }
            // 跳出循环的时候i++了,help[i+1]位置是没有元素的
            i--;
            // 将过程中的节点父节点都变成代表节点，扁平化链条（
            while (i >= 0) {
                parents[help[i--]] = index;
            }
            return index;
        }

        // 每次添加陆地对该位置陆地进行初始化和合并操作
        // i和j为添加陆地在地图中的位置，先转换为并查集所用数组的位置
        // 返回添加元素后的集合数
        public int addElement(int i, int j) {
            int index = getIndex(i, j);
            // size[index]>0 说明index已经被初始化过，不需要进行初始化操作和合并操作
            // size[index]==0 说明index未被初始化
            if (size[index] == 0) {
                parents[index] = index;
                size[index] = 1;
                sets++;
                // 对上下左右进行合并
                union(i, j, i - 1, j);
                union(i, j, i, j - 1);
                union(i, j, i + 1, j);
                union(i, j, i, j + 1);
            }
            return sets;
        }

        // 将两个节点所在集合进行合并
        // 找到较小的集合，较小集合的代表节点连到较大的集合的代表节点上
        public void union(int i1, int j1, int i2, int j2) {
            if (i1 < 0 || i1 == row || i2 < 0 || i2 == row || j1 < 0 || j1 == column || j2 < 0 || j2 == column) {
                return;
            }
            int a = getIndex(i1, j1);
            int b = getIndex(i2, j2);
            // 如果有一个集合未被初始化，即没有代表节点，无法合并
            // 注：初始化后size值肯定不为0，即使被合并了size也不会变，被合并后较大集合的代表节点的size才会变
            if (size[a] == 0 || size[b] == 0) {
                return;
            }
            int headA = getNode(a);
            int headB = getNode(b);
            if (headA != headB) {
                if (size[headA] > size[headB]) {
                    parents[headB] = headA;
                    size[headA] = size[size[headA] + size[headB]];
                } else {
                    parents[headA] = headB;
                    size[headB] = size[size[headA] + size[headB]];
                }
                sets--;
            }
        }

        // i和j为地图中的位置，返回在并查集所用数组中的位置
        public int getIndex(int i, int j) {
            return column * i + j;
        }
    }

    /**
     * 每添加一次陆地返回一次岛屿数
     * 上种方法需要初始化，如果对于m*n较大而加入的陆地比较小，不划算
     * 解决方案：用map
     * m和n乘积超过整数最大范围，可以用字符串表示节点("1_2")
     * 时间复杂度：O(positions.length)
     * 因为每次添加陆地进行四次union，union平摊下来复杂度是O(1)（不需要证明），所以单次添加陆地是O(1)
     * 所以总的是O(positions.length)，这样在m*n很大但是添加陆地很少的时候比第一种好，因为不需要初始化，但是
     * m*n较小时没第一种快，因为数组寻找元素比map快
     *
     * @param m         行数
     * @param n         列数
     * @param positions 在哪个位置添加陆地
     * @return 每次添加后岛屿数
     */
    public static List<Integer> numIslandsTwo(int m, int n, int[][] positions) {
        MyUnionSetTwo setTwo = new MyUnionSetTwo(m, n);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < positions.length; i++) {
            int sets = setTwo.addElement(positions[i][0], positions[i][1]);
            list.add(sets);
        }
        return list;
    }

    public static class MyUnionSetTwo {
        public HashMap<String, String> parentMap; // 记录每个节点
        public HashMap<String, Integer> sizeMap; // 记录每个集合的大小
        public int sets; // 记录集合数量
        public ArrayList<String> help; // 辅助数组，存寻找代表节点过程中的节点以便和代表节点连接

        public MyUnionSetTwo(int m, int n) {
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
            help = new ArrayList<>();
            sets = 0;
        }

        // 获取代表节点
        public String getNode(String curNode) {
            while (!curNode.equals(parentMap.get(curNode))) {
                help.add(curNode);
                curNode = parentMap.get(curNode);
            }
            for (String s : help) {
                parentMap.put(s, curNode);
            }
            help.clear();
            return curNode;
        }

        // 添加陆地（节点并且返回集合数）
        public int addElement(int i, int j) {
            String node = i + "_" + j;
            // 当前parentMap不包含node，即node未被添加过
            if (!parentMap.containsKey(node)) {
                parentMap.put(node, node);
                sizeMap.put(node, 1);
                sets++;
                // 将node所在集合与其上下左右节点所在的集合进行合并
                String up = (i - 1) + "_" + j;
                String down = (i + 1) + "_" + j;
                String left = i + "_" + (j - 1);
                String right = i + "_" + (j + 1);
                union(node, up);
                union(node, down);
                union(node, left);
                union(node, right);
            }
            return sets;
        }

        // 将两个节点所在的集合进行合并
        // 获取两个节点所在集合的代表节点，比较长度，将较小集合代表节点连在较大集合代表节点上
        public void union(String nodeA, String nodeB) {
            if (parentMap.containsKey(nodeA) && parentMap.containsKey(nodeB)) {
                String headA = getNode(nodeA);
                String headB = getNode(nodeB);
                if (!headA.equals(headB)) {
                    Integer sizeA = sizeMap.get(headA);
                    Integer sizeB = sizeMap.get(headB);
                    if (sizeA > sizeB) {
                        parentMap.put(headB, headA);
                        sizeMap.put(headA, sizeA + sizeB);
                        sizeMap.remove(headB);
                    } else {
                        parentMap.put(headA, headB);
                        sizeMap.put(headB, sizeA + sizeB);
                        sizeMap.remove(headA);
                    }
                    sets--;
                }
            }
        }
    }
}
















