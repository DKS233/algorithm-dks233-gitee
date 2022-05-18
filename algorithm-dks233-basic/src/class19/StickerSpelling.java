package class19;

import java.util.HashMap;

/**
 * leetcode691:贴纸拼词
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务。每个贴纸的数量是无限的
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * 所以返回2
 * 注：方法1和方法2都超时了，方法3正常测试通过
 *
 * @author dks233
 * @create 2022-05-18-15:05
 */
@SuppressWarnings("ALL")
public class StickerSpelling {
    // 纯暴力，测试超时
    public static class MethodOne {
        /**
         * 最少需要多少张贴纸
         * 每轮缺少的字符都用字符串处理：getNextRest(String initial, String minus)
         *
         * @param stickers 贴纸，每张贴纸数量无限
         * @param target   目标字符串
         * @return 最少需要多少张贴纸
         */
        public int minStickers(String[] stickers, String target) {
            if (target == null || "".equals(target)) {
                return -1;
            }
            int result = process(stickers, target);
            // result == Integer.MAX_VALUE 表示process的min没更新过，所有贴纸都无法拼接出target
            return result == Integer.MAX_VALUE ? -1 : result;
        }

        // rest:当前路径上缺少的字符，统计成一个字符串
        // stickers:贴纸
        // 返回：剩余rest字符串最少需要多少张贴纸
        // 每轮选择都是列举所有贴纸，直到target上的字符全部拼接
        public int process(String[] stickers, String rest) {
            if (rest.length() == 0) {
                return 0;
            }
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < stickers.length; i++) {
                // 选择stickers[i]贴纸后，还剩多少字符未拼接
                String nextRest = getNextRest(rest, stickers[i]);
                // stickers[i]中没有rest需要的字符，不需要进入sticker[i]分支
                if (nextRest.length() != rest.length()) {
                    // 剩下的字符最少需要多少贴纸
                    min = Math.min(min, process(stickers, nextRest));
                }
            }
            // 没有rest需要的字符，min没更新过，返回Integer.MAX_VALUE
            // min更新过，得到的贴纸数量是除去第一张选择的贴纸数量，总数量应该加1
            min += min == Integer.MAX_VALUE ? 0 : 1;
            return min;
        }

        // 求initial减掉minus后剩余的字符串
        public String getNextRest(String initial, String minus) {
            char[] initialChars = initial.toCharArray();
            char[] minusChars = minus.toCharArray();
            // 存剩余字符串中每个字符的数量
            int[] count = new int[26];
            for (int i = 0; i < initialChars.length; i++) {
                count[initialChars[i] - 'a']++;
            }
            for (int i = 0; i < minusChars.length; i++) {
                count[minusChars[i] - 'a']--;
            }
            // 将统计的字符数量拼接成一个字符串，作为剩余的字符号返回
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < count.length; i++) {
                for (int j = 0; j < count[i]; j++) {
                    builder.append((char) (i + 'a'));
                }
            }
            return String.valueOf(builder);
        }
    }

    // 优化后还是超时
    public static class MethodTwo {
        public int minStickers(String[] stickers, String target) {
            if (target == null || "".equals(target)) {
                return -1;
            }
            // 优化1：贴纸词频提前统计，用二维数组代替贴纸数组
            // 二维数组：贴纸数量*26
            int[][] counts = new int[stickers.length][26];
            for (int i = 0; i < counts.length; i++) {
                char[] chars = stickers[i].toCharArray();
                for (char c : chars) {
                    counts[i][c - 'a']++;
                }
            }
            int result = process(counts, target);
            // result == Integer.MAX_VALUE 表示process的min没更新过，所有贴纸都无法拼接出target
            return result == Integer.MAX_VALUE ? -1 : result;
        }

        // rest:当前路径上缺少的字符，统计成一个字符串
        // stickers:贴纸的词频统计数组
        // 返回：剩余rest字符串最少需要多少张贴纸
        // 每轮选择都是列举所有贴纸，直到target上的字符全部拼接
        public int process(int[][] stickers, String rest) {
            if (rest.length() == 0) {
                return 0;
            }
            // 统计rest的词频
            int[] restCount = new int[26];
            for (char c : rest.toCharArray()) {
                restCount[c - 'a']++;
            }
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < stickers.length; i++) {
                int[] sticker = stickers[i];
                // 优化2：只尝试包含target第一个字符的贴纸（剪枝）
                // 把第一个字符消除的时刻提前到来，不影响最终结果
                if (sticker[rest.toCharArray()[0] - 'a'] > 0) {
                    // 选择stickers[i]贴纸后，还剩多少字符未拼接
                    // rest和stickers[i]中对应的词频相减，然后拼接成字符串作为下一轮rest
                    StringBuilder builder = new StringBuilder();
                    for (int k = 0; k < 26; k++) {
                        if (restCount[k] > 0) {
                            int nums = restCount[k] - sticker[k];
                            for (int m = 0; m < nums; m++) {
                                builder.append((char) (k + 'a'));
                            }
                        }
                    }
                    String nextRest = builder.toString();
                    min = Math.min(min, process(stickers, nextRest));
                }
            }
            // 没有rest需要的字符，min没更新过，返回Integer.MAX_VALUE
            // min更新过，得到的贴纸数量是除去第一张选择的贴纸数量，总数量应该加1
            min += min == Integer.MAX_VALUE ? 0 : 1;
            return min;
        }
    }

    // hashMap记忆(rest,minCount)
    // 测试通过
    // 表示剩余rest字符情况下的最小贴纸数量
    public static class MethodThree {
        public int minStickers(String[] stickers, String target) {
            if (target == null || "".equals(target)) {
                return -1;
            }
            // 优化1：贴纸词频提前统计，用二维数组代替贴纸数组
            // 二维数组：贴纸数量*26
            int[][] counts = new int[stickers.length][26];
            for (int i = 0; i < counts.length; i++) {
                char[] chars = stickers[i].toCharArray();
                for (char c : chars) {
                    counts[i][c - 'a']++;
                }
            }
            // key:剩余字符（统计成了字符串）
            // value:剩余字符情况下的最小贴纸数量
            HashMap<String, Integer> map = new HashMap<>();
            map.put("", 0);
            int result = process(counts, target, map);
            // result == Integer.MAX_VALUE 表示process的min没更新过，所有贴纸都无法拼接出target
            return result == Integer.MAX_VALUE ? -1 : result;
        }

        // rest:当前路径上缺少的字符，统计成一个字符串
        // stickers:贴纸的词频统计数组
        // 返回：剩余rest字符串最少需要多少张贴纸
        // 每轮选择都是列举所有贴纸，直到target上的字符全部拼接
        public int process(int[][] stickers, String rest, HashMap<String, Integer> map) {
            if (map.containsKey(rest)) {
                return map.get(rest);
            }
            // 统计rest的词频
            int[] restCount = new int[26];
            for (char c : rest.toCharArray()) {
                restCount[c - 'a']++;
            }
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < stickers.length; i++) {
                int[] sticker = stickers[i];
                // 优化2：只尝试包含target第一个字符的贴纸（剪枝）
                // 把第一个字符消除的时刻提前到来，不影响最终结果
                if (sticker[rest.toCharArray()[0] - 'a'] > 0) {
                    // 选择stickers[i]贴纸后，还剩多少字符未拼接
                    // rest和stickers[i]中对应的词频相减，然后拼接成字符串作为下一轮rest
                    StringBuilder builder = new StringBuilder();
                    for (int k = 0; k < 26; k++) {
                        if (restCount[k] > 0) {
                            int nums = restCount[k] - sticker[k];
                            for (int m = 0; m < nums; m++) {
                                builder.append((char) (k + 'a'));
                            }
                        }
                    }
                    String nextRest = builder.toString();
                    min = Math.min(min, process(stickers, nextRest, map));
                }
            }
            // 没有rest需要的字符，min没更新过，返回Integer.MAX_VALUE
            // min更新过，得到的贴纸数量是除去第一张选择的贴纸数量，总数量应该加1
            min += min == Integer.MAX_VALUE ? 0 : 1;
            map.put(rest, min);
            return min;
        }
    }

}
