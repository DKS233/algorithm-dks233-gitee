package other;


/**
 * leetcode29. 两数相除
 *
 * @author dks233
 * @create 2022-07-16-18:13
 */
@SuppressWarnings("ALL")
public class DivideTwoNumbers {
    // 用long类型
    public static class MethodOne {
        /**
         * @param dividend 被除数
         * @param divisor  除数
         * @return
         */
        public int divide(int dividend, int divisor) {
            // 先将被除数和除数转换为long类型
            long dividendLong = dividend;
            long divisorLong = divisor;
            // 将符号位单独提取出来，被除数和除数都转换为正数
            int flag = ((dividendLong < 0 && divisorLong > 0) || (dividendLong > 0 && divisorLong < 0)) ? -1 : 1;
            if (dividendLong < 0) {
                dividendLong = -dividendLong;
            }
            if (divisorLong < 0) {
                divisorLong = -divisorLong;
            }
            // 对于都是正数的被除数和除数，0<=dividendLong/divisorLong<=dividendLong
            // 记相除结果为z，由于是向下取整，z*divisorLong<=dividendlong<(z+1)*dividendLong
            // 用二分法在[0,dividendLong]范围内找满足上述不等式的最大z，即为所求
            long left = 0;
            long right = dividendLong;
            long result = 0;
            // 循环条件是left==right，跳出循环前left==right
            // 最后一步如果是left向右移动，说明mid符合要求，更新result
            // 如果是right向左移动，说明mid不符合要求，不更新result
            while (left <= right) {
                long mid = left + ((right - left) >> 1);
                long curAns = quickMultiply(mid, divisorLong);
                if (curAns <= dividendLong) {
                    result = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            // 判断结果是否越界
            result = flag == 1 ? result : -result;
            if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            return (int) result;
        }


        // 快速乘法，计算z*divisorLong结果
        public long quickMultiply(long z, long divisorLong) {
            long result = 0;
            while (divisorLong > 0) {
                if ((divisorLong & 1) == 1) {
                    result += z;
                }
                z = z << 1;
                divisorLong = divisorLong >> 1;
            }
            return result;
        }
    }


    // 不用long类型
    public static class MethodTwo {
        public int divide(int dividend, int divisor) {
            // 边界条件
            if (dividend == Integer.MIN_VALUE) {
                if (divisor == -1) {
                    return Integer.MAX_VALUE;
                }
                if (divisor == 1) {
                    return Integer.MIN_VALUE;
                }
            }
            if (divisor == Integer.MIN_VALUE) {
                if (dividend == Integer.MIN_VALUE) {
                    return 1;
                } else {
                    return 0;
                }
            }
            // 由于Integer.MIN_VALUE直接取正会导致越界，所以把除数和被除数转换为负数
            // 将符号位单独提取出来，将除数和被除数转换为负数
            int flag = ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) ? -1 : 1;
            if (dividend > 0) {
                dividend = -dividend;
            }
            if (divisor > 0) {
                divisor = -divisor;
            }
            // 对于都是负数的被除数和除数，设dividend/divisor=z，z>=0
            // 由于是向下取整，z*divisor>=dividend>(z+1)*dividend
            // 用二分法在[0,Integer.MAX_VALUE]范围内找满足上述不等式的最大z，即为所求
            int left = 0;
            int right = Integer.MAX_VALUE;
            int result = 0;
            // 循环条件是left==right，跳出循环前left==right
            // 最后一步如果是left向右移动，说明mid符合要求，更新result
            // 如果是right向左移动，说明mid不符合要求，不更新result
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                int curAns = quickMultiply(mid, divisor);
                // 越界返回Integer.MAX_VALUE，说明curAns太小了，mid应该右移
                // curAns>=dividend，mid右移（为了使z尽可能变大）
                // curAns<divideng，mid左移（为了使z尽可能变小）
                if (curAns >= dividend && curAns != Integer.MAX_VALUE) {
                    result = mid;
                    if (mid == Integer.MAX_VALUE) {
                        break;
                    }
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            // 判断结果是否越界
            result = flag == 1 ? result : -result;
            return result;
        }


        // 快速乘法，计算z*divisor结果
        // 返回Integer.MAX_VALUE说明越界了
        // z是正数，divisor是负数
        public int quickMultiply(int z, int divisor) {
            int result = 0;
            boolean flag = false;
            while (z > 0) {
                if ((z & 1) == 1) {
                    if (result < Integer.MIN_VALUE - divisor || flag == true) {
                        return Integer.MAX_VALUE;
                    }
                    result += divisor;
                }
                // 这里不能直接判断越界，返回Integer.MAX_VALUE，因为下一轮result不一定会加divisor
                // 越界可以设置一个状态，flag，flag为true表示越界，然后通过flag控制下一轮可不可以加z
                if (divisor < Integer.MIN_VALUE / 2) {
                    flag = true;
                } else {
                    divisor = divisor << 1;
                }
                z = z >> 1;
            }
            return result;
        }
    }
}
