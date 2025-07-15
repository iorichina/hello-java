package iorichina.hellojava.hellosample.letter_combinations_of_a_phone_number;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty())
            return new ArrayList<>();
        return combines(digits);
    }

    private List<String> combines(String letters) {
        List<String> result = new ArrayList<>();
        combine(letters, 0, new StringBuilder(), result);
        return result;
    }

    private void combine(String letters, int index, StringBuilder current, List<String> result) {
        if (index == letters.length()) {
            result.add(current.toString());
            return;
        }
        char digit = letters.charAt(index);
        String possibleLetters = getLetters(digit);
        for (char letter : possibleLetters.toCharArray()) {
            current.append(letter);
            combine(letters, index + 1, current, result);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
    }

    private String getLetters(char digit) {
        return switch (digit) {
            case '2' -> "abc";
            case '3' -> "def";
            case '4' -> "ghi";
            case '5' -> "jkl";
            case '6' -> "mno";
            case '7' -> "pqrs";
            case '8' -> "tuv";
            case '9' -> "wxyz";
            default -> throw new IllegalArgumentException("Input must be digits from 2 to 9.");
        };
    }

}