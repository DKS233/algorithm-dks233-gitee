package class08;

import common.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 桶排序
 * 设置一个 BucketSize，作为每个桶所能放置多少个不同数值；
 * 遍历输入数据，并且把数据依次映射到对应的桶里去；
 * 对每个非空的桶进行排序，可以使用其它排序方法，也可以递归使用桶排序；
 * 从非空桶里把排好序的数据拼接起来
 *
 * @author dks233
 * @create 2022-08-18-23:28
 */
public class BucketSort {
    public static void bucketSort(int[] arr, int bucketSize) {
        int min = arr[0];
        int max = arr[0];
        for (int index = 0; index < arr.length; index++) {
            min = Math.min(arr[index], min);
            max = Math.max(arr[index], max);
        }
        List<List<Integer>> buckets = new ArrayList<>();
        // 计算桶的数量
        int bucketCount = (max - min + 1) / bucketSize + 1;
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
        // 将元素添加到桶中
        for (int index = 0; index < arr.length; index++) {
            int bucketIndex = (arr[index] - min) / bucketSize;
            buckets.get(bucketIndex).add(arr[index]);
        }
        // 将每个桶中元素进行排序
        for (int index = 0; index < buckets.size(); index++) {
            buckets.get(index).sort((a, b) -> a - b);
        }
        // 将排好序的数据合并
        int i = 0;
        for (int index = 0; index < buckets.size(); index++) {
            for (int j = 0; j < buckets.get(index).size(); j++) {
                arr[i++] = buckets.get(index).get(j);
            }
        }
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 10;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            // 针对非负数组-普通写法
            int[] randomArrOne = randomArr(maxLen, maxValue);
            int[] copyArrOne = ArrayUtils.copyArr(randomArrOne);
            int randomBucketSize = (int) (Math.random() * randomArrOne.length - 1) + 1;
            bucketSort(randomArrOne, randomBucketSize);
            Arrays.sort(copyArrOne);
            if (!ArrayUtils.isEquals(randomArrOne, copyArrOne)) {
                isSuccess = false;
                System.out.println("针对非负数组-普通写法测试出错");
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    public static int[] randomArr(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen)) + 1;
        int[] randomArr = new int[len];
        for (int index = 0; index < randomArr.length; index++) {
            randomArr[index] = (int) (Math.random() * (maxValue + 1));
        }
        return randomArr;
    }
}
