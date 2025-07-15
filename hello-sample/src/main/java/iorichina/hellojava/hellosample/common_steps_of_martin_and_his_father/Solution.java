package iorichina.hellojava.hellosample.common_steps_of_martin_and_his_father;

import java.util.Scanner;

public class Solution {
    /// 1 ≤ fatherPos ≤ 10⁵
    /// 0 ≤ martinPos ≤ fatherPos
    /// 1 ≤ velFather ≤ 10⁴
    /// 1 ≤ steps ≤ 10⁴
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int fatherPos = scanner.nextInt();  // 父亲初始位置 X1
//        int martinPos = scanner.nextInt();  // 马丁初始位置 X2
//        int velFather = scanner.nextInt();  // 父亲速度 V1
//        int steps = scanner.nextInt();  // 父亲步数 N

        {
            // 测试案例1：父亲和马丁起点相同，速度不同
            int fatherPos = 0;
            int martinPos = 0;
            int velFather = 2;
            int steps = 5;

            int[] result = commonFootsteps(fatherPos, martinPos, velFather, steps);

            System.out.println(result[0] + " " + result[1]);
        }
        {
// 测试案例2：马丁起点比父亲靠后，速度较快
            int fatherPos = 5;
            int martinPos = 2;
            int velFather = 1;
            int steps = 10;

            int[] result = commonFootsteps(fatherPos, martinPos, velFather, steps);

            System.out.println(result[0] + " " + result[1]);
        }
        // 边界1：所有参数最小
        {
            int fatherPos = 1;
            int martinPos = 0;
            int velFather = 1;
            int steps = 1;
            int[] result = commonFootsteps(fatherPos, martinPos, velFather, steps);
            //1 1
            System.out.println(result[0] + " " + result[1]);
        }
        // 边界2：所有参数最大
        {
            int fatherPos = 100_000;
            int martinPos = 100_000;
            int velFather = 10_000;
            int steps = 10_000;
            int[] result = commonFootsteps(fatherPos, martinPos, velFather, steps);
            //10000 10000
            System.out.println(result[0] + " " + result[1]);
        }
        // 边界3：martinPos = fatherPos
        {
            int fatherPos = 50_000;
            int martinPos = 50_000;
            int velFather = 5_000;
            int steps = 100;
            int[] result = commonFootsteps(fatherPos, martinPos, velFather, steps);
            //100 5000
            System.out.println(result[0] + " " + result[1]);
        }
        // 边界4：martinPos = 0, fatherPos > 0
        {
            int fatherPos = 10;
            int martinPos = 0;
            int velFather = 2;
            int steps = 5;
            int[] result = commonFootsteps(fatherPos, martinPos, velFather, steps);
            //5 4
            System.out.println(result[0] + " " + result[1]);
        }
        // 边界5：velFather = 1
        {
            int fatherPos = 10;
            int martinPos = 5;
            int velFather = 1;
            int steps = 10;
            int[] result = commonFootsteps(fatherPos, martinPos, velFather, steps);
            //10 1
            System.out.println(result[0] + " " + result[1]);
        }
        // 边界6：velFather = 10_000
        {
            int fatherPos = 10_000;
            int martinPos = 9_999;
            int velFather = 10_000;
            int steps = 2;
            int[] result = commonFootsteps(fatherPos, martinPos, velFather, steps);
            //2 9999
            System.out.println(result[0] + " " + result[1]);
        }
        // 边界7：steps = 1
        {
            int fatherPos = 100;
            int martinPos = 50;
            int velFather = 10;
            int steps = 1;
            int[] result = commonFootsteps(fatherPos, martinPos, velFather, steps);
            //1 10
            System.out.println(result[0] + " " + result[1]);
        }
        // 边界8：steps = 10_000
        {
            int fatherPos = 10_000;
            int martinPos = 5_000;
            int velFather = 2;
            int steps = 10_000;
            int[] result = commonFootsteps(fatherPos, martinPos, velFather, steps);
            //10000 2
            System.out.println(result[0] + " " + result[1]);
        }
        // 边界9：martinPos = fatherPos - 1
        {
            int fatherPos = 1_000;
            int martinPos = 999;
            int velFather = 100;
            int steps = 10;
            int[] result = commonFootsteps(fatherPos, martinPos, velFather, steps);
            //10 37
            System.out.println(result[0] + " " + result[1]);
        }
        // 边界10：martinPos = 0, fatherPos = 1
        {
            int fatherPos = 1;
            int martinPos = 0;
            int velFather = 1;
            int steps = 10;
            int[] result = commonFootsteps(fatherPos, martinPos, velFather, steps);
            //10 1
            System.out.println(result[0] + " " + result[1]);
        }
    }

    /// 1 ≤ fatherPos ≤ 10⁵
    /// 0 ≤ martinPos ≤ fatherPos
    /// 1 ≤ velFather ≤ 10⁴
    /// 1 ≤ steps ≤ 10⁴
    public static int[] commonFootsteps(int fatherPos, int martinPos, int velFather, int steps) {
        int[] answer = new int[2];
        // Write your code here
        if (fatherPos == martinPos) {
            // if they start at the same position, all steps are counted
            answer[0] = steps;
            answer[1] = velFather; // martin can run as fast as father
            return answer;
        }
        int maxVelMartin = 0;
        int maxSameSteps = 0;
        final int maxDistance = velFather * steps - (martinPos - fatherPos);
        //speed of martin, from 1 meter/step, to maxDistance/step
        for (int velMartin = 1; velMartin <= maxDistance; velMartin++) {
            int sameSteps = 0; // if they start at the same position, all steps are counted
            int fatherMaxPos = fatherPos + velFather * steps;
            int fatherCurrentPos = fatherPos;
            int martinCurrentPos = martinPos;

            // simulate the steps
//            for (int step = 0; step < steps; step++) {
            int step = 0;
            while (martinCurrentPos <= fatherMaxPos) {
                step++;
                if (step < steps) {
                    fatherCurrentPos += velFather;
                }
                martinCurrentPos += velMartin;
                // if martin is behind his father firstPos(include), do not count
                if (martinCurrentPos <= fatherPos) {
                    continue;
                }
                if (martinCurrentPos > fatherMaxPos) {
                    break;
                }
                // if pos the same, count
                if (martinCurrentPos == fatherCurrentPos || martinCurrentPos == fatherMaxPos) {
                    sameSteps++;
                    continue;
                }
                // if martin is behind his father, check his pos with father's pos
                if (martinCurrentPos < fatherCurrentPos) {
                    //if step is the same, count
                    if ((martinCurrentPos - fatherPos) % velFather == 0) {
                        sameSteps++;
                    }
                    continue;
                }
                //if step is the same, count
                if ((martinCurrentPos - fatherPos) % velFather == 0) {
                    sameSteps++;
                }
            }

            if (sameSteps >= maxSameSteps) {
                maxVelMartin = Math.max(maxVelMartin, velMartin);
                maxSameSteps = sameSteps;
            }
        }

        answer[0] = maxSameSteps;
        answer[1] = maxVelMartin;
        return answer;
    }

    public static void main1(String[] args) {
        Scanner in = new Scanner(System.in);
        // input for fatherPos
        int fatherPos = in.nextInt();

        // input for martinPos
        int martinPos = in.nextInt();

        // input for velFather
        int velFather = in.nextInt();

        // input for steps
        int steps = in.nextInt();


        int[] result = commonFootsteps(fatherPos, martinPos, velFather, steps);
        for (int idx = 0; idx < result.length - 1; idx++) {
            System.out.print(result[idx] + " ");
        }
        System.out.print(result[result.length - 1]);
    }
}