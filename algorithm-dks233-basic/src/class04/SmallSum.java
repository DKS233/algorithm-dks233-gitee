package class04;


/**
 * 小和问题
 * 在一个数组中，一个数左边比它小的数的总和，叫做数的小和，所有数的小和累加起来，叫数组小和
 * 给定一个数组arr，求数组小和
 * 思路：转换成求每个数右边有多少个数比它大，比如a右边有b个数比它大，小和累加a*b，依次累加，得到最终的数组小和
 *
 * @author dks233
 * @create 2022-04-02-10:10
 */
public class SmallSum {
    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        for (int i = 0; i < testTimes; i++) {
            int[] randomArr = randomArr(maxLen, maxValue);
            int[] copyArr = copyArr(randomArr);
            int smallSumOne = getSmallSumOne(randomArr);
            int comparator = comparator(copyArr);
            if (smallSumOne != comparator) {
                System.out.println("测试失败");
            }
        }
        System.out.println("测试成功");
    }

    private static int getSmallSumOne(int[] arr) {
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
        // 左组merge结果+右组merge结果+左右merge结果
        return process(arr, left, mid) + process(arr, mid + 1, right) + merge(arr, left, mid, right);
    }

    private static int merge(int[] arr, int left, int mid, int right) {
        int p1 = left;
        int p2 = mid + 1;
        int result = 0; // merge过程中的小和
        int[] help = new int[right - left + 1];
        int i = 0;
        // 左右组元素都没越界
        while (p1 <= mid && p2 <= right) {
            // 左组元素小于右组，拷贝左组元素，小和累加
            // 左组元素等于右组，拷贝右组元素，小和不累加
            // 左组元素大于右组，拷贝右组元素，小和不累加
            result += arr[p1] < arr[p2] ? (right - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 左组元素越界
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        // 右组元素越界
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[j + left] = help[j];
        }
        return result;
    }

    private static int[] randomArr(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] randomArr = new int[len];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return randomArr;
    }

    private static int[] copyArr(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] copyArr = new int[arr.length];
        System.arraycopy(arr, 0, copyArr, 0, copyArr.length);
        return copyArr;
    }

    private static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    ans += arr[i];
                }
            }
        }
        return ans;
    }
}
