package iorichina.hellojava.hellosample.course_schedule_ii;

import iorichina.hellojava.hellosample.org.springframework.util.Assert;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        /// numCourses =
        /// 3
        /// prerequisites =
        /// [[1,0],[2,0]]
        /// 输出
        /// [0,2,1] or [0,1,2]
        {
            int numCourses = 3;
            int[][] prerequisites = {{1, 0}, {2, 0}};
            int[] res = solution.findOrder(numCourses, prerequisites);
            System.out.print("numCourses = " + numCourses + ", prerequisites = ");
            for (int[] p : prerequisites) {
                System.out.print("[" + p[0] + "," + p[1] + "] ");
            }
            System.out.println("=> " + java.util.Arrays.toString(res));
            Assert.isTrue(numCourses == res.length && (
                    (res[0] == 0 && res[1] == 2 && res[2] == 1) ||
                            (res[0] == 0 && res[1] == 1 && res[2] == 2)
            ), "Test failed for numCourses = 3, prerequisites = [[1,0],[2,0]]");
        }
        /// numCourses =
        /// 10
        /// prerequisites =
        /// [[1,0],[0,2],[2,3],[3,4],[4,5],[5,6],[6,7],[7,8],[8,9],[9,1]]
        {
            int numCourses = 10;
            int[][] prerequisites = {{1, 0}, {0, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}, {9, 1}};
            int[] res = solution.findOrder(numCourses, prerequisites);
            System.out.print("numCourses = " + numCourses + ", prerequisites = ");
            for (int[] p : prerequisites) {
                System.out.print("[" + p[0] + "," + p[1] + "] ");
            }
            System.out.println("=> " + java.util.Arrays.toString(res));
            Assert.isTrue(0 == res.length, "Test failed for numCourses = 10, prerequisites = [[1,0],[0,2],[2,3],[3,4],[4,5],[5,6],[6,7],[7,8],[8,9],[9,1]]");
        }
        /// numCourses =
        /// 3
        /// prerequisites =
        /// [[1,0],[0,2],[2,1]]
        {
            int numCourses = 3;
            int[][] prerequisites = {{1, 0}, {0, 2}, {2, 1}};
            int[] res = solution.findOrder(numCourses, prerequisites);
            System.out.print("numCourses = " + numCourses + ", prerequisites = ");
            for (int[] p : prerequisites) {
                System.out.print("[" + p[0] + "," + p[1] + "] ");
            }
            System.out.println("=> " + java.util.Arrays.toString(res));
            Assert.isTrue(0 == res.length, "Test failed for numCourses = 3, prerequisites = [[1,0],[0,2],[2,1]]");
        }
        /// numCourses =
        /// 2
        /// prerequisites =
        /// [[0,1],[1,0]]
        /// 输出：[]
        {
            int numCourses = 2;
            int[][] prerequisites = {{0, 1}, {1, 0}};
            int[] res = solution.findOrder(numCourses, prerequisites);
            System.out.print("numCourses = " + numCourses + ", prerequisites = ");
            for (int[] p : prerequisites) {
                System.out.print("[" + p[0] + "," + p[1] + "] ");
            }
            System.out.println("=> " + java.util.Arrays.toString(res));
            Assert.isTrue(0 == res.length, "Test failed for numCourses = 2, prerequisites = [[0,1],[1,0]]");
        }
        /// numCourses = 2, prerequisites = [[0,1]]
        /// 输出： [1,0]
        {
            int numCourses = 2;
            int[][] prerequisites = {{0, 1}};
            int[] res = solution.findOrder(numCourses, prerequisites);
            System.out.print("numCourses = " + numCourses + ", prerequisites = ");
            for (int[] p : prerequisites) {
                System.out.print("[" + p[0] + "," + p[1] + "] ");
            }
            System.out.println("=> " + java.util.Arrays.toString(res));
            Assert.isTrue(numCourses == res.length && 1 == res[0] && 0 == res[1], "Test failed for numCourses = 2, prerequisites = [[0,1]]");
        }
        /// 输入：numCourses = 1, prerequisites = []
        /// 输出：[0]
        {
            int numCourses = 1;
            int[][] prerequisites = {};
            int[] res = solution.findOrder(numCourses, prerequisites);
            System.out.print("numCourses = " + numCourses + ", prerequisites = [] => ");
            System.out.println(java.util.Arrays.toString(res));
            Assert.isTrue(numCourses == res.length && 0 == res[0], "Test failed for numCourses = 1, prerequisites = []");
        }
        /// 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
        /// 输出：[0,2,1,3] 或者 [0,1,2,3]
        {
            int numCourses = 4;
            int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
            int[] res = solution.findOrder(numCourses, prerequisites);
            System.out.print("numCourses = " + numCourses + ", prerequisites = ");
            for (int[] p : prerequisites) {
                System.out.print("[" + p[0] + "," + p[1] + "] ");
            }
            System.out.println("=> " + java.util.Arrays.toString(res));
            Assert.isTrue(numCourses == res.length && (
                    (res[0] == 0 && res[1] == 2 && res[2] == 1 && res[3] == 3) ||
                            (res[0] == 0 && res[1] == 1 && res[2] == 2 && res[3] == 3)
            ), "Test failed for numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]");
        }
        /// 输入：numCourses = 2, prerequisites = [[1,0]]
        /// 输出：[0,1]
        {
            int numCourses = 2;
            int[][] prerequisites = {{1, 0}};
            int[] res = solution.findOrder(numCourses, prerequisites);
            System.out.print("numCourses = " + numCourses + ", prerequisites = ");
            for (int[] p : prerequisites) {
                System.out.print("[" + p[0] + "," + p[1] + "] ");
            }
            System.out.println("=> " + java.util.Arrays.toString(res));
            Assert.isTrue(numCourses == res.length && 0 == res[0] && 1 == res[1], "Test failed for numCourses = 2, prerequisites = [[1,0]]");
        }
    }
}

class Solution2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (1 == numCourses) {
            return new int[]{0};
        }
        if (0 == prerequisites.length) {
            int[] res = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                res[i] = i;
            }
            return res;
        }

        //course_i->pre_courses
        int[][] directPre = new int[numCourses][numCourses];
        for (int i = 0; i < numCourses; i++) {
            directPre[i] = new int[numCourses];
            Arrays.fill(directPre[i], -1);
        }
        //initial directPre
        for (int[] p : prerequisites) {
            directPre[p[0]][p[1]] = 1;//study p[1] before study p[0]
            //simple check for circular dependency
            if (directPre[p[1]][p[0]] > 0) {
                return new int[0];
            }
        }

        int[] courses = new int[numCourses];
        Arrays.fill(courses, -1);

        //every course study
        int idxRes = 0;
        int[] res = new int[numCourses];
        for (int course = 0; course < numCourses; course++) {
            //already study
            if (courses[course] > -1) {
                continue;
            }
            int[] studyStack = new int[numCourses];
            Arrays.fill(studyStack, -1);
            studyStack[course] = 1;
            //学习当前课程的依赖
            idxRes = studyPre(res, courses, idxRes, course, directPre, studyStack);
            if (idxRes == -1) {
                return new int[0];
            }
            courses[course] = idxRes;
            res[idxRes++] = course;
        }
        return res;
    }

    int studyPre(int[] res, int[] courses, int idxRes, int course, int[][] directPre, int[] studyStack) {
        int[] pre = directPre[course];
        for (int preCourse = 0; preCourse < pre.length; preCourse++) {
            //not a pre_course
            if (pre[preCourse] == -1) {
                continue;
            }
            //already study
            if (courses[preCourse] > -1) {
                continue;
            }
            //circular dependency
            if (studyStack[preCourse] > 0) {
                return -1;
            }
            //pre of pre_course
            studyStack[preCourse] = 1;
            //学习依赖课程的依赖
            idxRes = studyPre(res, courses, idxRes, preCourse, directPre, studyStack);
            if (idxRes == -1) {
                return -1;
            }
            studyStack[preCourse] = -1;
            //学习依赖课程
            courses[preCourse] = idxRes;
            res[idxRes++] = preCourse;
        }
        return idxRes;
    }
}

//7ms
class Solution1 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (1 == numCourses) {
            return new int[]{0};
        }
        if (0 == prerequisites.length) {
            int[] res = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                res[i] = i;
            }
            return res;
        }
        //课程i->直接依赖课程列表
        Map<Integer, Set<Integer>> directPre = new HashMap<>();
        //初始化依赖
        for (int[] p : prerequisites) {
            if (!directPre.containsKey(p[0])) {
                directPre.put(p[0], new HashSet<>());
            }
            directPre.get(p[0]).add(p[1]);//要学习p[0]，必须先学习p[1]
            //简单的循环依赖判断
            if (directPre.containsKey(p[1])) {
                if (directPre.get(p[1]).contains(p[0])) {
                    return new int[0];
                }
            }
        }

        int[] courses = new int[numCourses];
        Arrays.fill(courses, -1);

        //循环课程
        int idxRes = 0;
        int[] res = new int[numCourses];
        for (int course = 0; course < numCourses; course++) {
            //already study
            if (courses[course] > -1) {
                continue;
            }
            Set<Integer> pre = directPre.get(course);
            //课程没有依赖
            if (null == pre) {
                courses[course] = idxRes;
                res[idxRes++] = course;
                continue;
            }
            Set<Integer> studyStack = new HashSet<>();
            studyStack.add(course);
            //学习当前课程的依赖
            idxRes = studyPre(res, courses, idxRes, course, directPre, studyStack);
            if (idxRes == -1) {
                return new int[0];
            }
            courses[course] = idxRes;
            res[idxRes++] = course;
        }
        return res;
    }

    int studyPre(int[] res, int[] courses, int idxRes, int course, Map<Integer, Set<Integer>> directPre, Set<Integer> studyStack) {
        Set<Integer> pre = directPre.get(course);
        //没有依赖不学习
        if (null == pre || pre.isEmpty()) {
            return idxRes;
        }
        for (Integer preCourse : pre) {
            //已经学习过
            if (courses[preCourse] > -1) {
                continue;
            }
            //循环依赖了
            if (studyStack.contains(preCourse)) {
                return -1;
            }
            //依赖课程的依赖
            studyStack.add(preCourse);
            //学习依赖课程的依赖
            idxRes = studyPre(res, courses, idxRes, preCourse, directPre, studyStack);
            if (idxRes == -1) {
                return -1;
            }
            studyStack.remove(preCourse);
            //学习依赖课程
            courses[preCourse] = idxRes;
            res[idxRes++] = preCourse;
        }
        return idxRes;
    }
}

//27ms
class Solution0 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (1 == numCourses) {
            return new int[]{0};
        }
        if (0 == prerequisites.length) {
            int[] res = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                res[i] = i;
            }
            return res;
        }
        //课程i->直接依赖课程列表
        Map<Integer, Set<Integer>> directPre = new HashMap<>();
        //初始化依赖
        for (int[] p : prerequisites) {
            if (!directPre.containsKey(p[0])) {
                directPre.put(p[0], new HashSet<>());
            }
            if (!directPre.containsKey(p[1])) {
                directPre.put(p[1], new HashSet<>());
            }
            directPre.get(p[0]).add(p[1]);//要学习p[0]，必须先学习p[1]
            //简单的循环依赖判断
            if (directPre.get(p[1]).contains(p[0])) {
                return new int[0];
            }
        }

        //循环课程
        int idxRes = 0;
        int[] res = new int[numCourses];
        SortedSet<Integer> set = new TreeSet<>();
        for (int course = 0; course < numCourses; course++) {
            if (set.contains(course)) {
                continue;
            }
            Set<Integer> pre = directPre.get(course);
            //课程没有依赖
            if (null == pre) {
                res[idxRes++] = course;
                set.add(course);
                continue;
            }
            Deque<Integer> studyStack = new LinkedList<>();
            studyStack.offerLast(course);
            //学习当前课程的依赖
            idxRes = studyPre(res, set, idxRes, course, directPre, studyStack);
            if (idxRes == -1) {
                return new int[0];
            }
//            studyStack.pollLast();
            //学习当前课程
            res[idxRes++] = course;
            set.add(course);
        }
        return res;
    }

    int studyPre(int[] res, SortedSet<Integer> set, int idxRes, int course, Map<Integer, Set<Integer>> directPre, Deque<Integer> studyStack) {
        Set<Integer> pre = directPre.get(course);
        //没有依赖不学习
        if (null == pre || pre.isEmpty()) {
            return idxRes;
        }
        for (Integer preCourse : pre) {
            //已经学习过
            if (set.contains(preCourse)) {
                continue;
            }
            //循环依赖了
            if (studyStack.contains(preCourse)) {
                return -1;
            }
            //依赖课程的依赖
            studyStack.offerLast(preCourse);
            //学习依赖课程的依赖
            idxRes = studyPre(res, set, idxRes, preCourse, directPre, studyStack);
            if (idxRes == -1) {
                return -1;
            }
            studyStack.pollLast();
            //学习依赖课程
            res[idxRes++] = preCourse;
            set.add(preCourse);
        }
        return idxRes;
    }
}
