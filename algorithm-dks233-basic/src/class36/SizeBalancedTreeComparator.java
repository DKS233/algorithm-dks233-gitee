package class36;

import class36.SizeBalancedTree.SbTree;

import java.util.TreeMap;


// SB树对数器
public class SizeBalancedTreeComparator {

    public static void functionTest() {
        System.out.println("功能测试开始");
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        SbTree<Integer, Integer> sbt = new SbTree<>();
        int maxK = 500;
        int maxV = 50000;
        int testTime = 1000000;
        for (int i = 0; i < testTime; i++) {
            int addK = (int) (Math.random() * maxK);
            int addV = (int) (Math.random() * maxV);
            treeMap.put(addK, addV);
            sbt.put(addK, addV);

            int removeK = (int) (Math.random() * maxK);
            treeMap.remove(removeK);
            sbt.remove(removeK);

            int querryK = (int) (Math.random() * maxK);
            if (treeMap.containsKey(querryK) != sbt.containsKey(querryK)) {
                System.out.println("containsKey Oops");
                System.out.println(treeMap.containsKey(querryK));
                System.out.println(sbt.containsKey(querryK));
                break;
            }

            if (treeMap.containsKey(querryK)) {
                int v1 = treeMap.get(querryK);
                int v3 = sbt.get(querryK);
                if (v1 != v3) {
                    System.out.println("get Oops");
                    System.out.println(treeMap.get(querryK));
                    System.out.println(sbt.get(querryK));
                    break;
                }
                Integer f1 = treeMap.floorKey(querryK);
                Integer f3 = sbt.floorKey(querryK);
                if (f1 == null && f3 != null) {
                    System.out.println("floorKey Oops");
                    System.out.println(treeMap.floorKey(querryK));
                    System.out.println(sbt.floorKey(querryK));
                    break;
                }
                if (f1 != null && (f3 == null)) {
                    System.out.println("floorKey Oops");
                    System.out.println(treeMap.floorKey(querryK));
                    System.out.println(sbt.floorKey(querryK));
                    break;
                }
                if (f1 != null) {
                    int ans1 = f1;
                    int ans3 = f3;
                    if (ans1 != ans3) {
                        System.out.println("floorKey Oops");
                        System.out.println(treeMap.floorKey(querryK));
                        System.out.println(sbt.floorKey(querryK));
                        break;
                    }
                }
                f1 = treeMap.ceilingKey(querryK);
                f3 = sbt.ceilingKey(querryK);
                if (f1 == null && (f3 != null)) {
                    System.out.println("ceilingKey Oops");
                    System.out.println(treeMap.ceilingKey(querryK));
                    System.out.println(sbt.ceilingKey(querryK));
                    break;
                }
                if (f1 != null && (f3 == null)) {
                    System.out.println("ceilingKey Oops");
                    System.out.println(treeMap.ceilingKey(querryK));
                    System.out.println(sbt.ceilingKey(querryK));
                    break;
                }
                if (f1 != null) {
                    int ans1 = f1;
                    int ans3 = f3;
                    if (ans1 != ans3) {
                        System.out.println("ceilingKey Oops");
                        System.out.println(treeMap.ceilingKey(querryK));
                        System.out.println(sbt.ceilingKey(querryK));
                        break;
                    }
                }

            }

            Integer f1 = treeMap.firstKey();
            Integer f3 = sbt.firstKey();
            if (f1 == null && (f3 != null)) {
                System.out.println("firstKey Oops");
                System.out.println(treeMap.firstKey());
                System.out.println(sbt.firstKey());
                break;
            }
            if (f1 != null && (f3 == null)) {
                System.out.println("firstKey Oops");
                System.out.println(treeMap.firstKey());
                System.out.println(sbt.firstKey());
                break;
            }
            if (f1 != null) {
                int ans1 = f1;
                int ans3 = f3;
                if (ans1 != ans3) {
                    System.out.println("firstKey Oops");
                    System.out.println(treeMap.firstKey());
                    System.out.println(sbt.firstKey());
                    break;
                }
            }

            f1 = treeMap.lastKey();
            f3 = sbt.lastKey();
            if (f1 == null && (f3 != null)) {
                System.out.println("lastKey Oops");
                System.out.println(treeMap.lastKey());
                System.out.println(sbt.lastKey());
                break;
            }
            if (f1 != null && (f3 == null)) {
                System.out.println("firstKey Oops");
                System.out.println(treeMap.lastKey());
                System.out.println(sbt.lastKey());
                break;
            }
            if (f1 != null) {
                int ans1 = f1;
                int ans3 = f3;
                if (ans1 != ans3) {
                    System.out.println("lastKey Oops");
                    System.out.println(treeMap.lastKey());
                    System.out.println(sbt.lastKey());
                    break;
                }
            }
            if (treeMap.size() != sbt.getSize()) {
                System.out.println("size Oops");
                System.out.println(treeMap.size());
                System.out.println(sbt.getSize());
                break;
            }
        }
        System.out.println("功能测试结束");
    }

    public static void performanceTest() {
        System.out.println("性能测试开始");
        TreeMap<Integer, Integer> treeMap;
        SbTree<Integer, Integer> sbt;
        long start;
        long end;
        int max = 1000000;
        treeMap = new TreeMap<>();
        sbt = new SbTree<>();
        System.out.println("顺序递增加入测试，数据规模 : " + max);
        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            treeMap.put(i, i);
        }
        end = System.currentTimeMillis();
        System.out.println("treeMap 运行时间 : " + (end - start) + "ms");


        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            sbt.put(i, i);
        }
        end = System.currentTimeMillis();
        System.out.println("sbt 运行时间 : " + (end - start) + "ms");


        System.out.println("顺序递增删除测试，数据规模 : " + max);
        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            treeMap.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("treeMap 运行时间 : " + (end - start) + "ms");


        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            sbt.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("sbt 运行时间 : " + (end - start) + "ms");

        System.out.println("顺序递减加入测试，数据规模 : " + max);
        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            treeMap.put(i, i);
        }
        end = System.currentTimeMillis();
        System.out.println("treeMap 运行时间 : " + (end - start) + "ms");


        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            sbt.put(i, i);
        }
        end = System.currentTimeMillis();
        System.out.println("sbt 运行时间 : " + (end - start) + "ms");

        System.out.println("顺序递减删除测试，数据规模 : " + max);
        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            treeMap.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            sbt.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("sbt 运行时间 : " + (end - start) + "ms");

        System.out.println("随机加入测试，数据规模 : " + max);
        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            treeMap.put((int) (Math.random() * i), i);
        }
        end = System.currentTimeMillis();
        System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            sbt.put((int) (Math.random() * i), i);
        }
        end = System.currentTimeMillis();
        System.out.println("sbt 运行时间 : " + (end - start) + "ms");

        System.out.println("随机删除测试，数据规模 : " + max);
        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            treeMap.remove((int) (Math.random() * i));
        }
        end = System.currentTimeMillis();
        System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            sbt.remove((int) (Math.random() * i));
        }
        end = System.currentTimeMillis();
        System.out.println("sbt 运行时间 : " + (end - start) + "ms");

        System.out.println("性能测试结束");
    }

    public static void main(String[] args) {
        functionTest();
        System.out.println("======");
        performanceTest();
    }

}