package iorichina.hellojava.hellosample.basic_calculator;

import iorichina.hellojava.hellosample.org.springframework.util.Assert;

import java.util.concurrent.atomic.AtomicInteger;

class Solution {
    public int calculate(String s) {
        if (s.length() == 1) {
            return Integer.parseInt(s);
        }
        AtomicInteger res = new AtomicInteger(0);
        calculateSubString(s, res, 0);
        return res.get();
    }

    int calculateSubString(String s, AtomicInteger res, int left) {
        boolean negative = false;
        for (int i = left; i < s.length(); i++) {
            char c = s.charAt(i);
            if (' ' == c) {
                if (left != i) {
                    int n = Integer.parseInt(s.substring(left, i));
                    if (negative) {
                        setResult(res, -n);
                        negative = false;
                    } else {
                        setResult(res, n);
                    }
                }
                left = i + 1; // update left to the next character after the space
                continue; // skip spaces
            }
            if ('(' == c) {
                AtomicInteger sub = new AtomicInteger(0);
                i = calculateSubString(s, sub, i + 1);
                if (negative) {
                    setResult(res, -sub.get());
                    negative = false;
                } else {
                    setResult(res, sub.get());
                }
                left = i + 1; // update left to the next character after the parenthesis
                continue;
            }
            if (')' == c) {
                if (left != i) {
                    int n = Integer.parseInt(s.substring(left, i));
                    if (negative) {
                        setResult(res, -n);
                    } else {
                        setResult(res, n);
                    }
                }
                return i; // return the index of the closing parenthesis
            }
            if (c - '0' >= 0 && c - '0' <= 9 && i < s.length() - 1) {
                continue;
            }
            if (s.length() - 1 == i && left != i + 1) {
                int n = Integer.parseInt(s.substring(left, i + 1));
                if (negative) {
                    setResult(res, -n);
                } else {
                    setResult(res, n);
                }
                return i;
            }
            if (left != i) {
                int n = Integer.parseInt(s.substring(left, i));
                left = i + 1; // update left to the next character after the number
                if (negative) {
                    setResult(res, -n);
                    negative = false;
                } else {
                    setResult(res, n);
                }
            }
            if ('+' == c) {
                left = i + 1; // update left to the next character after the operator
            }
            if ('-' == c) {
                negative = true;
                left = i + 1; // update left to the next character after the operator
            }
        }
        return left;
    }

    void setResult(AtomicInteger res, int n) {
        if (res.get() == 0) {
            res.set(n);
            return;
        }
        res.addAndGet(n);
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        /// "2147483647"=2147483647
        {
            String s = "2147483647";
            int result = solution.calculate(s);
            int expected = 2147483647;
            Assert.isTrue(expected == result, "Expected: " + expected + ", but got: " + result);
            System.out.println(s + "= " + result); // Expected output: 2147483647
        }
        /// "(1+(4+5+2)-3)+(6+8)"=23
        if (true) {
            String s = "(1+(4+5+2)-3)+(6+8)";
            int result = solution.calculate(s);
            int expected = 23;
            Assert.isTrue(expected == result, "Expected: " + expected + ", but got: " + result);
            System.out.println(s + "= " + result); // Expected output: 23
        }
        /// "1 + 1"=2
        {
            String s = "1 + 1";
            int result = solution.calculate(s);
            int expected = 2;
            Assert.isTrue(expected == result, "Expected: " + expected + ", but got: " + result);
            System.out.println(s + "= " + result); // Expected output: 2
        }
        /// " 2-1 + 2 "=3
        {
            String s = " 2-1 + 2 ";
            int result = solution.calculate(s);
            int expected = 3;
            Assert.isTrue(expected == result, "Expected: " + expected + ", but got: " + result);
            System.out.println(s + "= " + result); // Expected output: 3
        }
    }
}