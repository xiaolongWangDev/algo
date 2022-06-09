package practice.leetcode;

import java.util.HashMap;
import java.util.Map;

public class L13RomanToInteger {
    public static int romanToInt(String s) {
        Map<Character, Integer> m = new HashMap<>();
        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);
        int r = 0;
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            int currentNumber = m.get(currentChar);
            if (i + 1 < s.length()) {
                char nextChar = s.charAt(i + 1);
                if ((currentChar == 'I' && nextChar == 'V') ||
                        (currentChar == 'I' && nextChar == 'X') ||
                        (currentChar == 'X' && nextChar == 'L') ||
                        (currentChar == 'X' && nextChar == 'C') ||
                        (currentChar == 'C' && nextChar == 'D') ||
                        (currentChar == 'C' && nextChar == 'M')
                ) {
                    currentNumber = m.get(nextChar) - currentNumber;
                    i++;
                }
            }
            r += currentNumber;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }
}
