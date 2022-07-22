package other;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指offer专项突击版：剑指 Offer II 035. 最小时间差
 *
 * @author dks233
 * @create 2022-07-21-15:52
 */
@SuppressWarnings("ALL")
public class MinimumTimeDifference {
    // 计算所有时间，排序
    public int findMinDifference(List<String> timePoints) {
        int[] minutes = new int[timePoints.size()];
        for (int index = 0; index < timePoints.size(); index++) {
            String str = timePoints.get(index);
            minutes[index] += 60 * Integer.parseInt(str.substring(0, str.indexOf(":")));
            minutes[index] += Integer.parseInt(str.substring(str.indexOf(":") + 1, str.length()));
        }
        // 重点优化：超过24*60种时间，肯定有重复的，直接返回0
        if (minutes.length > 24 * 60) {
            return 0;
        }
        Arrays.sort(minutes);
        int minGap = Integer.MAX_VALUE;
        // 计算非首尾元素最短时间差
        for (int index = 0; index < minutes.length - 1; index++) {
            minGap = Math.min(minGap, Math.abs(minutes[index] - minutes[index + 1]));
        }
        // 计算首尾时间差
        minGap = Math.min(minutes[0] + 24 * 60 - minutes[minutes.length - 1], minGap);
        return minGap;
    }
}
