package other;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * leetcode341. 扁平化嵌套列表迭代器
 *
 * @author dks233
 * @create 2022-08-26-23:10
 */
@SuppressWarnings("ALL")
public class NestedIterator implements Iterator<Integer> {
    List<Integer> list;
    int index;

    public NestedIterator(List<NestedInteger> nestedList) {
        index = 0;
        list = new ArrayList<>();
        process(nestedList);
    }

    public void process(List<NestedInteger> nestedList) {
        for (NestedInteger cur : nestedList) {
            if (cur.isInteger()) {
                list.add(cur.getInteger());
            } else {
                process(cur.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return list.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }
}

@SuppressWarnings("ALL")
interface NestedInteger {
    public boolean isInteger();

    public Integer getInteger();

    public List<NestedInteger> getList();
}

