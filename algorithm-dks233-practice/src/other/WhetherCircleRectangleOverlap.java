package other;

/**
 * leetcode1401. 圆和矩形是否有重叠
 * 参考文档：https://leetcode.cn/problems/circle-and-rectangle-overlapping/solution/hua-fen-qu-yu-python-3-by-z1m/
 *
 * @author dks233
 * @create 2022-09-12-14:18
 */
@SuppressWarnings("ALL")
public class WhetherCircleRectangleOverlap {
    // 根据矩形，将空间分为9个区域（3区域即为矩形所在位置），看圆心在哪个区域。将9个区域分为三类。
    // 1 | 2 | 1
    // 2 | 3 | 2
    // 1 | 2 | 1
    // 假设圆心在3区域内，肯定是重合的
    // 假设圆心在2区域内，半径超过圆心到边的距离就相交
    // 假设圆心在1区域内，检测4个顶点是否在圆心内部
    // 注：(x1,y1)左下角 (x2,y2)右上角
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        if (xCenter >= x1 && xCenter <= x2 && yCenter >= y1 && yCenter <= y2) {
            return true;
        } else if (xCenter >= x1 && xCenter <= x2 && yCenter < y1) {
            return radius >= y1 - yCenter;
        } else if (xCenter >= x1 && xCenter <= x2 && yCenter > y2) {
            return radius >= yCenter - y2;
        } else if (yCenter >= y1 && yCenter <= y2 && xCenter < x1) {
            return radius >= x1 - xCenter;
        } else if (yCenter >= y1 && yCenter <= y2 && xCenter > x2) {
            return radius >= xCenter - x2;
        } else {
            int leftDown = getDistance(xCenter, yCenter, x1, y1);
            int leftUp = getDistance(xCenter, yCenter, x1, y2);
            int rightUp = getDistance(xCenter, yCenter, x2, y2);
            int rightDown = getDistance(xCenter, yCenter, x2, y1);
            if (leftDown <= radius * radius || leftUp <= radius * radius || rightUp <= radius * radius || rightDown <= radius * radius) {
                return true;
            }
        }
        return false;
    }

    public int getDistance(int x1, int y1, int x2, int y2) {
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
    }
}
