package class35;

import java.util.TreeMap;

import class35.AvlTree.MyAvlTree;

// 左程云  AVL树测试
public class Comparator {

    public static void functionTest() {
        System.out.println("功能测试开始");
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        MyAvlTree<Integer, Integer> avl = new MyAvlTree<>();

        int maxK = 500;
        int maxV = 50000;
        int testTime = 1000000;
        for (int i = 0; i < testTime; i++) {
            int addK = (int) (Math.random() * maxK);
            int addV = (int) (Math.random() * maxV);
            treeMap.put(addK, addV);
            avl.put(addK, addV);
            int removeK = (int) (Math.random() * maxK);
            treeMap.remove(removeK);
            avl.remove(removeK);
            int querryK = (int) (Math.random() * maxK);
            if (treeMap.containsKey(querryK) != avl.containsKey(querryK)) {
                System.out.println("containsKey Oops");
                System.out.println(treeMap.containsKey(querryK));
                System.out.println(avl.containsKey(querryK));
                break;
            }

            if (treeMap.containsKey(querryK)) {
                int v1 = treeMap.get(querryK);
                int v2 = avl.get(querryK);
                if (v1 != v2) {
                    System.out.println("get Oops");
                    System.out.println(treeMap.get(querryK));
                    System.out.println(avl.get(querryK));
                    break;
                }
                Integer f1 = treeMap.floorKey(querryK);
                Integer f2 = avl.floorKey(querryK);
                if (f1 == null && (f2 != null)) {
                    System.out.println("floorKey Oops");
                    System.out.println(treeMap.floorKey(querryK));
                    System.out.println(avl.floorKey(querryK));
                    break;
                }
                if (f1 != null && (f2 == null)) {
                    System.out.println("floorKey Oops");
                    System.out.println(treeMap.floorKey(querryK));
                    System.out.println(avl.floorKey(querryK));
                    break;
                }
                if (f1 != null) {
                    int ans1 = f1;
                    int ans2 = f2;
                    if (ans1 != ans2) {
                        System.out.println("floorKey Oops");
                        System.out.println(treeMap.floorKey(querryK));
                        System.out.println(avl.floorKey(querryK));
                        break;
                    }
                }
                f1 = treeMap.ceilingKey(querryK);
                f2 = avl.ceilingKey(querryK);
                if (f1 == null && (f2 != null)) {
                    System.out.println("ceilingKey Oops");
                    System.out.println(treeMap.ceilingKey(querryK));
                    System.out.println(avl.ceilingKey(querryK));
                    break;
                }
                if (f1 != null && (f2 == null)) {
                    System.out.println("ceilingKey Oops");
                    System.out.println(treeMap.ceilingKey(querryK));
                    System.out.println(avl.ceilingKey(querryK));
                    break;
                }
                if (f1 != null) {
                    int ans1 = f1;
                    int ans2 = f2;
                    if (ans1 != ans2) {
                        System.out.println("ceilingKey Oops");
                        System.out.println(treeMap.ceilingKey(querryK));
                        System.out.println(avl.ceilingKey(querryK));
                        break;
                    }
                }

            }

            Integer f1 = treeMap.firstKey();
            Integer f2 = avl.firstKey();
            if (f1 == null && (f2 != null)) {
                System.out.println("firstKey Oops");
                System.out.println(treeMap.firstKey());
                System.out.println(avl.firstKey());
                break;
            }
            if (f1 != null && (f2 == null)) {
                System.out.println("firstKey Oops");
                System.out.println(treeMap.firstKey());
                System.out.println(avl.firstKey());
                break;
            }
            if (f1 != null) {
                int ans1 = f1;
                int ans2 = f2;
                if (ans1 != ans2) {
                    System.out.println("firstKey Oops");
                    System.out.println(treeMap.firstKey());
                    System.out.println(avl.firstKey());
                    break;
                }
            }

            f1 = treeMap.lastKey();
            f2 = avl.lastKey();
            if (f1 == null && (f2 != null)) {
                System.out.println("lastKey Oops");
                System.out.println(treeMap.lastKey());
                System.out.println(avl.lastKey());
                break;
            }
            if (f1 != null && (f2 == null)) {
                System.out.println("firstKey Oops");
                System.out.println(treeMap.lastKey());
                System.out.println(avl.lastKey());
                break;
            }
            if (f1 != null) {
                int ans1 = f1;
                int ans2 = f2;
                if (ans1 != ans2) {
                    System.out.println("lastKey Oops");
                    System.out.println(treeMap.lastKey());
                    System.out.println(avl.lastKey());
                    break;
                }
            }
            if (treeMap.size() != avl.getSize()) {
                System.out.println("size Oops");
                System.out.println(treeMap.size());
                System.out.println(avl.getSize());
                break;
            }
        }
        System.out.println("功能测试结束");
    }

    public static void performanceTest() {
        System.out.println("性能测试开始");
        TreeMap<Integer, Integer> treeMap;
        MyAvlTree<Integer, Integer> avl;
        long start;
        long end;
        int max = 1000000;
        treeMap = new TreeMap<>();
        avl = new MyAvlTree<>();
        System.out.println("顺序递增加入测试，数据规模 : " + max);
        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            treeMap.put(i, i);
        }
        end = System.currentTimeMillis();
        System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            avl.put(i, i);
        }
        end = System.currentTimeMillis();
        System.out.println("avl 运行时间 : " + (end - start) + "ms");


        System.out.println("顺序递增删除测试，数据规模 : " + max);
        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            treeMap.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            avl.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("avl 运行时间 : " + (end - start) + "ms");

        System.out.println("顺序递减加入测试，数据规模 : " + max);
        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            treeMap.put(i, i);
        }
        end = System.currentTimeMillis();
        System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            avl.put(i, i);
        }
        end = System.currentTimeMillis();
        System.out.println("avl 运行时间 : " + (end - start) + "ms");


        System.out.println("顺序递减删除测试，数据规模 : " + max);
        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            treeMap.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            avl.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("avl 运行时间 : " + (end - start) + "ms");

        System.out.println("随机加入测试，数据规模 : " + max);
        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            treeMap.put((int) (Math.random() * i), i);
        }
        end = System.currentTimeMillis();
        System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            avl.put((int) (Math.random() * i), i);
        }
        end = System.currentTimeMillis();
        System.out.println("avl 运行时间 : " + (end - start) + "ms");

        System.out.println("随机删除测试，数据规模 : " + max);
        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            treeMap.remove((int) (Math.random() * i));
        }
        end = System.currentTimeMillis();
        System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            avl.remove((int) (Math.random() * i));
        }
        end = System.currentTimeMillis();
        System.out.println("avl 运行时间 : " + (end - start) + "ms");

        System.out.println("性能测试结束");
    }

    public static void main(String[] args) {
        functionTest();
        System.out.println("======");
        performanceTest();
    }

}