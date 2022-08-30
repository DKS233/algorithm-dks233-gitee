package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode1282. 用户分组
 *
 * @author dks233
 * @create 2022-08-30-17:47
 */
@SuppressWarnings("ALL")
public class UserGrouping {
    List<List<Integer>> list = new ArrayList<>();
    // key:组的大小 value:对应的所有数
    HashMap<Integer, List<Integer>> map = new HashMap<>();

    // 分析：填充map，然后将map中的每个value按照key划分成若干组，拼接到list中
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        for (int i = 0; i < groupSizes.length; i++) {
            map.putIfAbsent(groupSizes[i], new ArrayList<>());
            map.get(groupSizes[i]).add(i);
        }
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            Integer count = entry.getKey();
            List<Integer> countList = entry.getValue();
            List<Integer> curList = new ArrayList<>();
            for (Integer value : countList) {
                curList.add(value);
                if (curList.size() == count) {
                    list.add(new ArrayList<>(curList));
                    curList.clear();
                }
            }
        }
        return list;
    }
}
