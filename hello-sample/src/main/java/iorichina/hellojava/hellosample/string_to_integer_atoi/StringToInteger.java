package iorichina.hellojava.hellosample.string_to_integer_atoi;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StringToInteger {
    public int myAtoi(String s) {
        StateMachine sm = new StateMachine();
        for (char c : s.toCharArray()) {
            sm.transition(c);
            if (sm.state.equals("end")) break;
        }
        return sm.sign * (int) sm.number;
    }

    static class StateMachine {
        String state = "start";
        long number = 0;
        int sign = 1;

        // 状态转移表: 当前状态 -> [空格, +/-, 数字, 其他]
        private final Map<String, String[]> table = new HashMap<String, String[]>() {{
            put("start", new String[]{"start", "signed", "in_number", "end"});
            put("signed", new String[]{"end", "end", "in_number", "end"});
            put("in_number", new String[]{"end", "end", "in_number", "end"});
        }};

        public void transition(char c) {
            String[] nextStates = table.get(state);
            int col = getColumn(c);
            state = nextStates[col];

            if (Objects.equals(state, "in_number")) {
                number = number * 10 + (c - '0');
                // 防止溢出
                if (sign == 1 && number > Integer.MAX_VALUE)
                    number = Integer.MAX_VALUE;
                else if (sign == -1 && number > -(long) Integer.MIN_VALUE)
                    number = -(long) Integer.MIN_VALUE;
            } else if (Objects.equals(state, "signed")) {
                sign = c == '-' ? -1 : 1;
            }
        }

        private int getColumn(char c) {
            if (c == ' ') return 0;
            if (c == '+' || c == '-') return 1;
            if (Character.isDigit(c)) return 2;
            return 3;
        }
    }

    // 测试
    public static void main(String[] args) {
        StringToInteger sol = new StringToInteger();
        System.out.println(sol.myAtoi("42"));             // 输出 42
        System.out.println(sol.myAtoi("   -42"));         // 输出 -42
        System.out.println(sol.myAtoi("4193 with words")); // 输出 4193
        System.out.println(sol.myAtoi("-91283472332"));   // 输出 -2147483648（溢出处理）
    }
}