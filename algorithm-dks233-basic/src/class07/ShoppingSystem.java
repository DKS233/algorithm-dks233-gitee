package class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 购物获奖系统
 * 注：候选区和得奖区，详细规则见OneNote
 *
 * @author dks233
 * @create 2022-04-09-21:20
 */
public class ShoppingSystem {

    /**
     * 用户提供每个时间点的用户id和购买记录，以及获奖区的最大人数
     *
     * @param ids          购买者或者退款者的id数组
     * @param buyOrRefunds 购买记录/退款记录数组
     * @param k            得奖区最多有几个用户
     * @return 每个时间点得奖区的用户id数组
     */
    public static ArrayList<List<Integer>> shoppingSystem(int[] ids, boolean[] buyOrRefunds, int k) {
        MyShoppingSystem myShoppingSystem = new MyShoppingSystem(k);
        ArrayList<List<Integer>> idLists = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            int id = ids[i];
            boolean buyOrRefund = buyOrRefunds[i];
            myShoppingSystem.operate(id, buyOrRefund, i);
            ArrayList<Integer> idList = myShoppingSystem.getAwardHeap();
            idLists.add(idList);
        }
        return idLists;
    }

    public static class MyShoppingSystem {
        private HashMap<Integer, Customer> customerMap; // 当前系统中的用户，即得奖区和候选区的用户
        private ReinforcedMinHeap<Customer> candidateHeap; // 候选区用户
        private ReinforcedMinHeap<Customer> awardHeap; // 得奖区用户
        private int limit; // 得奖区用户数量上限

        public MyShoppingSystem(int limit) {
            this.customerMap = new HashMap<>();
            this.candidateHeap = new ReinforcedMinHeap<>(new CandidateAreaComparator());
            this.awardHeap = new ReinforcedMinHeap<>(new AwardAreaComparator());
            this.limit = limit;
        }

        public void operate(int id, boolean buyOrRefund, int time) {
            // 四种情况
            // 情况1：购买数为0，发生退款
            // 情况2：购买数为0，发生购买
            // 情况3：购买数大于0，发生退款
            // 情况4：购买数大于0，发生购买
            // 情况1：当前系统中不存在该用户信息，且发生退货
            if (!customerMap.containsKey(id) && !buyOrRefund) {
                return;
            }
            // 情况2：购买数为0，发生购买
            if (!customerMap.containsKey(id) && buyOrRefund) {
                Customer customer = new Customer(id, 1, time);
                if (awardHeap.size() < limit) {
                    awardHeap.push(customer);
                } else {
                    candidateHeap.push(customer);
                }
            }
            // 情况34：购买数大于0，发生退款或购买
            Customer customer = customerMap.get(id);
            if (buyOrRefund) {
                customer.buy++;
            } else {
                customer.buy--;
            }
            // 发生退款后如果购买数为0，需要将该用户踢出系统
            if (customer.buy == 0) {
                customerMap.remove(id);
                if (awardHeap.contains(customer)) {
                    awardHeap.remove(customer);
                } else {
                    candidateHeap.remove(customer);
                }
                exchange(time);
            }
            // 发生购买或退款后购买数大于0，进行交换操作
            else {
                exchange(time);
            }
        }

        private void exchange(int time) {
            // 候选区为空，说明退款发生在候选区
            if (candidateHeap.isEmpty()) {
                return;
            }
            // 候选区不为空，得奖区不满，说明退款发生在得奖区
            // 将候选区中的0位置元素放到得奖区
            if (awardHeap.size() < limit) {
                Customer pop = candidateHeap.pop();
                pop.endTime = time;
                awardHeap.push(pop);
                return;
            }
            // 候选区不为空，得奖区满了，用户之前在候选区
            if (awardHeap.size() == limit) {
                if (candidateHeap.peek().buy > awardHeap.peek().buy) {
                    Customer popForCandidate = candidateHeap.pop();
                    Customer popForAward = awardHeap.pop();
                    popForCandidate.endTime = time;
                    popForAward.endTime = time;
                    awardHeap.push(popForCandidate);
                    candidateHeap.push(popForAward);
                }
            }
        }

        // 返回当前得奖区的用户id列表
        public ArrayList<Integer> getAwardHeap() {
            ArrayList<Integer> idList = new ArrayList<>();
            ArrayList<Customer> customerList = awardHeap.getAllElements();
            for (Customer customer : customerList) {
                idList.add(customer.id);
            }
            return idList;
        }
    }

    /**
     * 用户提供每个时间点的用户id和购买记录，以及获奖区的最大人数
     *
     * @param ids          购买者或者退款者的id数组
     * @param buyOrRefunds 购买记录/退款记录数组
     * @param k            得奖区最多有几个用户
     * @return 每个时间点得奖区的用户id数组
     */
    public static ArrayList<List<Integer>> comparator(int[] ids, boolean[] buyOrRefunds, int k) {
        HashMap<Integer, Customer> customerMap = new HashMap<>(); // 当前系统的用户，即得奖区和候选区的用户
        ArrayList<Customer> candidateArea = new ArrayList<>(); // 候选区的用户列表
        ArrayList<Customer> awardArea = new ArrayList<>(); // 得奖区的用户列表
        ArrayList<List<Integer>> awardAreaIds = new ArrayList<>(); // 每个时刻得奖区用户id列表
        for (int i = 0; i < ids.length; i++) {
            // 当前时间点用户有有四种情况
            // 情况1：购买数为0，发生退款
            // 情况2：购买数为0，发生购买
            // 情况3：购买数大于0，发生退款
            // 情况4：购买数大于0，发生购买
            // 情况1：将当前得奖区用户直接添加到列表中
            if (!customerMap.containsKey(ids[i]) && !buyOrRefunds[i]) {
                awardAreaIds.add(getCurrentAwardList(awardArea));
                continue;
            }
            // 情况2：购买数为0，发生购买
            if (!customerMap.containsKey(ids[i]) && buyOrRefunds[i]) {
                Customer customer = new Customer(ids[i], 1, i);
                customerMap.put(ids[i], customer);
                if (awardArea.size() < k) {
                    awardArea.add(customer);
                } else {
                    candidateArea.add(customer);
                }
                awardAreaIds.add(getCurrentAwardList(awardArea));
                continue;
            }
            // 情况3和4，当前用户购买数大于0
            Customer customer = customerMap.get(ids[i]);
            if (buyOrRefunds[i]) {
                customer.buy++;
            } else {
                customer.buy--;
            }
            // 发生退款后购买数变成0，将该用户从系统中剔除
            if (customer.buy == 0) {
                customerMap.remove(ids[i]);
                // 现在元素可能位于得奖区或者候选区
                // 如果位于得奖区，删掉元素后得奖区和候选区需要进行元素交换
                // 如果位于候选区，直接删除，得奖区和候选区元素不需要交换
                clearZeroBuyCustomer(candidateArea);
                clearZeroBuyCustomer(awardArea);
                awardArea.sort(new AwardAreaComparator());
                candidateArea.sort(new CandidateAreaComparator());
                exchange(awardArea, candidateArea, i, k);
                awardAreaIds.add(getCurrentAwardList(awardArea));
            } else {
                // 到这儿购买或退款后购买数大于0
                // 每轮将候选区和得奖区用户排序
                // 候选区中谁先进得奖区谁放前面，得奖区谁先进候选区谁放前面
                candidateArea.sort(new CandidateAreaComparator());
                awardArea.sort(new AwardAreaComparator());
                exchange(awardArea, candidateArea, i, k);
                awardAreaIds.add(getCurrentAwardList(awardArea));
            }
        }

        return awardAreaIds;
    }

    private static void exchange(ArrayList<Customer> awardArea,
                                 ArrayList<Customer> candidateArea, int i, int k) {
        // 候选区没有元素
        if (candidateArea.size() == 0) {
            return;
        }
        // 候选区有元素且得奖区有用户被删
        if (awardArea.size() < k) {
            Customer customer = candidateArea.get(0);
            customer.endTime = i;
            awardArea.add(customer);
            candidateArea.remove(0);
            return;
        }
        // 候选区有元素，得奖区满了
        // 候选区第一个元素购买数大于得奖区第一个元素购买数，进入得奖区
        if (candidateArea.get(0).buy > awardArea.get(0).buy) {
            // 得奖区第一个元素放到候选区
            Customer awardCustomer = awardArea.get(0);
            awardCustomer.endTime = i;
            candidateArea.add(awardCustomer);
            awardArea.remove(0);
            // 候选区第一个元素放到得奖区
            Customer candidateCustomer = candidateArea.get(0);
            candidateCustomer.endTime = i;
            awardArea.add(candidateCustomer);
            candidateArea.remove(0);
        }
    }

    public static class CandidateAreaComparator implements Comparator<Customer> {
        // 候选区，谁最先进得奖区，谁排前面
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? o2.buy - o1.buy : o1.endTime - o2.endTime;
        }
    }

    public static class AwardAreaComparator implements Comparator<Customer> {
        // 得奖区，谁最先掉进候选区，谁排前面
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? o1.buy - o2.buy : o1.endTime - o2.endTime;
        }
    }

    public static void clearZeroBuyCustomer(ArrayList<Customer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).buy == 0) {
                list.remove(i);
            }
        }
    }

    public static ArrayList<Integer> getCurrentAwardList(ArrayList<Customer> awardArea) {
        ArrayList<Integer> currentAwardList = new ArrayList<>();
        for (Customer customer : awardArea) {
            currentAwardList.add(customer.id);
        }
        return currentAwardList;
    }

    public static class Customer {
        public int id;
        public int buy;
        // 进去等待区或者获奖区的时间点
        public int endTime;

        public Customer(int id, int buy, int endTime) {
            this.id = id;
            this.buy = buy;
            this.endTime = endTime;
        }
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int len = 1000;
        int limit = 10;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] ids = generateIds(len);
            boolean[] buys = generateBuys(len);
            ArrayList<List<Integer>> listOne = shoppingSystem(ids, buys, limit);
            ArrayList<List<Integer>> listTwo = comparator(ids, buys, limit);
            if (!isEquals(listOne, listTwo)) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "自己的测试成功" : "自己的测试失败");
        boolean comparator = true;
        for (int i = 0; i < testTimes; i++) {
            int[] ids = generateIds(len);
            boolean[] buys = generateBuys(len);
            List<List<Integer>> zuoShoppingSystem = ZuoShoppingSystem.compare(ids, buys, limit);
            ArrayList<List<Integer>> myShoppingSystem = shoppingSystem(ids, buys, limit);
            if (!isEquals(zuoShoppingSystem, myShoppingSystem)) {
                comparator = false;
                break;
            }
        }
        System.out.println(comparator ? "对比测试成功" : "对比测试失败");
    }

    // 30个用户
    public static int[] generateIds(int len) {
        int[] ids = new int[len];
        for (int id : ids) {
            id = (int) (Math.random() * 30) + 1;
        }
        return ids;
    }

    public static boolean[] generateBuys(int len) {
        boolean[] buys = new boolean[len];
        for (boolean buy : buys) {
            buy = Math.random() < 0.5;
        }
        return buys;
    }

    public static boolean isEquals(List<List<Integer>> listOne, ArrayList<List<Integer>> listTwo) {
        if (listOne.size() != listTwo.size()) {
            return false;
        }
        for (int i = 0; i < listOne.size(); i++) {
            List<Integer> one = listOne.get(i);
            List<Integer> two = listTwo.get(i);
            one.sort((o1, o2) -> o1 - o2);
            two.sort((o1, o2) -> o1 - o2);
            for (int j = 0; j < one.size(); j++) {
                if (!one.get(i).equals(two.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }
}
