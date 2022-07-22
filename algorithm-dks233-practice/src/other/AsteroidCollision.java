package other;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * 剑指offer专项突击版：剑指 Offer II 037. 小行星碰撞
 *
 * @author dks233
 * @create 2022-07-21-19:59
 */
public class AsteroidCollision {
    // 设当前遍历到的元素是cur
    // 行星发生碰撞的条件：cur为负数（即向左移动），栈顶为正数（向右移动）
    public int[] asteroidCollision(int[] nums) {
        // 栈中存储未爆炸的行星
        Deque<Integer> stack = new ArrayDeque<>();
        for (int cur : nums) {
            // 当cur为负数且栈顶为正数的时候发生碰撞
            // 情况1：stack.peek()>-cur cur发生爆炸
            // 情况2：stack.peek()<-cur stack.peek()发生爆炸
            // 情况3：stack.peek()=cur cur和stack.peek()都发生爆炸
            boolean alive = true; // 当前行星是否还活着
            while (alive && !stack.isEmpty() && stack.peek() > 0 && cur < 0) {
                if (stack.peek() > -cur) {
                    alive = false;
                } else if (stack.peek() < -cur) {
                    stack.pop();
                } else {
                    alive = false;
                    stack.pop();
                }
            }
            // 只要当前行星未发生爆炸，就把当前行星加入到栈中
            if (alive) {
                stack.push(cur);
            }
        }
        // 栈中最后存在的元素就是剩余的元素
        int[] result = new int[stack.size()];
        for (int index = 0; index < result.length; index++) {
            result[index] = stack.pollLast();
        }
        return result;
    }
}
