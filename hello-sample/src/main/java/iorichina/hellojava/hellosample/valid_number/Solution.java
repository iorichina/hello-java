package iorichina.hellojava.hellosample.valid_number;

import iorichina.hellojava.hellosample.org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    /// 例如，下面的都是有效数字："2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"，而接下来的不是："abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"。
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] testCases = {
                "2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3",
                "3e+7", "+6e-1", "53.5e93", "-123.456e789",
                "44e016912630333"
        };
        for (String testCase : testCases) {
            boolean result = solution.isNumber(testCase);
            System.out.println("Is \"" + testCase + "\" a valid number? " + result);
            Assert.isTrue(result, "Invalid number: " + testCase);
        }
        String[] badCases = {
                "abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"
        };
        for (String badCase : badCases) {
            boolean result = solution.isNumber(badCase);
            System.out.println("Is \"" + badCase + "\" a valid number? " + result);
            Assert.isTrue(!result, "Invalid number: " + badCase);
        }
    }

    public boolean isNumber2(String s) {
        // 直接使用 BigDecimal 来验证数字格式
        if (s == null || s.isEmpty()) {
            return false;
        }
        s = s.trim();
        if (s.isEmpty()) {
            return false;
        }
        boolean hasNumber = false; // 是否有数字 可以结尾
        boolean hasPoint = false; // 是否有小数点 可以结尾
        boolean hasExponent = false; // 是否有指数
        boolean hasExponentNumber = false; // 是否有指数符号
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isDigit(c) && c != '.' && c != 'e' && c != 'E' && c != '+' && c != '-') {
                return false; // 非法字符
            }
            if (c == '.') {
                if (hasPoint || hasExponent) {
                    return false; // 小数点只能出现一次且不能出现在指数部分
                }
                hasPoint = true;
                continue;
            }
            if (c == 'e' || c == 'E') {
                if (hasExponent || !hasNumber) {
                    return false;
                }
                hasExponent = true;
                hasNumber = false;
                hasExponentNumber = false;
                continue;
            }
            if (c == '+' || c == '-') {
                if (i != 0 && !(s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E')) {
                    return false; // 符号只能出现在开头或指数部分
                }
                continue;
            }
            hasNumber = true;
            if (hasExponent) {
                hasExponentNumber = true;
            }
        }
        // 最终必须有数字，并且如果有e/E，指数部分必须有数字
        return (hasNumber && !hasExponent)
                || (hasNumber && hasExponentNumber);
    }

    /// 给定一个字符串 s ，返回 s 是否是一个 有效数字。
    /// 一般的，一个 有效数字 可以用以下的规则之一定义：
    /// 一个 整数 后面跟着一个 可选指数。
    /// 一个 十进制数 后面跟着一个 可选指数。
    /// 一个 整数 定义为一个 可选符号 '-' 或 '+' 后面跟着 数字。
    /// 一个 十进制数 定义为一个 可选符号 '-' 或 '+' 后面跟着下述规则：
    /// 数字 后跟着一个 小数点 .。
    /// 数字 后跟着一个 小数点 . 再跟着 数位。
    /// 一个 小数点 . 后跟着 数位。
    /// 指数 定义为指数符号 'e' 或 'E'，后面跟着一个 整数。
    /// 数字 定义为一个或多个数位。
    ///
    /// @param s
    /// @return
    public boolean isNumber(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        s = s.trim();
        if (s.isEmpty()) {
            return false;
        }
        try {
            new BigDecimal(s);//最多支持10个非0指数位
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return stateMachine(s);
        }
        return true;
    }

    enum CharType {
        CHAR_SPACE,
        CHAR_SIGN,
        CHAR_NUMBER,
        CHAR_EXP,
        CHAR_POINT,
//        CHAR_ILLEGAL
    }

    Map<Character, CharType> charTypeMap = new HashMap<Character, CharType>() {{
        put(' ', CharType.CHAR_SPACE);
        put('+', CharType.CHAR_SIGN);
        put('-', CharType.CHAR_SIGN);
        put('0', CharType.CHAR_NUMBER);
        put('1', CharType.CHAR_NUMBER);
        put('2', CharType.CHAR_NUMBER);
        put('3', CharType.CHAR_NUMBER);
        put('4', CharType.CHAR_NUMBER);
        put('5', CharType.CHAR_NUMBER);
        put('6', CharType.CHAR_NUMBER);
        put('7', CharType.CHAR_NUMBER);
        put('8', CharType.CHAR_NUMBER);
        put('9', CharType.CHAR_NUMBER);
        put('.', CharType.CHAR_POINT);
        put('e', CharType.CHAR_EXP);
        put('E', CharType.CHAR_EXP);
    }};

    enum State {
        STATE_INITIAL,
        STATE_INT_SIGN,
        STATE_INTEGER,// 可结尾
        STATE_POINT,// "{+|-|0~9}." 可结尾
        STATE_POINT_WITHOUT_INT,// "{+|-}." 必须接数字
        STATE_FRACTION_INT,// 可结尾
        STATE_EXP,
        STATE_EXP_SIGN,
        STATE_EXP_NUMBER,// 可结尾
        STATE_TRAILING_SPACE,// 可结尾
//        STATE_END
    }

    Map<State, Map<CharType, State>> stateMap = new HashMap<State, Map<CharType, State>>() {{
        put(State.STATE_INITIAL, new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_INITIAL);
            put(CharType.CHAR_SIGN, State.STATE_INT_SIGN);
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
//            put(CharType.CHAR_EXP, null);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
        }});
        put(State.STATE_INT_SIGN, new HashMap<CharType, State>() {{
//            put(CharType.CHAR_SPACE, null);
//            put(CharType.CHAR_SIGN, null);
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
//            put(CharType.CHAR_EXP, null);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
        }});
        put(State.STATE_INTEGER, new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_TRAILING_SPACE);
//            put(CharType.CHAR_SIGN, null);
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_POINT, State.STATE_POINT);
        }});
        put(State.STATE_POINT, new HashMap<CharType, State>() {{
//            put(CharType.CHAR_SPACE, null);
//            put(CharType.CHAR_SIGN, null);
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION_INT);
//            put(CharType.CHAR_EXP, null);
            put(CharType.CHAR_POINT, null);
        }});
        put(State.STATE_POINT_WITHOUT_INT, new HashMap<CharType, State>() {{
//            put(CharType.CHAR_SPACE, null);
//            put(CharType.CHAR_SIGN, null);
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION_INT);
//            put(CharType.CHAR_EXP, null);
            put(CharType.CHAR_POINT, null);
        }});
        put(State.STATE_FRACTION_INT, new HashMap<CharType, State>() {{
//            put(CharType.CHAR_SPACE, null);
//            put(CharType.CHAR_SIGN, null);
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION_INT);
            put(CharType.CHAR_EXP, State.STATE_EXP);
//            put(CharType.CHAR_POINT, null);
        }});
        put(State.STATE_EXP, new HashMap<CharType, State>() {{
//            put(CharType.CHAR_SPACE, null);
            put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
//            put(CharType.CHAR_EXP, null);
//            put(CharType.CHAR_POINT, null);
        }});
        put(State.STATE_EXP_SIGN, new HashMap<CharType, State>() {{
//            put(CharType.CHAR_SPACE, null);
//            put(CharType.CHAR_SIGN, null);
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
//            put(CharType.CHAR_EXP, null);
//            put(CharType.CHAR_POINT, null);
        }});
        put(State.STATE_EXP_NUMBER, new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_TRAILING_SPACE);
//            put(CharType.CHAR_SIGN, null);
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
//            put(CharType.CHAR_EXP, null);
//            put(CharType.CHAR_POINT, null);
        }});
        put(State.STATE_TRAILING_SPACE, new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_TRAILING_SPACE);
//            put(CharType.CHAR_SIGN, null);
//            put(CharType.CHAR_NUMBER, null);
//            put(CharType.CHAR_EXP, null);
//            put(CharType.CHAR_POINT, null);
        }});
    }};

    public boolean stateMachine(String s) {
        State state = State.STATE_INITIAL;
        for (char c : s.toCharArray()) {
            CharType charType = charTypeMap.getOrDefault(c, null);
            if (charType == null) {
                return false; //非法字符
            }
            Map<CharType, State> nextStateMap = stateMap.get(state);
            if (nextStateMap == null || !nextStateMap.containsKey(charType)) {
                return false; //非法状态转换
            }
            state = nextStateMap.get(charType);
        }
        // 最终状态必须是一个有效的结束状态
        return state == State.STATE_INTEGER
                || state == State.STATE_POINT
                || state == State.STATE_FRACTION_INT
                || state == State.STATE_EXP_NUMBER
                || state == State.STATE_TRAILING_SPACE;
    }

}
