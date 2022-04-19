package common;

/**
 * @author dks233
 * @create 2022-04-08-9:56
 */
public class ArrayUtils {
    /**
     * 生成随机数组
     *
     * @param maxLen   数组最大长度
     * @param maxValue 数组最大值
     * @return 随机数组
     */
    public static int[] randomArr(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] randomArr = new int[len];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return randomArr;
    }

    /**
     * 生成随机数组(非负)
     *
     * @param maxLen   数组最大长度
     * @param maxValue 数组最大值
     * @return 随机数组
     */
    public static int[] randomArrNoNegative(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] randomArr = new int[len];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return randomArr;
    }

    /**
     * 判断两个数组是否相等
     *
     * @param randomArr 排好序的随机数组
     * @param copyArr   排好序的复制数组
     * @return 排好序的两个数组是否相等
     */
    public static boolean isEquals(int[] randomArr, int[] copyArr) {
        if (randomArr == null && copyArr != null || randomArr != null && copyArr == null) {
            return false;
        }
        // randomArr == null && copyArr == null
        if (randomArr == null) {
            return false;
        }
        if (randomArr.length != copyArr.length) {
            return false;
        }
        for (int i = 0; i < randomArr.length; i++) {
            if (randomArr[i] != copyArr[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 复制随机数组
     *
     * @param randomArr 随机数组
     * @return 复制后的数组
     */
    public static int[] copyArr(int[] randomArr) {
        if (randomArr == null) {
            return null;
        }
        int[] copyArr = new int[randomArr.length];
        System.arraycopy(randomArr, 0, copyArr, 0, copyArr.length);
        return copyArr;
    }

    /**
     * 交换数组中的两个值
     *
     * @param arr 数组
     * @param a   位置1
     * @param b   位置2
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * 返回随机数
     *
     * @param maxValue 最大值范围
     * @return [-maxValue,+maxValue]
     */
    public static int randomNumber(int maxValue) {
        return (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
    }


}
