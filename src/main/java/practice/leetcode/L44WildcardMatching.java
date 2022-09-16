package practice.leetcode;

public class L44WildcardMatching {

    /**
     * dcba
     * ?*a
     * <p>
     * does substring of S ending at j matches, the substring pattern of P ending i
     * *           ""    d   dc  dcb dbca
     * *     i\j    0    1    2    3    4
     * *     0      T    F    F    F    F
     * *   ? 1      F    T    F    F    F
     * *  ?* 2      F    T    T    T    T
     * * ?*a 3      F    F    F    F    T
     */
    public static boolean isMatch(String s, String p) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= p.length(); i++) {
            boolean[] temp = new boolean[s.length() + 1];
            for (int j = 0; j <= s.length(); j++) {
                if (p.charAt(i - 1) == '*') {
                    if (j == 0) {
                        temp[j] = dp[j];
                    } else {
                        temp[j] = dp[j - 1] || dp[j] || temp[j - 1];
                    }
                } else {
                    if (j == 0) {
                        temp[j] = false;
                    } else {
                        if (p.charAt(i - 1) == '?') {
                            temp[j] = dp[j - 1];
                        } else {
                            temp[j] = dp[j - 1] && p.charAt(i - 1) == s.charAt(j - 1);
                        }
                    }
                }
            }
//            System.out.println(Arrays.toString(temp));
            dp = temp;
        }

        return dp[s.length()];
    }

    public static void main(String[] args) {
        isMatch("dcba", "?*a");
    }
}

