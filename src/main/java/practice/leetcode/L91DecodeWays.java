package practice.leetcode;

public class L91DecodeWays {
    public static int numDecodings(String s) {
        Integer[] memo = new Integer[s.length()];
        return numDecodings(s.toCharArray(), 0, memo);
    }

    public static int numDecodings(char[] s, int cur, Integer[] memo) {
        if (cur >= s.length) {
            return 1;
        }

        if (memo[cur] != null) {
            return memo[cur];
        }

        if (s.length == cur + 1) {
            memo[cur] = s[cur] - '0' == 0 ? 0 : 1;
            return memo[cur];
        }

        int firstDigit = s[cur] - '0';
        int secondDigit = s[cur + 1] - '0';
        if (firstDigit == 0) {
            memo[cur] = 0;
        } else if (firstDigit > 2 || (firstDigit == 2 && secondDigit > 6)) {
            memo[cur] = numDecodings(s, cur + 1, memo);
        } else {
            memo[cur] = numDecodings(s, cur + 1, memo) + numDecodings(s, cur + 2, memo);
        }
        return memo[cur];
    }

    public static int numDecodings(char[] s) {
        int[] dp = new int[s.length];
        dp[s.length - 1] = s[s.length - 1] == '0' ? 0 : 1;
        for (int i = s.length - 2; i >= 0; i--) {
            int curDigit = s[i] - '0';
            int nextDigit = s[i + 1] - '0';

            if (curDigit == 0) {
                dp[i] = 0;
            } else if (curDigit > 2 || (curDigit == 2 && nextDigit > 6)) {
                dp[i] = dp[i + 1];
            } else {
                if (i + 2 < s.length) {
                    dp[i] = dp[i + 1] + dp[i + 2];
                } else {
                    dp[i] = dp[i + 1] + 1;
                }
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("17".toCharArray()));
    }

}
