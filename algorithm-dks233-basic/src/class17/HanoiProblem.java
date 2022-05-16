package class17;

/**
 * 打印n层汉诺塔从最左边移动到最右边的全部过程（递归实现）
 * 需要移动2^n-1次，O(2^n-1)
 *
 * @author dks233
 * @create 2022-05-16-8:46
 */
public class HanoiProblem {
    public static class MethodOne {
        public void hanoiProblem(int n) {
            leftToRight(n);
        }

        // 把1~n层圆盘从左边移动到右边，三大步
        // (1) 把1~n-1层圆盘从左移动到中
        // (2) 把第n层圆盘从左移动到右
        // (3) 把第n-1层圆盘从中移动到右
        public void leftToRight(int n) {
            // 如果是第一层圆盘(1号圆盘)，可以直接移动（1号圆盘始终在最上层）
            if (n == 1) {
                System.out.println("move 1 from left to right");
                return;
            }
            leftToMid(n - 1);
            System.out.println("move " + n + " from left to right");
            midToRight(n - 1);
        }

        // 把1~n层圆盘从中移到右边，三大步
        // (1) 把1~n-1层圆盘从中移动到左
        // (2) 将第n层圆盘从中移动到右
        // (3) 将1~n-1层圆盘从左移动到右
        public void midToRight(int n) {
            // 如果是第一层圆盘(1号圆盘)，可以直接移动（1号圆盘始终在最上层）
            if (n == 1) {
                System.out.println("move 1 from mid to right");
                return;
            }
            midToLeft(n - 1);
            System.out.println("move " + n + " from mid to right");
            leftToRight(n - 1);
        }

        public void leftToMid(int n) {
            if (n == 1) {
                System.out.println("move 1 from left to mid");
                return;
            }
            leftToRight(n - 1);
            System.out.println("move " + n + " from left to mid");
            rightToMid(n - 1);
        }

        public void midToLeft(int n) {
            if (n == 1) {
                System.out.println("move 1 from mid to left");
                return;
            }
            midToRight(n - 1);
            System.out.println("move " + n + " from mid to left");
            rightToLeft(n - 1);
        }

        public void rightToLeft(int n) {
            if (n == 1) {
                System.out.println("move 1 from right to left");
                return;
            }
            rightToMid(n - 1);
            System.out.println("move " + n + " from right to left");
            midToLeft(n - 1);
        }

        private void rightToMid(int n) {
            if (n == 1) {
                System.out.println("move 1 from right to mid");
                return;
            }
            rightToLeft(n - 1);
            System.out.println("move " + n + " from right to mid");
            leftToMid(n - 1);
        }
    }

    public static class MethodTwo {
        public void hanoiProblem(int n) {
            process(n, "left", "right", "mid");
        }

        /**
         * 将from位置的n个圆盘通过other位置中转移动到to位置
         *
         * @param n     圆盘层数
         * @param from  初始位置
         * @param to    目标位置
         * @param other 中转
         */
        public void process(int n, String from, String to, String other) {
            // 如果是第1层圆盘（1号圆盘），可以直接移动，因为1号圆盘始终在最上层
            if (n == 1) {
                System.out.println("move 1 from " + from + " to " + to);
                return;
            }
            // 将1~n-1层圆盘从from移动到other
            process(n - 1, from, other, to);
            // 将第n层圆盘从from移动到to
            System.out.println("move " + n + " from " + from + " to " + to);
            // 将1~n-1层圆盘从other移动到to
            process(n - 1, other, to, from);
        }
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println("方法1实现");
        MethodOne methodOne = new MethodOne();
        methodOne.hanoiProblem(n);
        System.out.println("方法2实现");
        MethodTwo methodTwo = new MethodTwo();
        methodTwo.hanoiProblem(n);
    }
}
