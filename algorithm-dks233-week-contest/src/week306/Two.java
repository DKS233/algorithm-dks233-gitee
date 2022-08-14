package week306;


/**
 * 6149. 边积分最高的节点
 *
 * @author dks233
 * @create 2022-08-14-10:29
 */
public class Two {
    public int edgeScore(int[] edges) {
        long[] count = new long[edges.length];
        for (int index = 0; index < edges.length; index++) {
            count[edges[index]] += index;
        }
        long maxScore = 0;
        for (int index = 0; index < count.length; index++) {
            maxScore = Math.max(maxScore, count[index]);
        }
        for (int index = 0; index < count.length; index++) {
            if (maxScore == count[index]) {
                return index;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Two two = new Two();
        System.out.println(two.edgeScore(new int[]{1, 0, 0, 0, 0, 7, 7, 5}));
        System.out.println(two.edgeScore(new int[]{2, 0, 0, 2}));
    }
}
