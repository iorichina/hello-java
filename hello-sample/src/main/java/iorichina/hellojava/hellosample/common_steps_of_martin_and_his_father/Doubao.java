package iorichina.hellojava.hellosample.common_steps_of_martin_and_his_father;

import iorichina.hellojava.hellosample.org.springframework.util.Assert;

public class Doubao {
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

            Result result = getResult(fatherPos, martinPos, velFather, steps);

            System.out.println(result.maxCommonSteps() + " " + result.maxVelocity());
            Assert.isTrue(5 == result.maxCommonSteps(), "Expected 5 common steps, got " + result.maxCommonSteps());
            Assert.isTrue(2 == result.maxVelocity(), "Expected 2 velocity, got " + result.maxVelocity());
        }
        {
            // 测试案例2：马丁起点比父亲靠后，速度较快
            int fatherPos = 5;
            int martinPos = 2;
            int velFather = 1;
            int steps = 10;

            Result result = getResult(fatherPos, martinPos, velFather, steps);

            System.out.println(result.maxCommonSteps() + " " + result.maxVelocity());
            Assert.isTrue(10 == result.maxCommonSteps(), "Expected 10 common steps, got " + result.maxCommonSteps());
            Assert.isTrue(1 == result.maxVelocity(), "Expected 1 velocity, got " + result.maxVelocity());
        }
        // 边界1：所有参数最小
        {
            int fatherPos = 1;
            int martinPos = 0;
            int velFather = 1;
            int steps = 1;
            Result result = getResult(fatherPos, martinPos, velFather, steps);
            //1 1
            System.out.println(result.maxCommonSteps() + " " + result.maxVelocity());
        }
        // 边界2：所有参数最大
        {
            int fatherPos = 100_000;
            int martinPos = 100_000;
            int velFather = 10_000;
            int steps = 10_000;
            Result result = getResult(fatherPos, martinPos, velFather, steps);
            //10000 10000
            System.out.println(result.maxCommonSteps() + " " + result.maxVelocity());
        }
        // 边界3：martinPos = fatherPos
        {
            int fatherPos = 50_000;
            int martinPos = 50_000;
            int velFather = 5_000;
            int steps = 100;
            Result result = getResult(fatherPos, martinPos, velFather, steps);
            //100 5000
            System.out.println(result.maxCommonSteps() + " " + result.maxVelocity());
        }
        // 边界4：martinPos = 0, fatherPos > 0
        {
            int fatherPos = 10;
            int martinPos = 0;
            int velFather = 2;
            int steps = 5;
            Result result = getResult(fatherPos, martinPos, velFather, steps);
            //5 4
            System.out.println(result.maxCommonSteps() + " " + result.maxVelocity());
        }
        // 边界5：velFather = 1
        {
            int fatherPos = 10;
            int martinPos = 5;
            int velFather = 1;
            int steps = 10;
            Result result = getResult(fatherPos, martinPos, velFather, steps);
            //10 1
            System.out.println(result.maxCommonSteps() + " " + result.maxVelocity());
        }
        // 边界6：velFather = 10_000
        {
            int fatherPos = 10_000;
            int martinPos = 9_999;
            int velFather = 10_000;
            int steps = 2;
            Result result = getResult(fatherPos, martinPos, velFather, steps);
            //2 9999
            System.out.println(result.maxCommonSteps() + " " + result.maxVelocity());
        }
        // 边界7：steps = 1
        {
            int fatherPos = 100;
            int martinPos = 50;
            int velFather = 10;
            int steps = 1;
            Result result = getResult(fatherPos, martinPos, velFather, steps);
            //1 10
            System.out.println(result.maxCommonSteps() + " " + result.maxVelocity());
        }
        // 边界8：steps = 10_000
        {
            int fatherPos = 10_000;
            int martinPos = 5_000;
            int velFather = 2;
            int steps = 10_000;
            Result result = getResult(fatherPos, martinPos, velFather, steps);
            //10000 2
            System.out.println(result.maxCommonSteps() + " " + result.maxVelocity());
        }
        // 边界9：martinPos = fatherPos - 1
        {
            int fatherPos = 1_000;
            int martinPos = 999;
            int velFather = 100;
            int steps = 10;
            Result result = getResult(fatherPos, martinPos, velFather, steps);
            //10 37
            System.out.println(result.maxCommonSteps() + " " + result.maxVelocity());
        }
        // 边界10：martinPos = 0, fatherPos = 1
        {
            int fatherPos = 1;
            int martinPos = 0;
            int velFather = 1;
            int steps = 10;
            Result result = getResult(fatherPos, martinPos, velFather, steps);
            //10 1
            System.out.println(result.maxCommonSteps() + " " + result.maxVelocity());
        }
    }

    private static Result getResult(int fatherPos, int martinPos, int velFather, int steps) {
        int maxCommonSteps = 0;
        int maxVelocity = 0;

        for (int v2 = 1; v2 <= velFather * steps; v2++) {
            int commonSteps = 0;
            int currentFatherPos = fatherPos;
            int currentMartinPos = martinPos;
            for (int i = 0; i < steps; i++) {
                currentFatherPos += velFather;
                currentMartinPos += v2;
                if (currentMartinPos <= currentFatherPos) {
                    if (currentMartinPos % v2 == currentFatherPos % velFather) {
                        commonSteps++;
                    }
                } else {
                    break;
                }
            }
            if (commonSteps > maxCommonSteps) {
                maxCommonSteps = commonSteps;
                maxVelocity = v2;
            } else if (commonSteps == maxCommonSteps) {
                maxVelocity = Math.max(maxVelocity, v2);
            }
        }
        Result result = new Result(maxCommonSteps, maxVelocity);
        return result;
    }

    private record Result(int maxCommonSteps, int maxVelocity) {
    }
}