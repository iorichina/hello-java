package iorichina.hellojava.hellosample.circle_intersection_area;

import iorichina.hellojava.hellosample.org.springframework.util.Assert;

public class CircleIntersectionArea {
    public static void main(String[] args) {

        double x1 = 0, y1 = 0, r1 = 5;
        double x2 = 3, y2 = 4, r2 = 5;

        double area = calculateArea(x1, y1, r1, x2, y2, r2);
        System.out.printf("The area of intersection is: %.6f%n", area);

        // 测试用例
        double x3 = 0, y3 = 0, r3 = 1;
        double x4 = 0, y4 = 0, r4 = 2;
        double area2 = calculateArea(x3, y3, r3, x4, y4, r4);
        System.out.printf("The area of intersection for circles at the same center is: %.6f%n", area2);

        aaa aaa = new CircleIntersectionArea.aaa();
        CircleIntersectionArea.bbb b = new CircleIntersectionArea().new bbb();
    }

    /**
     * 计算两个圆的相交面积
     *
     * @param x1 圆1圆心x坐标
     * @param y1 圆1圆心y坐标
     * @param r1 圆1半径
     * @param x2 圆2圆心x坐标
     * @param y2 圆2圆心y坐标
     * @param r2 圆2半径
     * @return 相交面积
     */
    public static double calculateArea(double x1, double y1, double r1,
                                       double x2, double y2, double r2) {
        // 计算圆心距离
        double dx = x2 - x1;
        double dy = y2 - y1;
        double d = Math.sqrt(dx * dx + dy * dy);

        // 无重叠
        if (d >= r1 + r2) {
            return 0;
        }
        // 完全包含
        if (d <= Math.abs(r1 - r2)) {
            double r = Math.min(r1, r2);
            return Math.PI * r * r;
        }

        double intersectionArea;
        {
            //三角形周长 -- 两个圆边交叉点+两个圆心，组成1个四边形，根据两个圆心直线将四边形拆成2个三角形
            double tranglePerimeter = r1 + r2 + d;
            // 半周长
            double s = tranglePerimeter / 2;

            //三角形面积 -- 海伦公式
            double trangleArea = Math.sqrt(s * (s - r1) * (s - r2) * (s - d));

            // 三角形角度 -- 通过余弦定理 计算跟圆心关联的角度，即扇形角度的一半
            // cos(∠C) = (a^2 + b^2 - c^2) / (2ab)
            // ∠C = acos((a^2 + b^2 - c^2) / (2ab))
            double angle1 = Math.acos((r1 * r1 + d * d - r2 * r2) / (2 * r1 * d));
            double angle2 = Math.acos((r2 * r2 + d * d - r1 * r1) / (2 * r2 * d));

            // 角度 ： 弧度
            // 0° = 0 rad
            // 90° = π/2 rad
            // 180° = π rad
            // 360° = 2π rad
            // 最大扇形（圆形面积）= π * r²
            // 扇形面积 = 最大扇形（圆形面积）* ∠C/ 360° = (π * r²) * angle / (2 * π) = r² * angle/2 = r² * (angleX * 2) / 2
            double sectorArea1 = r1 * r1 * (angle1 * 2) / 2;
            double sectorArea2 = r2 * r2 * (angle2 * 2) / 2;

//        相交面积=r1扇形面积+r2扇形面积-2 * 三角形面积
            intersectionArea = sectorArea1 + sectorArea2 - 2 * trangleArea;
        }

        // 相交时的面积公式
        double a1 = Math.acos((r1 * r1 + d * d - r2 * r2) / (2 * r1 * d)) * r1 * r1;
        double a2 = Math.acos((r2 * r2 + d * d - r1 * r1) / (2 * r2 * d)) * r2 * r2;
        double area = a1 + a2 - 0.5 * Math.sqrt((-d + r1 + r2) * (d + r1 - r2) *
                (d - r1 + r2) * (d + r1 + r2));


        Assert.isTrue(Math.abs(area - intersectionArea) < 1e-10, String.format("Calculated area:%.10f does not match the expected intersection area:%.10f", intersectionArea, area));
        return area;
    }

    public static class aaa {
        public static void main(String[] args) {
            System.out.println("Hello, aaa!");
        }
    }

    public class bbb {
        public void printMessage() {
            System.out.println("Hello, bbb!");
        }
    }

}