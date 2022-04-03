package class04;


/**
 * 在一个数组中，对于任何一个数num，求有多少个后面的数*2依然<num，返回总个数
 * 分析：左组右组都是从左往右merge，看左组元素是否比右边元素乘2还大
 * 如果大，右组元素右移，直到左组元素小于或等于右边元素乘2，然后累计右边元素的个数
 * 然后左组元素右移...
 *
 * @author dks233
 * @create 2022-04-03-10:13
 */
public class BiggerThanDouble {

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] randomArr = randomArr(maxLen, maxValue);
            int[] copyArr = copyArr(randomArr);
            int count = biggerThanDoubleCount(randomArr);
            int comparator = comparator(copyArr);
            if (comparator != count) {
                isSuccess = false;
                System.out.println("测试失败");
                break;
            }
        }
        if (isSuccess) {
            System.out.println("测试成功");
        }
    }

    private static int biggerThanDoubleCount(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return process(arr, left, mid) + process(arr, mid + 1, right) + merge(arr, left, mid, right);
    }

    private static int merge(int[] arr, int left, int mid, int right) {
        // 先计算该组merge中左组数大于右组数两倍的数量
        int result = 0;
        int p1 = left;
        int p2 = mid + 1;
        for (; p1 <= mid; p1++) {
            while (p2 <= right && arr[p1] > (arr[p2] << 1)) {
                p2++;
            }
            result += p2 - mid - 1;
        }
        // 然后merge
        p1 = left;
        p2 = mid + 1;
        int i = 0;
        int[] help = new int[right - left + 1];
        while (p1 <= mid && p2 <= right) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }
        return result;
    }

    private static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    result += 1;
                }
            }
        }
        return result;
    }

    private static int[] randomArr(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] randomArr = new int[len];
        for (int element : randomArr) {
            element = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return randomArr;
    }

    private static int[] copyArr(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] copyArr = new int[arr.length];
        System.arraycopy(arr, 0, copyArr, 0, arr.length);
        return copyArr;
    }

    private static boolean isEquals(int[] randomArr, int[] copyArr) {
        if (randomArr != null && copyArr == null || randomArr == null && copyArr != null) {
            return false;
        }
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
}
