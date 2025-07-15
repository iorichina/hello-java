package iorichina.hellojava.hellosample.simplify_path;

import iorichina.hellojava.hellosample.org.springframework.util.Assert;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class Solution {
    public String simplifyPath(String path) {
        if (path.length() <= 1) {
            return path;
        }
        //一个字符数组，记录有效的路径
        //从第二个字符开始，轮询字符，记录left，len
        //遇到/或结尾，判断left-cur之间，是否等于./..
        String[] res = new String[path.length()];

        int left = 1, len = 0;
        for (int i = 1; i < path.length(); i++) {
            String s;
            if (path.charAt(i) == '/') {
                if (left == i) {
                    left = i + 1;
                    continue;
                }
                s = path.substring(left, i);
            } else if (i == path.length() - 1) {
                s = path.substring(left, i + 1);
            } else {
                continue;
            }
            left = i + 1;
            if (Objects.equals(s, ".")) {
                continue;
            }
            if (Objects.equals(s, "..")) {
                len = len == 0 ? 0 : len - 1;
                continue;
            }
            res[len++] = s;
        }
        if (len == 0) {
            return "/";
        }
        return "/" + Stream.of(res).limit(len).collect(Collectors.joining("/"));
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        /// "/."="/"
        {
            String path = "/.";
            String expected = "/";
            String result = solution.simplifyPath(path);
            System.out.println("Simplified path: " + result); // Output: /
            Assert.isTrue(Objects.equals(result, expected), expected + " != " + path);
        }
        /// "/home/"="/home"
        {
            String path = "/home/";
            String expected = "/home";
            String result = solution.simplifyPath(path);
            System.out.println("Simplified path: " + result); // Output: /home
            Assert.isTrue(Objects.equals(result, expected), expected + " != " + path);
        }
        /// "/home//foo/"="/home//foo"
        {
            String path = "/home//foo/";
            String expected = "/home/foo";
            String result = solution.simplifyPath(path);
            System.out.println("Simplified path: " + result); // Output: /home/foo
            Assert.isTrue(Objects.equals(result, expected), expected + " != " + path);
        }
        /// "/home/user/Documents/../Pictures"="/home/user/Pictures"
        {
            String path = "/home/user/Documents/../Pictures";
            String expected = "/home/user/Pictures";
            String result = solution.simplifyPath(path);
            System.out.println("Simplified path: " + result); // Output: /home/user/Pictures
            Assert.isTrue(Objects.equals(result, expected), expected + " != " + path);
        }
        /// "/../"="/"
        {
            String path = "/../";
            String expected = "/";
            String result = solution.simplifyPath(path);
            System.out.println("Simplified path: " + result); // Output: /
            Assert.isTrue(Objects.equals(result, expected), expected + " != " + path);
        }
        /// "/.../a/../b/c/../d/./"="/.../b/d"
        {
            String path = "/.../a/../b/c/../d/./";
            String expected = "/.../b/d";
            String result = solution.simplifyPath(path);
            System.out.println("Simplified path: " + result); // Output: /.../b/d
            Assert.isTrue(Objects.equals(result, expected), expected + " != " + path);
        }
    }
}