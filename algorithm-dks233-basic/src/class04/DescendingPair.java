package class04;

/**
 * 给定一个数组，求数组的降序对（逆序对）总数量
 * 降序对（逆序对）：排在前面的数，大于排在后面的数
 * 分析：方法1：看右组有多少数比左组数小
 * 分析：方法2：看左组有多少数比右组数大
 *
 * @author dks233
 * @create 2022-04-02-20:48
 */
public class DescendingPair {
    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] randomArrOne = randomArr(maxLen, maxValue);
            int[] randomArrTwo = copyArr(randomArrOne);
            int[] randomArrThree = copyArr(randomArrOne);
            int one = descendingPairOne(randomArrOne);
            int two = descendingPairTwo(randomArrTwo);
            int three = comparator(randomArrThree);
            if (!isEquals(randomArrOne, randomArrThree) || !isEquals(randomArrTwo, randomArrThree)) {
                isSuccess = false;
                System.out.println("测试失败");
                break;
            }
            if (one != three || two != three) {
                isSuccess = false;
                System.out.println("测试失败");
                break;
            }
        }
        if (isSuccess) {
            System.out.println("测试成功");
        }
    }

    private static int descendingPairOne(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return processOne(arr, 0, arr.length - 1);
    }

    private static int processOne(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return processOne(arr, left, mid) + processOne(arr, mid + 1, right) + mergeOne(arr, left, mid, right);
    }

    // 左右组都是从右往左merge，对每一个左组元素，看右组有多少个元素比它小
    // 左组数大，拷贝左组，逆序对累加
    // 左组数小，拷贝右组，逆序对不累加
    // 左组右组数相等，拷贝右组，逆序对不累加
    private static int mergeOne(int[] arr, int left, int mid, int right) {
        int p1 = mid; // 左组最后一个数
        int p2 = right; // 右组最后一个数
        int[] help = new int[right - left + 1];
        int i = help.length - 1;
        int ans = 0; // 当前merge的逆序对数量
        while (p1 >= left && p2 >= mid + 1) {
            ans += arr[p1] > arr[p2] ? p2 - mid : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        // p1越界
        while (p2 >= mid + 1) {
            help[i--] = arr[p2--];
        }
        // p2越界
        while (p1 >= left) {
            help[i--] = arr[p1--];
        }
        for (i = 0; i < help.length; i++) {
            arr[i + left] = help[i];
        }
        return ans;
    }

    private static int descendingPairTwo(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return processTwo(arr, 0, arr.length - 1);
    }

    private static int processTwo(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return processTwo(arr, left, mid) + processTwo(arr, mid + 1, right) + mergeTwo(arr, left, mid, right);
    }

    // 左右组都是从左往右merge
    // 对每一个右组元素，看左组有多少个元素比它大
    // 左组大，拷贝右组元素，逆序对累加
    // 右组大，拷贝左组元素，逆序对不累加
    // 左右组元素相等，拷贝左组元素，逆序对不累加
    private static int mergeTwo(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = help.length - 1;
        int p1 = left; // 左组第一个元素
        int p2 = mid + 1; // 右组第一个元素
        int ans = 0; // 当前merge的逆序对数量
        while (p1 <= mid && p2 <= right) {
            ans += arr[p1] > arr[p2] ? mid - p1 + 1 : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p2++] : arr[p1++];
        }
        while (p1 <= mid) {
            help[i--] = arr[p1++];
        }
        while (p2 <= right) {
            help[i--] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[i + left] = help[i];
        }
        return ans;
    }

    private static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans += 1;
                }
            }
        }
        return ans;
    }

    private static int[] copyArr(int[] arr) {
        int[] copyArr = new int[arr.length];
        System.arraycopy(arr, 0, copyArr, 0, copyArr.length);
        return copyArr;
    }

    private static int[] randomArr(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] randomArr = new int[len];
        for (int element : randomArr) {
            element = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return randomArr;
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
