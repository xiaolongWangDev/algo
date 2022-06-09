package practice.leetcode;

import java.util.Arrays;

public class L10RegularExpressionMatching {


    public static boolean isMatch(String s, String p) {
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        var v = f(0, 0, s, p, memo);
//        for (var l : memo) {
//            System.out.println(Arrays.toString(l));
//        }
        return v;
    }

    /**
     * whether the substring of S starting from i, matches the regex defined by substring of P starting from j
     */
    public static boolean f(int i, int j, String S, String P, Boolean[][] memo) {
        if (memo[i][j] != null) return memo[i][j];
        if (i == S.length() && j == P.length()) {
            memo[i][j] = true;
            return memo[i][j];
        }
        if (j == P.length()) {
            memo[i][j] = false;
            return memo[i][j];
        }

        boolean firstCharMatch = (i < S.length() && (S.charAt(i) == P.charAt(j) || P.charAt(j) == '.'));

        if (j + 1 < P.length() && P.charAt(j + 1) == '*') {
            memo[i][j] = f(i, j + 2, S, P, memo) || (firstCharMatch && f(i + 1, j, S, P, memo));
            return memo[i][j];
        }

        memo[i][j] = firstCharMatch && f(i + 1, j + 1, S, P, memo);
        return memo[i][j];
    }

    // time O(MN), space O(2M)
    public static boolean bottomUp(String S, String P) {
        boolean[] dp = new boolean[P.length() + 1];
        dp[P.length()] = true;

        for (int i = S.length(); i >= 0; i--) {
            boolean[] temp = new boolean[P.length() + 1];
            if(i == S.length()) temp[P.length()] = true;

            for (int j = P.length() - 1; j >= 0; j--) {
                boolean firstCharMatch = (i < S.length() && (S.charAt(i) == P.charAt(j) || P.charAt(j) == '.'));
                if (j + 1 < P.length() && P.charAt(j + 1) == '*') {
                    temp[j] = temp[j + 2] || (firstCharMatch && dp[j]);
                } else {
                    temp[j] = firstCharMatch && dp[j + 1];
                }
            }
            dp = temp;
        }

        return dp[0];
    }

    /**
     * TopDown
     * i/j  0    1     2     3     4     5     6     7      8     9     10
     * 0 [true, null, true, null, null, null, null,  null, null, null,  null]
     * 1 [null, null, null, true, null, true, null, false, null, null,  null]
     * 2 [null, null, null, null, null, true, null, false, null, null,  null]
     * 3 [null, null, null, null, null, true, null, false, null, null,  null]
     * 4 [null, null, null, null, null, true, null, false, null, null,  null]
     * 5 [null, null, null, null, null, true, null,  true, null, null,  null]
     * 6 [null, null, null, null, null, null, null,  null, true, null, false]
     * 7 [null, null, null, null, null, null, null,  null, true, null, false]
     * 8 [null, null, null, null, null, null, null,  null, true, null,  true]
     * <p>
     * <p>
     * BottomUp
     * i/j  0     1      2      3      4      5      6      7      8     9      10
     * 0 [ true, false,  true,  true, false,  true, false, false, true, false, false]
     * 1 [ true, false,  true,  true, false,  true, false, false, true, false, false]
     * 2 [ true, false,  true,  true, false,  true, false, false, true, false, false]
     * 3 [ true, false,  true,  true, false,  true, false, false, true, false, false]
     * 4 [ true, false,  true,  true, false,  true, false, false, true, false, false]
     * 5 [ true, false,  true,  true, false,  true, false,  true, true, false, false]
     * 6 [ true, false,  true,  true, false,  true, false,  true, true, false, false]
     * 7 [false, false, false,  true, false,  true, false,  true, true, false, false]
     * 8 [false, false, false, false, false, false, false, false, true, false,  true]
     */
    public static void main(String[] args) {
        System.out.println(isMatch("aabbaccc", "a*.b*.*c.*"));
        System.out.println(bottomUp("aabbaccc", "a*.b*.*c.*"));
    }
}
