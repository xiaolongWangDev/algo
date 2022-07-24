package practice.leetcode;

import java.util.Arrays;

public class L32LongestValidParentheses {

    public static int longestValidParentheses(String s) {
        // longest valid parentheses for the substring ending on i, char i need to be part of the valid result
        int[] res = new int[s.length()];
        Arrays.fill(res, 0);
        int longest = 0;
        // ( ( ) ) ( ) )
        // 0 0 2 4 0 6 0
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') { // only need to handle ) since no valid string ends on (
                int currentValidLen = 0;
                int leadingValidLen = res[i - 1];
                int potentialOpeningIndex = i - leadingValidLen - 1;

                if (potentialOpeningIndex >= 0 && s.charAt(potentialOpeningIndex) == '(') {
                    currentValidLen = leadingValidLen + 2;
                    if(potentialOpeningIndex - 1 >= 0) {
                        currentValidLen += res[potentialOpeningIndex - 1];
                    }
                    res[i] = currentValidLen;
                    longest = Math.max(longest, res[i]);
                }
            }
        }
//        System.out.println(s.toCharArray());
//        System.out.println(Arrays.toString(res));

        return longest;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("()()(())()))(())"));
    }

}
