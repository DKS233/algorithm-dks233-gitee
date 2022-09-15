package other;

/**
 * leetcode319. 灯泡开关
 * 参考文档：https://leetcode.cn/problems/bulb-switcher/solution/gong-shui-san-xie-jing-dian-shu-lun-tui-upnnb/
 * leetcode672. 灯泡开关II
 * 参考文档：https://leetcode.cn/problems/bulb-switcher-ii/solution/by-ac_oier-3ttx/
 *
 * @author dks233
 * @create 2022-09-15-8:11
 */
@SuppressWarnings("ALL")
public class BulbSwitch {
    public static class MethodOne {
        public int bulbSwitch(int n) {
            return (int) (Math.sqrt(n));
        }
    }

    public static class ProblemTwo {
        public int flipLights(int n, int presses) {
            if (presses == 0) {
                return 1;
            }
            if (n == 1) {
                return 2;
            } else if (n == 2) {
                return presses == 1 ? 3 : 4;
            } else {
                return presses == 1 ? 4 : presses == 2 ? 7 : 8;
            }
        }
    }
}
