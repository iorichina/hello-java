package iorichina.hellojava.hellosample.course_schedule_ii;

import java.util.*;

class Solution {
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
