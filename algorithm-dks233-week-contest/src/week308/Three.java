package week308;


/**
 * leetcode6162. 收集垃圾的最少总时间
 *
 * @author dks233
 * @create 2022-08-28-10:30
 */
public class Three {
    public int garbageCollection(String[] garbage, int[] travel) {
        int time = 0;
        // M金属 P纸 G玻璃
        // 时间=清理时间+路程时间
        // 清理时间=字符数
        // 路程时间=0到每种垃圾最远到达的位置所用的时间之和
        int[] maxDistance = new int[3];
        for (int i = 0; i < garbage.length; i++) {
            String str = garbage[i];
            if (str.contains("G")) {
                maxDistance[2] = i;
            }
            if (str.contains("P")) {
                maxDistance[1] = i;
            }
            if (str.contains("M")) {
                maxDistance[0] = i;
            }
            time += str.length();
        }
        for (int i = 0; i < maxDistance.length; i++) {
            for (int j = 0; j < maxDistance[i]; j++) {
                time += travel[j];
            }
        }
        return time;
    }

    public static void main(String[] args) {
        Three three = new Three();
        System.out.println(three.garbageCollection(new String[]{"G", "P", "GP", "GG"}, new int[]{2, 4, 3}));
    }
}
